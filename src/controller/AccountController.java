/** 
 * File: AccountController.java
 */
package controller;
import entity.*;
import interfaces.*;

 
/**
 * AccountController is a class that is controlled by AccountInterface
 * The purpose of this class is to validate the login information when a GeneralUser or Admin logs in.
 * 
 *
 * @author  Nimco Hussein
 * @version April 4th 2018
 */
public class AccountController  
{
  /** an Account object that stores account information */
  private Account account;
  
  /** an Account object that stores account information */	
  public boolean loginStatus;
  

  
  /**Log in method takes username and password and return true or false, if the information belongs to an account
    * If the credentials that are added are verified, then based on the type a GeneralUser or Admin object 
    * will be created
    * 
    * @param username the entered username of user attempting to login
    * @param password  the entered password of user attempting to login
    * 
    * @throws IllegalArgumentException   if input is empty
    * 
    * @return true if user was logged in, false if they were not
    */ 
  public boolean login(String username, String password)
  {
    // find a user in the database   
    DBController db =  new DBController();
    Account matchedUser = db.getUser(username); 
    
    //C1
    if(username.length() == 0 || password.length() == 0)
    {
    	//S2
    	throw new IllegalArgumentException("You must enter a non-empty String for log in credentials");
    }
    
    //C2
    if(matchedUser.getActive() == 'N')
    {
        //S2
    	//System.out.println("Error: Your account is deactivated and you cannot be logged in");
    	this.loginStatus = false;
    	return false;
    }
//    //C2
//    else if(matchedUser.getUsername().equals("") && matchedUser.getPassword().equals(""))
//    {
//    	//S2
//    	System.out.println("Error: A username and password was not entered.");
//        this.loginStatus = false;
//        return false;
//    	
//    }
     
    
    //C3
   else if(matchedUser.getUsername().equals("DummyUser"))
   { 
     //S3
      System.out.println("Error: The username is not registered.");
      this.loginStatus = false;
      return false;
    }
    
    //C3
    else if(!matchedUser.getPassword().equals(password) && matchedUser.getUsername().equals(username))
    {
      //S3
      System.out.println("Error: The password entered does not match an account.");
      this.loginStatus = false;
      return false;
    }
    
    //C4
    else if(!matchedUser.getUsername().equals(username) && matchedUser.getPassword().equals(password))
    {
      //S4
      System.out.println("Error: The username entered does not match an account");
      this.loginStatus = false;
      return false;
    }
    
    //C5
    else if(matchedUser.getUsername().equals(username) && matchedUser.getPassword().equals(password)){ 
    	
      //S5
      this.account = matchedUser;  
      this.loginStatus = true;
      char type = account.getType(); // get the type associated with this account 
      
      //C6
      if(type == 'a') 
      {
    	//S6
        AdminInterface ai = new AdminInterface();
        System.out.println("Login successful");
        ai.homepage(); 
        this.loginStatus = true;
        return true;
      }
      //C7
      else if(type == 'u')
      {
    	//S7
        UserInterface ui = new UserInterface((GeneralUser)matchedUser);  
        System.out.println("Login successful");
        this.loginStatus = true;
        ui.homePage();
        return true;
      }
    }
      //C8
      else if(!matchedUser.getUsername().equals(username) && !matchedUser.getPassword().equals(password))
      {
    	//S8
        System.out.println("Error: The username and pasword entered doesnt match a registered account. Please try again.");
        this.loginStatus = false;
       
      }
    return false;
    
  }
  
  /** The logout method will set their account login status to false.
    */ 
  public void logout() 
  {
    this.loginStatus = false;   
  } 
  
}