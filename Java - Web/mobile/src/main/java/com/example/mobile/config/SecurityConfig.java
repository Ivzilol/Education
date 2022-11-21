package com.example.mobile.config;

import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.MobileUserDetailsService;
import com.example.mobile.service.OAuthSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // 1. PasswordEncoder
    // 2. SecurityFilterChain
    // 3. UserDetailService



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           OAuthSuccessHandler oAuthSuccessHandler) throws Exception {

        http
                // define witch request are allowed and which not
                .authorizeRequests()

                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // everyone can login and register
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                .antMatchers("/offers/add").permitAll()
                .antMatchers("/offers/**").permitAll()
                .antMatchers("/maintenance").permitAll()
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
                // witch is the logout must be postRequest
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                // allow oauth login
                .oauth2Login()
                .loginPage("/users/login")
                .successHandler(oAuthSuccessHandler);

        return http.build();
    }

    @Primary
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MobileUserDetailsService(userRepository);
    }
}
