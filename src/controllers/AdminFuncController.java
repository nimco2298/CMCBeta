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
    DBController dbc = new DBController();
    char type = information.get(5).charAt(0);
    if(type == 'a'){
      // new Admin(username, password, active, firstName, lastName, type)
      Admin ad = new Admin( information.get(0),  information.get(1),  information.get(2).charAt(0),  information.get(3),  information.get(4));
      dbc.addAccount(ad);
    }
    else if(type == 'u'){
      // new GeneralUser( firstName, lastName, active, type, password)
      GeneralUser gu = new GeneralUser( information.get(0),  information.get(1), information.get(5).charAt(0), information.get(2), information.get(3), new ArrayList<String>());
      dbc.addAccount(gu);
    }
  }
  
  /**
   * View a list of users; gets a list of users from the database
   */
  public void viewUsers(){
    DBController dbc = new DBController();
    ArrayList<Account> accountList = dbc.getAccounts();
    System.out.println("First" + '\t'+'\t' + "Last" + '\t'+'\t' + "Username" + '\t'+'\t' + "Password" + '\t'+'\t' + "Type" + '\t'+'\t' + "Status" +'\n'+ 
                       "========================================================================================================================");
    for(Account user: accountList){
      System.out.println(user.getFirstName()+'\t'+'\t'+user.getLastName()+'\t'+'\t'+user.getUsername()+'\t'+'\t'+
                         user.getPassword()+'\t'+'\t'+user.getType()+'\t'+'\t'+user.getActive());
    }
  }
  
  
  /**
   * View a list of universities; gets a list of universities from the database
   */
  public void viewUniversities(){
    DBController dbc = new DBController();
    ArrayList<University> list = dbc.getUniversities();
    for(University u: list){
      System.out.println("School: " + u.getName() +'\n'+
                         "State: " + u.getState() +'\n'+
                         "Location: " + u.getLocation() +'\n'+
                         "Control: " + u.getControl() +'\n'+
                         "# of Students: " + u.getStudents() +'\n'+
                         "% Females: " + u.getFemPerc() +'\n'+
                         "SAT Verbal: " + u.getSatV() +'\n'+
                         "SAT Math: " + u.getSatM() +'\n'+
                         "Expenses: " + u.getCost() +'\n'+
                         "% with Financial Aid: " + u.getFinAidPerc() +'\n'+
                         "# of Applicants: " + u.getApplicants() +'\n'+
                         "% Admitted: " + u.getAdmitted() +'\n'+
                         "% Enrolled: " + u.getEnrolled() +'\n'+
                         "Academic Scale (1-5): " + u.getAcadScale() +'\n'+
                         "Social Scale (1-5): " + u.getSocScale() +'\n'+
                         "Quality of Life Scale (1-5): " + u.getQualScale() +'\n'+
                         "======================================================");
    }
  }
  
  
  /**
   * Accesses the Database and saves changes made to an account
   * 
   * @param account the account of the user
   */
  public void saveAccountChanges(Account account){
    DBController dbc = new DBController();
    dbc.updateAccount(account);
  }
  
  /**
   * Deletes a university
   * 
   * @param univ the university to be deleted
   */
  public void delete(University univ){
    DBController dbc = new DBController();
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
    DBController dbc = new DBController();
    return dbc.getUniversity(univ);
  }
  
  /**
   * Finds an account based on username
   * 
   * @param account the name of the requested account
   * @return the user
   */
  public Account getAccount(String account) throws NullPointerException
  {
    DBController dbc = new DBController();
    return dbc.getUser(account);
  }
  
  /**
   * Save changes made to a university
   * 
   * @param univ the university
   */
  public void saveUnivChanges(University univ){
    DBController dbc = new DBController();
    dbc.updateUniversity(univ);
  }
  
  /**
   * Adds a University
   * 
   * @param univ the University
   */
  public void addUniversity(University univ){
    DBController dbc = new DBController();
    dbc.addNewUniversity(univ);
  }
}