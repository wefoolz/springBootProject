package com.contactManager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class MyConfig {

	private final LoginSuccessHandler successHandler;
	
	public MyConfig(LoginSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}
	
    @Bean
    UserDetailsService getUserDetailService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasRole("USER")
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                )
                .formLogin(form->form.loginPage("/signin")
                		.loginProcessingUrl("/dologin")
                		.successHandler(successHandler)
                		//.defaultSuccessUrl("/user/index") //It lead every one who logs in to the given url. 
                		//altho they might not have authority to view the page but still they get redirected to this page
                		//.failureUrl("/login-fail") need statict resource named login-fail to user this method
                		)
                .logout(logout -> logout
                        .logoutUrl("/logout") 
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); 
        return http.build();
    }
}
