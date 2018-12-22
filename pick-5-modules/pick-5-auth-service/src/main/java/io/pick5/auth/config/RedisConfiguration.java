package io.pick5.auth.config;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializationContext;



@Configuration
@EnableRedisRepositories
@ComponentScan(basePackages = {"io.pick5.auth.repo"})
public class RedisConfiguration {

  @Autowired
  RedisConnectionFactory factory;

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory();
  }

  @Bean
  public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
    return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
  }

  @PreDestroy
  public void flushTestDb() {
    factory.getConnection().flushDb();
  }

}
