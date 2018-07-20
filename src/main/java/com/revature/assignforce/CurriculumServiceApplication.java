package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CurriculumServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CurriculumServiceApplication.class).run(args);
	}
}
