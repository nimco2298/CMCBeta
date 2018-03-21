/** 
  * File: GeneralUser.java
  */


package entities;
import java.util.*; //Java's ArrayList<ET>

/**
 * GeneralUser is a class that is an Account and stores a user's information.
 *
 * @author  Nimco Hussein
 * @version March 20, 2018
 */
public class GeneralUser extends Account
{

  /* the user's list of saved schools  */
  private ArrayList<String> savedUniversities;
 
  
  /** A constructor that creates a GeneralUser object
  * @param String firstName   a user's first name
  * @param String  lastName   a user's last name 
  * @param char     active    Y if user is active, N if not
  * @param char     type      the type of a user which is 'u'
  * @param String   password  a user's password
  * @param ArrayList<String> savedUniversities  a list of user's saved school
  */
  public GeneralUser(String firstName, String lastName, char active,String username, String password,ArrayList<String> savedUniversities) //add in paramaeter for savedUniverisisyd
  {
    this.setUsername(username);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setActive(active);
    this.setType('u');
    this.setPassword(password);
    this.savedUniversities = savedUniversities; // initializes all the users saved schools from DB
  }
  
  /** Returns the list of saved schools as a string
   * 
  * @return ArrayList<String>   list of saved schools of a General User
  */ 
  public ArrayList<String> getSavedSchools()
  {
   
   return this.savedUniversities;
      
  } 
  public String getDetails() {
	  
	  return "\n" + "***ACCOUNT INFORMATION*** \n"
			  +"First name:    " + "\t" + "\t" + this.getFirstName() + "\n" 
			   + "Last Name:   " + "\t" + "\t"+ this.getLastName() + "\n" + 
			   "Account Status: " + "\t" + this.getActive() + "\n" +
			   "Password:      " + "\t" + "\t" + this.getPassword() + "\n" +
			   "Saved Schools List: " + this.savedUniversities + "\n";
			   
  }
   
}