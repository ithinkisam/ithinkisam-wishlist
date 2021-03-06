package com.ithinkisam.wishlist.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**", "/registrationConfirm", "/register", "/login", "/badUser").permitAll()
			.anyRequest().authenticated().and()
			.formLogin().loginPage("/login");
			
	}
	
}
