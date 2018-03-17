package interfaces;
import java.util.*;
import entities.*;
import controllers.*;
/*
 * File:AdminInterface.java
 */

/**
 * All the functionalities which the admin should be able to do
 * @author Ian Bush, Sara Laufers
 * @version March 17, 2018
 */
public class AdminInterface {
  //==================================INSTANCE VARIABLES==========================
  private AdminFuncController ad;
  Scanner sc = new Scanner(System.in);
  
  //==================================CONSTRUCTORS==========================
  /**
   * Constructor for the AdminInterface
   */
  public AdminInterface() {
    this.ad = new AdminFuncController();
  }
  //==================================METHODS==========================
  /**
   * Shows a list of all universities in the system
   */
  public void viewUniversities() {
	ad.viewUniversities();
    System.out.print("=======================================" 			+'\n'+
    					"Would you like to add or edit Universities?" 	+'\n'+'\t'+
                       		"a: Add Universities"						+'\n'+'\t'+
                        	"e: Edit Universities"						+'\n'+'\t'+
                        	"r: Remove University"						+'\n'+'\t'+
                        	"d: Details of University"					+'\n'+'\t'+
                        	"q: Quit (Return to Homepage)"				+'\n'+
                        "Enter Here: ");
    String cmd = sc.next();
    
    if(cmd.equals("a")){ // ADD UNIVERSITIES
      addUniversity();
    }
    
    else if(cmd.equals("e")){ // EDIT UNIVERSITIES
      System.out.print("=======================================" +'\n'+ "Enter University Name: ");
      String univName = sc.next();
      if(ad.getUniversity(univName) instanceof University) {
        editUniversity(univName);
      }
      else {
    	System.out.println("ERROR: There is no such university");
    	viewUniversities();
      }
    }
    
    else if(cmd.equals("r")) { // REMOVE UNIVERSITY
      System.out.print("=======================================" +'\n'+ "Enter University Name: ");
      String univName = sc.next();
      if(ad.getUniversity(univName) instanceof University) {
        removeUniversity(univName);
      }
      else {
        System.out.println("ERROR: There is no such university");
        viewUniversities();
      }
    }
    
    else if(cmd.equals("d")||cmd.equals("D")) {
      System.out.print("=======================================" +'\n'+ "Enter University Name: ");
      String univName = sc.next();
      if(ad.getUniversity(univName) instanceof University) {
        ad.viewUniversityDetails(univName);
        viewUniversities();
      }
      else {
        System.out.println("ERROR: There is no such university");
        viewUniversities();
      }
    }
    
    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
      homepage();
    }
    
