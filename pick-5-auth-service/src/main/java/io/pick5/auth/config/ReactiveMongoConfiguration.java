package io.pick5.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import io.pick5.auth.handler.EmailMongoReactiveRepository;
import io.pick5.auth.handler.UserMongoReactiveRepository;


@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = {UserMongoReactiveRepository.class,
    EmailMongoReactiveRepository.class})
@ComponentScan(basePackages = "io.pick5.auth.repo")
class ReactiveMongoConfiguration extends AbstractReactiveMongoConfiguration {

  @Value("${spring.data.mongodb.host}")
  private String host;
  @Value("${spring.data.mongodb.port}")
  private Integer port;

  @Override
  protected String getDatabaseName() {
    return "mongodb";
  }

  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create();
  }

  @Override
@Bean
  public ReactiveMongoTemplate reactiveMongoTemplate() {
    return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
  }

}
