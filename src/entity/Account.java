/** 
 * File: Account.java
 */
package entity;


/**
 * Account is an abstract that is extends Admin and GeneralUser. 
 * The purpose of this class is to define the behavior of an Account for the CMC System.
 * The accessor methods in this class return information about an Account. 
 * The mutator methods allow the information of an Account to be manipulated. 
 * 
 *
 * @author  Nimco Hussein
 * @version April 8th, 2018
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
   * type
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
    
  /**USER CANNOT SET OWN USERNAME,ADMINS CANNOT SET USERNAMES AT ALL
   * Sets the username of an Account
   * 
   * @param String    newUsername  
   */
  public void setUsername (String newUsername)
  {
	if(newUsername.length() == 0 ||newUsername.matches(" ")){
		  throw new IllegalArgumentException("Error! You must enter a non-empty String to set the username field!");
	}
    else
    {
    	this.username = newUsername; 
    }
	
	   
  }
  
  /**
   * Sets the password of an Account
   * 
   * @param String    newPassword 
   * @throws  IllegalArgumentException  when the parameter is not of type String  
   */
  public void setPassword(String newPassword) {
    
    if((newPassword.length() == 0) || (newPassword.contains(" "))){
		  throw new IllegalArgumentException("Error! You must enter a non-empty String to set the password field!");
	  }
    else
    {
    	this.password = newPassword;
    }
   
  }
  
  /**
   * Sets the firstName of an Account
   * 
   * @param String firstName    
   * @throws  IllegalArgumentExcetion when parameter is empty or blank
   */
  public void setFirstName(String firstName) 
  {

	  if(firstName.length() == 0 ){
		  throw new IllegalArgumentException("Error! You can only enter a non empty String to set the first name field!");
	  }
	  
	  else
	  {
		  this.firstName = firstName; 
	  }
  }
    
  /**
   * Sets the lastName of an Account
   * 
   * @param String lastName
   * @throws  IllegalArgumentException  when the parameter is not of type String    
   */
  public void setLastName(String lastName) 
  {
	  if(lastName.length() == 0 ){
		  throw new IllegalArgumentException("Error! You can only enter a non empty String to set the last name field!");
	  }
	  
	  else
	  {
		  this.lastName = lastName; 
	  }
  }
    
  /**
   * Sets the type of an Account to either a 'a' for Admin or 'u' for User
   * 
   * @param char type    
   * @throws  IllegalArgumentException  when the parameter is not of type char
   */
  public void setType(char newType) 
  {
	  if( (newType != 'u') || (newType != 'a') )
	  {
		  //System.out.println("new type:  " + newType);
		  //throw new IllegalArgumentException("Error! You can only enter a single digit char to set the type!");
	  }
	  else 
	  {
		  this.type = newType; 
	  }
	  
  }          
  
    
  /**
   * Sets the activity of an Account
   * 
   * @param char activity 
   * @throws  IllegalArgumentException  when the parameter is not of type char 
   */
  public void setActive(char activity)
  {
	 
	  if( (activity != 'Y') || (activity != 'N')  ) {
		   
		  System.out.println("Actvity to be set: " + activity);
		//throw new IllegalArgumentException("Error! You can only enter a single digit char of either 'Y' or 'N' to set the activity ss!");  
	  }
	  
	  else 
	  {
		 // System.out.println("we lit"); 
		  this.active = activity;
	  }
  }
  
    
  /**
   * Returns all the account information
   * 
   * @return  ArrayList<String> list of account information 
   */
  public abstract String getDetails();
}