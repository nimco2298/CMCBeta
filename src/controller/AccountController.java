/** 
 * File: AccountController.java
 */
package controller;

import entity.*;
//import interfaces.*;

/**
 * AccountController is a class that is controlled by AccountInterface The
 * purpose of this class is to validate the login information when a GeneralUser
 * or Admin logs in.
 * 
 *
 * @author Nimco Hussein
 * @version April 10th 2018
 */
public class AccountController {
	/** an Account object that stores account information */
	private Account account;

	/** an Account object that stores account information */
	public boolean loginStatus;

	/**
	 * Log in method takes username and password and return true or false, if the
	 * information belongs to an account If the credentials that are added are
	 * verified, then based on the type a GeneralUser or Admin object will be
	 * created
	 * 
	 * @param username    the entered username of user attempting to login
	 *           
	 * @param password    the entered password of user attempting to login
	 *            
	 * 
	 * @throws IllegalArgumentException    if input is empty
	 *             
	 * 
	 * @return boolean          true if user was logged in, false if they were not
	 */
	public boolean login(String username, String password) {
		// find a user in the database
		DBController db = new DBController();
		Account matchedUser = db.getUser(username);

		// C1
		if (username.length() == 0 || password.length() == 0) {
			// S2
			throw new IllegalArgumentException("You must enter a non-empty String for log in credentials");
		}

		// C2
		if (matchedUser.getActive() == 'N') {
			// S2
			// System.out.println("Error: Your account is deactivated and you cannot be
			// logged in");
			this.loginStatus = false;
			// return false;
		}

		// C3
		else if (matchedUser.getUsername().equals("DummyUser")) {
			// S3
			// System.out.println("Error: The username is not registered.");
			this.loginStatus = false;
			// return false;
		}

		// C3
		else if (matchedUser.getUsername().equals(username) && !matchedUser.getPassword().equals(password)) {
			// S3
			// System.out.println("Error: The password entered does not match an account.");
			this.loginStatus = false;
			// return false;
		}

		// C4
		else if (!matchedUser.getUsername().equals(username) && matchedUser.getPassword().equals(password)) {
			// S4
			// System.out.println("Error: The username entered does not match an account");
			this.loginStatus = false;
			// return false;
		}

		// C5
		else if (matchedUser.getUsername().equals(username) && matchedUser.getPassword().equals(password)) {

			// S5
			this.account = matchedUser;

			char actualtype = account.getType(); // get the type associated with this account

			// C6
			if (actualtype == 'a') {
				// S6
				// AdminInterface ai = new AdminInterface();
				// System.out.println("Login successful");
				// ai.homepage();
				this.loginStatus = true;
				// return true;
			}
			// C7
			else if (actualtype == 'u') {
				// S7
				// UserInterface ui = new UserInterface((GeneralUser)matchedUser);
				// System.out.println("Login successful");
				this.loginStatus = true;
				// ui.homePage();
				// return true;
 
			}
		}
		// C8
		else if ((matchedUser.getUsername().equals(username) == false) && (matchedUser.getPassword().equals(password) == false)) {
			// S8
			// System.out.println("Error: The username and pasword entered doesnt match a
			// registered account. Please try again.");
			this.loginStatus = false;
			// return false;
		} 

		return loginStatus;
	}

	/**
	 * The logout method will set their account login status to false.
	 * 
	 * @return boolean true if the user was logged out, false if not
	 */
	public boolean logout() {
		if (this.loginStatus == true) {

			this.loginStatus = false;
			return true;
		}
		return false;

	}

}