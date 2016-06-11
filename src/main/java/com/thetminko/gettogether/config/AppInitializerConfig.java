package com.thetminko.gettogether.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by developer on 11/6/16.
 */
public class AppInitializerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{SpringMvcConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[0];
  }

  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}
