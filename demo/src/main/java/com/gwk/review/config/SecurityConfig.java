package com.gwk.review.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gwk.review.auth.PrincipalDetailsService;
import com.gwk.review.auth.filter.CorsConfig;
import com.gwk.review.auth.filter.JwtAuthenticationFilter;
import com.gwk.review.auth.filter.JwtBasicAuthenticationFilter;
import com.gwk.review.repository.UserRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CorsConfig corsConfig;
	
	@Autowired
	private UserRepository useRepository;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
			.httpBasic().disable()
			.apply(new MyCustomDsl()) // 커스텀 필터 등록
			.and()
			.authorizeHttpRequests()
			.antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**")
			.permitAll().anyRequest().authenticated();
//			.and()
//			.formLogin()	
//			.loginPage("/auth/loginForm")
//			.loginProcessingUrl("/auth/loginProc")
//			.successHandler(new AuthenticationSuccessHandler() {
//				@Override
//				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//							Authentication authentication) throws IOException, ServletException {
//						System.out.println("authentication : " + authentication.getName());
//					}
//				}).failureHandler(new AuthenticationFailureHandler() {
//					@Override
//					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//							org.springframework.security.core.AuthenticationException exception)
//							throws IOException, ServletException {
//						System.out.println("exception : " + exception.getMessage());
//					}
//				}).defaultSuccessUrl("/").failureUrl("/auth/loginForm").and().logout();
		return http.build();

	}
	
	public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>{

		@Override
		public void configure(HttpSecurity http) throws Exception {
		
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			
			http
				.addFilter(corsConfig.corsFilter())
				.addFilter(new JwtAuthenticationFilter(authenticationManager))
				.addFilter(new JwtBasicAuthenticationFilter(authenticationManager, useRepository));
		}
	}
}