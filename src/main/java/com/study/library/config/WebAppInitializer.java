package com.study.library.config;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.apache.logging.log4j.web.Log4jWebSupport;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(MainConfig.class);

        servletContext.setInitParameter(Log4jWebSupport.LOG4J_CONFIG_LOCATION, "classpath:log4j.xml");
        servletContext.addListener(Log4jServletContextListener.class);

        servletContext.addListener(new ContextLoaderListener(root));

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setServletContext(servletContext);
        servletContext.addFilter(CharacterEncodingFilter.class.getName(), characterEncodingFilter)
                .addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic appServlet =
                servletContext.addServlet("appServlet", new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setMultipartConfig(new MultipartConfigElement(null, -1L, -1L, 0));
        appServlet.setAsyncSupported(true);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }

}