package io.pick5.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import io.pick5.module.asyncHttpClient.EnableAsyncHttpClientModule;



@EnableAutoConfiguration(exclude = {
    ReactiveUserDetailsServiceAutoConfiguration.class,
    	ReactiveSecurityAutoConfiguration.class,
    		UserDetailsServiceAutoConfiguration.class,
    			MongoAutoConfiguration.class,
    				MongoDataAutoConfiguration.class})

//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)



@EnableAsyncHttpClientModule
@ComponentScan(basePackages = {"io.pick5.auth"})
@SpringBootApplication
public class AuthenticationApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}
	/**
     * A custom UserDetailsService to provide quick user rights for Spring Security,
     * more formal implementations may be added as separated files and annotated as
     * a Spring stereotype.
     *
     * @return MapReactiveUserDetailsService an InMemory implementation of user details
     */
//    @Bean
//    public MapReactiveUserDetailsService userDetailsRepository() {
//        UserDetails user =
//        		User
//        			.withUsername("user")
//                		.password("user")
//                			.roles("USER", "ADMIN")
//                				.build();
//                
//        return new MapReactiveUserDetailsService(user);
//    }

    /**
     * For Spring Security webflux, a chain of filters will provide user authentication
     * and authorization, we add custom filters to enable JWT token approach.
     *
     * @param http An initial object to build common filter scenarios.
     *             Customized filters are added here.
     * @return SecurityWebFilterChain A filter chain for web exchanges that will
     * provide security
     **/
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//
//        http.authorizeExchange()
//                    .pathMatchers("/login", "/")
//                    .authenticated()
//                .and()
//                    .addFilterAt(basicAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
//                       .authorizeExchange()
//                    .pathMatchers("/api/**")
//                    .authenticated()
//                .and()
//                    .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
//
//        return http.build();
//    }

    /**
     * Use the already implemented logic in  AuthenticationWebFilter and set a custom
     * SuccessHandler that will return a JWT when a user is authenticated with user/password
     * Create an AuthenticationManager using the UserDetailsService defined above
     *
     * @return AuthenticationWebFilter
     */
//    private AuthenticationWebFilter basicAuthenticationFilter(){
//        UserDetailsRepositoryReactiveAuthenticationManager authManager;
//        AuthenticationWebFilter basicAuthenticationFilter;
//        ServerAuthenticationSuccessHandler successHandler;
//
//        authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository());
//        successHandler = new  BasicAuthenticationSuccessHandler();
//
//        basicAuthenticationFilter = new AuthenticationWebFilter(authManager);
//        basicAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
//
//        return basicAuthenticationFilter;
//
//    }


//    private AuthenticationWebFilter bearerAuthenticationFilter(){
//        AuthenticationWebFilter bearerAuthenticationFilter;
//        Function<ServerWebExchange, Mono<Authentication>> bearerConverter;
//        ReactiveAuthenticationManager authManager;
//
//        authManager  = new BearerTokenReactiveAuthenticationManager();
//        bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
//        bearerConverter = new ServerHttpBearerAuthenticationConverter();
//
//        bearerAuthenticationFilter.setAuthenticationConverter(bearerConverter);
//        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/api/**"));
//
//        return bearerAuthenticationFilter;
//    }
}
