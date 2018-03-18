package interfaces;
import java.util.Scanner;
import controllers.*;
/*
 * File:AccountInterface.java
 */

/**
 * The starting terminal in which the user will log into the system
 * @author Ian Bush
 * @version March 15, 2018
 */
public class AccountInterface
{
  private AccountController ac;
  
  /**
   * Constructor 
   */
  public AccountInterface()
  {
    ac = new AccountController();
  }
  
  /**
   * Logs the user in
   * @param usr the username of the user
   * @param pass the password of the user
   */
  public void login(String usr, String password)
  {
    
   if(ac.login(usr, password) == false)
   {
	   this.start();
   }
  }
  
  /**
   * Logs the user out of the system
   * 
   */
  public void logout()
  {
    if(ac.loginStatus == true) // user is logged on
    {
    	ac.logout();
    	System.out.println("You have been logged out of the system");
    }
      
    this.start();
  }
  
 
  
  /**
   * Starting point for the user
   */
  public void start()
  {
    @SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
    System.out.print("Please enter your username: ");
    String u = sc.nextLine();
    System.out.print("Now enter your password: ");
    String p = sc.nextLine();
    this.login(u,p);
    
  }
}