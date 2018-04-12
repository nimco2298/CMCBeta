
/**
 * File: AdminFuncController

 * 
 * Description: A controller class that takes care of the "messy" parts of the CMC program for admins
 * 
 * @author Sara Laufers
 * @version February 24, 2018
 */
package controller;

import java.util.*;
import entity.*;
import interfaces.AccountInterface;

public class AdminFuncController {

	// ================================= INSTANCE VARIABLES
	// =================================
	/** An Admin class named admin */
	@SuppressWarnings("unused")
	private Admin admin;
	private AccountInterface ai = new AccountInterface();;
	private DBController dbc = new DBController();

	// ================================= CONSTRUCTORS
	// =================================
	/**
	 * Constructs a AdminFuncController which initializes several properties of an
	 * Admin
	 * 
	 * @param username
	 *            the name of the admin
	 * @param password
	 *            the admin's password
	 * @param active
	 *            the condition of whether the admin is activated or deactivated
	 * @param firstName
	 *            the first name of the admin
	 * @param lastName
	 *            the last name of the admin
	 * @param type
	 *            the type of user (in this case it is an admin 'a')
	 */
	public AdminFuncController(String username, String password, char active, String firstName, String lastName,
			char type) {
		this.admin = new Admin(username, password, active, firstName, lastName);
	}

	public AdminFuncController() {
	}

	// ================================= METHODS =================================

//	/**
//	 * View a list of users; gets a list of users from the database
//	 */
//	public void viewUsersList() {
//		ArrayList<Account> accountList = dbc.getAccounts();
//		System.out.println(
//				"========================================================================================================================"
//						+ '\n' + "First" + '\t' + '\t' + "Last" + '\t' + '\t' + "Username" + '\t' + '\t' + "Password"
//						+ '\t' + '\t' + "Type" + '\t' + '\t' + "Status" + '\n'
//						+ "========================================================================================================================");
//		for (Account user : accountList) {
//			System.out.println(user.getFirstName() + '\t' + '\t' + user.getLastName() + '\t' + '\t' + user.getUsername()
//					+ '\t' + '\t' + '\t' + user.getPassword() + '\t' + '\t' + '\t' + user.getType() + '\t' + '\t'
//					+ user.getActive());
//		}
//	}
//
//	/**
//	 * View a list of university names; gets a list of universities from the
//	 * database
//	 */
//	public void viewUniversitiesList() {
//		ArrayList<University> list = dbc.getUniversities();
//		System.out.println("=======================================" + '\n' + "             SchoolNames" + '\n'
//				+ "=======================================");
//		for (University u : list) {
//			System.out.println(u.getName());
//		}
//
//	}

//	/**
//	 * View the university's details
//	 * 
//	 * @param univName
//	 *            the name of the university
//	 */
//	public void viewUniversityDetails(String univName) {
//		University u = dbc.getUniversity(univName);
//		System.out.println("=======================================" + '\n' + '\t' + univName + " Details" + '\n'
//				+ "=======================================" + '\n' + "State: " + u.getState() + '\n' + "Location: "
//				+ u.getLocation() + '\n' + "Control: " + u.getControl() + '\n' + "# of Students: " + u.getStudents()
//				+ '\n' + "% Females: " + u.getFemPerc() + '\n' + "SAT Verbal: " + u.getSatV() + '\n' + "SAT Math: "
//				+ u.getSatM() + '\n' + "Expenses: " + u.getCost() + '\n' + "% with Financial Aid: " + u.getFinAidPerc()
//				+ '\n' + "# of Applicants: " + u.getApplicants() + '\n' + "% Admitted: " + u.getAdmitted() + '\n'
//				+ "% Enrolled: " + u.getEnrolled() + '\n' + "Academic Scale (1-5): " + u.getAcadScale() + '\n'
//				+ "Social Scale (1-5): " + u.getSocScale() + '\n' + "Quality of Life Scale (1-5): " + u.getQualScale());
//		ArrayList<String> emphases = u.getEmphases();
//		System.out.println("Emphases:");
//		for (String emphasis : emphases) {
//			System.out.println('\t' + emphasis);
//		}
//		// viewUniversities();
//	}

