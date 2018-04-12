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
	private GeneralUser gu, ui;
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
		ui = new GeneralUser("ImMad", "Rahal", 'Y', "Branzino", "fishy", new ArrayList<String>());
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
	    Admin testUser = (Admin)db.getUser("GreasySteve");
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
		gu = (GeneralUser)db.getUser("juser");
		assertTrue(gu.getSavedSchools().contains("BARD"));
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
		University testUniv = new University();
		testUniv.setName("TOMATO");
		int i = db.addSchoolToUserList(gu, testUniv);
		assertEquals("University should not have been added", i, -1);
	}
	//====================================================addNewUniversity()=====================================================
	/**
	 * tests the addNewUniversity() method
	 */
	@Test
	public void testAddNewUniversity()
	{
		int i = db.addNewUniversity(uNu);
		assertFalse(i == -1);
		assertTrue(db.getUniversity("TRUMP") != null);
	}
	
	/**
	 * tests to see if the addNewUniversity() method fails if the "new University"
	 * is already in the database
	 */
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
		int i = db.addAccount(ui);
		assertEquals("Account should be added", i, 1);
		assertTrue(db.getUser("Branzino") != null);
	}
	
	@Test
	public void testAddAccountFailsForAccountAlreadyInSystem()
	{
		int i = db.addAccount(gu);
		assertEquals("Should have failed", i, -1);
	}
	
	//======================================================updateAccount()====================================================
	/**
	 * Tests the updateAccount() method for a valid user
	 */
	@Test
	public void testUpdateAccount()
	{
		gu.setLastName("Sancocho");
		db.updateAccount(gu);
		assertEquals("Database should indicate a change", gu.getLastName(), db.getUser("juser").getLastName());
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
		u.setFemPerc(60);
		int i = db.updateUniversity(u);
		assertEquals("Value should have been updated", 60, db.getUniversity("YANKTOWN COLLEGE").getFemPerc());
		assertFalse("value should have been updated", i == -1);
	}
	//=========================================removeSchoolFromSavedSchoolList()==========================================
	/**
	 * Removes a school from the saved school list
	 */
	@Test
	public void testRemoveSchoolFromSavedSchoolList()
	{
		db.removeSchoolFromSavedSchoolList(gu, u);
		gu = (GeneralUser)db.getUser("juser");
		assertFalse("University should have been removed", gu.getSavedSchools().contains(u.getName()));
	}
	
	@Test
	public void testRemoveSchoolFromSavedSchoolListFailsForUniversityAlreadyRemoved()
	{
		db.removeSchoolFromSavedSchoolList(gu, u);
		int i = db.removeSchoolFromSavedSchoolList(gu, u);
		assertEquals("Double removal should have failed", i, 0);
	}
	//=================================================deleteUniversity()=================================================
	@Test
	public void testDeleteUniversity()
	{
		int i = db.deleteUniversity(uNu);
		
		assertFalse("University should be deleted",i == -1);
	}
	//=================================================deleteAccount()=====================================================
	@Test
	public void testDeleteAccount()
	{
		int i = db.deleteAccount(ui);
		assertFalse("Account should have been deleted", i == -1);
	}
	//=================================================addEmphasis()======================================================
	@Test
	public void testAddEmphasis()
	{
		db.addEmphasis(u, "BIOLOGY");
		u = db.getUniversity("YANKTOWN COLLEGE");
		assertTrue(u.getEmphases().contains("BIOLOGY"));
	}
	//=================================================deleteEmphasis()=====================================================
	@Test
	public void testDeleteEmphasis()
	{
		db.deleteEmphasis(u, "SOCIAL-SCIENCE");
		u = db.getUniversity("YANKTOWN COLLEGE");
		assertFalse("Emphasis should be gone", u.getEmphases().contains("SOCIAL-SCIENCE"));
	}
	
	@After
	public void run()
	{
		ArrayList<String> saved = new ArrayList<String>();
		saved.add("YALE");
		saved.add("YALE2");
		saved.add("YANKTOWN COLLEGE");
		gu = new GeneralUser("James", "Smith", 'Y', "juser", "user", saved);
		gu.setLastName("Smith");
		u.setFemPerc(30);
		u.addEmphases("SOCIAL-SCIENCE");
		db.addEmphasis(u, "SOCIAL-SCIENCE");
		db.deleteEmphasis(u, "BIOLOGY");
		db.updateUniversity(u);
		db.addAccount(gu);
		db.updateAccount(gu);
		db.addAccount(a);
		db.removeSchoolFromSavedSchoolList(gu, db.getUniversity("BARD"));
		db.addNewUniversity(u);
		db.updateUniversity(u);
		db.addSchoolToUserList(gu, u);
		db.deleteAccount(ui);
	}
}
