import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import controller.*;
import entity.*;

public class CompleteFuncTest {
	private AdminFuncController ad = new AdminFuncController();
	private DBController dbc = new DBController();
	private AccountController ac = new AccountController();
	private University u;
	private Account account;
	
	/**
	 * Inserts a test university and account into the database
	 */
	@Before
	public void init() {
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("TestForRemoval");
		u = new University("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, emphases);
		dbc.addNewUniversity(u);
		ad.addEmphases(u);
		
		account = new Admin("test", "password", 'Y', "first", "last");
		dbc.addAccount(account);
	}
	
	/**
	 * Tests homepage method in AdminFuncController and
	 * checks if it works for viewing universities, viewing users, and logging out.
	 */
	@Test
	public void testHomepage_ViewUniversities() {
		String message = ad.homepage("1");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Going to Manage_Universities page. ***", ad.homepage("1"));
		message = ad.homepage("2");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Going to Manage_Users page. ***", ad.homepage("2"));
		message = ad.homepage("3");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Logging out. ***", ad.homepage("3"));
	}
	/**
	 * Deletes the test university and account from the database
	 */
	@After
	public void revert() {
		if(ad.getUniversity(u.getName()) instanceof University) {
			ad.deleteEmphases(u);
			dbc.deleteUniversity(u);
		}
		if(!account.getUsername().equals("DummyUser")) {
			dbc.deleteAccount(account);
		}
	}
	
	// ****************************************LOGIN USE CASE***************************************************************************//
	/**MAIN SCENARIO FOR USE CASE: 1
	 * Test case for succesful login in as a User
	 */
		@Test
		public void testLogin_ValidPassValidUsername() {
			String expectedUser1 = "luser";
			String expectedPass1 = "user";
			boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
			assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);	
		}
	
		/**MAIN SCENARIO FOR USE CASE: 1
		 * Test method for succesful login in as an Admin
		 */
		@Test
		public void testLogin_ValidPassValidAdmin() {
			String expectedUser1 = "nadmin";
			String expectedPass1 = "admin";
			boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
			assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);
			
			
		}
		
	/**ALTERATIVE SCENARIO FOR USE CASE 1: INCORRECT USERNAME
	 * 
	 * Test method for login fails for invalid username,and a valid password
	 */
	@Test
	public void testLogin_InvalidUsernameValidPass() {
		String expectedUser = "lolaa982";
		String expectedPass = "user";
		boolean loginvalue = ac.login(expectedUser,expectedPass);
		assertEquals("Your login status is: " + loginvalue, ac.loginStatus, loginvalue);
			
	}
	 
	/**ALTERATIVE SCENARIO FOR USE CASE 1: INCORRECT PASSWORD
	 * 
	 * Test method for login fails for invalid username,and a valid password
	 */
	@Test
	public void testLogin_InvalidPassValidUsername() {
		String expectedUser1 = "nadmin";
		String expectedPass1 = "oooooo14";
		boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
		assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);
	}	
	
	
	
	/**ALTERATIVE SCENARIO FOR USE CASE 1: EMPTY USERNAME AND EMPTY PASSWORD
	 * Test method for login fails due to invalid user input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLogin_InvalidInput() {
		String expectedUser = "";
		String expectedPass = "";
		ac.login(expectedUser,expectedPass);
		
	}

	
	// ****************************************EDIT UNIVERSITY USE CASE***************************************************************************//
	/**MAIN SCENARIO FOR USE CASE: 12
	 * Test method for editUniversity in AdminFuncController.
	 * Tests each field parameter.
	 */
	@Test
	public void testEditUniversity() {
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("ART");
		emphases.add("BIOLOGY");
		ad.editUniversity("Test", "Minnesota", "URBAN", "CITY", 100, 50, 400, 400, 100, 50, 100, 50, 50, 3, 3, 3, emphases);
		Assert.assertTrue(ad.getUniversity("Test").getState().equals("Minnesota"));
		Assert.assertTrue(ad.getUniversity("Test").getLocation().equals("URBAN"));
		Assert.assertTrue(ad.getUniversity("Test").getControl().equals("CITY"));
		Assert.assertTrue(ad.getUniversity("Test").getStudents() == 100);
		Assert.assertTrue(ad.getUniversity("Test").getFemPerc() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getSatV() == 400);
		Assert.assertTrue(ad.getUniversity("Test").getSatM() == 400);
		Assert.assertTrue(ad.getUniversity("Test").getCost() == 100);
		Assert.assertTrue(ad.getUniversity("Test").getFinAidPerc() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getApplicants() == 100);
		Assert.assertTrue(ad.getUniversity("Test").getAdmitted() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getEnrolled() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getAcadScale() == 3);
		Assert.assertTrue(ad.getUniversity("Test").getSocScale() == 3);
		Assert.assertTrue(ad.getUniversity("Test").getQualScale() == 3);
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("ART"));
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("BIOLOGY"));
	}
	
	/**ALTERATIVE SCENARIO FOR USE CASE 1: EMPTY STATE 
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an empty state input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsEmptyState() {
		ad.editUniversity("Test", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**ALTERATIVE SCENARIO FOR USE CASE 1: EMPTY LOCATION
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an invalid location input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsInvalidLocation() {
		ad.editUniversity("Test", "0", "invalid", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	
	/**ALTERATIVE SCENARIO FOR USE CASE 1: EMPTY LOCATION
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an invalid control input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsInvalidControl() {
		ad.editUniversity("Test", "0", "", "invalid", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for a negative number of students input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsNegativeStudents() {
		ad.editUniversity("Test", "0", "", "", -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) female percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFemalePercentage_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, -1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) female percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFemalePercentage_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 101, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) SAT verbal score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATV_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, -1, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) SAT verbal score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATV_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 801, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) SAT math score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATM_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, -1, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) SAT math score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATM_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 801, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for a negative number of cost input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsNegativeCost() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, -1, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) financial aid percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFinancialAidPercentage_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) financial aid percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFinancialAidPercentage_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 101, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for a negative number of applicants input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsNegativeApplicants() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) admitted percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAdmitted_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, -1, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) admitted percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAdmitted_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 101, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) enrolled percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeEnrolled_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) enrolled percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeEnrolled_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 101, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) academic scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAcademicScale_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) academic scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAcademicScale_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) social scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSocialScale_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) social scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSocialScale_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) quality scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeQualityScale_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) quality scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeQualityScale_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 6, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an over-sized (above 5) array of emphases input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsArraySize() {
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("1");
		emphases.add("2");
		emphases.add("3");
		emphases.add("4");
		emphases.add("5");
		emphases.add("6");
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, emphases);
	}
	
	
	
	
	

}