	/**
	 * Accesses the Database and saves changes made to an account
	 * 
	 * @param account  the account of the user
	 *           
	 */
	public void saveAccountChanges(Account account) {
		dbc.updateAccount(account);
	}

	/**
	 * Save changes made to a university
	 * 
	 * @param univ the university
	 *            
	 */
	public void saveUnivChanges(University univ) {
		// takes the database's university and deletes all the old emphases
		University univDB = getUniversity(univ.getName());
		deleteEmphases(univDB);

		// adds all new emphases from the edited university into the database
		addEmphases(univ);
		dbc.updateUniversity(univ);
	}

	/**
	 * Deletes a university and its emphases
	 * 
	 * @param univ    the university to be deleted
	 *           
	 */
	public void delete(University univ) {
		if (!(getUniversity(univ.getName()) instanceof University)) {// if the university does not exist
			throw new IllegalArgumentException();
		}
		deleteEmphases(univ);
		dbc.deleteUniversity(univ);
	}

	/**
	 * Finds a university based on name
	 * 
	 * @param univ   the name of the university
	 *            
	 * @return the university
	 */
	public University getUniversity(String univ) throws NullPointerException {
		return dbc.getUniversity(univ);
	}

	/**
	 * Finds an account based on username
	 * 
	 * @param account  the username of the requested account
	 *            
	 * @return the user
	 */
	public Account getAccount(String account) throws NullPointerException {
		return dbc.getUser(account);
	}

	/**
	 * Adds all emphases to a university in the database
	 * 
	 * @param univ   the University
	 *            
	 */
	public void addEmphases(University univ) {
		if (!(getUniversity(univ.getName()) instanceof University)) {// if the university does not exist
			throw new IllegalArgumentException();
		}
		for (String emphasis : univ.getEmphases()) {
			dbc.addEmphasis(univ, emphasis);
		}
	}

	/**
	 * Deletes all emphases from a university in the database
	 * 
	 * @param univ   the University
	 *           
	 */
	public void deleteEmphases(University univ) {
		if (!(getUniversity(univ.getName()) instanceof University)) {// if the university does not exist
			throw new IllegalArgumentException();
		}
		for (String emphasis : univ.getEmphases()) {
			dbc.deleteEmphasis(univ, emphasis);
		}
	}

	// ======================================= METHODS CALLED FROM AdminInterface
	// ========================================
	/**
	 * Brings the admin to their homepage.
	 * 
	 * @param  prompt 
	 * @return a message of which page the user will be directed to next.
	 * @throws IllegalArgumentException   the user input an invalid prompt.
	 *             
	 */
	public String homepage(String prompt) {
		if (prompt.equals("1")) { // Manage universities
			return "*** Going to Manage_Universities page. ***";
		} else if (prompt.equals("2")) { // Manage users
			return "*** Going to Manage_Users page. ***";
		} else if (prompt.equals("3")) { // Logout
			return "*** Logging out. ***";
		} else { // invalid input
					// System.out.println("Error: Invalid input for prompt. Enter either 1 (view
					// universities), 2 (view users), or 3 (log out)");
			throw new IllegalArgumentException();
		}
	}


