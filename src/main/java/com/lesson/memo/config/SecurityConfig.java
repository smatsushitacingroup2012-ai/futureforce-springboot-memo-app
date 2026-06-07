package com.lesson.memo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
    		throws Exception {

        		http
            		.authorizeHttpRequests(auth -> auth
                	.requestMatchers("/admin/signup","/admin/signin").permitAll()
                	.anyRequest().authenticated()
            )
            .formLogin(form -> form.loginPage("/admin/signin")
            		.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/memo")
				.failureUrl("/admin/signin?error")
				.permitAll())
            .logout(logout -> logout
                    .logoutUrl("/logout")              
                    .logoutSuccessUrl("/admin/signin") 
                    .invalidateHttpSession(true)       
                    .deleteCookies("JSESSIONID")       
                );
            ;
        								
        		return http.build();
    		}
    
    		@Bean
    		public PasswordEncoder passwordEncoder() {
        		return new BCryptPasswordEncoder();
    		}
}

