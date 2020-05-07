package com.sidneysimmons.simple.web.socket.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Common configuration.
 * 
 * @author Sidney Simmons
 */
@Configuration
public class CommonConfiguration {

    /**
     * Create a common object mapper.
     * 
     * @return the object mapper
     */
    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
