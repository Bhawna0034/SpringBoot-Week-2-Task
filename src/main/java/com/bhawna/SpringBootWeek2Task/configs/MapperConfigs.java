package com.bhawna.SpringBootWeek2Task.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
