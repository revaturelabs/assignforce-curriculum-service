package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Curriculum;
import com.revature.assignforce.service.CurriculumService;

@CrossOrigin
@RestController
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;

	// findAll
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curriculum> getAll() {
		return curriculumService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> getById(@PathVariable("id") int id) {
		Optional<Curriculum> c = curriculumService.findById(id);
		if (!c.isPresent())
			return new ResponseEntity<Curriculum>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Curriculum>(c.get(), HttpStatus.OK);
	}

	// create
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> cdd(@RequestBody Curriculum c) {
		c = (Curriculum) curriculumService.create(c);
		if (c == null)
			return new ResponseEntity<Curriculum>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Curriculum>(c, HttpStatus.CREATED);
	}

	// update
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> update(@RequestBody Curriculum c) {
		c = (Curriculum) curriculumService.update(c);
		if (c == null)
			return new ResponseEntity<Curriculum>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Curriculum>(c, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> delete(@PathVariable("id") int id) {
		curriculumService.delete(id);
		return new ResponseEntity<Curriculum>(HttpStatus.CREATED);
	}

}
