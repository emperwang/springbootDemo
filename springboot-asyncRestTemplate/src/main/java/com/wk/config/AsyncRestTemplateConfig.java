package com.wk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AsyncRestTemplateConfig {

    @Bean
    public AsyncRestTemplate asyncRestTemplate(){
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        /*asyncRestTemplate.setDefaultUriVariables();
        asyncRestTemplate.setErrorHandler();
        asyncRestTemplate.setMessageConverters();
        asyncRestTemplate.setUriTemplateHandler();
        asyncRestTemplate.setAsyncRequestFactory();*/
        return asyncRestTemplate;
    }

    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }
}
