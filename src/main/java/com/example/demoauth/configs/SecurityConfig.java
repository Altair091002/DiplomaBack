package com.example.demoauth.configs;

import com.example.demoauth.jwt.AuthEntryPointJwt;
import com.example.demoauth.jwt.JWTFilter;
import com.example.demoauth.service.PersonDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final PersonDetailsServiceImpl personDetailService;
	private final JWTFilter jwtFilter;
	private final AuthEntryPointJwt unauthorizedHandler;

	private static final String[] SWAGGER_URLS = {
			// -- swagger ui
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/v2/api-docs",
			"/webjars/**",
			"/configuration/ui",
			"/configuration/security"

	};

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(SWAGGER_URLS);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
//				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers(SWAGGER_URLS).permitAll()
				.antMatchers("/api/auth/signin", "/api/auth/signup", "/api/main/all").permitAll();

		http.authorizeRequests().anyRequest().authenticated();
//				.anyRequest().authenticated();
//				.anyRequest().permitAll();
//		http.addFilter(new MyAuthenticationFilter(authenticationManagerBean()));

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(personDetailService).passwordEncoder(getPasswordEncoder());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
