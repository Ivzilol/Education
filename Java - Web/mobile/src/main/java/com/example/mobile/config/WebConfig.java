package com.example.mobile.config;

import com.example.mobile.service.MaintenanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MaintenanceInterceptor maintenanceInterceptor;
    private final LocaleChangeInterceptor localeChangeInterceptor;

    public WebConfig(MaintenanceInterceptor maintenanceInterceptor, LocaleChangeInterceptor localeChangeInterceptor) {
        this.maintenanceInterceptor = maintenanceInterceptor;
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(localeChangeInterceptor);
       registry.addInterceptor(maintenanceInterceptor);
    }
}
