package com.thetminko.gettogether.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by developer on 11/6/16.
 */
@Configuration
@ComponentScan(basePackages = "com.cab")
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public ViewResolver getViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/theme/**").addResourceLocations("/theme/");
    registry.addResourceHandler("/media/**").addResourceLocations("/media/");
    registry.addResourceHandler("/favicons/**").addResourceLocations("/favicons/");
    registry.addResourceHandler("/plug-in/**").addResourceLocations("/plug-in/");
  }
}