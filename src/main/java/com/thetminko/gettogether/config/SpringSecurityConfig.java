package com.thetminko.gettogether.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by developer on 11/6/16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${security.enable.csrf}")
  private boolean enableCsrf = true;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/js/**", "/theme/**", "/", "/login", "/logout")
        .permitAll()
        .antMatchers("/**").hasAnyAuthority("GET-TOGETHER-USER")
        .anyRequest().authenticated();

    http
        .formLogin().loginPage("/login").defaultSuccessUrl("/home")
        .failureUrl("/login?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .permitAll().and()
        .exceptionHandling().accessDeniedPage("/403").and()
        .logout().clearAuthentication(true).deleteCookies("JSESSIONID").invalidateHttpSession(true)
        .logoutUrl("/logout").logoutSuccessUrl("/login?logout");

    if (enableCsrf) {
      http.csrf();
    } else {
      http.csrf().disable();
    }

    http
        .sessionManagement().invalidSessionUrl("/login?invalidSession")
        .sessionAuthenticationErrorUrl("/login?error").and();

    http.addFilterBefore(characterEncodingFilter(), CsrfFilter.class);
  }

  private static CharacterEncodingFilter characterEncodingFilter() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);

    return characterEncodingFilter;
  }
}
