package com.thetminko.gettogether.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by developer on 11/6/16.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return authenticate(authentication);
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return false;
  }
}
