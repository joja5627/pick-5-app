package io.pick5.auth;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.PathResourceResolver;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements  WebFluxConfigurer {
	
	public static final String CORS_ALLOWED_ORIGINS = "*";
	public static final int CORS_MAX_AGE = 3600;

 

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

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/public/**")
        .addResourceLocations("classpath:/styles/", "classpath:/img/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
  }

  /**
   * CORS configuration
   */

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST", "GET", "PUT", "DELETE")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

    registry.addMapping("/api/**/**")
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST", "GET", "PUT", "DELETE")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

    registry.addMapping("/api/**/**/**")
        .allowedOrigins(CORS_ALLOWED_ORIGINS)
        .allowedMethods("POST", "GET", "PUT", "DELETE")
        .allowCredentials(true).maxAge(CORS_MAX_AGE);

//    registry.addMapping(AUTH_LOGIN_ENDPOINT)
//        .allowedOrigins(CORS_ALLOWED_ORIGINS)
//        .allowedMethods("POST")
//        .allowCredentials(true).maxAge(CORS_MAX_AGE);

  }
}