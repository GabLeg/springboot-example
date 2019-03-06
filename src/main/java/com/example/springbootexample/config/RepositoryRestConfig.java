package com.example.springbootexample.config;

import com.example.springbootexample.domain.Goody;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.ProfileResourceProcessor;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RepositoryRestConfig extends RepositoryRestMvcConfiguration {

    public RepositoryRestConfig(ApplicationContext context, ObjectFactory<ConversionService> conversionService) {
        super(context, conversionService);
    }

    /**
     * Config that make the JSON return the ID with @RepositoryRestResource
     * @param config config
     */
    @Override
    public ProfileResourceProcessor profileResourceProcessor(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Goody.class);
        return super.profileResourceProcessor(config);
    }
}
