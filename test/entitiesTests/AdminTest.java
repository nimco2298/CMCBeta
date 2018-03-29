package entities;
import org.junit.*;

public class AdminTest {

	private Admin adminTest;
	
	@Before
	public void init() {
		String username = "USERNAME";
		String password = "PASSWORD";
		char active = 'Y';
		String firstname = "FIRSTNAME";
		String lastname = "LASTNAME";
		this.adminTest = new Admin(username, password, active, firstname, lastname);
	}
	
	@Test
	public void testGetDetails() {
		String details = adminTest.getDetails();
		Assert.assertTrue("Error: Details are not shown", !details.equals(null));
	}

	@Test
	public void testAdmin() {
		Assert.assertTrue("Error: Username does not exist", !adminTest.getUsername().equals(null));
		Assert.assertTrue("Error: Password does not exist", !adminTest.getPassword().equals(null));
		Assert.assertTrue("Error: Active is set as deactivated", adminTest.getActive() != 'N');
		Assert.assertTrue("Error: First name does not exist", !adminTest.getFirstName().equals(null));
		Assert.assertTrue("Error: Last name does not exist", !adminTest.getLastName().equals(null));
	}

}