    else{ // INPUT ERROR
      System.out.println("ERROR: Invalid input");
      viewUniversities();
    }
  }
  
  /**
   * Shows a list of all users in the system (both general and admin)
   */
  public void viewUsers() {
	ad.viewUsers();
    System.out.print("======================================="	+ '\n' +
    					"Would you like to add, edit, or deactivate a user?" + '\n'
                         +'\t'+ "a: Add User" + '\n'
                         +'\t'+ "e: Edit User" + '\n'
                         +'\t'+ "d: Deactivate User" + '\n'
                         //+'\t'+ "r" + '\n' // TESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                         +'\t'+ "q: Quit (Return to Homepage)" + '\n' +
                         "Enter Here: ");
    String cmd = sc.next();
    if(cmd.equals("a")){ // ADD USER
    	addAccount();
    }
    else if(cmd.equals("e")){ // EDIT USER
    	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
    	String userName = sc.next();
    	if(!ad.getAccount(userName).getUsername().equals("DummyUser")) {
    		editUser(ad.getAccount(userName));
    	}
    	else {
      		System.out.println("ERROR: There is no such user");
      		viewUsers();
    	}
    }
    else if(cmd.equals("d")){ // DEACTIVATE USER
    	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
    	String userName = sc.next();
    	if(!ad.getAccount(userName).getUsername().equals("DummyUser")) {
    		deactivate(ad.getAccount(userName));
    	}
    	else {
    		System.out.println("ERROR: There is no such user");
    		viewUsers();
    	}
    }
//    else if(cmd.equals("r")){ // TESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    	DBController db = new DBController();
//    	db.deleteAccount(ad.getAccount(sc.next()));
//    	viewUsers();
//    }
    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
    	homepage();
    }
    else{ // INPUT ERROR
    	System.out.println("ERROR: Invalid input");
    	viewUsers();
    }
  }
  
  /**
   * Removes a university
   * 
   * @param univ The name of the university to remove
   */
  public void removeUniversity(String univ) {
	  University u = ad.getUniversity(univ);
	  System.out.println("=======================================" 						+'\n'+
			  			"Are you sure you want to delete " + univ + " from the list?" 	+'\n'+'\t'+
			  				"y: yes" 													+'\n'+'\t'+
			  				"n: no"														);
	  String prompt = sc.next();
	  if(prompt.equals("y") || prompt.equals("y")) {
		  System.out.println("*** Deleted " + univ + " ***");
		  ad.delete(u);
	  }
	  else if(prompt.equals("n") || prompt.equals("N")) {
		  System.out.println("*** Returning to Manage_University page ***");
	  }
	  else {
		  System.out.println("ERROR: Invalid input");
		  removeUniversity(univ);
	  }
	  viewUniversities();
  }
  
  /**
   * Modifies a university
   * 
   * @param univ The name of the University to modify
   */
  public void editUniversity(String univ) {
	  University u = ad.getUniversity(univ);
	  String prompt = "";
	  do {
		  System.out.print("=======================================" 		+'\n'+
				  			"What would you like to edit:" 					+'\n'+'\t'+
				  				"1: state" 									+'\n'+'\t'+
				  				"2: location" 								+'\n'+'\t'+
				  				"3: control" 								+'\n'+'\t'+
				  				"4: students" 								+'\n'+'\t'+
				  				"5: female percentage" 						+'\n'+'\t'+
				  				"6: SAT verbal score" 						+'\n'+'\t'+
				  				"7: SAT math score" 						+'\n'+'\t'+
				  				"8: cost" 									+'\n'+'\t'+
				  				"9: financial aid percentage" 				+'\n'+'\t'+
				  				"10: applicants" 							+'\n'+'\t'+
				  				"11: admitted" 								+'\n'+'\t'+
				  				"12: enrolled" 								+'\n'+'\t'+
				  				"13: academic scale" 						+'\n'+'\t'+
				  				"14: social scale" 							+'\n'+'\t'+
				  				"15: quality scale" 						+'\n'+'\t'+
				  				"16: add emphases" 							+'\n'+'\t'+
				  				"17: remove emphases" 						+'\n'+'\t'+
				  				"s: Save"									+'\n'+'\t'+
				  				"c: Cancel"									+'\n'+
            				"Enter Here: ");
		  prompt = sc.next();
		  switch (prompt){
		  	case "1":
		  		System.out.print("Enter the state: ");
		  		u.setState(sc.next());
		  		break;
		  	case "2":
		  		System.out.print("Enter the location: ");
		  		u.setLocation(sc.next());
		  		break;
		  	case "3":
		  		System.out.print("Enter the control: ");
		  		u.setControl(sc.next());
		  		break;
		  	case "4":
		  		System.out.print("Enter the number of students: ");
		  		u.setStudents(sc.nextInt());
		  		break;
	        case "5":
	        	System.out.print("Enter the female percentage: ");
	        	u.setFemPerc(sc.nextInt());
	        	break;
	        case "6":
	        	System.out.print("Enter the SAT verbal score: ");
	        	u.setSatV(sc.nextInt());
	        	break;
	        case "7":
	        	System.out.print("Enter the SAT math score: ");
	        	u.setSatM(sc.nextInt());
	        	break;
	        case "8":
	        	System.out.print("Enter the cost: ");
	        	u.setCost(sc.nextInt());
	        	break;
	        case "9":
	        	System.out.print("Enter the financial aid percentage: ");
	        	u.setFinAidPerc(sc.nextInt());
	        	break;
	        case "10":
	        	System.out.print("Enter the applicants: ");
	        	u.setApplicants(sc.nextInt());
	        	break;
	        case "11":
	        	System.out.print("Enter the admitted students: ");
	        	u.setAdmitted(sc.nextInt());
	        	break;
	        case "12":
	        	System.out.print("Enter the enrolled students: ");
	        	u.setEnrolled(sc.nextInt());
	        	break;
	        case "13":
	        	System.out.print("Enter the academic scale: ");
	        	u.setAcadScale(sc.nextInt());
	        	break;
	        case "14":
	        	System.out.print("Enter the social scale: ");
	        	u.setSocScale(sc.nextInt());
	        	break;
	        case "15":
	        	System.out.print("Enter the quality scale: ");
	        	u.setQualScale(sc.nextInt());
	        	break;
	        case "16":
	        	System.out.print("Enter an emphasis to add: ");
	        	u.addEmphases(sc.next());
	        	break;
	        case "17":
	        	System.out.print("Enter an emphasis to remove: ");
	        	u.removeEmphases(sc.next());
	        	break;
	        case "s":
	        	ad.saveUnivChanges(u);
	      	  	System.out.println("*** Saved updates to " + univ + " ***");
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
   * Adds a university to the database
   */
  public void addUniversity() {
    String emphasis = "";
    System.out.println("=======================================");
    // ask user for university properties
	System.out.print("Enter school name: ");
    String schoolName = sc.next();
    System.out.print("Enter state: ");
    String state = sc.next();
    System.out.print("Enter location: ");
    String location = sc.next();
    System.out.print("Enter control: ");
    String control = sc.next();
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
    ArrayList<String> emphases = new ArrayList<String>();
    System.out.print("Enter an emphasis (Press q to finish): ");
    emphasis = sc.next();
    while(!emphasis.equals("q")){
      emphases.add(emphasis);
      System.out.print("Enter an emphasis (Press q to finish): ");
      emphasis = sc.next();
    }
    University u = new University(schoolName, state, location, control, students, femPerc, satV, satM, cost,
                                  finAidPerc, applicants, admitted, enrolled, acadScale, socScale, qualScale, emphases);
    ad.addUniversity(u);
    System.out.println("*** Saved university " + schoolName + " to list ***");
    viewUniversities();
  }
  
  /**
   * Adds an account to the database
   */
  public void addAccount() {
    ArrayList<String> information = new ArrayList<String>();
    
    // Ask the admin for new username
    System.out.print("Please enter a new username: ");
    information.add(sc.next());
    
    // Ask the admin for new password
    System.out.print("Please enter a new password: ");
    information.add(sc.next());
    
    // Enter the active as "y" (default)
    // y is stored as a character in Database; changed to character in AdminFuncController
    information.add("y");
    
    // Ask the admin for new first name
    System.out.print("Please enter the user's first name: ");
    information.add(sc.next());
    
    // Ask the admin for new last name
    System.out.print("Please enter the user's last name: ");
    information.add(sc.next());
    
    // Ask the admin for new type
    System.out.print("Please enter the user's type: ");
    String type = sc.next();
    if(type.equals("u") || type.equals("a")) {
    	information.add(type);
    }
    else {
    	System.out.println("ERROR: Invalid Input; " + "The input needs to be either 'u' or 'a'");
    	addAccount();
    }
    
    ad.addAccount(information);
    viewUsers();
  }
  
  /**
   * Modifies a user
   * @param usr the user to modify
   */
  public void editUser(Account user){
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
    prompt = sc.next();
      switch (prompt){
        case "1":
          System.out.print("Enter the new first name: ");
          user.setFirstName(sc.next());
          break;
        case "2":
          System.out.print("Enter the new last name: ");
          user.setLastName(sc.next());
          break;
        case "3":
          System.out.print("Enter the new password: ");
          user.setPassword(sc.next());
          break;
        case "4":
          System.out.print("Enter the type (a=admin, u=general user): ");
          char type = sc.next().charAt(0);
          if(type == 'a' || type == 'u') {
          	user.setType(type);
            }
          else {
        	System.out.println("ERROR: Invalid input");
          }
          break;
        case "5":
          System.out.print("Enter the status (Y=active, N=deactive): ");
          char status = sc.next().charAt(0);
          if(status == 'Y' || status == 'N') {
        	user.setActive(status);
          }
          else {
          	System.out.println("ERROR: Invalid input; inputs must be capitalized");
          }
          break;
        case "s":
          ad.saveAccountChanges(user);
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
   * Deactivates a user
   * pre: the admin must agree (type y for yes) on prompt
   * post: the user will be deactivated, and no longer able to access the system
   * @param usr the user to deactivate
   */
  public void deactivate(Account usr) {
    System.out.print("======================================="				+ '\n' +
    					"Are you sure you want to deactivate this user?" 	+ '\n' + '\t'+ 
    						"y: yes" 										+ '\n' + '\t'+ 
    						"n: no"											+ '\n' + 
    					"Enter Here: ");
    String prompt = sc.next();
    if(prompt.equals("y") || prompt.equals("Y")){
      char active = usr.getActive();
      if(active == 'Y'){
    	System.out.println("***"+usr.getFirstName() + " has been deactivated"+"***");
        usr.setActive('N');
      }
      else {
    	System.out.println("***"+usr.getFirstName() + " is already deactivated"+"***");
      }
      ad.saveAccountChanges(usr);
      viewUsers();
    }
    else if(prompt.equals("n") || prompt.equals("N")) {
    	System.out.println("*** Returning to Manage_Users page ***");
    	viewUsers();
    }
    else {
    	System.out.println("ERROR: Invalid Input");
    	deactivate(usr);
    }
  }
  
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
    String prompt = sc.next();
    
    if(prompt.equals("1")){ // Manage universities
      viewUniversities();
    }
    else if(prompt.equals("2")){ // Manage users
      viewUsers();
    }
    else if(prompt.equals("3")){ // Manage users
      
    }
    else{ // invalid input
      System.out.println("ERROR: Invalid Input");
      homepage();
    }
  }
}