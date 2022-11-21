package com.example.pathfinder.config;

import com.example.pathfinder.intercepotors.IpBlackListInterceptor;
import com.example.pathfinder.intercepotors.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptorConfigurator implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new loginInterceptor());
//        registry.addInterceptor(new IpBlackListInterceptor());
    }
}
