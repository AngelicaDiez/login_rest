package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
//	@Autowired
//	private MyUserDetailsService myUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, true FROM users WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT u.username, r.authority FROM roles r, users u "
										+ "WHERE u.username = ? AND u.id = r.id_username");
		
//		auth.jdbcAuthentication().dataSource(dataSource).and().userDetailsService(myUserDetailsService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/user").hasAnyRole("ADMIN", "USER");
//			.anyRequest().authenticated();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}

}