package controllers;
import java.util.ArrayList;
import java.util.Scanner;
import entities.*;

/**
 * Controls functions for the general user 
 * @author Will Haanen
 * @version March 19, 2018
 */

public class UserFuncController
{
  private DBController db;
  private SearchController sc;
  private GeneralUser gu;
  
  /**
   * Creates a UserFuncController for the given user 
   * 
   * @param newUser The user that the userFuncController will control
   */
  public UserFuncController(GeneralUser newUser)
  {
    gu = newUser;
    db = new DBController();
    sc = new SearchController();
  }
  /**
   * Gets the list of saved schools for a specific general user
   * 
   * @return The list of saved schools
   */
  public ArrayList<String> getSavedSchools()
  {
    return gu.getSavedSchools();
  }
  
  /**
   * Adds a school to the users saved school list
   * 
   * @param university the school to be added to the list
   */
  public void saveToSavedSchoolList(University university)
  {
    db.addSchoolToUserList(gu,university);
  }
  
  public ArrayList<University> searchForSchools()
  {
	  String prompt = "1: School Name \n2: State \n3: Location \n4: Control: "
		  		+ "\n5: Minimum Number of Students:"
		  		+ "\n6: Maximum Number of Students:"
		  		+ "\n7: Minimum % of Female Students:"
		  		+ "\n8: Maximum % of Female Students:"
		  		+ "\n9: Minimum Average SAT Verbal Score:"
		  		+ "\n10: Maximum Average SAT Verbal Score:"
		  		+ "\n11: Minimum Average SAT Math Score:"
		  		+ "\n12: Maximum Average SAT Math Score"
		  		+ "\n13: Minimum annual tuition:"
		  		+ "\n14: Maximum annual tuition:"
		  		+ "\n15: Minimum % of Students Receiving Financial Aid: "
		  		+ "\n16: Maximum % of Students Receiving Financial Aid: "
		  		+ "\n17: Minimum number of applicants "
		  		+ "\n18: Maximum number of applicants "
		  		+ "\n19: Minimum admittance rate of applicants "
		  		+ "\n20: Maximum admittance rate of applicants "
		  		+ "\n21: Minimum enrollment rate of admitted students:  "
		  		+ "\n22: Maximum enrollment rate of admitted students: "
		  		+ "\n23: Minimum Academic Rating (Scale 1-5): "
		  		+ "\n24: Maximum Academic Rating (Scale 1-5): "
		  		+ "\n25: Minimum Social Life Rating (Scale 1-5): "
		  		+ "\n26: Maximum Social Life Rating (Scale 1-5): "
		  		+ "\n27: Minimum Quality of Life Rating (Scale 1-5): "
		  		+ "\n28: Maximum Quality of Life Rating (Scale 1-5): "
		  		+ "\n29: Add an emphasis of study: \n\nOr press '0' to search";
	  String schoolName = "", state = "", location = "", control = "";
	  int studentsLow = 0, studentsHigh = 999999, femPercLow = 0, femPercHigh = 100, satVLow = 0,
			  satVHigh = 1000, satMLow = 0, satMHigh = 1000, costLow = 0, costHigh = 99999, finAidPercLow = 0, 
			  finAidPercHigh = 100, applicantsLow = 0, applicantsHigh = 99999, admittedLow = 0, 
			  admittedHigh = 100, enrolledLow = 0, enrolledHigh =100, acadScaleLow = 0, acadScaleHigh = 99, 
			  socLifeScaleLow = 0, socLifeScaleHigh = 99, qualLifeScaleLow = 0, qualLifeScaleHigh = 99;
	  ArrayList<String> emphases = new ArrayList<String>();
	  ArrayList<University> uList = new ArrayList<University>();
	  System.out.println("Enter the desired search criteria");
	  int i = intIn(prompt);
	  do
	  {
		  switch(i)
		  {
		  case 1: schoolName=strIn("School Name: "); i = intIn(prompt);  					break;
		  case 2: state = strIn("State: "); i = intIn(prompt);          		  	  		break;
		  case 3: location=strIn("Location: ");   i = intIn(prompt);    		      		break;
		  case 4:  control=strIn("Control: "); i = intIn(prompt);                   		break;
		  case 5: studentsLow=intIn("low num of students: ");i = intIn(prompt);	  			break;
		  case 6: studentsHigh=intIn("high num of students: ");	 i = intIn(prompt); 		break;
		  case 7: femPercLow=intIn("low % of female: "); 		 i = intIn(prompt); 		break;
		  case 8: femPercHigh =intIn("high % of female: ");		 i = intIn(prompt); 		break;
		  case 9: satVLow=intIn("low verbal sat: ");			 i = intIn(prompt); 		break;
		  case 10: satVHigh=intIn("high verbal sat: ");			i = intIn(prompt);   		break;
		  case 11: satMLow=intIn("low math sat: ");				i = intIn(prompt);   		break;
		  case 12: satMHigh=intIn("high math sat: ");			i = intIn(prompt);   		break;
		  case 13: costLow=intIn("low cost: ");					i = intIn(prompt);   		break;
		  case 14: costHigh=intIn("High cost: ");				i = intIn(prompt);   		break;
		  case 15: finAidPercLow=intIn("low financial aid: ");	 i = intIn(prompt);  		break;
		  case 16: finAidPercHigh=intIn("high financial aid: "); i = intIn(prompt);  		break;
		  case 17: applicantsLow=intIn("low applicants num: ");	 i = intIn(prompt);  		break;
		  case 18: applicantsHigh=intIn("high applicants num: "); i = intIn(prompt); 		break;
		  case 19: admittedLow=intIn("low admitted num: ");		  i = intIn(prompt); 		break;
		  case 20: admittedHigh=intIn("high admitted num: "); 	 i = intIn(prompt); 		break;
		  case 21: enrolledLow=intIn("low enrolled num: ");		 i = intIn(prompt); 		break;
		  case 22: enrolledHigh=intIn("high enrolled num: ");	 i = intIn(prompt);  		break;
		  case 23: acadScaleLow=intIn("low academic scale: ");	 i = intIn(prompt);  		break;
		  case 24: acadScaleHigh=intIn("high academic scale: ");  i = intIn(prompt); 		break;
		  case 25: socLifeScaleLow=intIn("low social life scale: ");i = intIn(prompt); 		break;
		  case 26: socLifeScaleHigh =intIn("high social life scale: ");i = intIn(prompt);  break;
		  case 27: qualLifeScaleLow=intIn("low qual life scale: ");		i = intIn(prompt); break;
		  case 28: qualLifeScaleHigh=intIn("high qual life scale: ");i = intIn(prompt); 	break;
		  case 29: emphases.add(strIn("Add an emphasis"));			i = intIn(prompt); 	break; 
		  default: System.out.println("ERROR: Invalid input");			break; 
		  }
	  }while(i != 0);
	  uList = sc.search(schoolName,  state,  location,  control, studentsLow,
	            studentsHigh,  femPercLow,  femPercHigh,  satVLow,  satVHigh, 
	            satMLow,  satMHigh,  costLow,  costHigh,  finAidPercLow,  finAidPercHigh,
	            applicantsLow,  applicantsHigh,  admittedLow,  admittedHigh, 
	            enrolledLow,  enrolledHigh,  acadScaleLow,  acadScaleHigh,  socLifeScaleLow,
	            socLifeScaleHigh,  qualLifeScaleLow,  qualLifeScaleHigh, 
	            emphases);
	  
	  return uList; 
  }
  
