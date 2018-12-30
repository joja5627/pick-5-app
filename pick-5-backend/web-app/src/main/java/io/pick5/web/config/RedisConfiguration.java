package io.pick5.web.config;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.pick5.web.domain.BovadaResponse;
import io.pick5.web.repository.BovadaResponseRepository;

/**
 * X-Team WebFlux Redis Configuration.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Configuration
@EnableRedisRepositories(basePackageClasses = BovadaResponseRepository.class)
public class RedisConfiguration {

	@Autowired
	RedisConnectionFactory factory;

	@Bean
	public ReactiveRedisTemplate<String, BovadaResponse> reactiveJsonBovadaResponseRedisTemplate(
			ReactiveRedisConnectionFactory connectionFactory) {

		RedisSerializationContext<String, BovadaResponse> serializationContext = RedisSerializationContext
				.<String, BovadaResponse>newSerializationContext(new StringRedisSerializer())
				.hashKey(new StringRedisSerializer()).hashValue(new Jackson2JsonRedisSerializer<>(BovadaResponse.class))
				.build();

		return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
			ReactiveRedisConnectionFactory connectionFactory) {
		return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
	}

	@PreDestroy
	public void flushTestDb() {
		factory.getConnection().flushDb();
	}

}
