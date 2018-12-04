package com.revature.assignforce.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.beans.SkillMessage;
import com.revature.assignforce.repos.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

public class SkillListener {
    Logger logger = LoggerFactory.getLogger(SkillListener.class);

    private SkillRepository skillRepository;



    @Autowired
    public SkillListener(@Value("${spring.rabbitmq.batch-queue:trainer-queue}") String trainerQueue,
                         SkillRepository skillRepository) {
        super();
        this.skillRepository = skillRepository;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "curriculum-queue", durable = "true"), exchange = @Exchange(value = "assignforce", ignoreDeclarationExceptions = "true"), key = "assignforce.skill"))
    public void receiveMessage(final byte[] sm, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {

        SkillMessage skillMessage = null;

        try {
            skillMessage = new ObjectMapper().readValue(sm, SkillMessage.class);
            logger.info(String.format("Received message to %s skill %d", skillMessage.getContext(), skillMessage.getSkillId()));
            if(skillMessage.getContext().equalsIgnoreCase("create")) {
                logger.info("Adding new skill to SkillIdHolder");
                SkillIdHolder skillIdHolder = new SkillIdHolder();
                skillIdHolder.setSkillId(skillMessage.getSkillId());
                this.skillRepository.save(skillIdHolder);
            }
            channel.basicAck(tag, false);
        } catch (IOException e) {
            logger.warn("Error while processing skill message " + skillMessage.getSkillId());
            e.printStackTrace();
        }
    }
}
