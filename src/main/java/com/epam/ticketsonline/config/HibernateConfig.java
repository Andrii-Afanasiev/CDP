package com.epam.ticketsonline.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class HibernateConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Bean()
    public DataSource getDataSource() {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public HibernateTransactionManager transactionManager(
            final SessionFactory sessionFactory) {
        final HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }

    /*
     * @Bean public HibernateTemplate getHibernateTemplate( final SessionFactory
     * sessionFactory) { final HibernateTemplate hibernateTemplate = new
     * HibernateTemplate( sessionFactory); return hibernateTemplate; }
     */

    @Bean
    public AnnotationSessionFactoryBean getSessionFactory() {
        final AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
        asfb.setDataSource(getDataSource());
        asfb.setHibernateProperties(getHibernateProperties());
        asfb.setPackagesToScan(new String[] { "com.epam.ticketsonline.entity" });
        return asfb;
    }

    private Properties getHibernateProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

        return properties;
    }

}
