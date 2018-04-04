
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

public class AdminFuncController{
  
  // ================================= INSTANCE VARIABLES =================================
  /** An Admin class named admin */
  @SuppressWarnings("unused")
  private Admin admin;
  private AccountInterface ai = new AccountInterface();;
  private DBController dbc = new DBController();
  
  // ================================= CONSTRUCTORS =================================
  /**
   * Constructs a AdminFuncController which initializes several properties of an Admin
   * 
   * @param username  the name of the admin
   * @param password  the admin's password
   * @param active    the condition of whether the admin is activated or deactivated
   * @param firstName the first name of the admin
   * @param lastName  the last name of the admin
   * @param type      the type of user (in this case it is an admin 'a')
   */
  public AdminFuncController(String username, String password, char active, String firstName, String lastName, char type){
    this.admin = new Admin(username, password, active, firstName, lastName);
  }
  
  public AdminFuncController(){
  }
  
  
  // ================================= METHODS =================================
  
  /**
   * View a list of users; gets a list of users from the database
   */
  public void viewUsersList(){
    ArrayList<Account> accountList = dbc.getAccounts();
    System.out.println("========================================================================================================================" +'\n'+
    					"First" + '\t'+'\t' + "Last" + '\t'+'\t' + "Username" + '\t'+'\t' + "Password" + '\t'+'\t' + "Type" + '\t'+'\t' + "Status" +'\n'+ 
                       	"========================================================================================================================");
    for(Account user: accountList){
      System.out.println(user.getFirstName()+'\t'+'\t'+user.getLastName()+'\t'+'\t'+user.getUsername()+'\t'+'\t'+'\t'+
                         user.getPassword()+'\t'+'\t'+'\t'+user.getType()+'\t'+'\t'+user.getActive());
    }
  }
  
  /**
   * View a list of university names; gets a list of universities from the database
   */
  public void viewUniversitiesList(){
    ArrayList<University> list = dbc.getUniversities();
    System.out.println("=======================================" + '\n' +
    				   "             SchoolNames" + '\n' + 
    				   "=======================================");
    for(University u: list){
      System.out.println(u.getName());
    }
    
  }
  
  /**
   * View the university's details
   * 
   * @param univName the name of the university
   */
  public void viewUniversityDetails(String univName){
    University u = dbc.getUniversity(univName);
    System.out.println("=======================================" 			+'\n'+'\t'+
    				    univName + " Details"							 	+'\n'+ 
    				   "=======================================" 			+'\n'+
    				   "State: " + u.getState() 							+'\n'+
    				   "Location: " + u.getLocation() 						+'\n'+
    				   "Control: " + u.getControl() 						+'\n'+
    				   "# of Students: " + u.getStudents() 					+'\n'+
    				   "% Females: " + u.getFemPerc() 						+'\n'+
    				   "SAT Verbal: " + u.getSatV() 						+'\n'+
    				   "SAT Math: " + u.getSatM() 							+'\n'+
    				   "Expenses: " + u.getCost() 							+'\n'+
    				   "% with Financial Aid: " + u.getFinAidPerc() 		+'\n'+
    				   "# of Applicants: " + u.getApplicants() 				+'\n'+
    				   "% Admitted: " + u.getAdmitted() 					+'\n'+
    				   "% Enrolled: " + u.getEnrolled() 					+'\n'+
    				   "Academic Scale (1-5): " + u.getAcadScale() 			+'\n'+
    				   "Social Scale (1-5): " + u.getSocScale() 			+'\n'+
    				   "Quality of Life Scale (1-5): " + u.getQualScale() 	);
    ArrayList<String> emphases = u.getEmphases();
    System.out.println("Emphases:");
    for(String emphasis: emphases) {
    	System.out.println('\t'+emphasis);
    }
    //viewUniversities();
  }
  
  /**
   * Accesses the Database and saves changes made to an account
   * 
   * @param account the account of the user
   */
  public void saveAccountChanges(Account account){
    dbc.updateAccount(account);
  }
  
  
  /**
   * Save changes made to a university
   * 
   * @param univ the university
   */
  public void saveUnivChanges(University univ){
	  University univDB = getUniversity(univ.getName()); // deletes all the old emphases from university in the database 
	  deleteEmphases(univDB);
	  addEmphases(univ); // adds all new emphases from the university into the database
	  dbc.updateUniversity(univ);
  }
  
