/** 
 * File: University.java
 * 
 * @author Sara Laufers
 * @version February 22, 2018
 * 
 * Description: The University Class holds the properties and 
 * appropriate methods to access these properties of a university
 */
//package CMCClasses;
package entity;
import java.util.ArrayList;
public class University{
  
  // ================================= INSTANCE VARIABLES =================================
  /** The name of the school */
  private String schoolName;
  /** The state of where the Univserity is located ie: Minnesota, California ... */
  private String state;
  /** Suburban, small-city, urban, unknown (-1) ... */
  private String location;
  /** Either private, state, city, or unknown (-1) */
  private String control;
  /** Number of students in the University */
  private int students;
  /** Percentage of female students */
  private int femPerc;
  /** Average SAT verbal score of admitted students */
  private int satV;
  /** Average SAT math score of admitted students */
  private int satM;
  /** The cost of paying for the university */
  private int cost;
  /** Percentage of financial aid that the University offers */
  private int finAidPerc;
  /** Number of applicants */
  private int applicants;
  /** Percentage of applicants that get admitted*/
  private int admitted;
  /** Percentage of admitted students that choose to enroll */
  private int enrolled;
  /** The standard academic scale of the university (1-5)*/
  private int acadScale;
  /** The social life scale of the university (1-5)*/
  private int socScale;
  /** The quality of life scale of the university */
  private int qualScale;
  /** The academic fields the school is best known for ie: Tech, Nursing, etc*/
  private ArrayList<String> emphases;
  
  // ================================= CONSTRUCTOR =================================
  
  /**
   * Constructs an empty/default University
   */
  public University(){
    this.schoolName = "";
    this.state = "0";
    this.location = "";
    this.control = "";
    this.students = 0;
    this.femPerc = 0;
    this.satV = 0;
    this.satM = 0;
    this.cost = 0;
    this.finAidPerc = 0;
    this.applicants = 0;
    this.admitted = 0;
    this.enrolled = 0;
    this.acadScale = 1;
    this.socScale = 1;
    this.qualScale = 1;
    this.emphases = new ArrayList<String>();
  }
  
  /**
   * Constructs a University with specified parameters
   * 
   * @param schoolName the name of the school
   * @param state      the state of the school
   * @param location   the location of the school
   * @param control    the area of the school
   * @param students   the number of students in the school
   * @param femPerc    the female percentage of the school
   * @param satV       the SAT verbal score of the school
   * @param satM       the SAT math score of the school
   * @param cost       the cost of the school
   * @param finAidPerc the financial aid percentage of the school
   * @param applicants the number of applicants in the school
   * @param admitted   the number of admitted students in the school
   * @param enrolled   the number of enrolled students in the school
   * @param acadScale  the academic scale of the school
   * @param socScale   the social scale of the school
   * @param qualScale  the quality scale of the school
   * @param emphases   the department fields (emphasis) of the school
   */
  public University(String schoolName, String state, String location, String control, int students, int femPerc, int satV, int satM, int cost,
                    int finAidPerc, int applicants, int admitted, int enrolled, int acadScale, int socScale, int qualScale, ArrayList<String> emphases){
    this.schoolName = schoolName;
    this.state = state;
    this.location = location;
    this.control = control;
    this.students = students;
    this.femPerc = femPerc;
    this.satV = satV;
    this.satM = satM;
    this.cost = cost;
    this.finAidPerc = finAidPerc;
    this.applicants = applicants;
    this.admitted = admitted;
    this.enrolled = enrolled;
    this.acadScale = acadScale;
    this.socScale = socScale;
    this.qualScale = qualScale;
    this.emphases = emphases;
  }
  
  // ================================= METHODS =================================
  
  /**
   * Returns the name of the university
   * 
   * @return schoolName the name of the university
   */
  public String getName(){
    return schoolName;
  }
  
  /**
   * Returns the state of the university
   * 
   * @return state the state of the university
   */
  public String getState(){
    return state;
  }
  
