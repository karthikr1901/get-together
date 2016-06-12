package com.thetminko.gettogether.provider;

import com.thetminko.gettogether.common.Encryption;
import com.thetminko.gettogether.repository.UserDao;
import com.thetminko.gettogether.repository.model.User;
import com.thetminko.gettogether.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by developer on 11/6/16.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);


  @Autowired
  private UserService userService;

  @Autowired
  private UserDao userDao;

  @Value("${security.hash.iteration}")
  private int hashIterations;

  @Value("${security.hash.length}")
  private int hashLength;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (userService.checkIfUsernameIsEmpty(authentication)) {
      throw new InsufficientAuthenticationException("login.empty.username");
    }
    if (userService.checkIfCredentialsIsEmpty(authentication)) {
      throw new InsufficientAuthenticationException("login.empty.password");
    }
    LOG.trace("Authenticating user", authentication.getName());
    User user = userDao.findByUserCredential_username(authentication.getName());
    if (user == null) {
      throw new BadCredentialsException("login.bad.credentials");
    }
    String hashedPassword = Encryption.encodeByteToString(Encryption
        .generateHashedPassword(authentication.getCredentials().toString().toCharArray(),
            Encryption.decodeStringToBytes(user.getUserCredential().getSalt()), hashIterations,
            hashLength));
    if (!user.getUserCredential().getPassword().equals(hashedPassword)) {
      userService.incrementFailedAttempts(user);
      if (!user.getUserCredential().isAccountNonLocked()) {
        throw new LockedException("login.account.locked");
      }
      throw new BadCredentialsException("login.bad.credentials");
    }
    if (!user.getUserCredential().isEnabled()) {
      throw new DisabledException("login.account.disabled");
    }
    if (!user.getUserCredential().isAccountNonLocked()) {
      throw new LockedException("login.account.locked");
    }
    if (!user.getUserCredential().isCredentialsNonExpired()) {
      throw new CredentialsExpiredException("credentials.expired");
    }
    if (!user.getUserCredential().isAccountNonExpired()) {
      throw new AccountExpiredException("account.expired");
    }
    userService.resetFailedAttempts(user);
    return new UsernamePasswordAuthenticationToken(user, hashedPassword.toCharArray(),
        userService.getUserPermissions(user));
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}