  /**
   * Deletes a university and its emphases
   * 
   * @param univ the university to be deleted
   */
  public void delete(University univ){
	deleteEmphases(univ);
    dbc.deleteUniversity(univ);
  }
  
  /**
   * Finds a university based on name
   * 
   * @param univ the name of the university
   * @return the university
   */
  public University getUniversity(String univ) throws NullPointerException
  {
	  return dbc.getUniversity(univ);
  }
  
  /**
   * Finds an account based on username
   * 
   * @param account the username of the requested account
   * @return the user
   */
  public Account getAccount(String account) throws NullPointerException
  {
	  return dbc.getUser(account);
  }
  
  
  
  /**
   * Adds all emphases to a university in the database
   * 
   * @param univ the University
   */
  public void addEmphases(University univ) {
	  for(String emphasis: univ.getEmphases()) {
		  dbc.addEmphasis(univ, emphasis);
	  }
  }
  
  /**
   * Deletes all emphases from a university in the database
   * 
   * @param univ the University
   */
  public void deleteEmphases(University univ) {
	  for(String emphasis: univ.getEmphases()) {
		  dbc.deleteEmphasis(univ, emphasis);
	  }
  }
  
  //======================================= METHODS CALLED FROM AdminInterface ========================================
  /**
   * Brings the admin to their homepage.
   * 
   * @return a message of which page the user will be directed to next.
   * @throws IllegalArgumentException the user input an invalid prompt.
   */
  public String homepage(String prompt) {
	  if(prompt.equals("1")){ // Manage universities
		  return "*** Going to Manage_Universities page. ***";
	  }
	  else if(prompt.equals("2")){ // Manage users
		  return "*** Going to Manage_Users page. ***";
	  }
	  else if(prompt.equals("3")){ // Logout
		  return "*** Logging out. ***";
	  }
	  else{ // invalid input
		  //System.out.println("Error: Invalid input for prompt. Enter either 1 (view universities), 2 (view users), or 3 (log out)");
	      throw new IllegalArgumentException();
	  }
  }
  

  
  /**FROM USER INTERFACEEE
=======
//  public String viewUniversities(String prompt, String univName) {
//	  //show the list of universities
//	  this.viewUniversitiesList();
//	  //check for input
//	  if(prompt.equals("a")){ // ADD UNIVERSITIES
//		  return "*** Adding University ***";
//		  //this.addUniversity(univName);
//	  }
//	  else if(prompt.equals("e")){ // EDIT UNIVERSITIES
//		  
//		  //this.editUniversity(editPrompt, this.getUniversity(univName));
//	  }
//	  else if(cmd.equals("r")) { // REMOVE UNIVERSITY
//		  if(!(this.getUniversity(univName) instanceof University)) {// if the university does not exist
//			  System.out.println("*** There is no such university ***");
//			  this.viewUniversities();
//		  }
//  	  		this.removeUniversity(this.getUniversity(univName));
//	  }
//	  else if(cmd.equals("d")||cmd.equals("D")) { // SHOW DETAILS OF A UNIVERSITY
//		  if(!(this.getUniversity(univ) instanceof University)) {// if the university does not exist
//			  System.out.println("*** There is no such university ***");
//			  this.viewUniversities();
//		  }
//		  this.viewUniversityDetails(univ);
//	  }
//	  else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
//		  System.out.println("Returning to homepage");
//	  }
//	  else{ // INPUT ERROR
//		  System.out.println("ERROR: Invalid input");
//		  viewUniversities();
//	  }	
//  }
//  
  /**
>>>>>>> 6e73d2894a0ee7d9354f51b7a6f7b50ed667bd1e
   * Shows a list of all users in the system (both general and admin)
   * and presents a list of options for the admin to manage those
   * users.
   */
//  public String viewUsers(String prompt, String userName) {
//	this.viewUsersList();
//    System.out.print("======================================="					+'\n'+
//    					"Would you like to add, edit, or deactivate a user?" 	+'\n'+'\t'+
//                          "a: Add User" 										+'\n'+'\t'+
//                          "e: Edit User" 										+'\n'+'\t'+
//                          "d: Deactivate User" 									+'\n'+'\t'+
//                          //"r: Remove User" 										+'\n'+'\t'+
//                          "q: Quit (Return to Homepage)" 						+'\n'+
//                         "Enter Here: ");
//    
//    if(prompt.equals("a")){ // ADD USER
//    	System.out.print("=======================================" +'\n'+"Enter Username: ");
//        //String userName = sc.nextLine();
//        if(userName.length()==0) {//if the userName is empty
//          throw new IllegalArgumentException() ;
//          //this.viewUsers();
//        }
//        else if(!this.getAccount(userName).getUsername().equals("DummyUser")) {//if the username already exists
//          return "*** This user name already exists, please choose a different one ***";
//          this.viewUsers();
//        }
//        this.addAccount(userName);
//    }
//    else if(cmd.equals("e")){ // EDIT USER
//    	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
//      	String userName = sc.nextLine();
//      	if(this.getAccount(userName).getUsername().equals("DummyUser")) {//if the username does not exist
//      		System.out.println("*** There is no such user ***");
//      		this.viewUsers();
//      	}
//      	this.editUser(this.getAccount(userName));
//    }
//    else if(cmd.equals("d")){ // DEACTIVATE USER
//    	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
//      	String userName = sc.nextLine();
//      	if(this.getAccount(userName).getUsername().equals("DummyUser")) {//if the username does not exist
//      		System.out.println("ERROR: There is no such user");
//      		this.viewUsers();
//      	}
//      	this.deactivate(this.getAccount(userName));
    //}
//    else if(cmd.equals("r")){ // REMOVE USER (TESTING PURPOSE ONLY)
//    	System.out.print("=======================================" +'\n'+ "Enter Username (check name!!!): ");
//        String univ = sc.nextLine();
//        dbc.deleteAccount(this.getAccount(univ));
//        viewUsers();
//    }
//    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
//    	homepage();
//    }
//    else{ // INPUT ERROR
//    	System.out.println("ERROR: Invalid input");
//    	viewUsers();
//    }
  //}
  
  
  /**
   * Prompts the user to edit a university's fields
   * and then save the changes to the database
   * 
   * 
   */
  
