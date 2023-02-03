package com.gwk.review.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gwk.review.auth.PrincipalDetailsService;
@Configuration
//@EnableWebSecurity
public class SecurityConfig {



	@Autowired
	private PrincipalDetailsService prinsiDetailsService;
	
	
	
	


	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		System.out.println("동작하는가");
//		auth.userDetailsService(prinsiDetailsService).passwordEncoder(encodePwd());
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		http
			.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**")
			.permitAll().anyRequest().authenticated()
				.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
//			.successHandler(new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                    System.out.println("authentication : " + authentication.getName());
//                }
//            })
//            .failureHandler(new AuthenticationFailureHandler() {
//				@Override
//				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//						org.springframework.security.core.AuthenticationException exception)
//						throws IOException, ServletException {
//				      System.out.println("exception : " + exception.getMessage());
//				}
//            })
			.defaultSuccessUrl("/")
//			.failureUrl("/auth/loginForm")
			.and()
			.logout(); 
		return http.build();
		
	}

}