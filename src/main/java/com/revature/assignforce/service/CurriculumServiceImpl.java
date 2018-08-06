package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Curriculum;
import com.revature.assignforce.messaging.messenger.CurriculumMessenger;
import com.revature.assignforce.repos.CurriculumRepo;
import com.revature.assignforce.commands.FindSkillsCommand;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {
	
	@Autowired
	private CurriculumRepo currRepo;
	
	@Autowired
	private CurriculumMessenger currMessenger;
	
	@Autowired
	private FindSkillsCommand findSkills;
	
	@Override
	public List<Curriculum> getAll() {
		
		return currRepo.findAll();
	}

	@Override
	public Optional<Curriculum> findById(int id) {
		return currRepo.findById(id);
	}

	@Override
	public Curriculum create(Curriculum obj) {
		
		return currRepo.save(obj);
	}

	@Override
	public Curriculum update(Curriculum obj) {
		
		return currRepo.save(obj);
	}

	@Override
	public void delete(int id) {
		currMessenger.sendDeletionMessage(id);
		currRepo.deleteById(id);
		
	}
	
	/**
	 * Checks for referential integrity. The method will first call FindSkill
	 * Command and check if the skill exists.
	 * @param obj  to be checked
	 * @return curr after all, if any, changes are made
	 */
	private Curriculum validateReferences(Curriculum obj) {
		obj.setSkills(obj.getSkills().stream().filter((skillIdHolder) -> findSkills.findSkill(skillIdHolder)).collect(Collectors.toSet()));
		return curr;
}

}