  public void editUniversity(String univName, String state, String location, String control, int students,
		  int femPerc, int satv, int satm, int cost, int finAidPerc, int applicants, int admitted, int enrolled, 
		  int acadScale, int socScale, int qualScale, ArrayList<String> emphases) {
	  
	  //========================= Fail check: the university does not exist in the database =======================
	  if(!(this.getUniversity(univName) instanceof University)) {
		  System.out.println("*** University " + univName + " does not exist in the database ***");
		  throw new IllegalArgumentException();
	  }
	  University u = this.getUniversity(univName);
	  //============================ Fail checks: check if all field inputs are correct ===========================
	  //state must be a string
	  if(state.length() == 0) {
		  throw new IllegalArgumentException("Error: The state is empty.");
	  }
	  u.setState(state);
	  //location can be {SUBURBAN, URBAN, SMALL-CITY, -1(unknown/blank)}
	  if(!location.equals("SUBURBAN") && !location.equals("URBAN") && !location.equals("SMALL-CITY")) {
		  throw new IllegalArgumentException("Error: The location must be SUBURBAN, URBAN, or SMALL-CITY. It can be left empty if unknown.");
	  }
	  else if(location.length() == 0) {
		  u.setLocation("-1");
	  }
	  else {
		  u.setLocation(location);
	  }
	  //control can be {PRIVATE, STATE, CITY, -1(unknown/blank)}
	  if(!control.equals("PRIVATE") && !control.equals("STATE") && !control.equals("CITY")) {
		  throw new IllegalArgumentException("Error: The control must be PRIVATE, STATE, or CITY. It can be left empty if unknown.");
	  }
	  else if(control.length() == 0) {
		  u.setControl("-1");
	  }
	  else {
		  u.setControl(control);
	  }
	  //students must be an integer; cannot be negative
	  if(students < 0) {
		  throw new IllegalArgumentException("Error: The number of students is not in range.");
	  }
	  u.setStudents(students);
	  //femPerc must be an integer between 0 and 100
	  if(femPerc < 0 || femPerc > 100) {
		  throw new IllegalArgumentException("Error: The female percentage must be between 0 and 100.");
	  }
	  u.setFemPerc(femPerc);
	  //SATV must be between 0 and 800
	  if(satv < 0 || satv > 800) {
		  System.out.println("Error: The SAT verbal score must be between 0 and 800.");
		  throw new IllegalArgumentException();
	  }
	  u.setSatV(satv);
	  //SATM must be between 0 and 800
	  if(satm < 0 || satm > 800) {
		  System.out.println("Error: The SAT math score must be between 0 and 800");
		  throw new IllegalArgumentException();
	  }
	  u.setSatM(satm);
	  //cost must be an integer; cannot be negative
	  if(cost < 0) {
		  System.out.println("Error: The cost is not in range.");
		  throw new IllegalArgumentException();
	  }
	  u.setCost(cost);
	  //financial aid must be between 0 and 100
	  if(finAidPerc < 0 || finAidPerc > 100) {
		  System.out.println("Error: The financial aid percentage must be between 0 and 100.");
		  throw new IllegalArgumentException();
	  }
	  u.setFinAidPerc(finAidPerc);
	  // applicants must be an integer; cannot be negative
	  if(applicants < 0) {
		  System.out.println("Error: The number of applicants is not in range.");
		  throw new IllegalArgumentException();
	  }
	  u.setApplicants(applicants);
	  // admitted must be between 0 and 100
	  if(admitted < 0 || admitted > 100) {
		  System.out.println("Error: The admitted percentage is not in range.");
		  throw new IllegalArgumentException();
	  }	
	  u.setAdmitted(admitted);
	  // enrolled must be between 0 and 100
	  if(enrolled < 0 || enrolled > 100) {
		  System.out.println("Error: The enrolled percentage is not in range.");
		  throw new IllegalArgumentException();
	  }	
	  u.setEnrolled(enrolled);
	  // acadScale must be between 1 and 5
	  if(acadScale < 1 || acadScale > 5) {
		  System.out.println("Error: The academic scale must be between 1 and 5");
		  throw new IllegalArgumentException();
	  }	
	  u.setAcadScale(acadScale);
	  // socScale must be between 1 and 5
	  if(socScale < 1 || socScale > 5) {
		  System.out.println("Error: The social scale must be between 1 and 5.");
		  throw new IllegalArgumentException();
	  }	
	  u.setSocScale(socScale);
	  // qualScale must be between 1 and 5
	  if(qualScale < 1 || qualScale > 5) {
		  System.out.println("Error: The quality scale must be between 1 and 5.");
		  throw new IllegalArgumentException();
	  }	
	  u.setQualScale(qualScale);
	  // number of emphasis is limited to 5
	  if(emphases.size() > 5) { // almost never be true due to GUI but just in case for testing
		  System.out.println("Error: The number of emphases is over 5.");
		  throw new IllegalArgumentException();
	  }
	  saveUnivChanges(u);
  }

