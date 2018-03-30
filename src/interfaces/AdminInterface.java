
/*
 * File:AdminInterface.java
 */
package interfaces;
import controllers.*;


/**
 * AdminInterface contains all the functionalities which the admin should be able to do
 * @author Ian Bush, Sara Laufers
 * @version March 17, 2018
 */
public class AdminInterface {
  //==================================INSTANCE VARIABLES==========================
  private AdminFuncController ad;
  //==================================CONSTRUCTORS==========================
  /**
   * Constructor for the AdminInterface
   */
  public AdminInterface() {
    this.ad = new AdminFuncController();
  }
  //==================================METHODS==========================
  /**
   * Brings the admin to their homepage
   */
  public void homepage() {
	  ad.homepage();
  }
}