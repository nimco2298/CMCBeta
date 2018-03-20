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

public class AdminFuncController{
  
  // ================================= INSTANCE VARIABLES =================================
  /** An Admin class named admin */
  @SuppressWarnings("unused")
  private Admin admin;
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
   * Adds a new user account into the Database
   * Note: The active property should be true inside the information list
   * 
   * @param information a list of a user's properties including one that determines if the user is an admin or a general user (type)
   */
  public void addAccount(ArrayList<String> information){ // DONE I THINK!!!!!!!!!!!!!!!
    char type = information.get(5).charAt(0);
    if(type == 'a'){
      // new Admin(username, password, active, firstName, lastName, type)
      Admin ad = new Admin( information.get(0),  information.get(1),  information.get(2).charAt(0),  information.get(3),  information.get(4));
      dbc.addAccount(ad);
    }
    else if(type == 'u'){
      // new GeneralUser( firstName, lastName, active, username, password, arrayList)
      GeneralUser gu = new GeneralUser( information.get(3),  information.get(4), information.get(2).charAt(0), information.get(0), information.get(1), new ArrayList<String>());
      dbc.addAccount(gu);
    }
  }
  
  /**
   * View a list of users; gets a list of users from the database
   */
  public void viewUsers(){
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
  public void viewUniversities(){
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
   * @return the 
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
   * Save changes made to a university
   * 
   * @param univ the university
   */
  public void saveUnivChanges(University univ){
    dbc.updateUniversity(univ);
  }
  
  /**
   * Adds a University
   * 
   * @param univ the University
   */
  public void addUniversity(University univ){
    dbc.addNewUniversity(univ);
    addEmphases(univ);
  }
  
  /**
   * Adds all emphases to a university in the database
   * 
   * @param univ the University
   */
  public void addEmphases(University univ) {
	ArrayList<String> emphases = univ.getEmphases();
	for(String emphasis: emphases) {
	  addAnEmphasis(univ, emphasis);
	}
  }
  
  /**
   * Adds one emphasis to a university in the database
   * 
   * @param univ the University
   * @param emphasis the emphasis to add
   */
  public void addAnEmphasis(University univ, String emphasis) {
  	dbc.addEmphasis(univ, emphasis);
  }
  
  /**
   * Deletes all emphases from a university in the database
   * 
   * @param univ the University
   */
  public void deleteEmphases(University univ) {
	ArrayList<String> emphases = univ.getEmphases();
	for(String emphasis: emphases) {
	  deleteAnEmphasis(univ, emphasis);
	}
  }
  
  /**
   * Deletes one emphasis from a university in the database
   * 
   * @param univ the University
   * @param emphasis the emphasis to delete
   */
  public void deleteAnEmphasis(University univ, String emphasis) {
	dbc.deleteEmphasis(univ, emphasis);
  }
}