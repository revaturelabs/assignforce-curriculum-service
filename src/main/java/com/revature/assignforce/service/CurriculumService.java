package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Curriculum;

public interface CurriculumService {
	

	List<Curriculum> getAll();
	Optional<Curriculum> findById(int id);
	Curriculum create(Curriculum c);
	Curriculum update(Curriculum c);
	void delete(int id);
	
}
