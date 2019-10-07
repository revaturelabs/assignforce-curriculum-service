package com.revature.assignforce.messaging.messenger;

import org.springframework.stereotype.Component;

@Component
public class CurriculumMessenger {
	
	private String exchange;
	
	private String routingKey;

	
	public void sendDeletionMessage(int id) {
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}


	public String getRoutingKey() {
		return routingKey;
	}


	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	
	
	
}
