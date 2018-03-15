//import java.util.Scanner;
package driver;
import java.util.*;
import interfaces.AccountInterface;
/*
 * File:CMCDriver.java
 */
/**
 * The main class for the CMC project
 * @author Ian Bush
 * @version February 25, 2018
 */
@SuppressWarnings("unused")
public class CMCDriver
{
  public static void main(String [] args)
  {
    System.out.println("Welcome to CMC");
    AccountInterface ai = new AccountInterface();
    ai.start();
  }
}