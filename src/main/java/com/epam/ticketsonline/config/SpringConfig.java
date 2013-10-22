package com.epam.ticketsonline.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.epam.ticketsonline.controller.views.PdfBookedTicketsView;
import com.epam.ticketsonline.controller.views.PdfTicketsView;

@ComponentScan({ "com.epam.ticketsonline.dao",
        "com.epam.ticketsonline.service", "com.epam.ticketsonline.controller" })
@Configuration
@EnableWebMvc
public class SpringConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true)
                        .defaultContentType(MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(
          final ContentNegotiationManager manager) {
      final List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

      final InternalResourceViewResolver resolver1 = new InternalResourceViewResolver();
      resolver1.setViewClass(JstlView.class);
      resolver1.setPrefix("/WEB-INF/jsp/");
      resolver1.setSuffix(".jsp");
      //resolver1.setViewNames(new String[]{"html*"});
      resolver1.setOrder(0);
      resolvers.add(resolver1);

      final BeanNameViewResolver resolver2 = new BeanNameViewResolver();
      resolver2.setOrder(1);
      resolvers.add(resolver2);

      final ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
      resolver.setViewResolvers(resolvers);
      resolver.setContentNegotiationManager(manager);
      return resolver;
    }

    /*@Bean
    InternalResourceViewResolver getInternalResourceViewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames(new String[]{"html*"});
        resolver.setOrder(0);
        return resolver;
    }

    @Bean
    BeanNameViewResolver getBeanNameViewResolver() {
        final BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(1);
        return resolver;
    }*/

    @Bean(name = "tickets")
    public AbstractPdfView getPdfTicketsView() {
        return new PdfTicketsView();
    }

    @Bean(name = "bookedTickets")
    public AbstractPdfView getPdfBookedTicketsView() {
        return new PdfBookedTicketsView();
    }

}