  /**
   * Prompts the user to add a university's properties
   * 
   * @param univ the name of the university to add
   */
  public void addUniversity(String schoolName, String state, String location, String control, int students, int femPerc, int satV, int satM, int cost,
          int finAidPerc, int applicants, int admitted, int enrolled, int acadScale, int socScale, int qualScale, ArrayList<String> emphases) {
	//Fail: user entered a blank name for university
	  if(schoolName.equals("")) {
		  //System.out.println("*** Error: University name is blank. Please enter a university name. ***");
		  throw new IllegalArgumentException();
	  }
	  //Fail: university already exists in database
	  else if(this.getUniversity(schoolName) instanceof University) {
		  //System.out.println("*** This university name already exists, please choose a different one ***");
		  throw new IllegalArgumentException();
	  }

	  University u = new University(schoolName, state, location, control, students, femPerc, satV, satM, cost,
  								finAidPerc, applicants, admitted, enrolled, acadScale, socScale, qualScale, emphases);
	  dbc.addNewUniversity(u); // add the university to the database
	  addEmphases(u);
	  //System.out.println("*** Saved university " + schoolName + " to list ***");
  }

  /**
   * Prompts the user to remove a university and confirm deletion
   * 
   * @param u the university to remove
   */
  public void removeUniversity(University u) {
	  //System.out.println("=======================================" +'\n'+ "Are you sure you want to delete " + u.getName() + " from the list?" 	
			  			//	+'\n'+'\t'+ "y: yes" +'\n'+'\t'+ "n: no"+ '\n' + "Enter Here: ");
		  delete(u);
  }
  /**
   * Prompts the user to add a GeneralUser and its properties
   * 
   * @param userName the name of the GeneralUser to add
   */
  public void addAccount(String userName, String password, String firstName, String lastName, String acType ) {
      ArrayList<String> information = new ArrayList<String>();
	  information.add(userName);
	  information.add(password);
	  information.add("Y");
	  information.add(firstName);
	  information.add(lastName);
	  String type = acType;
	  if(type.charAt(0) == 'a'){
		  // new Admin(username, password, active, firstName, lastName)
		  Admin ad = new Admin(information.get(0),  information.get(1),  information.get(2).charAt(0),  information.get(3),  information.get(4));
		  dbc.addAccount(ad);
	  }
	  else if(type.charAt(0) == 'u'){
		  // new GeneralUser(firstName, lastName, active, username, password, arrayList)
		  GeneralUser gu = new GeneralUser(information.get(3), information.get(4), information.get(2).charAt(0), information.get(0), information.get(1), new ArrayList<String>());
		  dbc.addAccount(gu);
	  }
	  else {
		  System.out.println("ERROR: Invalid Input; " + "The input needs to be either 'u' or 'a'");
	  }
	  viewUsers();
  }
  
