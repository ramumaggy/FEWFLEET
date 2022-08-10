package com.few.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	CustomAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
       	.authorizeRequests()
       	.antMatchers("/src/**").permitAll()
       	.antMatchers("/login.html").permitAll()
    	.anyRequest().authenticated()
    	.and()
    	.formLogin()
    	.loginPage("/login.html")
    	.loginProcessingUrl("/login")
    	.successHandler(authenticationSuccessHandler)
    	.failureHandler(authenticationFailureHandler)
        .and()
    	.sessionManagement()
    	.maximumSessions(1)
    	.maxSessionsPreventsLogin(true)
    	.expiredUrl("/login.html?expired")
    	.and()
    	.and()
    	.logout()
    	.logoutSuccessUrl("/login.html")
    	.invalidateHttpSession(true)
    	.deleteCookies("JSESSIONID")
    	.clearAuthentication(true)
    	.permitAll()
    	
    	.and()
    	.csrf().disable();
    }
	
}
