package controllers;

import org.junit.*;

import org.junit.Test;

import entities.*;

public class AdminFuncControllerTest {
	private Account ac;
	private AdminFuncController ad;
	private DBController dbc = new DBController();
	
	@Before
	public void init() {
		ac = ad.getAccount("juser");
	}

	@Test
	public void testSaveAccountChanges() {
		String firstName = ac.getFirstName(); // edit the account's First name
		ac.setFirstName("FirstNameTest");
		ad.saveAccountChanges(ac); // save changes
		Assert.assertTrue("Error: Account changes has not been saved in database", ac.getFirstName().equals("FirstNameTest"));
		ac.setFirstName(firstName); // revert the First name back to original one
	}

//	@Test
//	public void testSaveUnivChanges() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetUniversity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAccount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddEmphases() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteEmphases() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEditUniversity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddUniversity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveUniversity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddAccount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEditUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeactivate() {
//		fail("Not yet implemented");
//	}

}
