package controllers;
import java.util.ArrayList;

import org.junit.*;
import org.junit.Assert;
import entity.*;
import controller.*;


public class AdminFuncControllerTest {
	private AdminFuncController ad = new AdminFuncController();
	private DBController dbc = new DBController();
	
	// Test delete(University univ) ===========================================================
	@Test
	public void testDeleteUniversity_Works() {
		University u = ad.getUniversity("Test");
		ad.delete(ad.getUniversity("Test"));
		Assert.assertFalse("Error: Test has not been deleted", ad.getUniversity("Test") instanceof University);
		dbc.addNewUniversity(u); // revert back to normal
		ad.addEmphases(u);
	}
	
	@Test(expected = NullPointerException.class)
	public void testDeleteUniversity_Fails() {
		ad.delete(ad.getUniversity("IDoNotExist"));
	}
	
	// Test getUniversity(univName) ===========================================================
	@Test
	public void testGetUniversity_Works() {
		Assert.assertTrue("Error: This university does not exist", ad.getUniversity("Test") instanceof University);
	}
	
	@Test
	public void testGetUniversity_Fails() {
		University expUniv = ad.getUniversity("Test");
		University univ = new University("IDoNotExist", "0", "0", "0", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new ArrayList<String>());
		Assert.assertFalse("Error: does not fail", expUniv.getName().equals(univ.getName()));
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetUniversity_FailsVersion2() {
		ad.addEmphases(ad.getUniversity("rvaerbav"));
	}
	
	// Test saveAccountChanges(univ) ===========================================================
	@Test
	public void testSaveAccountChanges_FirstName() {
		Account account = ad.getAccount("test");
		account.setFirstName("Sara");
		ad.saveAccountChanges(account);
		Assert.assertTrue("Error: the first name has not been changed", account.getFirstName().equals("Sara"));
		account.setFirstName("first"); // revert back to normal
		ad.saveAccountChanges(account);
	}
	
	@Test
	public void testSaveAccountChanges_LastName() {
		Account account = ad.getAccount("test");
		account.setLastName("Laufers");
		ad.saveAccountChanges(account);
		Assert.assertTrue("Error: the last name has not been changed", account.getLastName().equals("Laufers"));
		account.setLastName("last"); // revert back to normal 
		ad.saveAccountChanges(account);
	}
	
	@Test
	public void testSaveAccountChanges_Password() {
		Account account = ad.getAccount("test");
		account.setPassword("secret");
		ad.saveAccountChanges(account);
		Assert.assertTrue("Error: the password has not been changed", account.getPassword().equals("secret"));
		account.setPassword("password"); // revert back to normal 
		ad.saveAccountChanges(account);
	}
	
	@Test
	public void testSaveAccountChanges_Type() {
		Account account = ad.getAccount("test");
		account.setType('a');
		ad.saveAccountChanges(account);
		Assert.assertTrue("Error: the type has not been changed", account.getType() == 'a');
		account.setType('u'); // revert back to normal
		ad.saveAccountChanges(account);
	}
	
	@Test
	public void testSaveAccountChanges_Active() {
		Account account = ad.getAccount("test");
		account.setActive('N');
		ad.saveAccountChanges(account);
		Assert.assertTrue("Error: the last name has not been changed", account.getActive() == 'N');
		account.setActive('Y'); // revert back to normal
		ad.saveAccountChanges(account);
	}
	
	// Test getUniversity() ===========================================================
	@Test
	public void testGetUniversity_UnivExists() {
		Assert.assertTrue("Error: university 'Test' should exist.",ad.getUniversity("Test") instanceof University);
	}
	@Test
	public void testGetUniversity_UnivDoesNotExist() {
		Assert.assertFalse("Error: university 'IDoNotExist' should not exist.",ad.getUniversity("IDoNotExist") instanceof University);
	}
	
	// Test getAccount(accountName) ===========================================================
	@Test
	public void testGetAccount_UserExists() {
		Assert.assertTrue("Error: account 'juser' should exist.",!(ad.getAccount("juser").getUsername().equals("DummyUser")));
	}
	@Test
	public void testGetAccount_UserDoesNotExist() {
		Assert.assertTrue("Error: account 'IDoNotExist' should not exist.",ad.getAccount("IDoNotExist").getUsername().equals("DummyUser"));
	}
	
	// Test addEmphases(univ) ===========================================================
	@Test
	public void testAddEmphases() {
		University univ = ad.getUniversity("Test");
		univ.addEmphases("TestEmphasis");
		ad.addEmphases(univ);
		Assert.assertTrue("Error: the emphasis is not inserted", univ.getEmphases().contains("TestEmphasis"));
		univ.removeEmphases("TestEmphasis"); // revert back to normal 
		ad.deleteEmphases(univ);
	}
	
	// Test deleteEmphases(univ) ===========================================================
	@Test
	public void testDeleteEmphases() {
		University univ = ad.getUniversity("Test");
		univ.addEmphases("TestEmphasis");
		ad.addEmphases(univ);
		univ.removeEmphases("TestEmphasis");
		ad.deleteEmphases(univ);
		Assert.assertFalse("Error: the emphasis is not deleted", univ.getEmphases().contains("TestEmphasis"));
	}
	
	// ===================================================================================================
	//                                      WHITE-BOX TESTING 
	// ===================================================================================================
	
	@Test
	public void testHomepage_ViewUniversities() {
		String message = ad.homepage("1");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Going to Manage_Universities page. ***", ad.homepage("1"));
		//System.out.println("testHomepage_ViewUniversities: " + message);
	}
	
	@Test
	public void testHomepage_ViewUsers() {
		String message = ad.homepage("2");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Going to Manage_Users page. ***", ad.homepage("2"));
		//System.out.println("testHomepage_ViewUsers: " + message);
	}
	
	@Test
	public void testHomepage_LogOut() {
		String message = ad.homepage("3");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Logging out. ***", ad.homepage("3"));
		//System.out.println("testHomepage_LogOut: " + message);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHomepage_FailsInvalidPrompt() {
		//System.out.println("testHomepage_FailsInvalidPrompt: Now throwing exception");
		String message = ad.homepage("4");
		Assert.assertFalse(message != null);
	}
	
	@Test
	public void testViewUniversities() {
		
	}
	
	@Test
	public void testEditUniversity_Works() {
		System.out.println("TESTING testEditUniversity");
		String univName = "Test";
		String state = "Minnesota";
		String location = "URBAN";
		String control = "CITY";
		int students = 100;
		int femPerc = 50;
		int satv = 400;
		int satm = 400;
		int cost = 100;
		int finAidPerc = 50;
		int applicants = 100;
		int admitted = 50;
		int enrolled = 50;
		int acadScale = 3;
		int socScale = 3;
		int qualScale = 3;
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("ART");
		emphases.add("BIOLOGY");
		ad.editUniversity(univName, state, location, control, students, femPerc, satv, satm, cost, finAidPerc, applicants, 
							admitted, enrolled, acadScale, socScale, qualScale, emphases);
		Assert.assertTrue(ad.getUniversity(univName).getState().equals(state));
		Assert.assertTrue(ad.getUniversity(univName).getLocation().equals(location));
		Assert.assertTrue(ad.getUniversity(univName).getControl().equals(control));
		Assert.assertTrue(ad.getUniversity(univName).getStudents() == students);
		Assert.assertTrue(ad.getUniversity(univName).getFemPerc() == femPerc);
		Assert.assertTrue(ad.getUniversity(univName).getSatV() == satv);
		Assert.assertTrue(ad.getUniversity(univName).getSatM() == satm);
		Assert.assertTrue(ad.getUniversity(univName).getCost() == cost);
		Assert.assertTrue(ad.getUniversity(univName).getFinAidPerc() == finAidPerc);
		Assert.assertTrue(ad.getUniversity(univName).getApplicants() == applicants);
		Assert.assertTrue(ad.getUniversity(univName).getAdmitted() == admitted);
		Assert.assertTrue(ad.getUniversity(univName).getEnrolled() == enrolled);
		Assert.assertTrue(ad.getUniversity(univName).getAcadScale() == acadScale);
		Assert.assertTrue(ad.getUniversity(univName).getSocScale() == socScale);
		Assert.assertTrue(ad.getUniversity(univName).getQualScale() == qualScale);
		Assert.assertTrue(ad.getUniversity(univName).getEmphases().contains("ART"));
		Assert.assertTrue(ad.getUniversity(univName).getEmphases().contains("BIOLOGY"));
		//revert to normal
		ad.editUniversity(univName, "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsEmptyState() {
		ad.editUniversity("Test", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsLocation() {
		ad.editUniversity("Test", "0", "invalid", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	
	
}
