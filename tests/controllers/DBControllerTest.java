package controllers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.DBController;

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
}
