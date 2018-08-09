package com.revature.assignforce.messaging.messenger;

import javax.inject.Inject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.revature.assignforce.beans.Curriculum;

@Component
public class CurriculumMessenger {

	private final RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.exchange:assignforce}")
	private String exchange;
	
	@Value("${spring.rabbitmq.curriculum-routing-delete:assignforce.curriculum.delete")
	private String routingKey;
	
	@Inject
	public CurriculumMessenger(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendDeletionMessage(int id) {
		rabbitTemplate.convertAndSend(exchange, routingKey, id);
	}
	
}
