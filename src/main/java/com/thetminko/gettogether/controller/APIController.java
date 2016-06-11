package com.thetminko.gettogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.thetminko.gettogether.common.Constants.API_PAGE;
import static com.thetminko.gettogether.common.Constants.API_URL;

/**
 * Created by developer on 11/6/16.
 */
@Controller
@RequestMapping(value = API_URL)
public class APIController {

  @RequestMapping(method = RequestMethod.GET)
  public String apiPage(Model model){
    return API_PAGE;
  }

}
