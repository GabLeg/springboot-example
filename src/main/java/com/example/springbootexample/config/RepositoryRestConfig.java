package com.example.springbootexample.config;

import com.example.springbootexample.domain.Goody;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.ProfileResourceProcessor;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

    /**
     * Config that make the JSON return the ID with @RepositoryRestResource
     *
     * @param config config
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Goody.class);
    }
}
