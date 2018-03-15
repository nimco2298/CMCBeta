/** 
 * File: Account.java
 */
package entities;
import java.util.*;  //Java's ArrayList<ET>


/**
 * Account is an abstract that is extends Admin and GeneralUser. 
 * The purpose of this class is to define the behavior of an Account for the CMC System.
 * The accessor methods in this class return information about an Account. 
 * The mutator methods allow the information of an Account to be manipulated. 
 * 
 *
 * @author  Nimco Hussein
 * @version March 15, 2018
 */
public abstract class Account {
  
  /** username of an Account  */
  public String username; 
  /** password of an Account */
  private String password;
  /** activity status of an Account */
  private char active;
  /** firstName associated with an Account */
  private String firstName;
  /** The lastName associated with an Account */
  private String lastName;
  /** The type of an Account */
  private char type;
  
   /** The string representation of a list of saved universities */
  private ArrayList<String> savedUniversities;
  
 
  /**
   * Return the username of an Account
   * 
   * @return String  username
   */
  public String getUsername()
  {
    return this.username;
  }
  
  /**
   * Return the password of an Account
   * 
   * @return String  password
   */
  public String getPassword()
  {
    return this.password;
  }
  
  /**
   * Return the firstName of an Account
   * 
   * @return String  firstName
   */
  public String getFirstName()
  {
    return this.firstName;
  }
  
  /**
   * Return the lastname of an Account
   * 
   * @return String  lastName
   */
  public String getLastName()
  {
    return this.lastName;
  }
  
  /**
   * Return the type of an Account
   * 
   * @return String  type
   */
  public char getType() 
  {
    return this.type;
  }
  
  /**
   * Return the activity status of an Account
   * 
   * @return char active
   */
  public char getActive() 
  {
    return this.active;
  }
    
  /**
   * Sets the username of an Account
   * 
   * @param String    newUsername  
   */
  public void setUsername (String newUsername)
  {
    this.username = newUsername;
  }
  
  /**
   * Sets the password of an Account
   * 
   * @param String    newPassword  
   */
  public void setPassword(String newPassword)
  {
    this.password = newPassword;
  }
  
  /**
   * Sets the firstName of an Account
   * 
   * @param String firstName    
   */
  public void setFirstName(String firstName) 
  {
    this.firstName = firstName;
  }
    
  /**
   * Sets the lastName of an Account
   * 
   * @param String lastName    
   */
  public void setLastName(String lastName) 
  {
  
     this.lastName = lastName;
  }
    
  /**
   * Sets the type of an Account
   * 
   * @param char type    
   */
  public void setType(char type) 
  {
     this.type = type;
  }          
  
    
  /**
   * Sets the activiy of an Account
   * 
   * @param char activity  
   */
  public void setActive(char activity)
  {
     this.active = activity;
  }
  
    
  /**
   * Returns all the account information
   * 
   * @return  ArrayList<String> list of account information 
   */
  public String getDetails()
  {
    return  "First name: " + this.firstName + "\n" 
   + "Last Name:  " + this.lastName + "\n" + 
   "Account Status: " + this.active + "\n" +
   "Password: " + this.password + "\n" +
   "Saved Schools List: " + this.savedUniversities;
  }
}