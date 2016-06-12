package com.thetminko.gettogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.thetminko.gettogether.common.Constants.HOME_PAGE;
import static com.thetminko.gettogether.common.Constants.HOME_URL;
import static com.thetminko.gettogether.common.Constants.ROOT_URL;

/**
 * Created by developer on 12/6/16.
 */
@Controller
@RequestMapping(value = {ROOT_URL,HOME_URL})
public class HomeController {
  @RequestMapping(method = RequestMethod.GET)
  public String homePage(Model model){
    return HOME_PAGE;
  }
}
