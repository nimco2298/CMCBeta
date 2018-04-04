/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controllers.DBController;
import controllers.UserFuncController;
import entities.GeneralUser;

/**
 * @author Will Haanen
 *
 */
public class UserFuncControllerTest {
	
	private UserFuncController ufc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DBController db = new DBController();
		GeneralUser user = (GeneralUser) db.getUser("juser");
		ufc = new UserFuncController(user);
	}

	
	/**
	 * Test method for {@link controllers.UserFuncController#getSavedSchools()}.
	 */
	@Test
	public void testGetSavedSchools() {
		ArrayList<String> schools = ufc.getSavedSchools();
		assertTrue(schools.contains("YALE"));
		assertTrue(schools.contains("YANKTOWN COLLEGE"));
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
