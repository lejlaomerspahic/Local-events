package com.ptf.wp.projekat.dogadjaji_175.config;

import com.ptf.wp.projekat.dogadjaji_175.services.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Order(2)
public class SecurityConfig {

	private final UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	@Bean
	SecurityFilterChain securityFilterChian(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests()
				.requestMatchers("/static/css/**", "/js/**").permitAll()
			.requestMatchers("/registracija/**").permitAll()
				.requestMatchers("/dogadjaji/user/**","/kategorije/user/**","/lokacije/user/**").hasAnyAuthority("ROLE_USER")
				.requestMatchers("/dogadjaji/admin/**","/kategorije/admin/**","/lokacije/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin(form -> form
					 .loginPage("/prijava")
                     .loginProcessingUrl("/prijava")
                     .failureUrl("/prijava?greska")
                     .defaultSuccessUrl("/default", true)
                     .permitAll()
			)				
			.logout(
                    logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/odjava"))
                    .logoutSuccessUrl("/prijava?odjava")
                    .deleteCookies("JSESSIONID")
                    .permitAll()
			)
			.rememberMe()
			.key("remember-me-here")
			.tokenValiditySeconds(86400)
			.and()
			.userDetailsService(userDetailsService)
			.build();
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

}
