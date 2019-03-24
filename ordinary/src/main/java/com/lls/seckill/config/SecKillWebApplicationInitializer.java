package com.lls.seckill.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/************************************
 * SecKillWebApplicationInitializer
 * @author liliangshan
 * @date 2019-03-24
 ************************************/
public class SecKillWebApplicationInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
//        rootContext.register(RootConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        // Register and map the dispatcher servlet
        DispatcherServlet servlet =  new DispatcherServlet(dispatcherContext);
        servlet.setContextConfigLocation("classpath:spring-*.xml");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", servlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }


}

