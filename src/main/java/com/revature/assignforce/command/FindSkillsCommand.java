package com.revature.assignforce.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Curriculum;
import com.revature.assignforce.beans.SkillIdHolder;

@Component
public class FindSkillsCommand{
	@Value("${environment.gateway-url:http://localhost:8765/}")
	private String gatewayUrl;
	@Value("${environment.service.location:skill-service/}")
	private String skillUri;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Command called to verify that the curriculum skill provided exists in the skill service
	 * @param curr - new curriculum trying to be created
	 * @return - true if the location exists
	 */
	@HystrixCommand(fallbackMethod = "findSkillFallback")
	public boolean findSkill(SkillIdHolder skillIdHolder) {
		ResponseEntity<String> response = restTemplate.getForEntity(gatewayUrl + skillUri + skillIdHolder.getId(), String.class);
		return true;
	}
	
	/**
	 * Fallback method in case the location does not exist
	 * @param curr - new curriculum to be created
	 * @return - if the skill is not found, return false
	 */
	public boolean findSkillFallback(Curriculum curr) {
		return false;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
}
