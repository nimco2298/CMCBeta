/* 
 * File: SearchController.java
 * Description: This class displays the UI and take all user's commands
 * before passing them to the Controller
 * 
 * @author Karld Bai
 * @version Feb 22 2018
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
  
  public UserInterface(GeneralUser user){
    this.ufc= new UserFuncController(user);
    this.user=user;
    this.db=new DBController();
  }
  
  Scanner scan= new Scanner(System.in);
  
  /**
   * redirects the user to the homepage
   */
  public void homePage(){
    
    System.out.print("s for search, m for manage saved schools, p for manage profile: ");
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
	      String schoolName=strIn("School Name: ");
	      String state= strIn("State: ");
	      String location=strIn("Location: ");
	      String control=strIn("Control: ");
	      int studentsLow=intIn("low num of students: ");                           
	      int studentsHigh=intIn("high num of students: ");
	      int femPercLow=intIn("low % of female: ");
	      int femPercHigh =intIn("high % of female: ");
	      int satVLow=intIn("low verbal sat: ");
	      int satVHigh=intIn("high verbal sat: ");                       
	      int satMLow=intIn("low math sat: ");
	      int satMHigh=intIn("high math sat: ");
	      int costLow=intIn("low cost: ");
	      int costHigh=intIn("High cost: ");
	      int finAidPercLow=intIn("low financial aid: ");
	      int finAidPercHigh=intIn("high financial aid: ");                       
	      int applicantsLow=intIn("low applicants num: ");
	      int applicantsHigh=intIn("high applicants num: ");
	      int admittedLow=intIn("low admitted num: ");
	      int admittedHigh=intIn("high admitted num: ");                            
	      int enrolledLow=intIn("low enrolled num: ");
	      int enrolledHigh=intIn("high enrolled num: ");
	      int acadScaleLow=intIn("low academic scale: ");
	      int acadScaleHigh=intIn("high academic scale: ");
	      int socLifeScaleLow=intIn("low social life scale: ");                      
	      int socLifeScaleHigh =intIn("high social life scale: ");  
	      int qualLifeScaleLow=intIn("low qual life scale: ");  
	      int qualLifeScaleHigh=intIn("high qual life scale: ");  
	      ArrayList<String> emphases=new ArrayList<String>();
	      String sss=strIn("emphases: ");
	      while(!sss.equals(" ")){
	        emphases.add(sss);
	        sss=strIn("some more emphases? space to finish: ");
	      }
	      ArrayList<University> ulist= new ArrayList<University>();
	      ulist= sc.search( schoolName,  state,  location,  control, studentsLow,
	                       studentsHigh,  femPercLow,  femPercHigh,  satVLow,  satVHigh, 
	                       satMLow,  satMHigh,  costLow,  costHigh,  finAidPercLow,  finAidPercHigh,
	                       applicantsLow,  applicantsHigh,  admittedLow,  admittedHigh, 
	                       enrolledLow,  enrolledHigh,  acadScaleLow,  acadScaleHigh,  socLifeScaleLow,
	                       socLifeScaleHigh,  qualLifeScaleLow,  qualLifeScaleHigh, 
	                       emphases);
	      
	      viewSearchedSchools(ulist);
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
    else if(str.equals("m")){
      viewSavedSchools();
     
      System.out.print("r for remove and v for view: ");
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
    	  homePage();
      }
    }
    else{
      viewProfile();
      System.out.print("Do you want to edit? y/n: ");
      String s4 = scan.next();
      if(s4.equals("y")){
        editProfile();
      }
      else
        homePage();
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
   * @param schoolName the name of the school
   * @param state      the state of the school
   * @param location   the location of the school
   * @param control    the area of the school
   * @param students   the number of students in the school
   * @param femPerc    the female percentage of the school
   * @param satV       the SAT verbal score of the school
   * @param satM       the SAT math score of the school
   * @param cost       the cost of the school
   * @param finAidPerc the financial aid percentage of the school
   * @param applicants the number of applicants in the school
   * @param admitted   the number of admitted students in the school
   * @param enrolled   the number of enrolled students in the school
   * @param acadScale  the academic scale of the school
   * @param socScale   the social scale of the school
   * @param qualScale  the quality scale of the schoos
   * @param emphases   the department fields (emphasis) of the school
   * 
   * @return Collection the schools that have matched this criteria
   */
  public Collection<University> searchForShools(String schoolName, String state, String location, String control,int studentsLow,
                                    int studentsHigh, int femPercLow, int femPercHigh, int satVLow, int satVHigh, 
                                    int satMLow, int sayMHigh, int costLow, int costHigh, int finAidPercLow, int finAidPercHigh,
                                    int applicantsLow, int applicantsHigh, int admittedLow, int admittedHigh, 
                                    int enrolledLow, int enrolledHigh, int acadScaleLow, int acadScaleHigh, int socLifeScaleLow,
                                    int socLifeScaleHigh, int qualLifeScaleLow, int qualLifeScaleHigh, 
                                    ArrayList<String> emphases)
  {
    
    return null;
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
  
//para for copy: String schoolName, String state, String location, String control,int students, int femPerc, int satM, int cost, int finAidPerc, int applicants, int admitted, ArrayList<String> emphases
}

