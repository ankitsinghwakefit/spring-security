package com.example.security_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// it's a configuration file so 
@Configuration
// we want spring security
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // see there are many security filters in between thus we have to return
    // SecurityFilterChain object
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable()); // disable csrf protection
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // all requests will be
                                                                                     // authenticated
        // http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // means requests will be statelessso with all requests we have to pass session
        // / login creds
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // here we define our user details service
        // we can fetch user details from database, file, etc
        UserDetails user1 = User.withUsername("user1").password("{noop}password").roles("USER").build();
        UserDetails admin1 = User.withUsername("admin1").password("{noop}password").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1, admin1);
    }
}
