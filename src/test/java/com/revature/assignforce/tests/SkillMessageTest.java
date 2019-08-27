package com.revature.assignforce.tests;

import com.revature.assignforce.messaging.beans.SkillMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillMessageTest {

    @Configuration
    static class SkillMessageTestConfiguration {
        @Bean
        public SkillMessage skillMessage() {
            return new SkillMessage();
        }
    }

    @Test
    public void skillMessageTest1() {
        SkillMessage sm1 = new SkillMessage();
        assertNotNull(sm1);
    }

    @Test
    public void SkillMessageTest2(){
        String context = "test";
        Integer skillId = 1;
        SkillMessage sm1 = new SkillMessage(context, skillId);
        assertTrue(sm1.getContext().equals("test") && sm1.getId() == skillId);
    }

    @Test
    public void getSetContext(){
        String context = "test";
        SkillMessage sm1 = new SkillMessage();
        sm1.setContext(context);
        assertTrue(sm1.getContext().equals("test"));
    }

    @Test
    public void getSetSkillId(){
        Integer skillId = 1;
        SkillMessage sm1 = new SkillMessage();
        sm1.setId(1);
        assertTrue(sm1.getId() == skillId);
    }
}
