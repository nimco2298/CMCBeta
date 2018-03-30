/* 
 * File: UserInterface.java
 * Description: This class displays the UI and take all user's commands
 * before passing them to the Controller
 * 
 * @author Karld Bai
 * @version March 19 2018
 */
package interfaces;
import java.util.*;
import entities.*;
import controllers.*;

/**
 * UserInterface is responsible for enabling various controllers to carry out a user function.
 * h
 * @author Karld Bai
 * @version March 20, 2018
 */

public class UserInterface
{
  private GeneralUser user;
  private UserFuncController ufc;
  private SearchController sc;
  private DBController db;
  private AccountInterface ai;
  
  public UserInterface(GeneralUser user){
    this.ufc = new UserFuncController(user);
    this.user = user;
    this.db = new DBController();
    this.ai = new AccountInterface();
    this.sc = new SearchController();
  }
  
  Scanner scan = new Scanner(System.in);
  
  
  /** THE FOLLOWIGN CODE WAS MOVED FROM USERINTERFACE TO HERE */
  
  
  /**
   * Redirects the user to their homepage
   */
  public void homePage(){
    System.out.println( "=======================================" 			+'\n'+
    		            "Welcome " + user.getFirstName() +  " " + user.getLastName() + "! " +'\n'+
    					"Would would you like to do?" 	+'\n'+'\t'+
                       		"s: Search for Schools"						+'\n'+'\t'+
                        	"m: Manage Saved Schools"						+'\n'+'\t'+
                        	"p: Manage Your Profile"						+'\n'+'\t'+
                        	"q: Logout"				+'\n'+
                        "Enter Here: ");
  
    String str= scan.next();
    
    
   // ac.homepage(str);
    
    if(str.equals("s")){
      System.out.println("Here are all the schools:");
      viewSearchedSchools(db.getUniversities());
      System.out.println("Do you want to add a school very quickly? y/n");
      if(scan.next().equals("y")) {
    	  System.out.print("please enter the name of the school you want to save:");
    	  String sName= scan.next();
    	  System.out.println(db.getUniversity(sName).getName());
	      ufc.saveToSavedSchoolList(db.getUniversity(sName));
	      this.user=(GeneralUser)db.getUser(user.getUsername());
      }
      else {
    	  viewSearchedSchools(ufc.searchForSchools());
    	  System.out.println("s for save and v for view:");
	      String s1 = scan.next();
	      if(s1.equals("s")){
	    	  System.out.println("please enter the name of the school you want to save:");
	        String sName= scan.next();
	        ufc.saveToSavedSchoolList(db.getUniversity(sName));
	        homePage();
	      }
	      else{
	        String sName=strIn("please enter the name of the school you want to view details:");
	        viewSchoolDetailsAndTop5(db.getUniversity(sName));
	      }
	    }
    }
    else if(str.equals("m") || str.equals("M")){  //MANAGE SAVED SCHOOLS
      viewSavedSchools();
      System.out.print( "\n" + "Please select r for removing a school or  v for viewing further details: ");
      String s3 = scan.next();
      if(s3.equals("r")){
    	  if(user.getSavedSchools().isEmpty()) {
    		  System.out.println("Error: " + user.getSavedSchools() + " Your list of saved schools is empty! You cannot remove any schools!");
    		  homePage();
    	  }
    	System.out.println("please enter the name of the school you want to remove: ");
      	String sName="";
      	sName= scan.next();
        ufc.removeSavedSchool(db.getUniversity(sName));
        homePage();
      }
 
      else if(!user.getSavedSchools().isEmpty()){
    	
    	System.out.println("please enter the name of the school you want to view details: ");
        String sName= scan.next();
        while(!user.getSavedSchools().contains(sName)) {
        	System.out.println("no match, please enter again");
        	sName= scan.next();
        }
        
        viewSavedSchoolDetails(db.getUniversity(sName));
        homePage();

	      }

	    }
	      
     else if (str.equals("p") || str.equals("P")) {  
    	viewProfile();
        System.out.print("Do you want to edit your profile? y/n: ");
        String s4 = scan.next();
        if(s4.equals("y")){
        	ufc.editProfile();
            homePage();
        }
        else { 
        homePage(); 	  
        }
  
     }
     
     else if(str.equals("q")||str.equals("Q")){ // QUIT)
            ai.logout();

        }  
     else {
   	  System.out.print("Invalid input ");  
   	  homePage();
     }
    }

  
  /**
   * Takes the view details command and displays the details
   * @param University  the selected university 
   */
  public void viewSchoolDetailsAndTop5(University u)
  {
	  
    viewSavedSchoolDetails(u);
    viewSearchedSchools(sc.recSearch(u));
    System.out.print("Do you want to save it? y/n:");  
    String s2 = scan.next();
    if(s2.equals("y")){
      ufc.saveToSavedSchoolList(u);
      homePage();
    }
    else{
      homePage();
    }
  }
  
  /**
   * Takes the view saved school command and displays the saved school list
   */
  public void viewSavedSchools(){
	  System.out.println("Your list of saved schools:" + "\n");
    for(String u: user.getSavedSchools()){
      System.out.println(u);
    }
  }
  /**
   * Takes the view saved school details command and displays the details
   */
  public void viewSavedSchoolDetails(University u){
    System.out.println("School details: ");
    u.printDetails();
  }
 
  
  /**
   * Takes the view profile command and displays the profile of the user
   */
  public void viewProfile(){
    System.out.println("Profile details: \n"+this.user.getDetails());
  }
  

  /**
   * Displays the result of all university names in a list of Universities.
   * @param  c  The list of Universities stored in DB
   */
  public void viewSearchedSchools(ArrayList<University> c){
    System.out.println("Here is the result of searching: ");
    for(University u: c){
      System.out.println(u.getName());
    }
  }
  


  
  /**
   * Takes a string and prints it, and prompts user to enter another String
   * @param ss       a string that will be printed 
   * @return String  a new string the User entered
   */
  public String strIn(String ss){
    System.out.print(ss);
    String re= scan.next();
    return re;
  }
  
  /**
   * Takes a String and prints it, and prompts user to enter a Integer 
   * @param ss       a string that will be printed 
   * @return int  a new integer the User entered
   */
  public int intIn(String ss){
    System.out.print(ss);
    int re= scan.nextInt();
    return re;
  }
}
