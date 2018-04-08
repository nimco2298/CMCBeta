package controllers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.DBController;
import entity.*;
import java.util.ArrayList;

/**
 * A JUnit class for the DBController
 * 
 * @author Ian Bush
 * @version April 5, 2018
 */
public class DBControllerTest {

	private DBController db;
	private GeneralUser gu;
	private Admin a;
	private University u;
	
	/**
	 * This method initializes the information for testing
	 */
	@Before
	public void init()
	{
		db = new DBController();
		
		ArrayList<String> saved = new ArrayList<String>();
		saved.add("YALE");
		saved.add("YALE2");
		saved.add("YANKTOWN COLLEGE");
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("BUSINESS-ADMINISTRATION");
		emphases.add("SOCIAL-SCIENCE");
		
		gu = new GeneralUser("James", "Smith", 'Y', "juser", "user", saved);
		a = new Admin("nadmin", "admin", 'Y', "Noreen", "Admin");
		u = new University("YANKTOWN COLLEGE", "SOUTH DAKOTA", "SUBURBAN", "PRIVATE", 10000, 30, 450, 400, 15736, 95, 4000, 95, 90, 1, 2, 2, emphases);
	}
	
	/**
	 * Tests the getUniversity() method
	 */
	@Test
	public void testGetUniversity() {
		University testUniversity = db.getUniversity("YANKTOWN COLLEGE");
		assertEquals("University name does not match expected", u.getName(), testUniversity.getName());
		assertEquals("University state does not match expected", u.getState(), testUniversity.getState());
		assertEquals("University location does not match expected", u.getLocation(), testUniversity.getLocation());
		assertEquals("University control does not match expected", u.getControl(), testUniversity.getControl());
		fail("Not yet implemented");
	}
	
	/**
	 * Tests that the getUniversity() method fails when trying to get a university that is not included in the system
	 */
	@Test
	public void testGetUniversityForUniversityNotInDatabase() {
		fail("Not yet implemented");
	}
	
	/**
	 * Checks to see if the getUser() method works properly for a GeneralUser
	 */
	@Test
	public void testGetUserForGeneralUser()
	{	
		GeneralUser testUser = (GeneralUser)db.getUser("juser");
		assertEquals("First name should be James", gu.getFirstName(), testUser.getFirstName());
		assertEquals("Last name should be Smith", gu.getLastName(), testUser.getLastName());
		assertEquals("Username should be juser", gu.getUsername(), testUser.getUsername());
		assertEquals("Password should be user", gu.getPassword(), testUser.getPassword());
		assertEquals("User type should be 'u'", gu.getType(), testUser.getType());
		assertEquals("User status should be 'Y'", gu.getActive(), testUser.getActive());
	}
	
	/**
	 * Checks to see if the getUser() method works properly for an Admin
	 */
	@Test
	public void testGetUserForAdmin()
	{
		Admin testAdmin = (Admin)db.getUser("nadmin");
		assertEquals("User type should be 'a'",testAdmin.getType(), a.getType());
	}
	
	/**
	 * Checks to see if the getUser() method returns DummyUser for
	 * a user not already in the system
	 */
	@Test
	public void testGetUserFailsForIncorrectUserName()
	{
		GeneralUser testUser = (GeneralUser)db.getUser("Greasy Steve");
		assertEquals("Username is 'DummyUser'", testUser.getUsername(), "DummyUser");
	}
	
	/**
	 * Checks to see if an account is added properly into the system
	 */
	@Test
	public void testAddAccount()
	{
	fail("Not yet implemented");
	}
	
	@Test
	public void testAddAccountFailsForAccountAlreadyInSystem()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddAccountFailsForIncorrectAccountType()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateAccount()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateAccountFailsForAccountNotInTheSystem()
	{
		fail("Not yet implemented");
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
	public void testUpdateUniversity()
	{
		
	}
	
	@Test
	public void testAddSchoolToUserList()
	{
		
	}
	
	@Test
	public void testAddSchoolToUserListFailsForUniversityAlreadySavedToList()
	{
		
	}
	
	@Test
	public void testRemoveSchoolFromSavedSchoolList()
	{
		
	}
	
	@Test
	public void testRemoveSchoolFromSavedSchoolListFailsForUniversityNotAlreadyRemoved()
	{
		
	}
}