  /**
   * Returns the location of the university
   * 
   * @return location the location of the university
   */
  public String getLocation(){
    return location;
  }
  
  /**
   * Returns the control of the university
   * 
   * @return control the control of the university
   */
  public String getControl(){
    return control;
  }
  
  /**
   * Returns the number of students in the university
   * 
   * @return students the students in the university
   */
  public int getStudents(){
    return students;
  }
  
  /**
   * Returns the percentage of females of the university
   * 
   * @return femPerc the female-percentage of the university
   */
  public int getFemPerc(){
    return femPerc;
  }
  
  /**
   * Returns the SAT verbal score of the university
   * 
   * @return satV the SAT verbal score of the university
   */
  public int getSatV(){
    return satV;
  }
  
  /**
   * Returns the SAT math score of the university
   * 
   * @return satM the SAT math score of the university
   */
  public int getSatM(){
    return satM;
  }
  
  /**
   * Returns the cost of the university
   * 
   * @return cost the cost of the university
   */
  public int getCost(){
    return cost;
  }
  
  /**
   * Returns the financial aid percentage of the university
   * 
   * @return finAidPerc the financial aid percentage of the university
   */
  public int getFinAidPerc(){
    return finAidPerc;
  }
  
  /**
   * Returns the number of applicants of the university
   * 
   * @return applicants the applicants of the university
   */
  public int getApplicants(){
    return applicants;
  }
  
  /**
   * Returns the number of admitted students of the university
   * 
   * @return admitted the admitted students of the university
   */
  public int getAdmitted(){
    return admitted;
  }
  
  /**
   * Returns the number of enrolled students of the university
   * 
   * @return enrolled the enrolled students of the university
   */
  public int getEnrolled(){
    return enrolled;
  }
  
  /**
   * Returns the academic scale of the university
   * 
   * @return acadScale the academic scale of the university
   */
  public int getAcadScale(){
    return acadScale;
  }
  
  /**
   * Returns the social scale of the university
   * 
   * @return socScale social scale the name of the university
   */
  public int getSocScale(){
    return socScale;
  }
  
  /**
   * Returns the quality scale of the university
   * 
   * @return qualScale the quality scale of the university
   */
  public int getQualScale(){
    return qualScale;
  }
  
  /**
   * Returns the list of emphases of the university
   * 
   * @return emphases the emphases of the university
   */
  public ArrayList<String> getEmphases(){
    return emphases;
  }
  
  /**
   * Returns the details of the university including all properties except emphases
   * 
   * @return detailList the list of the properties (except emphases) of the university
   */
  public ArrayList<String> getUniversityDetails(){
    ArrayList<String> detailList = new ArrayList<String>();
    detailList.add(this.getName());
    detailList.add(this.getState());
    detailList.add(this.getLocation());
    detailList.add(this.getControl());
    detailList.add(Integer.toString(this.getStudents()));
    detailList.add(Integer.toString(this.getFemPerc()));
    detailList.add(Integer.toString(this.getSatV()));
    detailList.add(Integer.toString(this.getSatM()));
    detailList.add(Integer.toString(this.getCost()));
    detailList.add(Integer.toString(this.getFinAidPerc()));
    detailList.add(Integer.toString(this.getApplicants()));
    detailList.add(Integer.toString(this.getAdmitted()));
    detailList.add(Integer.toString(this.getEnrolled()));
    detailList.add(Integer.toString(this.getAcadScale()));
    detailList.add(Integer.toString(this.getSocScale()));
    detailList.add(Integer.toString(this.getQualScale()));
    return detailList;
  }
  
  /**
   * Sets the name of the university
   * 
   * @param schoolName the name of the school
   */
  public void setName(String schoolName){
    this.schoolName = schoolName;
  }
  
  /**
   * Sets the state of the university
   * 
   * @param state the state of the school
   */
  public void setState(String state){
    this.state = state;
  }
  
  /**
   * Sets the location of the university
   * 
   * @param location the location of the school
   */
  public void setLocation(String location){
    this.location = location;
  }
  
