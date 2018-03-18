/*
 * File: Admin.java
 */
package entities;
/**
 * Creates an admin 
 * @author Will Haanen
 * @version February 24, 2018
 */

public class Admin extends Account
{
  
  /*
   * Creates an account whose type is Admin
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
    public String getDetails() {
  	  
  	  return "\n" + "***ACCOUNT INFORMATION****** \n"
  			+"First name:    " + "\t" + "\t" + this.getFirstName() + "\n" 
			   + "Last Name:   " + "\t" + "\t"+ this.getLastName() + "\n" + 
			   "Account Status: " + "\t" + this.getActive() + "\n" +
			   "Password:      " + "\t" + "\t" + this.getPassword() + "\n"; 
    }
}