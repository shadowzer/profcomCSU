package com.profCom;

import com.profCom.config.WebConfig;
import com.profCom.repository.RemindRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.text.Annotation;

/**
 * Created by VolgiNN on 06.12.2016.
 */
public class ApplicationInitializer implements WebApplicationInitializer{

    private final static String DISPATCHER="dispatcher";

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();

        ctx.register(WebConfig.class);

        servletContext.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER,new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
