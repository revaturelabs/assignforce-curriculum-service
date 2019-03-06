package com.revature.assignforce.config;

import com.revature.assignforce.filters.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .cors()
                // .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .and().addFilter(new AuthorizationFilter(authenticationManager()));
    }

    // @Bean
	// public CorsConfigurationSource corsConfigurationSource() {
	// 	System.out.println("USING YOUR CORS FILTER");
	// 	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// 	final CorsConfiguration config = new CorsConfiguration();
	// 	config.setAllowCredentials(true);
	// 	config.addAllowedOrigin("https://assignforce.cfapps.io");
	// 	config.setAllowedHeaders(Arrays.asList("authorization", "content-type"));
	// 	config.setAllowedMethods(Arrays.asList("GET", "POST","PUT", "DELETE","OPTIONS","HEAD", "PATCH"));
	// 	source.registerCorsConfiguration("/**", config);
	// 	return source;
	// }
}
