package com.websystique.springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.websystique.springmvc.jwt.JwtAuthFilter;
import com.websystique.springmvc.jwt.JwtAuthenticationEntryPoint;
import com.websystique.springmvc.jwt.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
    private JwtAuthenticationEntryPoint jwtAuthEndPoint;
	
	@Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

	/*@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("in28Minutes").password("dummy")
				.roles("USER", "ADMIN");
	}*/

	//@Override
	//protected void configure(HttpSecurity http) throws Exception {
	//	http.authorizeRequests().antMatchers("/login").permitAll()
	//			.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
	//			.formLogin();
	//}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.csrf().ignoringAntMatchers("/login");
		//http.csrf().ignoringAntMatchers("/HelloWorldSpringMvc/gettoken");

		String[] patterns = new String[] {
				"/login",
				"/students/**/*",
				"/gettoken",
				"/bower_components/**/*",
				"/app/**/*",
				"/index.html",
				"/home.html",
				"/signin.html"
		};
		http.authorizeRequests()
		.antMatchers(patterns)
		.permitAll()
		.antMatchers("/**/*")
		.hasAuthority("ROLE_USER")
		.and()
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthEndPoint);
	}
	
}