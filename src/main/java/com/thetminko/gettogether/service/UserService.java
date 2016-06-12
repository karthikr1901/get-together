package com.thetminko.gettogether.service;

import com.thetminko.gettogether.repository.UserCredentialDao;
import com.thetminko.gettogether.repository.UserDao;
import com.thetminko.gettogether.repository.model.Permission;
import com.thetminko.gettogether.repository.model.User;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.thetminko.gettogether.common.Constants.SYSTEM_ADMIN;

/**
 * Created by developer on 12/6/16.
 */
@Service
@Transactional
public class UserService {
  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  @Value("${max.failed.attempts}")
  private int maxFailedAttempts;

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserCredentialDao userCredentialDao;

  public boolean checkIfUsernameIsEmpty(Authentication authentication) {
    return StringUtils.isBlank(authentication.getName());
  }

  public boolean checkIfCredentialsIsEmpty(Authentication authentication) {
    return StringUtils.isBlank(authentication.getCredentials().toString());
  }

  public Collection<? extends GrantedAuthority> getUserPermissions(User user) {
    List<String> permissions = new ArrayList<>();
    for (Permission permission :
        user.getPermissions()) {
      permissions.add(permission.getPermission());
    }
    return AuthorityUtils.createAuthorityList(
        permissions.toArray(new String[permissions.size()]));
  }

  public void incrementFailedAttempts(User user) {
    if (user.getUserCredential().getFailedAttempts() >= maxFailedAttempts) {
      user.getUserCredential().setAccountNonLocked(false);
      userCredentialDao.saveAndFlush(user.getUserCredential());
    } else {
      user.getUserCredential().setFailedAttempts(user.getUserCredential().getFailedAttempts() + 1);
      user.setUserCredential(user.getUserCredential());
      userCredentialDao.saveAndFlush(user.getUserCredential());
    }
  }

  public void resetFailedAttempts(User user) {
    user.getUserCredential().setFailedAttempts(0);
    user.getUserCredential().setAccountNonLocked(true);
    userCredentialDao.saveAndFlush(user.getUserCredential());
  }

  public User getSystemAdminInfo() {
    return userDao.findByPermissions_permission(SYSTEM_ADMIN);
  }
}
