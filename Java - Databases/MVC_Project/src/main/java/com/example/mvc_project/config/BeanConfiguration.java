package com.example.mvc_project.config;

import com.example.mvc_project.util.MyValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public MyValidator getValidator(){
        return new MyValidator();
    }

    @Bean(name = "default")
    public ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }

    @Bean(name = "withLocalDate")
    public ModelMapper getAllModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

}
