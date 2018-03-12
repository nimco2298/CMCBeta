package interfaces;
import java.util.Scanner;
import controllers.*;
/*
 * File:AccountInterface.java
 */

/**
 * The starting terminal in which the user will log into the system
 * @author Ian Bush
 * @version February 23, 2018
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
	 // System.out.println("Test");
    if (ac.login(usr, password))
    {
      System.out.println("Login successful");
    }
    else
    {
      System.out.println("Please try again");
      this.start();
    }
  }
  
  /**
   * Logs the user out of the system
   * 
   */
  public void logout()
  {
    ac.logout();
    System.out.println("You have been logged out of the system");
  }
  
 
  
  /**
   * Starting point for the user
   */
  public void start()
  {
    @SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
    System.out.print("Please enter your username: ");
    String u = sc.next();
    System.out.print("Now enter your password: ");
    String p = sc.next();
    this.login(u,p);
    //sc.close();
  }
}