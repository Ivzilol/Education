package com.example.security.config;

import com.example.security.model.enums.UserRoleEnum;
import com.example.security.repository.UserRepository;
import com.example.security.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
public class SecurityConfiguration {

    // Here we have to expose three things
    // 1. PasswordEncoder
    // 2. SecurityFilterChain
    // 3. UserDetailService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // define witch request are allowed and which not
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // everyone can login and register
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                //pages for moderator
                .antMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name())
                //pages for admin
                .antMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name())
                //all another pages for user
                .anyRequest()
                .authenticated()
        .and()
                //configuration form login
                .formLogin()
                .loginPage("/users/login")
                //name of the username field
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                //name of the password field
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // where go if successful
                .defaultSuccessUrl("/")
                // where go if login failed
                .failureForwardUrl("/users/login-error")
        .and()
                .logout()
                .logoutUrl("/users/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
