import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import controller.*;
import entity.*;

public class CompleteFuncTest {
	private AdminFuncController ad = new AdminFuncController();
	private DBController dbc = new DBController();
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

	/**
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

}
