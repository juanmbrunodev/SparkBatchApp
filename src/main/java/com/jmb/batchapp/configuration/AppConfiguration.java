package com.jmb.batchapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    /**
     * //Register Bean here for certain environments by adding + @Profile at
     * at the class level or use @Component + @Profile; example:
     *
     @Bean
     public BeanType beanName() {
        return new BeanType();
     }
     */
}
