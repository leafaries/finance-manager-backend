package com.leafaries.financemanagerbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the ModelMapper.
 * <p>
 * This class provides the configuration for a ModelMapper bean.
 * </p>
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures a {@link ModelMapper} bean.
     * <p>
     * The ModelMapper is used for mapping objects from one type to another
     * with customizable configurations.
     * </p>
     *
     * @return the configured {@link ModelMapper}
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
