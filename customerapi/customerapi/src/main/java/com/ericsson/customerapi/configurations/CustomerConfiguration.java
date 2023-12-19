package com.ericsson.customerapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfiguration {

	@Bean
	public RestTemplate createRestTemplateInstance() {
		return new RestTemplate();
	}
}