  /**
   * Removes a specific university from the users saved schools list
   * 
   * @param university the school to be removed from the list
   */
  public void removeSavedSchool(University university)
  {
    db.removeSchoolFromSavedSchoolList(gu, university);
  }
  
  /**
   * Saves changes made to a profile by a user
   * 
   * @param password users password
   * @param firstName users first name
   * @param lastName users last name
   */
  public void submitProfileChanges(String firstName, String lastName,String password)
  {
	//ADD IN INPUT VALIDATION SO METHODS ONLY ACCCEPTS STRINGS  
    gu.setPassword(password);
    gu.setFirstName(firstName);
    gu.setLastName(lastName);
    db.updateAccount(gu); 
  }
  
  /**
   * Update the controlled user if changes were made to the account
   * 
   * @param newUser The updated user object
   */
  public void updateUser(GeneralUser newUser)
  {
   gu = newUser;
  }
  
  /**
   * Takes a string and prints it, and prompts user to enter another String
   * @param ss       a string that will be printed 
   * @return String  a new string the User entered
   */
  public String strIn(String ss){
	  Scanner scan = new Scanner(System.in);  
	  System.out.print(ss);
	    String re= scan.next();
	    scan.close();
	    return re;
	  }
  
  public int intIn(String ss){
	  Scanner scan = new Scanner(System.in);  
	    System.out.print(ss);
	    int re= scan.nextInt();
	    scan.close();
	    return re;
	  }
}