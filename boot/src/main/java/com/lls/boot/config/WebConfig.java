package com.lls.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/************************************
 * WebConfig
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    super.addResourceHandlers(registry);
    String[] resources = new String[]{"/static/**"};
    registry.addResourceHandler(resources);
  }

//  @Bean
//  public InternalResourceViewResolver viewResolver() {
//    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//    viewResolver.setPrefix("/WEB-INF/");
//    viewResolver.setSuffix(".jsp");
//    return viewResolver;
//  }

}
