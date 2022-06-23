
package com.airline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());

		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()

				.authorizeRequests().antMatchers("/user/**", "/captain/**", "/role/**", "/timetracking/**")
				.hasAuthority("ADMIN").antMatchers("/timetracking/**").hasAnyAuthority("USER", "ADMIN","Captain").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home", true)
				.successHandler(customizedAutheSuccesHanlder()).and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/accessdenied");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	public AuthenticationSuccessHandler customizedAutheSuccesHanlder() {
		CustomizedAuthenticationSuccessHandler successHanlder = new CustomizedAuthenticationSuccessHandler();
		successHanlder.setDefaultTargetUrl("/home");
		successHanlder.setAlwaysUseDefaultTargetUrl(false);
		return successHanlder;
	}
}
