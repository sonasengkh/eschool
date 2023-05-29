package com.iauto.eschool.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.iauto.eschool.config.jwt.JwtLoginFilter;
import com.iauto.eschool.config.jwt.TokenVerifyFilter;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.addFilter(new JwtLoginFilter(authenticationManager()))
			.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers("/index.html").permitAll()
			//.antMatchers("*").hasRole("ADMIN")
			//.antMatchers("/courses*","/courses/*").hasAnyRole("ADMIN","AUTHOR")
			.anyRequest()
			.authenticated()
			//.and()
			//.httpBasic()
			;
	}
	
	/*
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder()
			.username("dara")
			.password(passwordEncoder.encode("123"))
			//.roles("ADMIN") //ROLE_ADMIN
			.authorities(RoleEnum.ADMIN.getAuthorities())
			.build();
			System.out.println(user1.getPassword());
		
		UserDetails user2 = User.builder()
			.username("dollar")
			.password(passwordEncoder.encode("1234"))
			//.roles("AUTHOR")
			.authorities(RoleEnum.AUTHOR.getAuthorities())
			.build();
			System.out.println(user2.getPassword());
		
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1,user2);
		
		return userDetailsService;
	}
	
*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	
}
