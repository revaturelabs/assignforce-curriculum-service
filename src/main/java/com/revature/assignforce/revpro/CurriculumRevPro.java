package com.revature.assignforce.revpro;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Curriculum;

/**
 * 
 * @author Nick
 *
 * The purpose of this class is to house methods for either retrieving or modifying information from Revature Pro. Each method needs to
 * handle both the communication with the Revature Pro API and the conversion of data between Revature Pro and Assign Force. 
 */
public class CurriculumRevPro 
{
	/**
	 * The purpose of this method is to get a list of all curriculum from Revature Pro. It should send a GET request
	 * to the Revature Pro API and convert the returned JSON Array to the List of curriculum, preferably using something
	 * like Jackson or GSON.
	 * 
	 * @return This method returns a list of all Curriculum
	 */
	public List<Curriculum> getAll()
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to return an optional value that corresponds to a specific curriculum. It should send a GET 
	 * request and convert the response to a Curriculum object, preferably using Jackson or GSON. You should take advantage of
	 * the Optional type to check if the curriculum is actually found. The isPresent() method will tell you if the object is 
	 * null or not and the get() method will actually return the value.
	 * 
	 * @param id : This parameter is the id corresponding to the specific curriculum that needs to be retrieved.
	 * @return This method returns a Optional value depending on whether the specific Curriculum is found.
	 */
	public Optional<Curriculum> findById(int id)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to add new curriculum information to Revature Pro. It should convert the curriculum information into
	 * the format that the API requires. It will send a POST request to the API with the information to be added contained in the body of
	 * the request.
	 * 
	 * @param c : This parameter is the Curriculum object that you wish to add to Revature Pro.
	 * @return This method returns the newly added curriculum.
	 */
	public Curriculum create(Curriculum c)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to update existing information in Revature Pro with new information from Assign Force. 
	 * This method will need to convert the data from assign force into the form that is required by the Revature Pro
	 * API. It should then send a PUT request to Revature Pro with the updated information stored in the body of the request.
	 * 
	 * @param c : This parameter will be the updated Curriculum object that you wish to send to the Revature Pro API.
	 * @return This method will return the newly updated Curriculum object.
	 */
	public Curriculum update(Curriculum c)
	{
		return null;
	}
	
	/**
	 * The purpose of this method is to delete a curriculum from Revature Pro. This method will send an id to the Revature Pro API so that 
	 * the Curriculum can be successfully deleted. This could be done with a simple GET request or a DELETE request for best practice.
	 * 
	 * @param The id corresponding to the curriculum that needs to be deleted.
	 */
	public void delete(int id)
	{
		
	}
}
