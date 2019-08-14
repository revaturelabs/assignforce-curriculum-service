package com.revature.assignforce.tests;

import static org.junit.Assert.*;

import java.util.*;

import com.revature.assignforce.command.FindSkillsCommand;
import com.revature.assignforce.messaging.messenger.CurriculumMessenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Curriculum;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.controllers.CurriculumController;
import com.revature.assignforce.repos.CurriculumRepo;
import com.revature.assignforce.service.CurriculumService;
import com.revature.assignforce.service.CurriculumServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurriculumControllerTest {

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
		
		@Bean
		public CurriculumController curriController() {
			return new CurriculumController();
		}
	}
	
	@Autowired
	private CurriculumService curriService;
	@Autowired
	private CurriculumRepo curriRepository;
	
	@Autowired
	private CurriculumController curriController;
	
	@Test
	public void getAllTest() {
		SkillIdHolder s1 = new SkillIdHolder();
		SkillIdHolder s2 = new SkillIdHolder();
		SkillIdHolder s3 = new SkillIdHolder();
		SkillIdHolder s4 = new SkillIdHolder();
		SkillIdHolder s5 = new SkillIdHolder();
		SkillIdHolder s6 = new SkillIdHolder();
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		skillSet.add(s6);
		Curriculum c1 = new Curriculum(1, "Schedule1", true, true, skillSet);
		Curriculum c2 = new Curriculum(3, "Schedule2", true, false, skillSet);
		List<Curriculum> curriList = new ArrayList<Curriculum>();
		curriList.add(c1);
		curriList.add(c2);
		Mockito.when(curriRepository.findAll()).thenReturn(curriList);
		List<Curriculum> testList = curriController.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void getByIdTestOK() {
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
		Optional<Curriculum> op1 = Optional.ofNullable(c1);
		Mockito.when(curriRepository.findById(1)).thenReturn(op1);
		ResponseEntity<Curriculum> reTest = curriController.getById(1);
		assertTrue(reTest.getBody().getId() == 1 &&  reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Curriculum> reTest = curriController.getById(5);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void addTestCreated() {
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
		Curriculum c1 = new Curriculum(12, "Schedule12", false, true, skillSet);
		Mockito.when(curriRepository.save(c1)).thenReturn(c1);
		ResponseEntity<Object> reTest = curriController.add(c1);
		assertTrue(((Curriculum) reTest.getBody()).getId() == 12 &&  reTest.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void shouldReturnMessagesAndBadRequestOnInvalidCurriculum() {
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
		Curriculum c1 = new Curriculum(12, "J@V@", false, true, skillSet);
		Mockito.when(curriRepository.save(c1)).thenReturn(c1);
		ResponseEntity<Object> reTest = curriController.add(c1);
		assertTrue(((Set<String>) reTest.getBody()).contains("Curriculum name has invalid characters.") &&
				reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	public void shouldReturnMessagesAndBadRequestOnInvalidCurriculumUpdate() {
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
		Curriculum c1 = new Curriculum(12, "", false, true, skillSet);
		Mockito.when(curriRepository.save(c1)).thenReturn(c1);
		ResponseEntity<Object> reTest = curriController.update(c1);
		assertTrue(((Set<String>) reTest.getBody()).contains("A curriculum must have a name.") &&
				reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	public void addTestBadRequest() {
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
		Curriculum c1 = new Curriculum(12, "Schedule12", false, true, skillSet);
		ResponseEntity<Object> reTest = curriController.add(null);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void updateTestCreated() {
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
		Curriculum c1 = new Curriculum(12, "Schedule12", false, true, skillSet);
		c1.setIsCore(false);
		Mockito.when(curriRepository.save(c1)).thenReturn(c1);
		ResponseEntity<Object> reTest = curriController.update(c1);
		assertTrue(!((Curriculum) reTest.getBody()).getIsCore() && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void updateTestBadRequest() {
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
		Curriculum c1 = new Curriculum(12, "Schedule12", false, true, skillSet);
		ResponseEntity<Object> reTest = curriController.update(null);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(curriRepository).deleteById(7);
		ResponseEntity<Curriculum> reTest = curriController.delete(7);
		assertTrue(reTest.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void getByNameTestNotFound() {
		ResponseEntity<List<Curriculum>> reTest = curriController.getByName("Gengar");
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	@Test
	public void getByNameTestOK() {
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
		ResponseEntity<List<Curriculum>> reTest = curriController.getByName("Schedule8");
		assertTrue(reTest.getBody().size() == 1 && reTest.getStatusCode() == HttpStatus.OK);
	}

}