	/**
	 * Prompts the user to edit a university's fields and then save the changes to
	 * the database
	 * 
	 * @param univName
	 * @param state
	 * @param location
	 * @param control
	 * @param students
	 * @param femPerc
	 * @param satv
	 * @param satm
	 * @param cost
	 * @param finAidPerc
	 * @param applicants
	 * @param admitted
	 * @param enrolled
	 * @param acadScale
	 * @param socScale
	 * @param qualScale
	 * @param emphases
	 */
	public void editUniversity(String univName, String state, String location, String control, int students,
			int femPerc, int satv, int satm, int cost, int finAidPerc, int applicants, int admitted, int enrolled,
			int acadScale, int socScale, int qualScale, ArrayList<String> emphases) {

		// ========================= Fail check: the university does not exist in the
		// database =======================
		if (!(this.getUniversity(univName) instanceof University)) {
			//System.out.println("*** University " + univName + " does not exist in the database ***");
			throw new IllegalArgumentException();
		}
		University u = this.getUniversity(univName);
		// ============================ Fail checks: check if all field inputs are
		// correct ===========================
		// state must be a string
		if (state.length() == 0) {
			throw new IllegalArgumentException("Error: The state is empty.");
		}
		u.setState(state);
		// location can be {SUBURBAN, URBAN, SMALL-CITY, -1(unknown/blank)}
		if (location.length() == 0) {
			u.setLocation("-1");
		} else if (!location.equals("SUBURBAN") && !location.equals("URBAN") && !location.equals("SMALL-CITY")) {
			throw new IllegalArgumentException(
					"Error: The location must be SUBURBAN, URBAN, or SMALL-CITY. It can be left empty if unknown.");
		} else {
			u.setLocation(location);
		}
		// control can be {PRIVATE, STATE, CITY, -1(unknown/blank)}
		if (control.length() == 0) {
			u.setControl("-1");
		} else if (!control.equals("PRIVATE") && !control.equals("STATE") && !control.equals("CITY")) {
			throw new IllegalArgumentException(
					"Error: The control must be PRIVATE, STATE, or CITY. It can be left empty if unknown.");
		} else {
			u.setControl(control);
		}
		// students must be an integer; cannot be negative
		if (students < 0) {
			throw new IllegalArgumentException("Error: The number of students is not in range.");
		}
		u.setStudents(students);
		// femPerc must be an integer between 0 and 100
		if (femPerc < 0 || femPerc > 100) {
			throw new IllegalArgumentException("Error: The female percentage must be between 0 and 100.");
		}
		u.setFemPerc(femPerc);
		// SATV must be between 0 and 800
		if (satv < 0 || satv > 800) {
			
			throw new IllegalArgumentException("Error: The SAT verbal score must be between 0 and 800.");
		}
		u.setSatV(satv);
		// SATM must be between 0 and 800
		if (satm < 0 || satm > 800) {
			
			throw new IllegalArgumentException("Error: The SAT math score must be between 0 and 800");
		}
		u.setSatM(satm);
		// cost must be an integer; cannot be negative
		if (cost < 0) {
			
			throw new IllegalArgumentException("Error: The cost is not in range.");
		}
		u.setCost(cost);
		// financial aid must be between 0 and 100
		if (finAidPerc < 0 || finAidPerc > 100) {
			
			throw new IllegalArgumentException("Error: The financial aid percentage must be between 0 and 100.");
		}
		u.setFinAidPerc(finAidPerc);
		// applicants must be an integer; cannot be negative
		if (applicants < 0) {
			
			throw new IllegalArgumentException("Error: The number of applicants is not in range.");
		}
		u.setApplicants(applicants);
		// admitted must be between 0 and 100
		if (admitted < 0 || admitted > 100) {
			
			throw new IllegalArgumentException("Error: The admitted percentage is not in range.");
		}
		u.setAdmitted(admitted);
		// enrolled must be between 0 and 100
		if (enrolled < 0 || enrolled > 100) {
			
			throw new IllegalArgumentException("Error: The enrolled percentage is not in range.");
		}
		u.setEnrolled(enrolled);
		// acadScale must be between 1 and 5
		if (acadScale < 1 || acadScale > 5) {
			
			throw new IllegalArgumentException("Error: The academic scale must be between 1 and 5");
		}
		u.setAcadScale(acadScale);
		// socScale must be between 1 and 5
		if (socScale < 1 || socScale > 5) {
			
			throw new IllegalArgumentException("Error: The social scale must be between 1 and 5.");
		}
		u.setSocScale(socScale);
		// qualScale must be between 1 and 5
		if (qualScale < 1 || qualScale > 5) {
			
			throw new IllegalArgumentException("Error: The quality scale must be between 1 and 5.");
		}
		u.setQualScale(qualScale);
		// number of emphasis is limited to 5
		if (emphases.size() > 5) { // almost never be true due to GUI but just in case for testing
			
			throw new IllegalArgumentException("Error: The number of emphases is over 5.");
		}
		// replaces u's emphases
		u.removeAllEmphases(); // remove all the emphases (not in database)
		for (String emphasis : emphases) { // add the new emphases (not in database)
			u.addEmphases(emphasis);
		}
		saveUnivChanges(u); // save university changes in database
	}

