package com.thetminko.gettogether.bean;

import java.io.Serializable;

/**
 * Created by developer on 12/6/16.
 */
public class LoginBean implements Serializable{
  private static final long serialVersionUID = 2266035349698575444L;
  private String username;
  private String password;
  private boolean rememberMe;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isRememberMe() {
    return rememberMe;
  }

  public void setRememberMe(boolean rememberMe) {
    this.rememberMe = rememberMe;
  }
}
