package com.bjit.nusaiba.backend_project.confuguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(value= {"com.bjit.nusaiba.backend_project.service"})
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
        .antMatchers("/**/*.js", "/**/*.css").permitAll()
        .antMatchers("/","/registration","/register","/login","/error")
        .permitAll()
        .antMatchers("/add_employee","/edit_employee","/save")
        .hasAnyAuthority("employee","admin")
        .antMatchers("/delete_employee")
        .hasAuthority("admin")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .and().logout().invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/");
        
        http.csrf().disable();
        
        return http.build();
    }
	
}
