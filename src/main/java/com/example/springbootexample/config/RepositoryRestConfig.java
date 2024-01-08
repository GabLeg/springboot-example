package com.example.springbootexample.config;

import com.example.springbootexample.domain.object.Goody;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.springbootexample.infra.repository")
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
