package com.iauto.eschool.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/index.html").permitAll()
			.antMatchers("*").hasRole("ADMIN")
			.antMatchers("/courses*","/courses/*").hasAnyRole("ADMIN","AUTHOR")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder()
			.username("dara")
			.password(passwordEncoder.encode("123"))
			//.roles("ADMIN") //ROLE_ADMIN
			.authorities(RoleEnum.ADMIN.getAuthorities())
			.build();
		
		
		UserDetails user2 = User.builder()
			.username("dollar")
			.password(passwordEncoder.encode("1234"))
			//.roles("AUTHOR")
			.authorities(RoleEnum.AUTHOR.getAuthorities())
			.build();
		
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1,user2);
		
		return userDetailsService;
	}
	
}
