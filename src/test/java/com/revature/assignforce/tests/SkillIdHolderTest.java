package com.revature.assignforce.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.SkillIdHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillIdHolderTest {

	@Configuration
	static class SkillIdHolderTestConfiguration {
		@Bean
		public SkillIdHolder SkillIdHolder() {
			return new SkillIdHolder();
		}
	}
	
	@Test
	public void skillIdHolderTest1() {
		SkillIdHolder s1 = new SkillIdHolder();
		assertNotNull(s1);
	}
	
	@Test
	public void skillIdHolderTest2() {
		SkillIdHolder s1 = new SkillIdHolder(3);
		assertTrue(s1.getId() == 3);
	}
	
	@Test
	public void getSetSkillIdTest() {
		SkillIdHolder s1 = new SkillIdHolder();
		s1.setId(12);
		assertTrue(s1.getId() == 12);
	}

}
