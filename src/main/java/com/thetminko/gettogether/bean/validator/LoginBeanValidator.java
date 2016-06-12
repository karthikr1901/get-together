package com.thetminko.gettogether.bean.validator;

import com.thetminko.gettogether.bean.LoginBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by developer on 12/6/16.
 */
@Component
public class LoginBeanValidator implements Validator {

  private String validUsername = "login.valid.username";

  private String validPassword = "login.valid.password";

  @Override
  public boolean supports(Class<?> aClass) {
    return LoginBean.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    LoginBean loginBean = (LoginBean) o;
    ValidationUtils
        .rejectIfEmpty(errors, "username", "loginBean.username.empty", validUsername);
    ValidationUtils.rejectIfEmpty(errors, "password", "loginBean.password.empty", validPassword);
  }
}
