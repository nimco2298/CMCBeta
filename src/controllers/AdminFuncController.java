/**
 * File: AdminFuncController

 * 
 * Description: A controller class that takes care of the "messy" parts of the CMC program for admins
 * 
 * @author Sara Laufers
 * @version February 24, 2018
 */
package controllers;
import java.util.*;
import entities.*;
import interfaces.AccountInterface;

public class AdminFuncController{
  
  // ================================= INSTANCE VARIABLES =================================
  /** An Admin class named admin */
  @SuppressWarnings("unused")
  private Admin admin;
  private AccountInterface ai = new AccountInterface();;
  private DBController dbc = new DBController();
  Scanner sc = new Scanner(System.in);
  
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
    viewUniversities();
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
   * Brings the admin to their homepage
   */
  public void homepage() {
	  System.out.print("=======================================" 		+'\n'+ 
							"Welcome to the Admin Homepage:" 			+'\n'+'\t'+ 
								"1: Manage Universities" 				+'\n'+'\t'+
								"2: Manage Users"						+'\n'+'\t'+
								"3: Logout"								+'\n'+
							"Enter Here: ");
	  String prompt = sc.nextLine();
	  if(prompt.equals("1")){ // Manage universities
	      viewUniversities();
	  }
	  else if(prompt.equals("2")){ // Manage users
	      viewUsers();
	  }
	  else if(prompt.equals("3")){ // Manage users
	      ai.logout();
	  }
	  else{ // invalid input
		  System.out.println("ERROR: Invalid Input");
	      homepage();
	  }
  }
  
