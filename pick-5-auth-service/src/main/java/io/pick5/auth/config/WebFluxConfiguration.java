package io.pick5.auth.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
@EnableWebFlux
public class WebFluxConfiguration implements ApplicationContextAware, WebFluxConfigurer {

  private ApplicationContext context;


  @Override
  public void setApplicationContext(ApplicationContext context) {
    this.context = context;
  }

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


//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/api/**")
//        .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//        .allowedMethods("POST", "GET", "PUT", "DELETE")
//        .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//    registry.addMapping("/api/**/**")
//        .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//        .allowedMethods("POST", "GET", "PUT", "DELETE")
//        .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//    registry.addMapping("/api/**/**/**")
//        .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//        .allowedMethods("POST", "GET", "PUT", "DELETE")
//        .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//    registry.addMapping(CONSTANTS.AUTH_LOGIN_ENDPOINT)
//        .allowedOrigins(CONSTANTS.CORS_ALLOWED_ORIGINS)
//        .allowedMethods("POST")
//        .allowCredentials(true).maxAge(CONSTANTS.CORS_MAX_AGE);
//
//  }
}