  /**
   * Prompts the user to edit an account through several options
   * 
   * @param user the account to edit
   */
  public void editUser(Account user) {
	  String prompt = "";
	  do {
		  System.out.print("======================================="	+ '\n' +
							"What would you like to edit:" 			+ '\n' +'\t'+
								"1: FirstName" 						+ '\n' +'\t'+
								"2: LastName" 						+ '\n' +'\t'+
								"3: Password" 						+ '\n' +'\t'+
								"4: Type" 							+ '\n' +'\t'+
								"5: Status" 						+ '\n' +'\t'+
	            				"s: Save"							+ '\n' +'\t'+
	            				"c: Cancel"							+ '\n' +
	            			"Enter Here: ");
		  prompt = sc.nextLine();
	      switch (prompt){
	      	case "1":
	          System.out.print("Enter the new first name: ");
	          user.setFirstName(sc.nextLine());
	          break;
	        case "2":
	          System.out.print("Enter the new last name: ");
	          user.setLastName(sc.nextLine());
	          break;
	        case "3":
	          System.out.print("Enter the new password: ");
	          user.setPassword(sc.nextLine());
	          break;
	        case "4":
	          System.out.print("Enter the type (a=admin, u=general user): ");
	          char type = sc.nextLine().charAt(0);
	          if(type != 'a' && type != 'u') {
	        	  System.out.println("ERROR: Invalid input");
	          }
	          else {
	        	  user.setType(type);
	          }
	          break;
	        case "5":
	          System.out.print("Enter the status (Y=active, N=deactive): ");
	          char status = sc.nextLine().charAt(0);
	          if(status != 'Y' && status != 'N') {
	        	  System.out.println("ERROR: Invalid input; inputs must be capitalized");
	          }
	          else {
	        	  user.setActive(status);
	          }
	          break;
	        case "s":
	          saveAccountChanges(user);
	          System.out.println("======================================="	+ '\n' +
	        		  			"Updates have been saved:" + '\n' + '\t'+
	            					"FirstName: " + user.getFirstName() + '\n' + '\t'+
	            					"LastName: " + user.getLastName() + '\n' + '\t'+
	            					"Password: " + user.getPassword() + '\n' + '\t'+
	            					"Type: " + user.getType() + '\n' + '\t'+
	            					"Active: " + user.getActive());
	          break;
	        case "c":
	          System.out.println("*** Returning to Manage_Users page ***");
	          break;
	        default:
	          System.out.println("ERROR: Invalid input");
	          break;
	      }
	    } while(!prompt.equals("s")&&!prompt.equals("c"));
	  viewUsers();
  }
  /**
   * Prompts the user to deactivate an account
   * 
   * @param usr the account to deactivate
   */
  public void deactivate(Account usr) {
	  System.out.print("======================================="				+ '\n' +
				"Are you sure you want to deactivate this user?" 	+ '\n' + '\t'+ 
					"y: yes" 										+ '\n' + '\t'+ 
					"n: no"											+ '\n' + 
				"Enter Here: ");
	  String prompt = sc.nextLine();
	  if(prompt.equals("y") || prompt.equals("Y")){
		  if(usr.getActive() == 'Y'){
			  System.out.println("*** "+ usr.getFirstName() + " has been deactivated ***");
			  usr.setActive('N');
			  saveAccountChanges(usr);
		  }
		  else {
			  System.out.println("*** "+ usr.getFirstName() + " is already deactivated ***");
		  }
	  }
	  else if(prompt.equals("n") || prompt.equals("N")) {
		  System.out.println("*** Returning to Manage_Users page ***");
	  }
	  else {
		  System.out.println("ERROR: Invalid Input");
	  }
	  viewUsers();
  }
}
