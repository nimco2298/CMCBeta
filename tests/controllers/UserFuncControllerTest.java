/**
 * 
 */
package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import controller.*;
import entity.*;

/**
 * @author Will Haanen
 *
 */
@SuppressWarnings("unused")
public class UserFuncControllerTest {
	
	/** instance variable    */
	private UserFuncController ufc;
	private DBController dbc = new DBController();
	//private University u;
	private GeneralUser account;
	private GeneralUser user;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		user = (GeneralUser) dbc.getUser("juser");
		ufc = new UserFuncController(user);
		account = new GeneralUser("first", "last", 'Y', "test", "password", null);
		dbc.addAccount(account);
	}
	@After
	public void tearDown() throws Exception{
		dbc.deleteAccount(dbc.getUser("test"));
	}
	

	
	/**
	 * Test method for {@link controllers.UserFuncController#getSavedSchools()}.
	 */
	@Test
	public void testGetSavedSchools() {
		ArrayList<String> schools = ufc.getSavedSchools();
		dbc.addSchoolToUserList(user, dbc.getUniversity("YALE"));
		Assert.assertTrue(schools.contains("YALE"));
		dbc.removeSchoolFromSavedSchoolList(user, dbc.getUniversity("YALE"));

	}
	/** 
	 * Test if the user profile can be edited
	 */
	@Test
	public void testEditProfile() {
		ufc.editProfile(account, "TestFirst", "TestLast", "TestPass");
		Assert.assertTrue("Succesful first name edit", dbc.getUser("test").getFirstName().equals("TestFirst"));
		Assert.assertTrue("Succesful last name edit", dbc.getUser("test").getLastName().equals("TestLast"));
		Assert.assertTrue("Succesful password edit", dbc.getUser("test").getPassword().equals("TestPass"));
	}
	/** 
	 * Tests if edit fails for empty first name
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsEmptyFirstName() {
		ufc.editProfile(account, "", "TestLast", "TestPass");
	}
	/** 
	 * Tests if edit fails for empty last name
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsEmptyLastName() {
		ufc.editProfile(account, "Test", "", "TestPass");
	}
	/** 
	 * Tests if edit fails for empty password
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsEmptyPass() {
		ufc.editProfile(account, "Test", "Test", "");
	}
	/** 
	 * Tests if edit fails for a space in the password
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsSpaceInPass() {
		ufc.editProfile(account, "Test", "Test", "Oh No");
	}
	
	
	

	/**
	 * Test method for {@link controllers.UserFuncController#saveToSavedSchoolList(entities.University)}.
	 */
	@Test
	public void testSaveToSavedSchoolList() {
		ufc.saveToSavedSchoolList(dbc.getUniversity("BARD"));
		ArrayList<String> list = ufc.getSavedSchools();
		Assert.assertTrue("Check to see if school was added", list.contains("BARD"));
		dbc.removeSchoolFromSavedSchoolList((GeneralUser)dbc.getUser("juser"), dbc.getUniversity("BARD"));
	}


}