  /**
   * Shows a list of all universities in the system, and
   * presents options for the admin to manage those
   * universities.
   */
  public void viewUniversities() {
	this.viewUniversitiesList();
    System.out.print("=======================================" 			+'\n'+
    					"Would you like to add or edit Universities?" 	+'\n'+'\t'+
                       		"a: Add Universities"						+'\n'+'\t'+
                        	"e: Edit Universities"						+'\n'+'\t'+
                        	"r: Remove University"						+'\n'+'\t'+
                        	"d: Details of University"					+'\n'+'\t'+
                        	"q: Quit (Return to Homepage)"				+'\n'+
                        "Enter Here: ");
    String cmd = sc.nextLine();
    if(cmd.equals("a")){ // ADD UNIVERSITIES
    	System.out.print("=======================================" +'\n'+ "Enter University name: ");
        String univ = sc.nextLine();
        if(univ.equals("")) {//if the user entered nothing
        	System.out.println("*** Please enter a university name ***");
        	this.viewUniversities();
        }
        else if(this.getUniversity(univ) instanceof University) {// if the university already exists
        	System.out.println("*** This university name already exists, please choose a different one ***");
        	this.viewUniversities();
        }
        this.addUniversity(univ);
    }
    else if(cmd.equals("e")){ // EDIT UNIVERSITIES
    	System.out.print("=======================================" +'\n'+ "Enter University Name: ");
        String univ = sc.nextLine();
        if(!(this.getUniversity(univ) instanceof University)) {// if the university does not exist
      	  System.out.println("*** There is no such university ***");
      	  this.viewUniversities();
        }
        this.editUniversity(this.getUniversity(univ));
    }
    else if(cmd.equals("r")) { // REMOVE UNIVERSITY
    	System.out.print("=======================================" +'\n'+ "Enter University Name: ");
        String univ = sc.nextLine();
        if(!(this.getUniversity(univ) instanceof University)) {// if the university does not exist
      	  System.out.println("*** There is no such university ***");
      	  this.viewUniversities();
        }
  	  	this.removeUniversity(this.getUniversity(univ));
    }
    else if(cmd.equals("d")||cmd.equals("D")) { // SHOW DETAILS OF A UNIVERSITY
    	System.out.print("=======================================" +'\n'+ "Enter University Name: ");
        String univ = sc.nextLine();
        if(!(this.getUniversity(univ) instanceof University)) {// if the university does not exist
      	  System.out.println("*** There is no such university ***");
      	  this.viewUniversities();
        }
        this.viewUniversityDetails(univ);
    }
    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
    	this.homepage();
    }
    else{ // INPUT ERROR
      System.out.println("ERROR: Invalid input");
      viewUniversities();
    }
  }
  
  /**
   * Shows a list of all users in the system (both general and admin)
   * and presents a list of options for the admin to manage those
   * users.
   */
  public void viewUsers() {
	this.viewUsersList();
    System.out.print("======================================="					+'\n'+
    					"Would you like to add, edit, or deactivate a user?" 	+'\n'+'\t'+
                          "a: Add User" 										+'\n'+'\t'+
                          "e: Edit User" 										+'\n'+'\t'+
                          "d: Deactivate User" 									+'\n'+'\t'+
                          "r: Remove User" 										+'\n'+'\t'+
                          "q: Quit (Return to Homepage)" 						+'\n'+
                         "Enter Here: ");
    String cmd = sc.nextLine();
    if(cmd.equals("a")){ // ADD USER
    	System.out.print("=======================================" +'\n'+"Enter Username: ");
        String userName = sc.nextLine();
        if(!this.getAccount(userName).getUsername().equals("DummyUser")) {//if the username already exists
        	System.out.println("*** This user name already exists, please choose a different one ***");
            this.viewUsers();
        }
        this.addAccount(userName);
    }
    else if(cmd.equals("e")){ // EDIT USER
    	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
      	String userName = sc.nextLine();
      	if(this.getAccount(userName).getUsername().equals("DummyUser")) {//if the username does not exist
      		System.out.println("*** There is no such user ***");
      		this.viewUsers();
      	}
      	this.editUser(this.getAccount(userName));
    }
    else if(cmd.equals("d")){ // DEACTIVATE USER
    	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
      	String userName = sc.nextLine();
      	if(this.getAccount(userName).getUsername().equals("DummyUser")) {//if the username does not exist
      		System.out.println("ERROR: There is no such user");
      		this.viewUsers();
      	}
      	this.deactivate(this.getAccount(userName));
    }
    else if(cmd.equals("r")){ // REMOVE USER (TESTING PURPOSE ONLY)
    	System.out.print("=======================================" +'\n'+ "Enter Username (check name!!!): ");
        String univ = sc.nextLine();
        DBController dbc = new DBController();
        dbc.deleteAccount(this.getAccount(univ));
        viewUsers();
    }
    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
    	homepage();
    }
    else{ // INPUT ERROR
    	System.out.println("ERROR: Invalid input");
    	viewUsers();
    }
  }
  
  /**
   * Prompts the user to edit a university through several options
   * 
   * @param u the university to edit
   */
  
  public void editUniversity(University u) {
	  String prompt = "";
	  do {
		  System.out.print("=======================================" +'\n'+ "What would you like to edit:" 			+'\n'+'\t'+ 	
		      "1: state" 			+'\n'+'\t'+ "2: location" 			+'\n'+'\t'+ "3: control" 					+'\n'+'\t'+ 
		      "4: students" 		+'\n'+'\t'+ "5: female percentage" 	+'\n'+'\t'+ "6: SAT verbal score" 			+'\n'+'\t'+
		      "7: SAT math score" 	+'\n'+'\t'+ "8: cost" 				+'\n'+'\t'+ "9: financial aid percentage" 	+'\n'+'\t'+ 
		      "10: applicants" 		+'\n'+'\t'+ "11: admitted" 			+'\n'+'\t'+ "12: enrolled" 					+'\n'+'\t'+
		      "13: academic scale" 	+'\n'+'\t'+ "14: social scale" 		+'\n'+'\t'+ "15: quality scale" 			+'\n'+'\t'+ 
			  "16: add emphases" 	+'\n'+'\t'+ "17: remove emphases" 	+'\n'+'\t'+ 
			  "s: Save" 			+'\n'+'\t'+ "c: Cancel" 			+'\n'+ "Enter Here: ");
		  prompt = sc.nextLine();
		  switch (prompt){
		  	case "1":
		  		System.out.print("=======================================" +'\n'+ "Current state: " + u.getState() +'\n'+ "Enter the state: ");
		  		u.setState(sc.nextLine());
		  		break;
		  	case "2":
		  		System.out.print("=======================================" +'\n'+ "Current location: " + u.getLocation() +'\n'+ "Enter the location: ");
		  		u.setLocation(sc.nextLine());
		  		break;
		  	case "3":
		  		System.out.print("=======================================" +'\n'+ "Current control: " + u.getControl() +'\n'+ "Enter the control: ");
		  		u.setControl(sc.nextLine());
		  		break;
		  	case "4":
		  		System.out.print("=======================================" +'\n'+ "Current number of students: " + u.getStudents() +'\n'+ "Enter the number of students: ");
		  		u.setStudents(sc.nextInt());
		  		break;
	        case "5":
	        	System.out.print("=======================================" +'\n'+ "Current female percentage: " + u.getFemPerc() +'\n'+ "Enter the female percentage: ");
	        	u.setFemPerc(sc.nextInt());
	        	break;
	        case "6":
	        	System.out.print("=======================================" +'\n'+ "Current SAT verbal score: " + u.getSatV() +'\n'+ "Enter the SAT verbal score: ");
	        	u.setSatV(sc.nextInt());
	        	break;
	        case "7":
	        	System.out.print("=======================================" +'\n'+ "Current SAT math score: " + u.getSatM() +'\n'+ "Enter the SAT math score: ");
	        	u.setSatM(sc.nextInt());
	        	break;
	        case "8":
	        	System.out.print("=======================================" +'\n'+ "Current cost: " + u.getCost() +'\n'+ "Enter the cost: ");
	        	u.setCost(sc.nextInt());
	        	break;
	        case "9":
	        	System.out.print("=======================================" +'\n'+ "Current financial aid %: " + u.getFinAidPerc() +'\n'+ "Enter the financial aid %: ");
	        	u.setFinAidPerc(sc.nextInt());
	        	break;
	        case "10":
	        	System.out.print("=======================================" +'\n'+ "Current applicants: " + u.getApplicants() +'\n'+ "Enter the applicants: ");
	        	u.setApplicants(sc.nextInt());
	        	break;
	        case "11":
	        	System.out.print("=======================================" +'\n'+ "Current admitted students: " + u.getAdmitted() +'\n'+ "Enter the admitted students: ");
	        	u.setAdmitted(sc.nextInt());
	        	break;
	        case "12":
	        	System.out.print("=======================================" +'\n'+ "Current enrolled students: " + u.getEnrolled() +'\n'+ "Enter the enrolled students: ");
	        	u.setEnrolled(sc.nextInt());
	        	break;
	        case "13":
	        	System.out.print("=======================================" +'\n'+ "Current academic scale: " + u.getAcadScale() +'\n'+ "Enter the academic scale: ");
	        	u.setAcadScale(sc.nextInt());
	        	break;
	        case "14":
	        	System.out.print("=======================================" +'\n'+ "Current social scale: " + u.getSocScale() +'\n'+ "Enter the social scale: ");
	        	u.setSocScale(sc.nextInt());
	        	break;
	        case "15":
	        	System.out.print("=======================================" +'\n'+ "Current quality scale: " + u.getQualScale() +'\n'+ "Enter the quality scale: ");
	        	u.setQualScale(sc.nextInt());
	        	break;
	        case "16":
	        	System.out.println("======================================="+'\n'+ "Current emphases:");
	        	for(String emphasis: u.getEmphases()) {
	            	System.out.println('\t'+emphasis);
	            }
	        	System.out.print("Enter an emphasis to add: ");
	        	u.addEmphases(sc.nextLine());
	        	break;
	        case "17":
	        	System.out.println("======================================="+'\n'+ "Current emphases:");
	        	for(String emphasis: u.getEmphases()) {
	            	System.out.println('\t'+emphasis);
	            }
	        	System.out.print("Enter an emphasis to remove: ");
	        	u.removeEmphases(sc.nextLine());
	        	break;
	        case "s":
	        	saveUnivChanges(u);
	      	  	System.out.println("*** Saved updates to " + u.getName() + " ***");
	        	break;
	        case "c":
	      	  	System.out.println("*** Returning to Manage_University page ***");
	        	break;
	        default:
	        	System.out.println("ERROR: Invalid input");
	        	break;
		  } 
	  } while(!prompt.equals("s")&&!prompt.equals("c"));
	  viewUniversities();
  }

  /**
   * Prompts the user to add a university's properties
   * 
   * @param univ the name of the university to add
   */
  public void addUniversity(String univ) {
	  System.out.print("Enter state: ");
	  String state = sc.nextLine();
      System.out.print("Enter location: ");
	  String location = sc.nextLine();
	  System.out.print("Enter control: ");
	  String control = sc.nextLine();
	  System.out.print("Enter number of students: ");
	  int students = sc.nextInt();    
	  System.out.print("Enter female percentage: ");
	  int femPerc = sc.nextInt();
	  System.out.print("Enter SAT verbal score: ");
	  int satV = sc.nextInt();
	  System.out.print("Enter SAT math score: ");
	  int satM = sc.nextInt();
	  System.out.print("Enter cost: ");
	  int cost = sc.nextInt();
	  System.out.print("Enter financial aid percentage: ");
	  int finAidPerc = sc.nextInt();
	  System.out.print("Enter applicants: ");
	  int applicants = sc.nextInt();
	  System.out.print("Enter number of admitted students: ");
	  int admitted = sc.nextInt();
	  System.out.print("Enter number of enrolled students: ");
	  int enrolled = sc.nextInt();
	  System.out.print("Enter academic scale: ");
	  int acadScale = sc.nextInt();
	  System.out.print("Enter social scale: ");
	  int socScale = sc.nextInt();
	  System.out.print("Enter quality scale: ");
	  int qualScale = sc.nextInt();
	  ArrayList<String> emphases = new ArrayList<String>(); // add emphases
	  System.out.print("Enter an emphasis (Enter Press q to finish): ");
	  String emphasis = sc.next();
	  while(!emphasis.equals("q")){
	     emphases.add(emphasis);
	     System.out.print("Enter an emphasis (Enter Press q to finish2): ");
	     emphasis = sc.next();
	  }
	  University u = new University(univ, state, location, control, students, femPerc, satV, satM, cost,
  								finAidPerc, applicants, admitted, enrolled, acadScale, socScale, qualScale, emphases);
	  dbc.addNewUniversity(u); // add the university to the database
	  addEmphases(u);
	  System.out.println("*** Saved university " + univ + " to list ***");
	  viewUniversities();
  }

  /**
   * Prompts the user to remove a university and confirm deletion
   * 
   * @param u the university to remove
   */
  public void removeUniversity(University u) {
	  System.out.println("=======================================" +'\n'+ "Are you sure you want to delete " + u.getName() + " from the list?" 	
			  				+'\n'+'\t'+ "y: yes" +'\n'+'\t'+ "n: no"+ '\n' + "Enter Here: ");
	  String prompt = sc.nextLine();
	  if(prompt.equals("y") || prompt.equals("Y")) {
		  System.out.println("*** Deleted " + u.getName() + " ***");
		  delete(u);
	  }
	  else if(prompt.equals("n") || prompt.equals("N")) {
		  System.out.println("*** Returning to Manage_University page ***");
	  }
	  else {
		  System.out.println("ERROR: Invalid input");
		  viewUniversities();
	  }
  }
  /**
   * Prompts the user to add a GeneralUser and its properties
   * 
   * @param userName the name of the GeneralUser to add
   */
  public void addAccount(String userName) {
      ArrayList<String> information = new ArrayList<String>();
	  information.add(userName);
	  System.out.print("Please enter a new password: ");
	  information.add(sc.nextLine());
	  information.add("Y");
	  System.out.print("Please enter the user's first name: ");
	  information.add(sc.nextLine());
	  System.out.print("Please enter the user's last name: ");
	  information.add(sc.nextLine());
	  System.out.print("Please enter the user's type (u=user, a=admin): ");
	  String type = sc.nextLine();
	  if(type.charAt(0) == 'a'){
		  // new Admin(username, password, active, firstName, lastName)
		  Admin ad = new Admin( information.get(0),  information.get(1),  information.get(2).charAt(0),  information.get(3),  information.get(4));
		  dbc.addAccount(ad);
	  }
	  else if(type.charAt(0) == 'u'){
		  // new GeneralUser(firstName, lastName, active, username, password, arrayList)
		  GeneralUser gu = new GeneralUser( information.get(3),  information.get(4), information.get(2).charAt(0), information.get(0), information.get(1), new ArrayList<String>());
		  dbc.addAccount(gu);
	  }
	  else {
		  System.out.println("ERROR: Invalid Input; " + "The input needs to be either 'u' or 'a'");
		  addAccount(userName);
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
		  deactivate(usr);
	  }
	  viewUsers();
  }
}