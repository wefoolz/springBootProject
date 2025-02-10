package com.onlineExamSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SystemConfiguration {

	private final LoginSuccessHandler successHandler;
	
	public SystemConfiguration(LoginSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}
	
	@Bean
	UserDetailsService getuserDetailsService() {
		return new UserDetailServiceImpl();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getuserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(auth->auth
				.requestMatchers(new AntPathRequestMatcher("/teacher/**")).hasRole("TEACHER")
				.requestMatchers(new AntPathRequestMatcher("/student/**")).hasRole("STUDENT")
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
				)
		.formLogin(form->form.loginPage("/online_exam_system/home")
				.loginProcessingUrl("/dologin")
				.successHandler(successHandler))
		.logout(logout->logout.logoutUrl("/logout").permitAll())
		.exceptionHandling(exception -> exception
	            .authenticationEntryPoint((request, response, authException) -> {
	                // Redirect unauthenticated users to login page
	                response.sendRedirect("/online_exam_system/home");
	            })
	        )
		.csrf(csrf->csrf.disable());
		
		 http.addFilterBefore(new RedirectAuthenticatedUserFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	
}
