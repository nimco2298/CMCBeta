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
public class UserFuncControllerTest {
	
	/** instance variable    */
	private UserFuncController ufc;
	private DBController dbc = new DBController();
	private University u;
	private GeneralUser account;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DBController db = new DBController();
		GeneralUser user = (GeneralUser) db.getUser("juser");
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
		Assert.assertTrue(schools.contains("YALE"));
		Assert.assertTrue(schools.contains("YANKTOWN COLLEGE"));
	}
	@Test
	public void testEditProfile() {
		ufc.editProfile(account, "TestFirst", "TestLast", "TestPass");
		Assert.assertTrue("Succesful first name edit", dbc.getUser("test").getFirstName().equals("TestFirst"));
		Assert.assertTrue("Succesful lasr name edit", dbc.getUser("test").getLastName().equals("TestLast"));
		Assert.assertTrue("Succesful password edit", dbc.getUser("test").getPassword().equals("TestPass"));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsEmptyFirstName() {
		ufc.editProfile(account, "", "TestLast", "TestPass");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsEmptyLastName() {
		ufc.editProfile(account, "Test", "", "TestPass");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsEmptyPass() {
		ufc.editProfile(account, "Test", "Test", "");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileFailsSpaceInPass() {
		ufc.editProfile(account, "Test", "Test", "Oh No");
	}
	
	
	

//	/**
//	 * Test method for {@link controllers.UserFuncController#saveToSavedSchoolList(entities.University)}.
//	 */
//	@Test
//	public void testSaveToSavedSchoolList() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link controllers.UserFuncController#searchForSchools()}.
//	 */
//	@Test
//	public void testSearchForSchools() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link controllers.UserFuncController#removeSavedSchool(entities.University)}.
//	 */
//	@Test
//	public void testRemoveSavedSchool() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link controllers.UserFuncController#submitProfileChanges(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testSubmitProfileChanges() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link controllers.UserFuncController#updateUser(entities.GeneralUser)}.
//	 */
//	@Test
//	public void testUpdateUser() {
//		fail("Not yet implemented");
//	}
//
//
//	/**
//	 * Test method for {@link controllers.UserFuncController#editProfile()}.
//	 */
//	@Test
//	public void testEditProfile() {
//		fail("Not yet implemented");
//	}

}
