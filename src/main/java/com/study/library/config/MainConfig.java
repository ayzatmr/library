package com.study.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Ayzat on 03.02.2016.
 */
@Configuration
@ComponentScan({"com.study.library.config", "com.study.library.repository", "com.study.library.validation"})
@PropertySource("classpath:jdbc.properties")
public class MainConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