  /**
   * Sets the control of the university
   * 
   * @param control the control of the school
   */
  public void setControl(String control){
    this.control = control;
  }
  
  /**
   * Sets the number of students of the university
   * 
   * @param students the students of the school
   */
  public void setStudents(int students){
    this.students = students;
  }
  
  /**
   * Sets the female percentage of the university
   * 
   * @param femPerc the name of the school
   */
  public void setFemPerc(int femPerc){
    this.femPerc = femPerc;
  }
  
  /**
   * Sets the SAT verbal score of the university
   * 
   * @param satV the SAT verbal score of the school
   */
  public void setSatV(int satV){
    this.satV = satV;
  }
  
  /**
   * Sets the SAT math score of the university
   * 
   * @param satM the SAT math score of the school
   */
  public void setSatM(int satM){
    this.satM = satM;
  }
  
  /**
   * Sets the cost of the university
   * 
   * @param cost the cost of the school
   */
  public void setCost(int cost){
    this.cost = cost;
  }
  
  /**
   * Sets the financial aid percentage of the university
   * 
   * @param finAidPerc the financial aid percentage of the school
   */
  public void setFinAidPerc(int finAidPerc){
    this.finAidPerc = finAidPerc;
  }
  
  /**
   * Sets the number of applicants of the university
   * 
   * @param applicants the applicants of the school
   */
  public void setApplicants(int applicants){
    this.applicants = applicants;
  }
  
  /**
   * Sets the number of addmitteed students of the university
   * 
   * @param admitted the admitted students of the school
   */
  public void setAdmitted(int admitted){
    this.admitted = admitted;
  }
  
  /**
   * Sets the number of enrolled students of the university
   * 
   * @param enrolled the  enrolled students of the school
   */
  public void setEnrolled(int enrolled){
    this.enrolled = enrolled;
  }
  
  /**
   * Sets the academic scale of the university
   * 
   * @param acadScale the academic scale of the school
   */
  public void setAcadScale(int acadScale){
    this.acadScale = acadScale;
  }
  
  /**
   * Sets the social scale of the university
   * 
   * @param socScale the social scale of the school
   */
  public void setSocScale(int socScale){
    this.socScale = socScale;
  }
  
  /**
   * Sets the quality scale of the university
   * 
   * @param qualScale the quality scale of the school
   */
  public void setQualScale(int qualScale){
    this.qualScale = qualScale;
  }
  
  /**
   * Adds a department field to the emphases of the university
   * 
   * @param field the department field that the school specializes in
   */
  public void addEmphases(String field){
    this.emphases.add(field);
  }
  
  /**
   * Removes a department field of the university
   * 
   * @param field the department field that the school specializes in
   */
  public void removeEmphases(String field){
    this.emphases.remove(field);
  }
  
  public void removeAllEmphases() {
	  this.emphases = new ArrayList<String>();
  }
  /**
   * Displays all the details for the university
   */
  public void printDetails()
  {
    System.out.println("School Name: " + schoolName);
    System.out.println("State: " + state); 
    System.out.println("Location: " + location); 
    System.out.println("Control: " + control); 
    System.out.println("Number of students: " + students); 
    System.out.println("Female percentage of students: " +  femPerc); 
    System.out.println("Average SAT verbal score: " + satV);
    System.out.println("Average SAT math score: " + satM);
    System.out.println("Cost: " +cost);
    System.out.println("Percentage of financial aid provided by university: " + finAidPerc);
    System.out.println("Number of applicants: " +applicants);
    System.out.println("Percentage of applicants admitted: " + admitted);
    System.out.println("Percentage of admitted students that enroll: " + enrolled);
    System.out.println("The standard academic scale of the university (1-5): " + acadScale);
    System.out.println("The social life scale of the university (1-5): " + socScale);
    System.out.println("The quality of life scale of the university (1-5): " +qualScale);
    System.out.println("Emphasis: ");
    for (String emph: this.emphases)
    {
      System.out.println(emph);
    }
  }
}