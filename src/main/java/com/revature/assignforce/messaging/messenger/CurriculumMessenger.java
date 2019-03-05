package com.revature.assignforce.messaging.messenger;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.revature.assignforce.beans.Curriculum;

@Component
public class CurriculumMessenger {
	
	private String exchange;
	
	private String routingKey;

	
	public void sendDeletionMessage(int id) {
	}
	
}
