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
		
		//user.setUsername("supremeSimon");
	    //user.setFirstName("Simone");
	    //user.setLastName("Cloudy");
	    savUnisList.add("VASSAR");
	    savUnisList.add("MESA");
	    savUnisList.add("MESA");
	    
		user = new GeneralUser("Simon","Cloud", 'Y', "sassySimon", "simonSays1", savUnisList);
	}
	
	/**
	 * Test method for {@link entities.GeneralUser#getDetails()}.
	 */
	@Test
	public void testGetDetails() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.GeneralUser#GeneralUser(java.lang.String, java.lang.String, char, java.lang.String, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testGeneralUser() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.GeneralUser#getSavedSchools()}.
	 */
	@Test
	public void testGetSavedSchools() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#getLastName()}.
	 */
	@Test
	public void testGetLastName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#getType()}.
	 */
	@Test
	public void testGetType() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#getActive()}.
	 */
	@Test
	public void testGetActive() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#setUsername(java.lang.String)}.
	 */
	@Test
	public void testSetUsername() {
		String expected = "Simon";
		user.setUsername(expected);
		String result = user.getUsername();
		assertEquals("Your username is now: " + result, expected,result);
	}

	/**
	 * Test method for {@link entities.Account#setPassword(java.lang.String)}.
	 */
	@Test
	public void testSetPassword() {
		String expected = "user";
		user.setUsername(expected);
		String result = user.getPassword();
		assertEquals("Your password is now: " + result, expected,result);
	}

	/**
	 * Test method for {@link entities.Account#setFirstName(java.lang.String)}.
	 */
	@Test
	public void testSetFirstName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#setLastName(java.lang.String)}.
	 */
	@Test
	public void testSetLastName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#setType(char)}.
	 */
	@Test
	public void testSetType() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link entities.Account#setActive(char)}.
	 */
	@Test
	public void testSetActive() {
		fail("Not yet implemented"); // TODO
	}

}
