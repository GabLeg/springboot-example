package com.example.integration.config;

import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.mappers.exceptions.ExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ExceptionMapper exceptionMapper() {
        return new ExceptionMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
