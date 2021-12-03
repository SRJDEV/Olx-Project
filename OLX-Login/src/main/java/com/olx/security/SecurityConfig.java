package com.olx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//to create in memory user lists
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
PasswordEncoder passwordEncoder;

@Autowired
UserDetailsService userDetailService;
 @Override
public void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
//	auth.inMemoryAuthentication()
//	.withUser("Tom")
//	.password(passwordEncoder.encode("Tom123"))
//	.roles("ADMIN")
//	.and()
//	.withUser("Jerry")
//	.password(passwordEncoder.encode("Jerry123"))
//	.roles("USER");
	 
	 auth.userDetailsService(userDetailService);
}
 
 
 // for authorization
 @Override
	protected void configure(HttpSecurity http) throws Exception {
	 http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user/authenticate").permitAll()
		.and().formLogin();
	
	}


 
 @Bean
 public AuthenticationManager getAuthManager() throws Exception {
	 
	return super.authenticationManager();
 }

}
