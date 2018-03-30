package entities;
import org.junit.*;

public class AdminTest {

	private Admin adminTest;
	
	@Before
	public void init() {
		this.adminTest = new Admin("USERNAME", "PASSWORD", 'Y', "FIRSTNAME", "LASTNAME");
	}
	
	@Test
	public void testGetDetails() {
		String details = adminTest.getDetails();
		Assert.assertTrue("Error: Details are not shown", !details.equals(null));
	}
	
	// Test methods for constructor - each test method tests a field of admin
	@Test
	public void testAdminUserName() {
		Assert.assertTrue("Error: Username does not exist", adminTest.getUsername().equals("USERNAME"));
	}
	
	@Test
	public void testAdminPassword() {
		Assert.assertTrue("Error: Password does not exist", adminTest.getPassword().equals("PASSWORD"));
	}

	@Test
	public void testAdminActive() {
		Assert.assertTrue("Error: Active is set as deactivated", adminTest.getActive() == 'Y');
	}
	
	@Test
	public void testAdminFirstName() {
		Assert.assertTrue("Error: First name does not exist", adminTest.getFirstName().equals("FIRSTNAME"));
	}
	
	@Test
	public void testAdminLastName() {
		Assert.assertTrue("Error: Last name does not exist", adminTest.getLastName().equals("LASTNAME"));
	}
}
