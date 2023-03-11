package com.kevinvanthuyne.agon_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "UPDATE", "OPTIONS", "DELETE")
//                .allowedHeaders("x-xsrf-token", "XSRF-TOKEN")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
