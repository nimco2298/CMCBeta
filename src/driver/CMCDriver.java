
/*
 * File:CMCDriver.java
 */

package driver;
import java.util.*;

import interfaces.AccountInterface;




/**
 * The main driver class for the CMC project.
 * 
 * @author Ian Bush
 * @version March 20, 2018
 */
@SuppressWarnings("unused")
public class CMCDriver
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to CMC SystemMMMM!");
		AccountInterface ai = new AccountInterface();
		ai.start();
	}
}