package com.revature.assignforce.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.revature.assignforce.command.FindSkillsCommand;
import com.revature.assignforce.messaging.messenger.CurriculumMessenger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Curriculum;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.CurriculumRepo;
import com.revature.assignforce.service.CurriculumService;
import com.revature.assignforce.service.CurriculumServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurriculumServiceImplTest {
	
	@Configuration
	static class CurriculumServiceTestConfiguration {
		@Bean
		public FindSkillsCommand findSkillsCommand() {return Mockito.mock(FindSkillsCommand.class); }
		@Bean
		public CurriculumMessenger curriculumMessenger() {return Mockito.mock(CurriculumMessenger.class); }
		@Bean
		public CurriculumService curriculumService() {
			return new CurriculumServiceImpl();
		}
		@Bean
		public CurriculumRepo curriRepository() {
			return Mockito.mock(CurriculumRepo.class);
		}
	}

	@Autowired
	private CurriculumService curriService;
	@Autowired
	private CurriculumRepo curriRepository;
	@Autowired
	private CurriculumServiceImpl currisServiceImpl;
	
	@Test
	public void getAllTest() {
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
		Curriculum c2 = new Curriculum(3, "Schedule2", true, false, skillSet);
		Curriculum c3 = new Curriculum(5, "Schedule3", false, true, skillSet);
		List<Curriculum> curriList = new ArrayList<Curriculum>();
		curriList.add(c1);
		curriList.add(c2);
		curriList.add(c3);
		Mockito.when(curriRepository.findAll()).thenReturn(curriList);
		List<Curriculum> testList = curriService.getAll();
		assertTrue(testList.size() == 3);
	}
	
	@Test
	public void findByIdTest() {
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
		Curriculum c1 = new Curriculum(2, "Schedule8", true, true, skillSet);
		Optional<Curriculum> op1 = Optional.ofNullable(c1);
		Mockito.when(curriRepository.findById(2)).thenReturn(op1);
		Optional<Curriculum> opTest = curriService.findById(2);
		assertTrue(opTest.get().getId() == 2);
	}
	
	@Test
	public void createTest() {
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
		Curriculum c1 = new Curriculum(5, "Schedule5", false, false, skillSet);
		Mockito.when(curriRepository.save(c1)).thenReturn(c1);
		Curriculum cTest = curriService.create(c1);
		assertTrue(cTest.getId() == 5);
	}
	
	@Test
	public void updateTest() {
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
		Curriculum c1 = new Curriculum(5, "Schedule5", false, false, skillSet);
		c1.setName("Schedule500");
		Mockito.when(curriRepository.save(c1)).thenReturn(c1);
		Curriculum cTest = curriService.update(c1);
		assertTrue(cTest.getName().equals("Schedule500"));
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(curriRepository).deleteById(6);
		curriService.delete(6);
		Optional<Curriculum> opTest = curriService.findById(6);
		assertFalse(opTest.isPresent());
	}

	@Test
	public void validateReferencesTest() {
		Curriculum obj = null;
		Curriculum testObj = currisServiceImpl.validateReferences(obj);
		Assert.assertNull(testObj);
	}

	@Test
	public void findByNameTest() {
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
		Curriculum c1 = new Curriculum(2, "Schedule8", true, true, skillSet);
		List<Curriculum> curriList = new ArrayList<>();
		curriList.add(c1);
		Mockito.when(curriRepository.findByName("Schedule8")).thenReturn(curriList);
		List<Curriculum> testList = curriService.findByName("Schedule8");
		assertTrue(testList.size() == 1);
	}

}
