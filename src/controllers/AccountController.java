/** 
 * File: AccountController.java
 */
package controllers;
import entities.*;
import interfaces.*;

 
/**
 * AccountController is a class that is controlled by AccountInterface
 * The purpose of this class is to validate the login information when a GeneralUser or Admin logs in.
 * 
 *
 * @author  Nimco Hussein
 * @version March 20 2018
 */
public class AccountController  
{
  /** an Account object that stores account information */
  private Account account;
  
  /** an Account object that stores account information */	
  public boolean loginStatus;
  
  /**A default constructor that creates an AccountController object 
    */
  public AccountController()
  {
  }
  
  /**Log in method takes username and password and return true or false, if the information belongs to an account
    * If the credentials that are added are verified, then based on the type a GeneralUser or Admin object 
    * will be created
    * 
    * @param username entered username of user attempting to login
    * @param password entered password of user attempting to login
    * 
    * @return true if user was logged in false if they were not
    */ 
  public boolean login(String username, String password)
  {
	 
    // find a user in the database   
    DBController db =  new DBController();
    Account matchedUser = db.getUser(username); 
    
    if(matchedUser.getActive() == 'N')
    {
    	System.out.println("Error: Your account is deactivated and you cannot be logged in");
    	this.loginStatus = false;
    	return false;
    }

    if(matchedUser.getUsername().equals("") && matchedUser.getPassword().equals(""))
    {
    	System.out.println("Error: A username and password was not entered.");
        this.loginStatus = false;
        return false;
    	
    }
    //check if the matchedUser is a dummy user, if it is do not CONTINUE
    else if(matchedUser.getUsername().equals("DummyUser"))
    { 
      //The user name and password associated with this account is not registered 
      System.out.println("Error: The username is not registered ");
      this.loginStatus = false;
      return false;
    }
    else if(!matchedUser.getPassword().equals(password))
    {
      //print the username and password do not match an an associated account
      System.out.println("Error: The username and password do not match");
      this.loginStatus = false;
      return false;
    }
    
    else { 
      
      this.account = matchedUser;  
      this.loginStatus = true;
      char type = account.getType(); // get the type associated with this account 
      
      if(type == 'a') 
      {
        AdminInterface ai = new AdminInterface();
        System.out.println("Login successful");
        ai.homepage(); 
        this.loginStatus = true;
        return true;
      }
      else if(type == 'u')
      {
        UserInterface ui = new UserInterface((GeneralUser)matchedUser);  
        System.out.println("Login successful");
        this.loginStatus = true;
        ui.homePage();
        return true;
      }
      else
      {
        System.out.println("Error: There is something wrong with the account in the database. Please try again.");
        return false;
      }
    }
    
  }
  
  /** The logout method will set their account login status to false.
    */ 
  public void logout() 
  {
   
    this.loginStatus = false;   
  } 
  
}