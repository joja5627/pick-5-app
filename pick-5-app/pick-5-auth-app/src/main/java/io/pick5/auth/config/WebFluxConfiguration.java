package io.pick5.auth.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;

import io.pick5.handlers.ErrorHandler;
import reactor.core.publisher.Mono;

//http://www.kennybastani.com/2016/04/event-sourcing-microservices-spring-cloud.html
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxConfiguration {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;

	@Autowired
	private LogoutHandler logoutHandler;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private Mono<AuthorizationDecision> isAdmin(final Mono<Authentication> authentication, final AuthorizationContext authorizationContext) {

	    return authentication
	    			.map(Authentication::getName)
	                         .map(login -> login.toLowerCase().contains("admin"))
	                         	.map(AuthorizationDecision::new);
	  }
  @Bean
  public MapReactiveUserDetailsService userDetailsRepository() {
      UserDetails user =
    		  
      		User.withUsername("user")
              		.password("user")
              			.roles("USER", "ADMIN")
              				.build();
              
      return new MapReactiveUserDetailsService(user);
  }

	@Bean
	PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
    /**
     * Use the already implemented logic in  AuthenticationWebFilter and set a custom
     * SuccessHandler that will return a JWT when a user is authenticated with user/password
     * Create an AuthenticationManager using the UserDetailsService defined above
     *
     * @return AuthenticationWebFilter
     */
    private AuthenticationWebFilter basicAuthenticationFilter(){
    	
        
        
        AuthenticationWebFilter basicAuthenticationFilter;
        
        ServerAuthenticationSuccessHandler successHandler;
        UserDetailsRepositoryReactiveAuthenticationManager authManager;
        authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository());
        successHandler = new  BasicAuthenticationSuccessHandler();

        basicAuthenticationFilter = new AuthenticationWebFilter(authManager);
        basicAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);

        return basicAuthenticationFilter;

    }
	
  private AuthenticationWebFilter bearerAuthenticationFilter(){
	  
  AuthenticationWebFilter bearerAuthenticationFilter;
  
  Function<ServerWebExchange, Mono<Authentication>> bearerConverter;
  
  ReactiveAuthenticationManager authManager;

  authManager  = new BearerTokenReactiveAuthenticationManager();
  bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
  bearerConverter = new ServerHttpBearerAuthenticationConverter();

  bearerAuthenticationFilter.setAuthenticationConverter(bearerConverter);
  bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/api/**"));

  return bearerAuthenticationFilter;
}
//  .pathMatchers("/profiles/admin/**", "/api/**")
//  .access(this::isAdmin) 
//  
//.pathMatchers("/favicon.ico", "/css/**", "/webjars/**")
//  .permitAll()
  
//.anyExchange()
//  	.authenticated()
//  	.and()
//  		
// .httpBasic()
//  	.and()

	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {

		return http
				.authorizeExchange()
					.pathMatchers( "/","/register","/login")
						.permitAll()
							.and()
								.build();
//					.pathMatchers("/login", "/")
//						.authenticated()
					
//				.formLogin()
//				 	.loginPage("/login")
//				 		.authenticationFailureHandler((exchange, exception) -> Mono.error(exception))
//				 			.authenticationSuccessHandler(new WebFilterChainServerAuthenticationSuccessHandler());
//					.authenticationManager(authenticationManager)
//						.securityContextRepository(securityContextRepository)
//					    	.authenticationSuccessHandler(authenticationSuccessHandler)
//					        	.authenticationFailureHandler(authenticationFailureHandler)
//					          		.and()
//				.logout()
//					.logoutHandler(logoutHandler)
//						.logoutUrl("/logout")
//					    	.logoutSuccessHandler(logoutSuccessHandler)
//					        	.and()				
//							
//				.addFilterAt(basicAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
//                	.authorizeExchange()
//                		.pathMatchers("/api/**")
//                			.authenticated()
//                				.and()
//                				
//                .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
//                
//		   
//		          			
		        
		          

		
	}
	
	
	@Bean
	ErrorHandler errorHandler() {
	    return new ErrorHandler();
	}
//	  @Override
//		public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
//		}
}

//@Override
//public void addCorsMappings(CorsRegistry registry) {
//  registry.addMapping("/api/**")
//      .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//      .allowedMethods("POST", "GET", "PUT", "DELETE")
//      .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//  registry.addMapping("/api/**/**")
//      .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//      .allowedMethods("POST", "GET", "PUT", "DELETE")
//      .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//  registry.addMapping("/api/**/**/**")
//      .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//      .allowedMethods("POST", "GET", "PUT", "DELETE")
//      .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//  registry.addMapping(CONSTANTS.AUTH_LOGIN_ENDPOINT)
//      .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//      .allowedMethods("POST")
//      .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//}



//@Configuration
//@EnableWebFlux
//public class WebFluxConfiguration implements ApplicationContextAware, WebFluxConfigurer {
//
//  private ApplicationContext context;
//
//
//  @Override
//  public void setApplicationContext(ApplicationContext context) {
//    this.context = context;
//  }

  

//  @Bean
//  public FreeMarkerConfigurer freeMarkerConfigurer() {
//    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//    configurer.setPreferFileSystemAccess(false);
//    configurer.setTemplateLoaderPaths("classpath:/templates/");
//    configurer.setResourceLoader(this.context);
//    return configurer;
//  }

//  @Override
//  public void configureViewResolvers(ViewResolverRegistry registry) {
//    registry.freeMarker();
//  }

//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/public/**")
//        .addResourceLocations("classpath:/styles/", "classpath:/img/")
//        .resourceChain(true)
//        .addResolver(new PathResourceResolver());
//  }


 

