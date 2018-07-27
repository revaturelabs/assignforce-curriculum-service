package com.revature.assignforce.tests;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.assignforce.beans.Curriculum;


public class CurriculumConstraintTest {
	private static Validator validator;
	
	
	@BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }

//Test to check if the constraint  that checks that curriculum name cannot be null works
	  @Test
	   public void CurriculumIsNull() {
	      Curriculum myBatch = new Curriculum();
	      myBatch.setName(null);

	      Set<ConstraintViolation<Curriculum>> constraintViolations =
	      validator.validate( myBatch );

	      
	      
	   
	 
	 
	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
		 	         "name cannot be null",
		 	         constraintViolations.iterator().next().getMessage()
		 	      );
	     
	   }
	  
	 //Test to check if the constraint  that checks that curriculum name cannot be an empty string works
	  @Test
	   public void CurriculumEmptyString() {
	      Curriculum myBatch = new Curriculum();
	      myBatch.setName("");

	      Set<ConstraintViolation<Curriculum>> constraintViolations =
	      validator.validate( myBatch );

	      
	      
	   
	 
	 
	      assertEquals( 1, constraintViolations.size() );
	      assertEquals(
		 	         "size must be between 1 and 128",
		 	         constraintViolations.iterator().next().getMessage()
		 	      );
	     
	   }
	  
}
