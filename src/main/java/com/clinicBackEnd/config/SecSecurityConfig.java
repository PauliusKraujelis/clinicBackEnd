package com.clinicBackEnd.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
//	@Bean
//    InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//            .password(passwordEncoder().encode("user1"))
//            .roles("USER")
//            .build();
//        UserDetails user2 = User.withUsername("user2")
//            .password(passwordEncoder().encode("user2"))
//            .roles("USER")
//            .build();
//        UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//        .cors(cors -> cors.disable())
//        .csrf(csrf -> csrf.disable())
        	.authorizeHttpRequests(authorize -> authorize
//        			.requestMatchers(HttpMethod.POST,"/register").permitAll()
//        				.requestMatchers("/admin/a").hasRole("ADMIN")
//        				.requestMatchers("/anonymous*").anonymous()
//        				.requestMatchers("/login*").permitAll()
//        				.requestMatchers("/logout*").permitAll()
//        				.requestMatchers("/register*").permitAll()
        				.anyRequest().permitAll()
        	)
//    	.authorizeHttpRequests(authorize -> authorize
//				.anyRequest().permitAll()
//	)
        	
        	.formLogin(withDefaults()
        	)
        
        	.logout(logout -> logout
        			.logoutUrl("/logout")
        			.deleteCookies("JSESSIONID")
        	)
        	

        			
        ;
	    return http.build();
	}
    

	@Bean 
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
}