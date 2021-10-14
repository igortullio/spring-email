package com.igortullio.email.adapter.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        Converter<Set<String>, String> stringSetConverter = createStringSetConverter();
        modelMapper.addConverter(stringSetConverter);

        return modelMapper;
    }

    private Converter<Set<String>, String> createStringSetConverter() {
        return new AbstractConverter<>() {
            @Override
            protected String convert(Set<String> set) {
                return String.join(",", set);
            }
        };
    }

}
