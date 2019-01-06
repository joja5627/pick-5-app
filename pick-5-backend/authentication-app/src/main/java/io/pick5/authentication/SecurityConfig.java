package io.pick5.authentication;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;
//    @Bean
//    public JwtAuthenticationConfig jwtConfig() {
//        return new JwtAuthenticationConfig();
//    }
    @Bean
  	public JwtConfig jwtConfig() {
    	   return new JwtConfig();
  	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth
        	.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN", "USER").and()
                .withUser("shuaicj").password("{noop}shuaicj").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .anonymous()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
     		   .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
//                    .addFilterAfter(new JwtUsernamePasswordAuthenticationFilter(jwtConfig, authenticationManager()),
//                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers( jwtConfig.getUri()).permitAll()
                    .anyRequest().authenticated();
    }
}

