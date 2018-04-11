package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.DBController;
import entity.*;
import java.util.ArrayList;

/**
 * A JUnit class for the DBController
 * 
 * @author Ian Bush
 * @version April 8, 2018
 */
public class DBControllerTest {

	private DBController db;
	private GeneralUser gu;
	private Admin a;
	private University u, uNu;
	
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
		a = new Admin("kuser", "admin", 'Y', "Caaarld", "Paul");
		u = new University("YANKTOWN COLLEGE", "SOUTH DAKOTA", "SUBURBAN", "PRIVATE", 10000, 30, 450, 400, 15736, 95, 4000, 95, 90, 1, 2, 2, emphases);
		uNu = new University();
		uNu.setName("TRUMP");
	}
	//=====================================================getUniversity()===================================================
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
		assertEquals("Number of students in university does not match expected", u.getStudents(), testUniversity.getStudents());
		assertEquals("Female percentage of student body does not match expected", u.getFemPerc(), testUniversity.getFemPerc());
		assertEquals("Average SAT verbal score does not match expected", u.getSatV(), testUniversity.getSatV());
		assertEquals("Average SAT math score does not match expected", u.getSatM(), testUniversity.getSatM());
		assertEquals("Average annual cost does not match expected", u.getCost(), testUniversity.getCost());
		assertEquals("Percentage of financial aid grants does not match expected", u.getFinAidPerc(), testUniversity.getFinAidPerc());
		assertEquals("Number of applicants does not match expected", u.getApplicants(), testUniversity.getApplicants());
		assertEquals("Number of admitted students does not match expected", u.getAdmitted(), testUniversity.getAdmitted());
		assertEquals("Number of enrolled students does not match expected", u.getEnrolled(), testUniversity.getEnrolled());
		assertEquals("Academic ranking does not match expected", u.getAcadScale(), testUniversity.getAcadScale());
		assertEquals("Social ranking does not match expected", u.getSocScale(), testUniversity.getSocScale());
		assertEquals("Quality of life ranking does not match expected", u.getQualScale(), testUniversity.getQualScale());
		assertTrue("Emphases do not match expected", u.getEmphases().equals(testUniversity.getEmphases()));
	}
	
	/**
	 * Tests that the getUniversity() method fails when trying to get a university that is not included in the system
	 */
	@Test
	public void testGetUniversityForUniversityNotInDatabase() {
		University un = db.getUniversity("PizzaGuy");
		assertEquals("Answer should be null", un, null);
	}
	//=====================================================getUser()======================================================
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
		Admin testAdmin = (Admin)db.getUser("kuser");
		assertEquals("User type should be 'a'",testAdmin.getType(), a.getType());
		assertEquals("Username does not match expected", a.getUsername(),testAdmin.getUsername());
		assertEquals("Password does not match expected", a.getPassword(), testAdmin.getPassword());
		assertEquals("First name does not match expected", a.getFirstName(), testAdmin.getFirstName());
		assertEquals("Last name does not match expected", a.getLastName(), testAdmin.getLastName());
		assertEquals("Activity does not match expected", a.getActive(), testAdmin.getActive());
	}
	
	/**
	 * Checks to see if the getUser() method returns DummyUser for
	 * a user not already in the system
	 */
	@Test
	public void testGetUserFailsForIncorrectUserName()
	{
	    GeneralUser testUser = (GeneralUser)db.getUser("GreasySteve");
		assertEquals("Username is 'DummyUser'", testUser.getUsername(), "DummyUser");
	}
	//=============================================addSchoolToUserList()==============================================
	/**
	 * Tests addSchoolToUserList()
	 */
	@Test
	public void testAddSchoolToUserList()
	{
		int i = db.addSchoolToUserList(gu, db.getUniversity("BARD"));
		assertEquals("Test failed", 1, i);
	}
	
	/**
	 * Tests if addSchoolToUserList() fails if the school has already been saved to the university list
	 */
	@Test
	public void testAddSchoolToUserListFailsForUniversityAlreadySavedToList()
	{
		int i = db.addSchoolToUserList(gu, db.getUniversity("YALE2"));
		assertEquals("Test failed", -1, i);
	}
	
	/**
	 * Tests that the addSchoolToUserList() method fails if the university is not in the database
	 */
	@Test
	public void testAddSchoolToUserListFailsForUniversityNotInTheSystem()
	{
		fail("Not yet implemented");
	}
	//====================================================addNewUniversity()=====================================================
	@Test
	public void testAddNewUniversity()
	{
		int i = db.addNewUniversity(uNu);
		assertFalse(i == -1);
		assertTrue(db.getUniversity("TRUMP") != null);
	}
	@Test
	public void testAddNewUniversityFailsForUniversityAlreadyInSystem()
	{
		int i = db.addNewUniversity(db.getUniversity("YALE2"));
		assertEquals("Test should have failed", i, -1);
	}
	//===================================================addAccount()====================================================
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
	
	//======================================================updateAccount()====================================================
	/**
	 * Tests the updateAccount() method for a valid user
	 */
	@Test
	public void testUpdateAccount()
	{
		
		fail("Not yet implemented");
	}
	
	/**
	 * Tests that updateAccount() fails if the requested user is not located in the system
	 */
	@Test
	public void testUpdateAccountFailsForAccountNotInTheSystem()
	{
		GeneralUser testFail = new GeneralUser("ImMad", "Rahal", 'Y', "UserFail", "failure", new ArrayList<String>());
		assertEquals("Account should not have been updated", 0, db.updateAccount(testFail));
	}
	
	//==================================================updateUniversity()===============================================
	@Test
	public void testUpdateUniversity()
	{
		fail("Not yet implemented");
	}
	//=========================================removeSchoolFromSavedSchoolList()==========================================
	@Test
	public void testRemoveSchoolFromSavedSchoolList()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemoveSchoolFromSavedSchoolListFailsForUniversityNotAlreadyRemoved()
	{
		fail("Not yet implemented");
	}
	//=================================================deleteUniversity()=================================================
	@Test
	public void testDeleteUniversity()
	{
		int i = db.deleteUniversity(u);
		assertTrue("University should be deleted",i == 0);
	}
	//=================================================deleteAccount()=====================================================
	@Test
	public void testDeleteAccount()
	{
		int i = db.deleteAccount(a);
		assertFalse("Account should have been deleted", i == -1);
	}
	//=================================================addEmphasis()======================================================
	@Test
	public void testAddEmphasis()
	{
		db.addEmphasis(uNu, "BIOLOGY");
		uNu.addEmphases("BIOLOGY");
		
		fail("Not yet implemented");
	}
	//=================================================deleteEmphasis()=====================================================
	@Test
	public void testDeleteEmphasis()
	{
		fail("Not yet implemented");
	}
	
	@After
	public void run()
	{
		ArrayList<String> saved = new ArrayList<String>();
		saved.add("YALE");
		saved.add("YALE2");
		saved.add("YANKTOWN COLLEGE");
		gu = new GeneralUser("James", "Smith", 'Y', "juser", "user", saved);
		db.addAccount(gu);
		db.addAccount(a);
		db.removeSchoolFromSavedSchoolList(gu, db.getUniversity("BARD"));
		db.addNewUniversity(u);
		db.deleteUniversity(uNu);
	}
}
