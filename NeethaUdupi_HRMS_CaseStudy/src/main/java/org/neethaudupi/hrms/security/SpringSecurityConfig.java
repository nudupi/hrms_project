package org.neethaudupi.hrms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
		
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	
		return daoAuthenticationProvider;
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/css/**","/js/**","/images/**").permitAll()
		.antMatchers("/mainPage","/employeeSelf","/employeeProf","/payslip").permitAll()
		
		// Restrict access to "/admin" route to users with ADMIN role
		
		.antMatchers("/manageEmployee","/register","/updateEmployee").hasRole("ADMIN")
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		
		// Handle access denied exceptions
		
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied")
		
		// Handle logout
		
		.and()
		.logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login").permitAll();
		
	/*	http.authorizeRequests().antMatchers("/").permitAll();*/
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}

