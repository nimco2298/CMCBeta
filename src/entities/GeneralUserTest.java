/**
 * 
 */
package entities;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author nhussein001
 *
 */
public class GeneralUserTest {

	
	private GeneralUser user;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ArrayList<String> savUnisList = new ArrayList<String>();
		
	    savUnisList.add("VASSAR");
	    savUnisList.add("MESA");
	    savUnisList.add("MESA");
	    
		user = new GeneralUser("Simon","Cloud", 'Y', "sassySimon", "simonSays1", savUnisList);
	}
	
	
	/**
	 * Test method for {@link entities.GeneralUser#GeneralUser(java.lang.String, java.lang.String, char, java.lang.String, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testGeneralUser() {
	    ArrayList<String> savUnis = new ArrayList<String>();
	    savUnis.add("UNIVERSITY OF MINNESOTA");
	    GeneralUser newUser = new GeneralUser("Salahi", "Takahashi",'Y',"sTaka", "coolKid1",savUnis);
	    newUser.getDetails();
		//fail("Not yet implemented"); // TODO
	}



	/**
	 * Test method for {@link entities.Account#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
	    assertEquals(user.getUsername(), "sassySimon");
	}

	/**
	 * Test method for {@link entities.Account#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		assertEquals(user.getPassword(), "simonSays1");
	}

	/**
	 * Test method for {@link entities.Account#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() {
		assertEquals(user.getFirstName(), "Simon");
	}

	/**
	 * Test method for {@link entities.Account#getLastName()}.
	 */
	@Test
	public void testGetLastName() {
		assertEquals(user.getLastName(), "Cloud");
	}

	/**
	 * Test method for {@link entities.Account#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(user.getType(), 'u');
	}

	/**
	 * Test method for {@link entities.Account#getActive()}.
	 */
	@Test
	public void testGetActive() {
		assertEquals(user.getActive(), 'Y');
	}

	/**USER CANNOT SET THEIR USERNAME
	 * Test method for {@link entities.Account#setUsername(java.lang.String)}.
	 */
	@Test
	public void testSetUsername() {
		//String expected = "Simon";
		//user.setUsername(expected);
		//String result = user.getUsername();
		//assertEquals("Your username is now: " + result, expected,result);
		fail("User cannot set a username."); 
	}

	/**
	 * Test method for {@link entities.Account#setPassword(java.lang.String)}.
	 */
	@Test
	public void testSetPassword() {
		String expected = "user";
		user.setPassword(expected);
		String result = user.getPassword();
		assertEquals("Your password is now: " + result, expected,result);
	}
	
	/**
	 * Fails to set empty  password
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetPasswordFails() {
		user.setPassword("");
	}

	/**
	 * Test method for {@link entities.Account#setFirstName(java.lang.String)}.
	 */
	@Test
	public void testSetFirstName() {
		String expected = "Simone";
		user.setFirstName(expected);
		String result = user.getFirstName();
		assertEquals("Your first name is now: " + result, expected,result);
	}
	/**
	 * Fails to set empty first name
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetFirstNameFails() {
		user.setFirstName("");
	}

	/**
	 * Test method for {@link entities.Account#setLastName(java.lang.String)}.
	 */
	@Test
	public void testSetLastName() {
		String expected = "Claudius";
		user.setLastName(expected);
		String result = user.getLastName();
		assertEquals("Your last name is now: " + result, expected,result);
	}
	
	/**
	 * Fails to set empty last name 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetLastNameFails() {
		user.setLastName("");
	}

	/**USER CANNOT SET THEIR TYPE,ADMINS CAN MODIFY TYPE
	 * Test method for {@link entities.Account#setType(char)}.
	 */
	@Test
	public void testSetType() {
		//user.setType('Y');
	    char expected = 'a';
		user.setType(expected);
		char result = user.getType();
		
		assertEquals("Your password is no: " + result, expected,result);
		//fail("User canno"); // TODO
	}

	/**USER CANNOT SET THEIR ACTIVE
	 * Test method for {@link entities.Account#setActive(char)}.
	 */
	@Test
	public void testSetActive() {
		fail("USER CANNOT SET THEIR ACTIVE"); // TODO
	}

}
