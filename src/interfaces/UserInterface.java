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
 * 
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
    if(str.equals("s")){
      System.out.println("Here are all the schools:");
      viewSearchedSchools(db.getUniversities());
      System.out.println("Do you want to add a school very quickly? y/n");
      if(scan.next().equals("y")) {
    	  System.out.print("please enter the name of the school you want to save:");
    	  String sName= scan.next();
    	  System.out.println(db.getUniversity(sName).getName());
	      saveToSavedSchoolList(db.getUniversity(sName));
	      this.user=(GeneralUser)db.getUser(user.getUsername());
      }
      else {
    	  viewSearchedSchools(searchForSchools());
    	  System.out.println("s for save and v for view:");
	      String s1 = scan.next();
	      if(s1.equals("s")){
	    	  System.out.println("please enter the name of the school you want to save:");
	        String sName= scan.next();
	        saveToSavedSchoolList(db.getUniversity(sName));
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
    	System.out.println("please enter the name of the school you want to remove: ");
    	String sName="";
    	sName= scan.next();
        removeSavedSchool(db.getUniversity(sName));
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
          editProfile();
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
      saveToSavedSchoolList(u);
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
   * Takes the edit file command and redirect the user to the edit page
   */
  public void editProfile(){
	  System.out.println("What would you like to edit:" + '\n' +
			                "1: FirstName" + '\n' +
                             "2: LastName" + '\n' +
                             "3: Password" + '\n' +                            
                             "4: Quit " + '\n'
                             + "Enter here: ");
                    
    String prompt = scan.next();
      switch (prompt){
        case "1":
          System.out.print("Enter the new first name: ");
          String change=scan.next();
          ufc.submitProfileChanges(change,user.getLastName(),user.getPassword());
          user.setFirstName(change);
          break;
        case "2":
          System.out.print("Enter the new last name: ");
          change=scan.next();
          ufc.submitProfileChanges(user.getFirstName(),change,user.getPassword());
          user.setLastName(change);
          break;
        case "3":
          System.out.print("Enter the new password: ");
          change=scan.next();
          ufc.submitProfileChanges(user.getFirstName(),user.getLastName(),change);
          user.setPassword(change);
          break;
          
        case "4":
        	homePage();
        	break;
       
        default:
          System.out.println("Invalid input");
          break;
      
      
    }
    homePage();
  }
  /**
   * redirect the user to the homepage and save the changes
   */
  public void submitProfileChanges(){
    
  }
  /**
   * This method takes the search command and shows the result
   * 
   * @return ArrayList<University> the schools that have matched this criteria
   */
  public ArrayList<University> searchForSchools()
  {
	  String schoolName = "", state = "", location = "", control = "";
	  int studentsLow = 0, studentsHigh = 999999, femPercLow = 0, femPercHigh = 100, satVLow = 0,
			  satVHigh = 1000, satMLow = 0, satMHigh = 1000, costLow = 0, costHigh = 99999, finAidPercLow = 0, 
			  finAidPercHigh = 100, applicantsLow = 0, applicantsHigh = 99999, admittedLow = 0, 
			  admittedHigh = 100, enrolledLow = 0, enrolledHigh =100, acadScaleLow = 0, acadScaleHigh = 99, 
			  socLifeScaleLow = 0, socLifeScaleHigh = 99, qualLifeScaleLow = 0, qualLifeScaleHigh = 99;
	  ArrayList<String> emphases = new ArrayList<String>();
	  ArrayList<University> uList = new ArrayList<University>();
	  System.out.println("Enter the desired search criteria");
	  int i = intIn("1: School Name\n2: State\n3: Location\nEnter Search Criteria Here: ");
	  do
	  {
		  switch(i)
		  {
		  case 1: schoolName=strIn("School Name: "); i = intIn("");  					break;
		  case 2: state = strIn("State: "); i = intIn("");          		  	  		break;
		  case 3: location=strIn("Location: ");   i = intIn("");    		      		break;
		  case 4:  control=strIn("Control: "); i = intIn("");                   		break;
		  case 5: studentsLow=intIn("low num of students: ");i = intIn("");	  			break;
		  case 6: studentsHigh=intIn("high num of students: ");	 i = intIn(""); 		break;
		  case 7: femPercLow=intIn("low % of female: "); 		 i = intIn(""); 		break;
		  case 8: femPercHigh =intIn("high % of female: ");		 i = intIn(""); 		break;
		  case 9: satVLow=intIn("low verbal sat: ");			 i = intIn(""); 		break;
		  case 10: satVHigh=intIn("high verbal sat: ");			i = intIn("");   		break;
		  case 11: satMLow=intIn("low math sat: ");				i = intIn("");   		break;
		  case 12: satMHigh=intIn("high math sat: ");			i = intIn("");   		break;
		  case 13: costLow=intIn("low cost: ");					i = intIn("");   		break;
		  case 14: costHigh=intIn("High cost: ");				i = intIn("");   		break;
		  case 15: finAidPercLow=intIn("low financial aid: ");	 i = intIn("");  		break;
		  case 16: finAidPercHigh=intIn("high financial aid: "); i = intIn("");  		break;
		  case 17: applicantsLow=intIn("low applicants num: ");	 i = intIn("");  		break;
		  case 18: applicantsHigh=intIn("high applicants num: "); i = intIn(""); 		break;
		  case 19: admittedLow=intIn("low admitted num: ");		  i = intIn(""); 		break;
		  case 20: admittedHigh=intIn("high admitted num: "); 	 i = intIn(""); 		break;
		  case 21: enrolledLow=intIn("low enrolled num: ");		 i = intIn(""); 		break;
		  case 22: enrolledHigh=intIn("high enrolled num: ");	 i = intIn("");  		break;
		  case 23: acadScaleLow=intIn("low academic scale: ");	 i = intIn("");  		break;
		  case 24: acadScaleHigh=intIn("high academic scale: ");  i = intIn(""); 		break;
		  case 25: socLifeScaleLow=intIn("low social life scale: ");i = intIn(""); 		break;
		  case 26: socLifeScaleHigh =intIn("high social life scale: ");i = intIn("");  break;
		  case 27: qualLifeScaleLow=intIn("low qual life scale: ");		i = intIn(""); break;
		  case 28: qualLifeScaleHigh=intIn("high qual life scale: ");i = intIn(""); 	break;
		  case 29: emphases.add(strIn("Add an emphasis"));			i = intIn(""); 	break;
		  
		  
		  default: System.out.println("ERROR: Invalid input");			break; 
	  }
	  }while(i != 30);
	  uList = sc.search(schoolName,  state,  location,  control, studentsLow,
	            studentsHigh,  femPercLow,  femPercHigh,  satVLow,  satVHigh, 
	            satMLow,  satMHigh,  costLow,  costHigh,  finAidPercLow,  finAidPercHigh,
	            applicantsLow,  applicantsHigh,  admittedLow,  admittedHigh, 
	            enrolledLow,  enrolledHigh,  acadScaleLow,  acadScaleHigh,  socLifeScaleLow,
	            socLifeScaleHigh,  qualLifeScaleLow,  qualLifeScaleHigh, 
	            emphases);
	  //uList=sc.search("Y", "", "", "", 0, 999999, 0, 99, 0, 999, 0, 999, 0, 99999, 0, 99, 0, 99999, 0, 99, 0, 999999, 0, 99999, 0, 99999, 0, 99, emphases);
	  return uList;
	 
  }
  /**
   * Displays the result of all universiy names in a list of Universities.
   * @param  c  The list of Universities stored in DB
   */
  public void viewSearchedSchools(ArrayList<University> c){
    System.out.println("Here is the result of searching: ");
    for(University u: c){
      System.out.println(u.getName());
    }
  }
  
  /**
   * Takes the save to list command and add the school to the saved school list 
   * @param  u The select university to save   
   */
  public void saveToSavedSchoolList(University u){
    ufc.saveToSavedSchoolList(u);
    this.user=(GeneralUser)db.getUser(user.getUsername());
    ufc.updateUser(user);
    System.out.println("Success");
    homePage();
  }
  
  /**
   * Removes a school from  the saved school list 
   * @param  u  The select university to remove 
   */
  public void removeSavedSchool(University u){
    ufc.removeSavedSchool( u);
    this.user=(GeneralUser)db.getUser(user.getUsername());
    ufc.updateUser(user);
    System.out.println("Success!" + "Your current list of saved schools are: " + user.getSavedSchools() + '\n');
    homePage();
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
