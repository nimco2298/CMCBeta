/* 
 * File: SearchController.java
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
    sc = new SearchController();
  }
  
  Scanner scan = new Scanner(System.in);
  
  /**
   * redirects the user to the homepage
   */
  public void homePage(){
    System.out.println( "=======================================" 			+'\n'+
    		            "Welcome " + user.getFirstName() +  " " + user.getLastName() + "! " +'\n'+
    					"Would would you like to do?" 	+'\n'+'\t'+
                       		"s: Search for Schools"						+'\n'+'\t'+
                        	"m: Manage Saved Schools"						+'\n'+'\t'+
                        	"p: Manage Your Profile"						+'\n'+'\t'+
                        	"q: Quit"				+'\n'+
                        "Enter Here: ");
   // System.out.print("s for search, m for manage saved schools, p for manage profile, q for quit program: ");
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
	      String s1 = scan.next("s for save and v for view:");
	      if(s1.equals("s")){
	        String sName= scan.next("please enter the name of the school you want to save:");
	        saveToSavedSchoolList(db.getUniversity(sName));
	      }
	      else{
	        String sName= scan.next("please enter the name of the school you want to view details:");
	        viewSchoolDetailsAndTop5(db.getUniversity(sName));
	      }
	    }
    }
    else if(str.equals("m") || str.equals("M")){  //MANAGE SAVED SCHOOLS
      viewSavedSchools();
     

      System.out.print("Please select r for remove or  v for view: ");
      String s3 = scan.next();
      if(s3.equals("r")){
    	System.out.print("please enter the name of the school you want to remove: ");
        String sName= scan.next();
        removeSavedSchool(db.getUniversity(sName));
      }
      //!user.getSavedSchools().isEmpty()
      else if(!user.getSavedSchools().isEmpty()){
    	
    	System.out.print("please enter the name of the school you want to view details: ");
        String sName= scan.next();
        while(!user.getSavedSchools().contains(sName)) {
        	System.out.println("no match, please enter again");
        	sName= scan.next();
        }
        viewSavedSchoolDetails(db.getUniversity(sName));

	      }
	      else {
	    	  System.out.print("Invalid input ");  
	      }
        
      }


     else if (str.equals("p") || str.equals("P")) {  
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
   
    }

  
  /**
   * takes the view details command and displays the details
   */
  public void viewSchoolDetailsAndTop5(University u)
  {
	  
    viewSavedSchoolDetails(u);
    viewSearchedSchools(sc.recSearch(u));
    String s2 = scan.next("Do you want to save it? y/n:");
    if(s2.equals("y")){
      saveToSavedSchoolList(u);
    }
    else{
      homePage();
    }
  }
  /**
   * takes the view saved school command and displays the saved school list
   */
  public void viewSavedSchools(){
    for(String u: user.getSavedSchools()){
      System.out.println(u);
    }
  }
  /**
   * takes the view saved school details command and displays the details
   */
  public void viewSavedSchoolDetails(University u){
    System.out.println("School details: ");
    u.printDetails();
  }
  /**
   * 
   */
  public boolean isEmpty(){
    
    return true;
  }
  
  /**
   * takes the view profile command and displays the profile of the user
   */
  public void viewProfile(){
    System.out.println("Profile details: \n"+this.user.getDetails());
  }
  
  
  /**
   * takes the edit file command and redirect the user to the edit page
   */
  public void editProfile(){
	  System.out.print("What would you like to edit:" + '\n' +
                            "1: FirstName" + '\n' +
                            "2: LastName" + '\n' +
                            "3: Password" + '\n' +                            
                            "q: Quit: ");
    String prompt = scan.next();
    while(!prompt.equals("q")){
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
          
        default:
          System.out.println("Invalid input");
          break;
      }
      System.out.print("What would you like to edit:" + '\n' +
                       "1: FirstName" + '\n' +
                       "2: LastName" + '\n' +
                       "3: Password" + '\n' +   
                       "q: Quit: ");
      prompt = scan.next();
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
   * @return the schools that have matched this criteria
   */
  public ArrayList<University> searchForSchools()
  {
	  String schoolName = "", state = "", location = "", control = "";
	  int studentsLow = 0, studentsHigh = 999999, femPercLow = 0, femPercHigh = 100, satVLow = -1,
			  satVHigh = 1000, satMLow = -1, satMHigh = 1000, costLow = 0, costHigh = 999999, finAidPercLow = 0, 
			  finAidPercHigh = 100, applicantsLow = 0, applicantsHigh = 1000000, admittedLow = 0, 
			  admittedHigh = 100, enrolledLow = 0, enrolledHigh =100, acadScaleLow = 1, acadScaleHigh = 5, 
			  socLifeScaleLow = 1, socLifeScaleHigh = 5, qualLifeScaleLow = 1, qualLifeScaleHigh = 5;
	  ArrayList<String> emphases = new ArrayList<String>();
	  ArrayList<University> uList = new ArrayList<University>();
	  System.out.println("Enter the desired search criteria");
	  int i = intIn("");
	  do
	  {
		  switch(i)
		  {
		  case 1: schoolName=strIn("School Name: ");  		  	  		break;
		  case 2: state = strIn("State: ");           		  	  		break;
		  case 3: location=strIn("Location: ");       		      		break;
		  case 4:  control=strIn("Control: ");                    		break;
		  case 5: studentsLow=intIn("low num of students: ");	  		break;
		  case 6: studentsHigh=intIn("high num of students: ");	  		break;
		  case 7: femPercLow=intIn("low % of female: "); 		  		break;
		  case 8: femPercHigh =intIn("high % of female: ");		  		break;
		  case 9: satVLow=intIn("low verbal sat: ");			  		break;
		  case 10: satVHigh=intIn("high verbal sat: ");			  		break;
		  case 11: satMLow=intIn("low math sat: ");				  		break;
		  case 12: satMHigh=intIn("high math sat: ");			  		break;
		  case 13: costLow=intIn("low cost: ");					  		break;
		  case 14: costHigh=intIn("High cost: ");				  		break;
		  case 15: finAidPercLow=intIn("low financial aid: ");	  		break;
		  case 16: finAidPercHigh=intIn("high financial aid: ");  		break;
		  case 17: applicantsLow=intIn("low applicants num: ");	  		break;
		  case 18: applicantsHigh=intIn("high applicants num: "); 		break;
		  case 19: admittedLow=intIn("low admitted num: ");		  		break;
		  case 20: admittedHigh=intIn("high admitted num: "); 	 		break;
		  case 21: enrolledLow=intIn("low enrolled num: ");		 		break;
		  case 22: enrolledHigh=intIn("high enrolled num: ");	  		break;
		  case 23: acadScaleLow=intIn("low academic scale: ");	  		break;
		  case 24: acadScaleHigh=intIn("high academic scale: ");  		break;
		  case 25: socLifeScaleLow=intIn("low social life scale: ");	break;
		  case 26: socLifeScaleHigh =intIn("high social life scale: "); break;
		  case 27: qualLifeScaleLow=intIn("low qual life scale: ");		break;
		  case 28: qualLifeScaleHigh=intIn("high qual life scale: ");	break;
		  case 29: emphases.add(strIn("Add an emphasis"));				break;
		  case 30:  uList = sc.search(schoolName,  state,  location,  control, studentsLow,
	            studentsHigh,  femPercLow,  femPercHigh,  satVLow,  satVHigh, 
	            satMLow,  satMHigh,  costLow,  costHigh,  finAidPercLow,  finAidPercHigh,
	            applicantsLow,  applicantsHigh,  admittedLow,  admittedHigh, 
	            enrolledLow,  enrolledHigh,  acadScaleLow,  acadScaleHigh,  socLifeScaleLow,
	            socLifeScaleHigh,  qualLifeScaleLow,  qualLifeScaleHigh, 
	            emphases);
		  break;
		  default: System.out.println("ERROR: Invalid input");			break; 
	  }
	  }while(i != 30);
	 
	  return uList;
	 
  }
  /**
   * displays the result of searching
   * @param  ArrayList<University>  
   */
  public void viewSearchedSchools(ArrayList<University> c){
    System.out.println("Here is the result of searching: ");
    for(University u: c){
      System.out.println(u.getName());
    }
  }
  /*
   *  takes the save to list command and add the school to the saved school list 
   */
  public void saveToSavedSchoolList(University u){
    ufc.saveToSavedSchoolList(u);
    this.user=(GeneralUser)db.getUser(user.getUsername());
    ufc.updateUser(user);
    System.out.println("Success");
    homePage();
  }
  /*
   * removes the selected school
   */
  public void removeSavedSchool(University u){
    ufc.removeSavedSchool( u);
    this.user=(GeneralUser)db.getUser(user.getUsername());
    ufc.updateUser(user);
    System.out.println("Success");
    homePage();
  }
  
  public String strIn(String ss){
    System.out.print(ss);
    String re= scan.next();
    return re;
  }
  public int intIn(String ss){
    System.out.print(ss);
    int re= scan.nextInt();
    return re;
  }
}

