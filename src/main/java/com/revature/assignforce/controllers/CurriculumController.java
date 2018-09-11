package com.revature.assignforce.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.validation.Validator;

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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(c.get(), HttpStatus.OK);
	}

	// create
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody Curriculum curriculum) {
		if (curriculum == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Set<ConstraintViolation<Curriculum>> violations =
				Validation.buildDefaultValidatorFactory().getValidator().validate(curriculum);
		if (violations.size() == 0) {
			curriculum = (Curriculum) curriculumService.create(curriculum);
			return new ResponseEntity<>(curriculum, HttpStatus.CREATED);
		}
		else {
			Set<String> errorMessages = new HashSet<>();
			for (ConstraintViolation<Curriculum> violation : violations) {
				errorMessages.add(violation.getMessage());
			}
			return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
		}
	}

	// update
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Curriculum curriculum) {
		if (curriculum == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Set<ConstraintViolation<Curriculum>> violations =
				Validation.buildDefaultValidatorFactory().getValidator().validate(curriculum);
		if (violations.size() == 0) {
			curriculum = (Curriculum) curriculumService.update(curriculum);
			return new ResponseEntity<>(curriculum, HttpStatus.CREATED);
		}
		else {
			Set<String> errorMessages = new HashSet<>();
			for (ConstraintViolation<Curriculum> violation : violations) {
				errorMessages.add(violation.getMessage());
			}
			return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
		}
	}

	// delete
	@DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> delete(@PathVariable("id") int id) {
		curriculumService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
