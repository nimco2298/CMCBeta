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
 * @version February 22, 2018
 */
public class AdminInterface
{
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
    //ad.viewUniversities();
    System.out.println("Would you like to add or edit Universities?" + '\n'
                         +'\t'+ "a: Add Universities" + '\n'
                         +'\t'+ "e: Edit Universities" + '\n'
                         +'\t'+ "r: Remove University" + '\n'
                         +'\t'+ "q: Quit (Return to Homepage)");
    String cmd = sc.next();
    if(cmd.equals("a")){ // ADD UNIVERSITIES
      addUniversity();
    }
    else if(cmd.equals("e")){ // EDIT UNIVERSITIES
      System.out.println("Enter a University name");
      String univName = sc.next();
      if(ad.getUniversity(univName) instanceof University) {
        editUniversity(univName);
      }
      else {
    	System.out.println("There is no such university");
    	viewUniversities();
      }
    }
    else if(cmd.equals("r")) {
    	System.out.println("Enter a university's name");
    	String univName = sc.next();
    	if(ad.getUniversity(univName) instanceof University) {
            removeUniversity(univName);
          }
        else {
        	System.out.println("There is no such university");
        	viewUniversities();
        }
    }
    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
      homepage();
    }
    else{ // INPUT ERROR
      System.out.println("Invalid input");
      viewUniversities();
    }
  }
  
  /**
   * Shows a list of all users in the system (both general and admin)
   */
  public void viewUsers() {
    ad.viewUsers();
    System.out.println("Would you like to add, edit, or deactivate a User?" + '\n'
                         +'\t'+ "a: Add User" + '\n'
                         +'\t'+ "e: Edit User" + '\n'
                         +'\t'+ "d: Deactivate User" + '\n'
                         +'\t'+ "q: Quit (Return to Homepage)");
    String cmd = sc.next();
    if(cmd.equals("a")){ // ADD USER
      addAccount();
    }
    else if(cmd.equals("e")){ // EDIT USER
      System.out.println("Enter a Username");
      String userName = sc.next();
      if(!ad.getAccount(userName).getUsername().equals("DummyUser")) {
    	editUser(ad.getAccount(userName));
      }
      else {
      	System.out.println("There is no such user");
      	viewUsers();
      }
    }
    else if(cmd.equals("d")){ // DEACTIVATE USER
      System.out.println("Enter a Username");
      String userName = sc.next();
      if(!ad.getAccount(userName).getUsername().equals("DummyUser")) {
    	  deactivate(ad.getAccount(userName));
      }
      else {
    	  System.out.println("There is no such user");
          viewUsers();
      }
    }
    else if(cmd.equals("q")||cmd.equals("Q")){ // QUIT
      homepage();
    }
    else{ // INPUT ERROR
      System.out.println("Invalid input");
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
	  System.out.println("Are you sure you want to delete " + univ + " from the list?"
			  + '\n' + "y for 'yes'" + '\n' + "n for 'no'");
	  String prompt = sc.next();
	  if(prompt.equals("y") || prompt.equals("y")) {
		  System.out.println("Deleting " + univ + "...");
		  ad.delete(u);
	  }
	  else if(prompt.equals("n") || prompt.equals("N")) {
		  System.out.println("Returning to page...");
	  }
	  else {
		  System.out.println("Invalid input");
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
      System.out.println("What would you like to edit:" + '\n' +
            				"1: state" + '\n' +
            				"2: location" + '\n' +
            				"3: control" + '\n' +
            				"4: students" + '\n' +
            				"5: female percentage" + '\n' +
            				"6: SAT verbal score" + '\n' +
            				"7: SAT math score" + '\n' +
            				"8: cost" + '\n' +
            				"9: financial aid percentage" + '\n' +
            				"10: applicants" + '\n' +
            				"11: admitted" + '\n' +
            				"12: enrolled" + '\n' +
            				"13: academic scale" + '\n' +
            				"14: social scale" + '\n' +
            				"15: quality scale" + '\n' +
            				"16: add emphases" + '\n' +
            				"17: remove emphases" + '\n' +
            				"q: Quit");
      prompt = sc.next();
      switch (prompt){
    	case "1":
    	  System.out.println("Enter the state");
    	  u.setState(sc.next());
          break;
        case "2":
          System.out.println("Enter the location");
          u.setLocation(sc.next());
          break;
        case "3":
          System.out.println("Enter the control");
          u.setControl(sc.next());
          break;
        case "4":
          System.out.println("Enter the number of students");
          u.setStudents(sc.nextInt());
          break;
        case "5":
          System.out.println("Enter the female percentage");
          u.setFemPerc(sc.nextInt());
          break;
        case "6":
          System.out.println("Enter the SAT verbal score");
          u.setSatV(sc.nextInt());
          break;
        case "7":
          System.out.println("Enter the SAT math score");
          u.setSatM(sc.nextInt());
          break;
        case "8":
          System.out.println("Enter the cost");
          u.setCost(sc.nextInt());
          break;
        case "9":
          System.out.println("Enter the financial aid percentage");
          u.setFinAidPerc(sc.nextInt());
          break;
        case "10":
          System.out.println("Enter the applicants");
          u.setApplicants(sc.nextInt());
          break;
        case "11":
          System.out.println("Enter the admitted students");
          u.setAdmitted(sc.nextInt());
          break;
        case "12":
          System.out.println("Enter the enrolled students");
          u.setEnrolled(sc.nextInt());
          break;
        case "13":
          System.out.println("Enter the academic scale");
          u.setAcadScale(sc.nextInt());
          break;
        case "14":
          System.out.println("Enter the social scale");
          u.setSocScale(sc.nextInt());
          break;
        case "15":
          System.out.println("Enter the quality scale");
          u.setQualScale(sc.nextInt());
          break;
        case "16":
          System.out.println("Enter an emphasis to add");
          u.addEmphases(sc.next());
          break;
        case "17":
          System.out.println("Enter an emphasis to remove");
          u.removeEmphases(sc.next());
          break;
        case "q":
          break;
        case "Q":
          break;
        default:
          System.out.println("Invalid input");
          break;
      } 
    } while(!prompt.equals("q")&&!prompt.equals("Q"));
      
    System.out.println("Saving updates to " + univ);
    ad.saveUnivChanges(u);
    viewUniversities();
  }
  
  /**
   * Adds a university to the database
   */
  public void addUniversity() {
    String emphasis = "";
	  
    // ask user for university properties
	System.out.println("Enter school name");
    String schoolName = sc.next();
    System.out.println("Enter state");
    String state = sc.next();
    System.out.println("Enter location");
    String location = sc.next();
    System.out.println("Enter control");
    String control = sc.next();
    System.out.println("Enter number of students");
    int students = sc.nextInt();
    System.out.println("Enter female percentage");
    int femPerc = sc.nextInt();
    System.out.println("Enter SAT verbal score");
    int satV = sc.nextInt();
    System.out.println("Enter SAT math score");
    int satM = sc.nextInt();
    System.out.println("Enter cost");
    int cost = sc.nextInt();
    System.out.println("Enter financial aid percentage");
    int finAidPerc = sc.nextInt();
    System.out.println("Enter applicants");
    int applicants = sc.nextInt();
    System.out.println("Enter number of admitted students");
    int admitted = sc.nextInt();
    System.out.println("Enter number of enrolled students");
    int enrolled = sc.nextInt();
    System.out.println("Enter academic scale");
    int acadScale = sc.nextInt();
    System.out.println("Enter social scale");
    int socScale = sc.nextInt();
    System.out.println("Enter quality scale");
    int qualScale = sc.nextInt();
    ArrayList<String> emphases = new ArrayList<String>();
    System.out.println("Enter an emphasis. Press q to finish.");
    emphasis = sc.next();
    while(!emphasis.equals("q")){
      emphases.add(emphasis);
      System.out.println("Enter an emphasis. Press q to finish.");
      emphasis = sc.next();
    }
    System.out.println("Saving university " + schoolName + " to list");
    // apply properties to a University object
    University u = new University(schoolName, state, location, control, students, femPerc, satV, satM, cost,
                                  finAidPerc, applicants, admitted, enrolled, acadScale, socScale, qualScale, emphases);
    // add the university to database
    ad.addUniversity(u);
    viewUniversities();
  }
  
  /**
   * Adds an account to the database
   */
  public void addAccount() {
    ArrayList<String> information = new ArrayList<String>();
    
    // Ask the admin for new username
    System.out.println("Please enter a new username");
    information.add(sc.next());
    
    // Ask the admin for new password
    System.out.println("Please enter a new password");
    information.add(sc.next());
    
    // Enter the active as "y" (default)
    // y is stored as a character in Database; changed to character in AdminFuncController
    information.add("y");
    
    // Ask the admin for new first name
    System.out.println("Please enter the user's first name");
    information.add(sc.next());
    
    // Ask the admin for new last name
    System.out.println("Please enter the user's last name");
    information.add(sc.next());
    
    // Ask the admin for new type
    System.out.println("Please enter the user's type");
    information.add(sc.next());
    
    ad.addAccount(information);
    homepage();
  }
  
  /**
   * Modifies a user
   * @param usr the user to modify
   */
  public void editUser(Account user){
	String prompt = "";
	do {
	System.out.println("What would you like to edit:" + '\n' +
						"1: FirstName" + '\n' +
						"2: LastName" + '\n' +
						"3: Password" + '\n' +
						"4: Type" + '\n' +  
						"5: Status" + '\n' +  
            			"q: Quit (Return to homepage and save updates)");
    prompt = sc.next();
      switch (prompt){
        case "1":
          System.out.println("Enter the new first name");
          user.setFirstName(sc.next());
          break;
        case "2":
          System.out.println("Enter the new last name");
          user.setLastName(sc.next());
          break;
        case "3":
          System.out.println("Enter the new password");
          user.setPassword(sc.next());
          break;
        case "4":
          System.out.println("Enter the type:" + '\n' + "a for admin" + '\n' + "u for user");
          char type = sc.next().charAt(0);
          if(type == 'a' || type == 'u') {
          	user.setType(type);
            }
          else {
        	System.out.println("Invalid input");
          }
          break;
        case "5":
          System.out.println("Enter the status:"+ '\n' + "Y for active" + '\n' + "N for deactive");
          char status = sc.next().charAt(0);
          if(status == 'Y' || status == 'N') {
        	user.setActive(status);
          }
          else {
          	System.out.println("Invalid input");
          }
          break;
        case "q":
          System.out.println("Now Saving...");
          break;
        case "Q":
          System.out.println("Now Saving...");
          break;
        default:
          System.out.println("Invalid input");
          break;
      }
    } while(!prompt.equals("q")&&!prompt.equals("Q"));
	
    ad.saveAccountChanges(user);
    System.out.println("Updates have been saved:" + '\n' + 
    						"FirstName: " + user.getFirstName() + '\n' + 
    						"LastName: " + user.getLastName() + '\n' + 
    						"Password: " + user.getPassword() + '\n' + 
    						"Type: " + user.getType() + '\n' + 
    						"Active: " + user.getActive());
    viewUsers();
  }
  
  /**
   * Deactivates a user
   * pre: the admin must agree (type y for yes) on prompt
   * post: the user will be deactivated, and no longer able to access the system
   * @param usr the user to deactivate
   */
  public void deactivate(Account usr) {
    System.out.println("Are you sure you want to deactivate this user?" + '\n'
                         + "Type y for 'yes'" + '\n' + "Type n for 'no'");
    String prompt = sc.next();
    if(prompt.equals("y") || prompt.equals("Y")){
      char active = usr.getActive();
      if(active == 'Y'){
    	System.out.println(usr.getFirstName() + " has been deactivated");
        usr.setActive('N');
      }
      else {
    	System.out.println(usr.getFirstName() + " is already deactivated");
      }
      ad.saveAccountChanges(usr);
    }
    viewUsers();
  }
  
  /**
   * Brings the admin to their homepage
   */
  public void homepage() {
	System.out.println("Welcome to the Admin Homepage:" + '\n' + 
						'\t'+"Type 1 to Manage Universities" + '\n' + 
						'\t'+"Type 2 to Manage Users");
    String prompt = sc.next();
    if(prompt.equals("1")){ // Manage universities
      viewUniversities();
    }
    else if(prompt.equals("2")){ // Manage users
      viewUsers();
    }
    else{ // invalid input
      System.out.println("Invalid Input");
      homepage();
    }
  }
}