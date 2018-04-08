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
 * @version April 8, 2018
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
	
	//===================================================getUniversities()==================================================
	@Test
	public void testGetUniversities()
	{
		
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
		fail("Not yet implemented");
	}
	//==================================================getAccounts()====================================================
	@Test
	public void testGetAccounts()
	{
		
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
		Admin testAdmin = (Admin)db.getUser("nadmin");
		assertEquals("User type should be 'a'",testAdmin.getType(), a.getType());
		assertEquals("Username does not match expected", a.getUsername(),testAdmin.getUsername());
		assertEquals("Password does not match expected", a.getPassword(), testAdmin.getPassword());
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
	//=============================================addSchoolToUserList()==============================================
	@Test
	public void testAddSchoolToUserList()
	{
		
	}
	
	/**
	 * Tests if addSchoolToUserList() fails if the school has already been saved to the university list
	 */
	@Test
	public void testAddSchoolToUserListFailsForUniversityAlreadySavedToList()
	{
		
	}
	//====================================================addNewUniversity()=====================================================
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
	
	//==================================================updateUniversity()===============================================
	@Test
	public void testUpdateUniversity()
	{
		
	}
	//=========================================removeSchoolFromSavedSchoolList()==========================================
	@Test
	public void testRemoveSchoolFromSavedSchoolList()
	{
		
	}
	
	@Test
	public void testRemoveSchoolFromSavedSchoolListFailsForUniversityNotAlreadyRemoved()
	{
		
	}
	//=================================================deleteUniversity()=================================================
	@Test
	public void testDeleteUniversity()
	{
		
	}
	//=================================================deleteAccount()=====================================================
	@Test
	public void testDeleteAccount()
	{
		
	}
	//=================================================addEmphasis()======================================================
	@Test
	public void testAddEmphasis()
	{
		
	}
	//=================================================deleteEmphasis()=====================================================
	public void testDeleteEmphasis()
	{
	
	}
}
