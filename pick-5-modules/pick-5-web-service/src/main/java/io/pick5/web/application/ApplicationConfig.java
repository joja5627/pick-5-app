package io.pick5.web.application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pick5.web.service.handlers.ErrorHandler;


@Configuration
//@EnableReactiveMongoRepositories(basePackages = "io.pick5.web.repository")
//class ApplicationConfig  extends AbstractReactiveMongoConfiguration {
class ApplicationConfig {
//	@Bean
//    public MongoClient mongoClient() throws UnknownHostException {
//        return MongoClients.create("mongodb://localhost");
//    }
//    public ReactiveMongoDatabaseFactory mongoDbFactory() throws UnknownHostException  {
//        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), 
//           "test");
//    }
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() throws UnknownHostException {
//        return new ReactiveMongoTemplate(mongoDbFactory());
//    }
//    @Override
//	public MongoClient reactiveMongoClient() {
//		return  MongoClients.create();
//	}
//
//
//	@Override
//	protected String getDatabaseName() {
//		return "pick-5-reactive-mongo";
//	}

//	@Bean
//	ApiHandler apiHandler(final GeoLocationService geoLocationService, final SunriseSunsetService sunriseSunsetService,
//			final ErrorHandler errorHandler) {
//		return new ApiHandler(geoLocationService, sunriseSunsetService, errorHandler);
//	}

//	
//    @Bean
//    GameLinesService gameLinesServiceImpl() {
//        return new GameLinesServiceImpl();
//    }

//    @Bean
//    SunriseSunsetService sunriseSunsetService(@Value("${SunriseSunsetServiceImpl.endPoint}") final String endPoint) {
//        return new SunriseSunsetServiceImpl(endPoint);
//    }

	@Bean
	ErrorHandler errorHandler() {
		return new ErrorHandler();
	}

//    @Bean
//    public MongoClient mongoClient() throws UnknownHostException {
//        return MongoClients.create("mongodb://localhost");
//    }

//    public ReactiveMongoDatabaseFactory mongoDbFactory() throws UnknownHostException  {
//        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), 
//           "test");
//    }
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() throws UnknownHostException  {
//        return new ReactiveMongoTemplate(mongoDbFactory());
//    }
}
