package com.sistema.easyservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sistema.easyservice.security.CustomAuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private CustomAuthenticationProvider authenticationProvider;
		
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**").antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
	   		.anyRequest()
	   		.authenticated()
	   		.and()
	   			.formLogin()
	   			.loginPage("/login")
	   			.permitAll()
	   		.and()
	   		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	   		.and()
	   			.exceptionHandling()
	   			.accessDeniedPage("/403")
	   		.and()
	   			.sessionManagement()
	   			.invalidSessionUrl("/login").and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
}
