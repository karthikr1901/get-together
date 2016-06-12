package com.thetminko.gettogether.repository;

import com.thetminko.gettogether.repository.model.UserCredential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by developer on 12/6/16.
 */
@Repository
public interface UserCredentialDao extends JpaRepository<UserCredential, Long>{
}
