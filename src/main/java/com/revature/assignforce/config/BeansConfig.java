package com.revature.assignforce.config;

import com.revature.assignforce.service.CurriculumService;
import com.revature.assignforce.service.CurriculumServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public CurriculumService curriculumService() {
        return new CurriculumServiceImpl();
    }
}
