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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.validation.Validator;

@RestController
@Api(value = "CurriculumController")
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;

	/**
	 * Find all curricula using a get request and return a list of items
	 * 
	 * @return	List of all Curricula
	 */
	@ApiOperation(value = "List All Curricula from the System ", response = Iterable.class, tags = "getAllCurricula")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curriculum> getAll() {
		return curriculumService.getAll();
	}

	
	/**
	 * Find curriculum by id using get request and return status 200 - OK
	 * If no batch found, return status 404 - not found
	 * 
	 * @param id	Find by Id
	 * @return		RequestEntity
	 */
	@ApiOperation(value = "List Curriculum by Id ", response = ResponseEntity.class, tags = "getCurriculumById")
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> getById(@PathVariable("id") int id) {
		Optional<Curriculum> c = curriculumService.findById(id);
		if (!c.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(c.get(), HttpStatus.OK);
	}


	/**
	 *  Create curriculum with unique id, name, isActive, isCore, skills, and if the request doesn't return any violations, 
	 *	Return status 201 - created
	 *
	 *	If the request violates a constraint, 
	 *		return violation message
	 *
	 *	If the request doesn't fit the parameters, 
	 *		it returns status 400 - bad request
	 *
	 * @param curriculum create with unique id, name, isActive, isCore, skills
	 * @return			 ResponseEntity
	 */
	@ApiOperation(value = "Create a Curriculum to insert into the System ", response = ResponseEntity.class, tags = "addCurriculum")
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


	/**
	 * 	Update curriculum's id, name, isActive, isCore, skills parameters.
	 *	If request is null, return status 400 - bad request
	 *
	 *	Check request for constraint violations
	 *	If the request doesn't return any violations, 
	 *		return status 201 - created
	 *
	 *	If the request violates a constraint, 
	 *		return violation message
	 *
	 *	If the request doesn't fit the parameters, 
	 *		it returns status 400 - bad request
	 * 
	 * @param curriculum update id, name, isActive, isCore, skills
	 * @return 			 ResponseEntity
	 */
	@ApiOperation(value = "Update Curriculum Information", response = ResponseEntity.class, tags = "updateCurriculum")
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

	/**
	 * Find a curriculum by id and delete. return status 200 - OK
	 * 
	 * @param id 	Curriculum by Id
	 * @return		ResponseEntity
	 */
	@ApiOperation(value = "Delete Curriculum from the System ", response = ResponseEntity.class, tags = "deleteCurriculum")
	@DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curriculum> delete(@PathVariable("id") int id) {
		curriculumService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
