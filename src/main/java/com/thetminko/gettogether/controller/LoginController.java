package com.thetminko.gettogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.thetminko.gettogether.common.Constants.LOGIN_PAGE;
import static com.thetminko.gettogether.common.Constants.LOGIN_URL;

/**
 * Created by developer on 11/6/16.
 */
@Controller
@RequestMapping(value = LOGIN_URL)
public class LoginController {

  @RequestMapping(method = RequestMethod.GET)
  public String loginPage(Model model) {
    return LOGIN_PAGE;
  }
}
