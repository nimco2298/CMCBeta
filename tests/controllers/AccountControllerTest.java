package controllers;

import controller.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



/**
 * @author nhussein001
 *
 */
public class AccountControllerTest {

	private AccountController ac;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ac = new AccountController();
	}

	/**
	 * Test method for login fails due to invalid user input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLogin_InvalidInput() {
		String expectedUser = "";
		String expectedPass = "";
		ac.login(expectedUser,expectedPass);
		
	}
	
	/**
	 * Test method for login fails for a deactivated user.
	 */
	@Test
	public void testLogin_DeactivatedUser() {
		String expectedUser = "Ian";
		String expectedPass = "Ian";
		ac.login(expectedUser,expectedPass);
		
	}

	/**
	 * Test method for login fails for invalid usernam,and a valid password
	 */
	@Test
	public void testLogin_InvalidUserValidPass() {
		String expectedUser = "lolaa982";
		String expectedPass = "user";
		boolean loginvalue = ac.login(expectedUser,expectedPass);
		assertEquals("Your login status is: " + loginvalue, ac.loginStatus, loginvalue);
			
	}
	
	/**
	 * Test method for login fails for invalid username,and a valid password
	 */
	@Test
	public void testLogin_InvalidPassValidUser() {
		String expectedUser1 = "nadmin";
		String expectedPass1 = "oooooo14";
		boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
		assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);
	}	
	
	/**
	 * Test method for login in as an Admin
	 */
	@Test
	public void testLogin_ValidPassValidAdmin() {
		String expectedUser1 = "nadmin";
		String expectedPass1 = "admin";
		boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
		assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);
		
		
	}
	
	 /** Test method for login in as an User
	 */
	@Test
	public void testLogin_ValidPassValidUser() {
		String expectedUser1 = "luser";
		String expectedPass1 = "user";
		boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
		assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);
		
	}
	
	 /** Test method for login in as an User
		 */
		@Test
	public void testLogin_UnregisteredAccount() {
	    String expectedUser1 = "invalidme";
		String expectedPass1 = "unvalid123";
		boolean loginvalue1 = ac.login(expectedUser1,expectedPass1);
		assertEquals("Your login status is: " + loginvalue1, ac.loginStatus, loginvalue1);
	
		}

	/**
	 * Test method for {@link controllers.AccountController#logout()}.
	 */
	@Test
	public void testLogout() {
		String expectedUser1 = "juser";
		String expectedPass1 = "user";
		boolean loginvalue = ac.login(expectedUser1,expectedPass1);
		ac.logout();
		assertEquals("Your login status was : " + loginvalue + ". Now, it is ", ac.loginStatus, false);	
		
	}
	
	


}
