//package io.pick5.user;
//
//import java.net.UnknownHostException;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//
//
//@Configuration
//@EnableReactiveMongoRepositories
////(basePackages = "io.pick5.user.repo")
//public class UserApplicationConfig {
//    @Bean
//    public MongoClient mongoClient() throws UnknownHostException {
//        return MongoClients.create();
//    }
//    
//    public ReactiveMongoDatabaseFactory mongoDbFactory() throws UnknownHostException {
//        return new SimpleReactiveMongoDatabaseFactory(mongoClient(),"test");
//    }
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() throws UnknownHostException  {
//        return new ReactiveMongoTemplate(mongoDbFactory());
//    }
//   
//}
