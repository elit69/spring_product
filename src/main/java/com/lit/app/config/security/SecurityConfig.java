package com.lit.app.config.security;

//import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lit.app.services.impl.UserServiceImpl;

@Configuration
/*
* indicates that the class can be used by the Spring IoC
* container as a source of bean definitions.
*/
@EnableWebSecurity
/*
* The @EnableWebSecurity annotation and WebSecurityConfigurerAdapter work
* together to provide web based security. 
*/
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserServiceImpl userDetailsService;	
	
	@Autowired
	@Qualifier(value="ajaxAuthenticationSuccessHandler")
	private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
	
	@Autowired
	@Qualifier(value="ajaxAuthenticationFailureHandler")
	private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers("/author/**").hasAnyRole("AUTHOR" , "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN");
		http
			.formLogin()
			.permitAll()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(ajaxAuthenticationSuccessHandler)
			.failureHandler(ajaxAuthenticationFailureHandler);
		http
			.sessionManagement()
			.sessionAuthenticationErrorUrl("/login")
			.maximumSessions(1)
			.expiredUrl("/login")
			.sessionRegistry(sessionRegistryImpl());
		http
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JESSIONID")
			.permitAll();
		http.csrf().disable();
		http.exceptionHandling().accessDeniedPage("/accessDenied");
	}

	@Bean
	protected SessionRegistry sessionRegistryImpl(){
		return new SessionRegistryImpl();
	}	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("1"));
		//System.out.println(Base64.getUrlEncoder().encodeToString("1".getBytes()));
	}
}
