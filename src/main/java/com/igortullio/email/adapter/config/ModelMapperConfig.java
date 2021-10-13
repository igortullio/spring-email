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

        Converter<String, Set<String>> stringSetConverter = createStringSetConverter();
        modelMapper.addConverter(stringSetConverter);

        return modelMapper;
    }

    private Converter<String, Set<String>> createStringSetConverter() {
        return new AbstractConverter<>() {
            protected Set<String> convert(String source) {
                String[] split = source.substring(1, source.length() - 1).split(", ");
                return Set.of(split);
            }
        };
    }

}
