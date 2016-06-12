package com.thetminko.gettogether.config;

import org.apache.tiles.extras.renderer.OptionsRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by developer on 11/6/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.thetminko.gettogether")
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

  private String tilesOptionCacheTtl = "5000";

//  @Bean
//  public ViewResolver getViewResolver() {
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setPrefix("/WEB-INF/views/");
//    resolver.setSuffix(".jsp");
//    return resolver;
//  }

  /**
   * Setup the tiles view resolver for the application
   *
   * @return tilesViewResolver
   */
  @Bean(name = "tilesViewResolver")
  public TilesViewResolver tilesViewResolver() {
    System.setProperty(OptionsRenderer.CACHE_LIFE_PROPERTY, tilesOptionCacheTtl);
    TilesViewResolver tilesViewResolver = new TilesViewResolver();
    tilesViewResolver.setViewClass(TilesView.class);
    tilesViewResolver.setContentType("text/html;charset=UTF-8");
    return tilesViewResolver;
  }

  /**
   * Setup the tilesConfigurer, using SLS own initialiser
   *
   * @return tilesConfigurer
   */
  @Bean
  @DependsOn("tilesViewResolver")
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer tilesConfigurer = new TilesConfigurer();
    tilesConfigurer.setCheckRefresh(true);
    tilesConfigurer.setTilesInitializer(new TilesInitializer());
    return tilesConfigurer;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/theme/**").addResourceLocations("/theme/");
    registry.addResourceHandler("/media/**").addResourceLocations("/media/");
    registry.addResourceHandler("/favicons/**").addResourceLocations("/favicons/");
    registry.addResourceHandler("/plug-in/**").addResourceLocations("/plug-in/");
    registry.addResourceHandler("/font-awesome/**").addResourceLocations("/font-awesome/");
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();

  }
  /**
   * Setup a reloadable resource bundle message source
   *
   * @return ReloadableResourceBundleMessageSource
   */
  @Bean(name = "messageSource")
  public ReloadableResourceBundleMessageSource getMessageSource() {
    ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
    resource.setBasenames("classpath:messages","classpath:ValidationMessages");
    resource.setDefaultEncoding("UTF-8");
    return resource;
  }

}