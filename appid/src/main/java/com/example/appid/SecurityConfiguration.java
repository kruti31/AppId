package com.example.appid;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login**", "/user", "/userInfo").authenticated().and().oauth2Login();
//        http.oauth2Login().csrf().disabled();
//	    http.cors().and().authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer().jwt();

	}
}
