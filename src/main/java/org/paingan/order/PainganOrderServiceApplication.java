package org.paingan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EnableResourceServer
@EnableFeignClients("org.paingan.order.client")
public class PainganOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PainganOrderServiceApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
