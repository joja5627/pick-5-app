///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package io.pick5.user.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//
///**
// * @author hantsy
// */
//@Configuration
//@EnableWebFluxSecurity
//class SecurityConfig {

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
//        return http
//                .authorizeExchange()
//                .pathMatchers(HttpMethod.GET, "/posts/**").permitAll()
//                .pathMatchers(HttpMethod.DELETE, "/posts/**").hasRole("ADMIN")
//                .pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
//                .anyExchange().authenticated()
//                .and()
//                .build();
//    }
//
//    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
//        return authentication
//                .map(a -> context.getVariables().get("user").equals(a.getName()))
//                .map(granted -> new AuthorizationDecision(granted));
//    }
//
//    @Bean
//    public ReactiveUserDetailsService userDetailsService(UserRepository users) {
//        return (username) -> users.findByUsername(username).cast(UserDetails.class);
//    }

//}
