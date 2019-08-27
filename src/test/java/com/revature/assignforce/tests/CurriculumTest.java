package com.revature.assignforce.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Curriculum;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.CurriculumRepo;
import com.revature.assignforce.service.CurriculumService;
import com.revature.assignforce.service.CurriculumServiceImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurriculumTest {

	@Configuration
	static class CurriculumTestConfiguration {
		@Bean
		public Curriculum Curriculum() {
			return new Curriculum();
		}
	}
	
	@Test
	public void curriculumTest1() {
		Curriculum c1 = new Curriculum();
		assertNotNull(c1);
	}
	
	@Test
	public void curriculumTest2() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		SkillIdHolder s6 = new SkillIdHolder(6);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		skillSet.add(s6);
		Curriculum c1 = new Curriculum(1, "Schedule1", true, true, skillSet);
		assertTrue(c1.getId() == 1);
	}
	
	@Test
	public void getSetIdTest() {
		Curriculum c1 = new Curriculum();
		c1.setId(12);
		assertTrue(c1.getId() == 12);
	}
	
	@Test
	public void getSetNameTest() {
		Curriculum c1 = new Curriculum();
		c1.setName("ScheduleA");
		assertTrue(c1.getName().equals("ScheduleA"));
	}
	
	@Test
	public void getSetIsActiveTest() {
		Curriculum c1 = new Curriculum();
		c1.setIsActive(true);
		assertTrue(c1.getIsActive());
	}
	
	@Test
	public void getSetIsCoreTest() {
		Curriculum c1 = new Curriculum();
		c1.setIsCore(true);
		assertTrue(c1.getIsCore());
	}
	
	@Test
	public void getSetSkills() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		SkillIdHolder s6 = new SkillIdHolder(6);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		skillSet.add(s6);
		Curriculum c1 = new Curriculum();
		c1.setSkills(skillSet);
		assertTrue(c1.getSkills().size() == 6);
	}


	@Test
	public void testValidationShouldReturn2ViolationsOnNullAndEmptyValues() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Curriculum curriculum = new Curriculum();
		curriculum.setName("");
		curriculum.setIsActive(null);
		Set<ConstraintViolation<Curriculum>> violations = validator.validate(curriculum);
		Assert.assertEquals(2, violations.size());
	}

	@Test
	public void validationShouldCatchIllegalCharacters() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		SkillIdHolder s6 = new SkillIdHolder(6);

		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		skillSet.add(s6);

		Curriculum curriculum = new Curriculum();
		curriculum.setName("J@v@");
		curriculum.setIsActive(true);
		curriculum.setIsCore(true);
		curriculum.setSkills(skillSet);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Curriculum>> violations = validator.validate(curriculum);
		Assert.assertEquals(1, violations.size());
	}

	@Test
	public void CurriculumEmptyString() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Curriculum curriculum = new Curriculum();
		curriculum.setName("");

		Set<String> messages = new HashSet<>();
		Set<ConstraintViolation<Curriculum>> violations = validator.validate(curriculum);
		for (ConstraintViolation<Curriculum> violation : violations) {
			messages.add(violation.getMessage());
		}
		Assert.assertTrue(messages.contains("The name has to be between 1 and 128 characters in length."));
		Assert.assertTrue(messages.contains("A curriculum must have a name."));
	}
}
