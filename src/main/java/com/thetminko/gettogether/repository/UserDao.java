package com.thetminko.gettogether.repository;

import com.thetminko.gettogether.repository.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by developer on 11/6/16.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
  User findByUserCredential_username(String username);

  User findByPermissions_permission(String permission);
}
