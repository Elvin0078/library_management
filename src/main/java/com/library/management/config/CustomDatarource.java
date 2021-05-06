package com.library.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomDatarource {


    @Value("${datasource.url}")
    public String url;

    @Value("${datasource.driver-class-name}")
    public String driverClassName;

    @Value("${datasource.username}")
    public String username;

    @Value("${datasource.password}")
    public String password;

    @Bean
    public DataSource getConnection() {
        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driverClassName)
                .build();
    }



}
