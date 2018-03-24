
/*
 * File:AdminInterface.java
 */
package interfaces;
import java.util.*;
import entities.*;
import controllers.*;


/**
 * AdminInterface contains all the functionalities which the admin should be able to do
 * @author Ian Bush, Sara Laufers
 * @version March 17, 2018
 */
public class AdminInterface {
  //==================================INSTANCE VARIABLES==========================
  private AdminFuncController ad;
  Scanner sc = new Scanner(System.in);
  private AccountInterface ai;
  
  
  //==================================CONSTRUCTORS==========================
  /**
   * Constructor for the AdminInterface
   */
  public AdminInterface() {
    this.ad = new AdminFuncController();
    this.ai = new AccountInterface();
  }
  //==================================METHODS==========================
  /**
   * Shows a list of all universities in the system, and
   * presents options for the admin to manage those
   * universities.
   */
  public void viewUniversities() {
	//ad.viewUniversities();
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
    	addUniversity();
    }
    else if(cmd.equals("e")){ // EDIT UNIVERSITIES
    	editUniversity();
    }
    else if(cmd.equals("r")) { // REMOVE UNIVERSITY
    	removeUniversity();
    }
    else if(cmd.equals("d")||cmd.equals("D")) { // SHOW DETAILS OF A UNIVERSITY
    	viewUniversityDetails();
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
   * and presents a list of options for the admin to manage those
   * users.
   */
  public void viewUsers() {
	ad.viewUsers();
    System.out.print("======================================="	+ '\n' +
    					"Would you like to add, edit, or deactivate a user?" + '\n'
                         +'\t'+ "a: Add User" + '\n'
                         +'\t'+ "e: Edit User" + '\n'
                         +'\t'+ "d: Deactivate User" + '\n'
                         +'\t'+ "r: Remove User" + '\n'
                         +'\t'+ "q: Quit (Return to Homepage)" + '\n' +
                         "Enter Here: ");
    String cmd = sc.nextLine();
    if(cmd.equals("a")){ // ADD USER
    	addAccount();
    }
    else if(cmd.equals("e")){ // EDIT USER
    	editUser();
    }
    else if(cmd.equals("d")){ // DEACTIVATE USER
    	deactivate();
    }
    else if(cmd.equals("r")){ // DEACTIVATE USER
    	System.out.print("=======================================" +'\n'+ "Enter University Name: ");
        String univ = sc.nextLine();
        DBController dbc = new DBController();
        dbc.deleteAccount(ad.getAccount(univ));
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
   * Views the details of the university
   */
  public void viewUniversityDetails() {
	  System.out.print("=======================================" +'\n'+ "Enter University Name: ");
      String univ = sc.nextLine();
      if(!(ad.getUniversity(univ) instanceof University)) {// if the university does not exist
    	  System.out.println("*** There is no such university ***");
    	  viewUniversityDetails();
      }
      ad.viewUniversityDetails(univ);
      viewUniversities();
  }
  
  /**
   * Removes a university
   * 
   * @param univ The name of the university to remove
   */
  public void removeUniversity() {
	  System.out.print("=======================================" +'\n'+ "Enter University Name: ");
      String univ = sc.nextLine();
      if(!(ad.getUniversity(univ) instanceof University)) {// if the university does not exist
    	  System.out.println("*** There is no such university ***");
    	  removeUniversity();
      }
	  ad.removeUniversity(ad.getUniversity(univ));
	  viewUniversities();
  }
  
  /**
   * Modifies a university
   * 
   * @param univ The name of the University to modify
   */
  public void editUniversity() {
	  System.out.print("=======================================" +'\n'+ "Enter University Name: ");
      String univ = sc.nextLine();
      if(!(ad.getUniversity(univ) instanceof University)) {// if the university does not exist
    	  System.out.println("*** There is no such university ***");
    	  this.editUniversity();
      }
      ad.editUniversity(ad.getUniversity(univ));
      viewUniversities();
  }
  
  /**
   * Adds a university to the database
   */
  public void addUniversity() {
    System.out.print("=======================================" +'\n'+ "Enter University name: ");
    String univ = sc.nextLine();
    if(ad.getUniversity(univ) instanceof University) {// if the university already exists
    	System.out.println("*** This university name already exists, please choose a different one ***");
    	this.addUniversity();
    }
    ad.addUniversity(univ);
    viewUniversities();
  }
  
  /**
   * Adds an account to the database
   */
  public void addAccount() {
    System.out.print("=======================================" +'\n'+"Enter Username: ");
    String userName = sc.nextLine();
    if(!ad.getAccount(userName).getUsername().equals("DummyUser")) {//if the username already exists
    	System.out.println("*** This user name already exists, please choose a different one ***");
        addAccount();
    }
    ad.addAccount(userName);
    viewUsers();
  }
  
  /**
   * Modifies a user
   * @param usr the user to modify
   */
  public void editUser(){
	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
  	String userName = sc.nextLine();
  	if(ad.getAccount(userName).getUsername().equals("DummyUser")) {//if the username does not exist
  		System.out.println("*** There is no such user ***");
  		editUser();
  	}
  	ad.editUser(ad.getAccount(userName));
    viewUsers();
  }
  
  /**
   * Deactivates a user
   * pre: the admin must agree (type y for yes) on prompt
   * post: the user will be deactivated, and no longer able to access the system
   * @param usr the user to deactivate
   */
  public void deactivate() {
	System.out.print("=======================================" +'\n'+ "Enter a Username: ");
  	String userName = sc.nextLine();
  	if(ad.getAccount(userName).getUsername().equals("DummyUser")) {
  		System.out.println("ERROR: There is no such user");
  		deactivate();
  	}
  	ad.deactivate(ad.getAccount(userName));
  	viewUsers();
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
}