	/**
	 * Prompts the user to add a university's properties
	 *           
	 * @param schoolName
	 * @param state
	 * @param location
	 * @param control
	 * @param students
	 * @param femPerc
	 * @param satV
	 * @param satM
	 * @param cost
	 * @param finAidPerc
	 * @param applicants
	 * @param admitted
	 * @param enrolled
	 * @param acadScale
	 * @param socScale
	 * @param qualScale
	 * @param emphases
	 */
	public void addUniversity(String schoolName, String state, String location, String control, int students,
			int femPerc, int satV, int satM, int cost, int finAidPerc, int applicants, int admitted, int enrolled,
			int acadScale, int socScale, int qualScale, ArrayList<String> emphases) {
		// Fail: user entered a blank name for university
		if (schoolName.equals("")) {;
			throw new IllegalArgumentException("*** Error: University name is blank. Please enter a university name. ***");
		}
		// Fail: university already exists in database
		else if (dbc.getUniversity(schoolName) != null) {;
			throw new IllegalArgumentException("Error: This university name already exists, please choose a different one");
		}

		University u = new University(schoolName, state, location, control, students, femPerc, satV, satM, cost,
				finAidPerc, applicants, admitted, enrolled, acadScale, socScale, qualScale, emphases);
		dbc.addNewUniversity(u); // add the university to the database
		addEmphases(u);
		
	}


	/**
	 * Prompts the user to add a GeneralUser and its properties
	 * 
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param acType
	 */
	public void addAccount(String userName, String password, String firstName, String lastName, String acType) {
		
		ArrayList<String> information = new ArrayList<String>();
		if (!(dbc.getUser(userName).getUsername().equals("DummyUser")))
		{
			throw new IllegalArgumentException();
		}
		information.add(userName);
		information.add(password);
		information.add("Y");
		information.add(firstName);
		information.add(lastName);
		String type = acType;
		if (type.charAt(0) == 'a') {
			// new Admin(username, password, active, firstName, lastName)
			Admin ad = new Admin(information.get(0), information.get(1), information.get(2).charAt(0),
					information.get(3), information.get(4));
			dbc.addAccount(ad);
		} else if (type.charAt(0) == 'u') {
			// new GeneralUser(firstName, lastName, active, username, password, arrayList)
			GeneralUser gu = new GeneralUser(information.get(3), information.get(4), information.get(2).charAt(0),
					information.get(0), information.get(1), new ArrayList<String>());
			dbc.addAccount(gu);
		} else {
			throw new IllegalArgumentException("ERROR: Invalid Input; " + "The input needs to be either 'u' or 'a'");
			
		}
		
	}

	/**
	 * Prompts the user to edit an account through several options
	 *    
	 * @param user
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param status
	 * @param type
	 */
	public void editUser(Account user, String firstName, String lastName, String password, char status, char type) {
		Account account = this.getAccount(user.getUsername());		
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setPassword(password);
		account.setType(type);
		account.setActive(status);
		this.saveAccountChanges(account);
	}

	/**
	 * This method will deactivate a user's account
	 * 
	 * @param  usr      the account to be deactivated
	 * @return boolean true if the user was deactivated, false if not
	 */
	public boolean deactivate(String usr) {
		Account account = this.getAccount(usr);
       
		
		if (account.getActive() == 'Y') {
			//S2
			account.setActive('N');
			saveAccountChanges(account);
			return true;
       //C3
		} 
		else if(account.getActive() == 'N')
		{
			//S3
			return false;
		}
		else
			throw new IllegalArgumentException("ERROR: Invalid Input");

	}
}
