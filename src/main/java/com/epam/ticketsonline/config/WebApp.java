package com.epam.ticketsonline.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApp implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext servletContext)
	    throws ServletException {
	final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
	root.setServletContext(servletContext);
	root.scan("com.epam.ticketsonline.config");
	root.refresh();

	final Dynamic servlet = servletContext.addServlet("spring",
		new DispatcherServlet(root));
	servlet.addMapping("/action/*");
    }
}