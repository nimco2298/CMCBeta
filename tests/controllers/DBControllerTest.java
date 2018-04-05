package controllers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.DBController;
import entity.University;

/**
 * A JUnit class for the DBController
 * 
 * @author Ian Bush
 * @version April 4, 2018
 */
public class DBControllerTest {

	private DBController db;
	
	/**
	 * This method initializes the information for testing
	 */
	@Before
	public void init()
	{
		db = new DBController();
	}
	
	@Test
	public void testGetUniversityForUniversityInDatabase() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetUniversityForUniversityNotInDatabase() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetUserForGeneralUser()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void testGetUserForAdmin()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void testGetUserFailsForUnrecognizedUserType()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void testAddNewUniversity()
	{
		fail("not yet implemented");
	}
	
	@Test
	public void testAddNewUniversityFailsForUniversityAlreadyInSystem()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddAccount()
	{
	fail("Not yet implemented");
	}
}
