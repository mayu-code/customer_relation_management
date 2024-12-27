package com.management.customer_relation_management.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ImageConfig {

    @Bean
	public Cloudinary getCloudinary() {
		Map<String,Object> config = new HashMap<>();
		config.put("cloud_name", "ddcgb86ce");
		config.put("api_key", "697631535778226");
		config.put("api_secret", "olaHzTs5vDcPw1cuBjYUHDZ5T-A");
		config.put("secure", true);
		return new Cloudinary(config);
	}
    
}

