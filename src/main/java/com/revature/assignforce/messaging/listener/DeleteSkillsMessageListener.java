package com.revature.assignforce.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.messaging.beans.SkillMessage;
import com.revature.assignforce.repos.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeleteSkillsMessageListener implements SkillsMessageListener {
    private static final String name = "DeleteSkillMessageListener";
    private static final Logger LOG = LoggerFactory.getLogger(name);
    private SkillRepository skillRepository;

    @Autowired
    public DeleteSkillsMessageListener(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    @SqsListener(value="${app.sqs.queues.del-skill-queue}", deletionPolicy= SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String message) throws Exception{
        LOG.info("Received -- " + message);
        Map<String, String> messageMap = new ObjectMapper().readValue(message, Map.class);
        SkillMessage sm = new ObjectMapper().readValue(messageMap.get("Message"), SkillMessage.class);
        SkillIdHolder s = new SkillIdHolder(sm.getId());
        LOG.info("Skills are being updated to remove " + s.getId());
    }
}
