/*
 * File: Admin.java
 */
package entities;

/**
 * Admin is a class that is an Account and stores an admin's information.
 * @author Will Haanen
 * @version March 20, 2018
 */
public class Admin extends Account
{
  
  /** A constructor that creates a Admin object
  *
  * @param String  username   an admin's username
  * @param String   password  a admin's password
  * @param char     active    Y if user is active, N if not
  * @param String  firstName  a admin's first name
  * @param String  lastName   a admins's last name 
  * 
  */ 
    public Admin(String username, String password, char active, String firstName, String lastName)
  {
    this.setUsername(username);
    this.setPassword(password);
    this.setActive(active);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setType('a');
  }
    
    /**Prints the Admin account details
     * 
     * @return String the string representation of Account details
     */
    public String getDetails() {
  	  
  	  return "\n" + "***ACCOUNT INFORMATION****** \n"
  			+"First name:    " + "\t" + "\t" + this.getFirstName() + "\n" 
			   + "Last Name:   " + "\t" + "\t"+ this.getLastName() + "\n" + 
			   "Username: " + "\t" + this.getUsername() + "\n" +
			   "Password:      " + "\t" + "\t" + this.getPassword() + "\n" +
			   "Account Status: " + "\t" + this.getActive(); 
    }
}