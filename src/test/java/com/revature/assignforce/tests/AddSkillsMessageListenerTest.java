package com.revature.assignforce.tests;

import com.revature.assignforce.messaging.listener.AddSkillsMessageListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddSkillsMessageListenerTest {

    @Configuration
    static class AddSkillsMessageListenerTestConfiguration {
        @Bean
        public AddSkillsMessageListener addSkillsMessageListener() {return Mockito.mock(AddSkillsMessageListener.class);}
    }

    @Autowired
    private AddSkillsMessageListener addSkillsMessageListener;

    //This test doesn't actually test functionality
    //Needs work
    @Test
    public void receiveTest() {
        String message = "Gengar";
        try {
            addSkillsMessageListener.receive(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(message);
        }
//
//    @Test(expected = IOException.class)
//    public void receiveExceptionTest() throws IOException {
//        String message = null;
//        addSkillsMessageListener.receive(message);
//        }
    }

