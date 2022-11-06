package com.glearning.employee.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employee.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsService UserDetailsSerivce;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		//configure users
//		authenticationManagerBuilder
//		                           .inMemoryAuthentication()
//		                           .withUser("Ramesh")
//		                           .password(passwordEncoder().encode("root"))
//		                           .roles("USER","ADMIN")
//		                           .and()
//		                           .withUser("Mahesh")
//		                           .password(passwordEncoder().encode("root"))
//		                           .roles("USER");

		authenticationManagerBuilder.userDetailsService(this.UserDetailsSerivce).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/employees/**")
				// .hasAnyRole("USER","ADMIN")
				.permitAll().antMatchers("/h2-console/**", "/login**", "/contact-us**").permitAll().and()
				.authorizeRequests().antMatchers(HttpMethod.POST, "/api/employees/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				// .antMatchers("/api/e","/student/delete").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().formLogin().and().httpBasic().and()
				/*
				 * Set the sessioncreation policy to avoid using cookies for authentication
				 * https://stackoverflow.com/questions/50842258/spring-security-caching-my-
				 * authentication/50847571
				 */
				.sessionManagement().sessionCreationPolicy(STATELESS);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
