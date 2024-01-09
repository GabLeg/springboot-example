package com.example.springbootexample.config;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.springbootexample.infra.mongo")
public class MongoConfig {

  @Bean
  public MongoServer mongoServer(@Value("${spring.data.mongodb.host}") String host, @Value("${spring.data.mongodb.port}") int port) {
    MongoServer mongoServer = new MongoServer(new MemoryBackend());
    mongoServer.bind(host, port);
    return mongoServer;
  }

  @Bean
  public MongoDatabaseFactory mongoDatabaseFactory(MongoServer mongoServer) {
    String connectionString = mongoServer.getConnectionString();
    return new SimpleMongoClientDatabaseFactory(connectionString + "/test");
  }
}
