/* 
 * File: SearchController.java
 */
package controller;
import entity.*;
import java.util.*;

/**
 * Description: This class gets all the universities from DBController and
 * returns one or more schools based on the request from the user
 * 
 * @author Zhaochen Bai
 * @version March 15, 2018
 */
public class SearchController{
  
  private DBController db = new DBController();
  /**
   * Description: generates a list of schools that match the search criteria
   * 
   * @param schoolName the name of the school
   * @param state the state in which the school is located
   * @param location the location of the school (urban, suburban, small city, etc.)
   * @param control the control
   * @param studentsLow the minimum number of students
   * @param studentHigh the maximum number of students
   * @param femPercLow the minimum percentage of female students
   * @param femPercHigh the maximum percentage of female students
   * @param satVLow the minimum average on SAT verbal score of admitted students
   * @param satVHigh the maximum average on SAT verbal score of admitted students
   * @param satMLow the minimum average on SAT math score of admitted students
   * @param satMHigh the maximum average on SAT math score of admitted students
   * @param costLow the minimum annual cost of attending
   * @param costHigh the maximum annual cost of attending
   * @param finAidPercLow the minimum percentage of students receiving financial aid
   * @param finAidPercHigh the maximum percentage of students receiving financial aid
   * @param applicantsLow the minimum number of applicants
   * @param applicantsHigh the maximum number of applicants
   * @param admittedLow the minimum percentage of admitted applicants
   * @param admittedHigh the maximum percentage of admitted applicants
   * @param enrolledLow the minimum percentage of enrolled applicants
   * @param enrolledHigh the maximum percentage of enrolled applicants
   * @param acadScaleLow the minimum academic ranking (on a scale of 1-5, 1 being worst and 5 being best)
   * @param acadScaleHigh the maximum academic ranking (on a scale of 1-5, 1 being worst and 5 being best)
   * @param socLifeScaleLow the minimum social life ranking (on a scale of 1-5, 1 being worst and 5 being best)
   * @param socLifeScaleHigh the maximum social life ranking (on a scale of 1-5, 1 being worst and 5 being best)
   * @param qualLifeScaleLow the minimum quality of life ranking (on a scale of 1-5, 1 being worst and 5 being best)
   * @param qualLifeScaleHigh the maximum quality of life ranking (on a scale of 1-5, 1 being worst and 5 being best)
   * @param emphases the academic emphases of the universities
   * @return a list of universities that match the search criteria
   * 
   */
  public ArrayList<University> search(String schoolName, String state, String location, String control,int studentsLow,
                                      int studentsHigh, int femPercLow, int femPercHigh, int satVLow, int satVHigh, 
                                      int satMLow, int satMHigh, int costLow, int costHigh, int finAidPercLow, int finAidPercHigh,
                                      int applicantsLow, int applicantsHigh, int admittedLow, int admittedHigh, 
                                      int enrolledLow, int enrolledHigh, int acadScaleLow, int acadScaleHigh, int socLifeScaleLow,
                                      int socLifeScaleHigh, int qualLifeScaleLow, int qualLifeScaleHigh, 
                                      ArrayList<String> emphases){
    ArrayList<University> rlist = new ArrayList<University>();
    boolean n=false, s=false, l=false, c=false, ns=false, pf=false, sv=false, sm=false, exp=false, pfa=false, na=false, 
      pa=false, pe=false, as=false, ss=false, qls=false, em=true;
    ArrayList<University> ulist = db.getUniversities();
    for(University u: ulist)
    {
    	n=false; s=false; l=false; c=false; ns=false; pf=false; sv=false; sm=false; exp=false; pfa=false; na=false; 
	      pa=false; pe=false; as=false; ss=false; qls=false; em=true;
      if(u.getName().contains(schoolName))
        n=true;
      if(u.getState().contains(state))
        s=true;
      if(u.getLocation().contains(location))
        l=true;
      if(u.getControl().contains(control))
        c=true;
      if(studentsLow<=u.getStudents() && u.getStudents()<=studentsHigh)
        ns=true;
      if(femPercLow<=u.getFemPerc() && u.getFemPerc()<=femPercHigh)
        pf=true;
      if(satVLow<=u.getSatV() && u.getFemPerc()<=satVHigh)
        sv=true;
      if(satMLow<=u.getSatM() && u.getSatM()<=satMHigh)
        sm=true;
      if(costLow<=u.getCost() && u.getCost()<=costHigh)
        exp=true;
      if(finAidPercLow<=u.getFinAidPerc() && u.getFinAidPerc()<=finAidPercHigh)
        pfa=true;
      if(applicantsLow<=u.getApplicants() && u.getApplicants()<=applicantsHigh)
        na=true;
      if(admittedLow<=u.getAdmitted() && u.getAdmitted()<=admittedHigh)
        pa=true;
      if(enrolledLow<=u.getEnrolled() && u.getEnrolled()<=enrolledHigh)
        pe=true;
      if(acadScaleLow<=u.getAcadScale() && u.getAcadScale()<=acadScaleHigh)
        as=true;
      if(socLifeScaleLow<=u.getSocScale() && u.getSocScale()<=socLifeScaleHigh)
        ss=true;
      if(qualLifeScaleLow<=u.getQualScale() && u.getQualScale()<=qualLifeScaleHigh)
        qls=true;
      
      for(String str: emphases){
        if(!u.getEmphases().contains(str)) {
          em=false;
          break;
        }
      }
      
      if(n && s && l && c && ns & pf && sv && sm && exp && pfa && na && pa && pe && as && ss && qls && em)
      {
        rlist.add(u);
      }
    }
    return rlist;
  }
  
 
  /**
   * Finds the five universities closest to the selected university
   * 
   * @param a University that the user selected
   * @return a list of five universities that are the closest to the selected university
   */
  public ArrayList<University> recSearch(University cu){
    
    ArrayList<University> rlist = new ArrayList<University>();
    ArrayList<University> ulist = db.getUniversities();
    
    int n=ulist.size();
    int[][] data = new int[12][n];
    int[] diff = new int[12];
    double[] distList = new double[n];
    Map<Double, University> map=new HashMap<Double, University>();
    
    for(int i=0; i<n; i++){    
        data[0][i]=ulist.get(i).getStudents();
    }
    for(int i=0; i<n; i++){    
        data[1][i]=ulist.get(i).getFemPerc();
    }
    for(int i=0; i<n; i++){    
        data[2][i]=ulist.get(i).getSatV();
    }
    for(int i=0; i<n; i++){    
        data[3][i]=ulist.get(i).getSatM();
    }
    for(int i=0; i<n; i++){    
        data[4][i]=ulist.get(i).getCost();
    }
    for(int i=0; i<n; i++){    
        data[5][i]=ulist.get(i).getFinAidPerc();
    }
    for(int i=0; i<n; i++){    
        data[6][i]=ulist.get(i).getApplicants();
    }
    for(int i=0; i<n; i++){    
        data[7][i]=ulist.get(i).getAdmitted();
    }
    for(int i=0; i<n; i++){    
        data[8][i]=ulist.get(i).getEnrolled();
    }
    for(int i=0; i<n; i++){    
        data[9][i]=ulist.get(i).getAcadScale();
    }
    for(int i=0; i<n; i++){    
        data[10][i]=ulist.get(i).getSocScale();
    }
    for(int i=0; i<n; i++){    
        data[11][i]=ulist.get(i).getQualScale();
    }
    int max,min;
    double dist=0;
    for(int i=0; i<12; i++){
      max=data[i][0];
      min=data[i][0];
      for(int j=0; j<n; j++){
        if(data[i][j]>max)
          max=data[i][j];
        if(data[i][j]<min)
          min=data[i][j];
      }
      diff[i]=max-min;
    }
    //int index=ulist.indexOf(cu);
    int index=-1;
    for(University u: ulist)
    {
    	if(u.getName().equals(cu.getName()))
    		break;
    	index++;
    }
    int j=0;
    for(University u: ulist)
    {
      
      dist=0;
      if(!u.getName().equals(cu.getName()))
       dist++;
      if(!u.getState().equals(cu.getState()))
       dist++;
      if(!u.getLocation().equals(cu.getLocation()))
       dist++;
      if(!u.getControl().equals(cu.getControl()))
       dist++;
      for(int i=0; i<12; i++)
      {
       dist+=Math.abs((double)(data[i][index]-data[i][j])/(double)diff[i]);
       
     }
     map.put(dist, u);
     distList[j]=dist;
      j++;
    }
    Arrays.sort(distList);
    //the first one will be itself so we don't add distList[0]
    rlist.add(map.get(distList[1]));
    rlist.add(map.get(distList[2]));
    rlist.add(map.get(distList[3]));
    rlist.add(map.get(distList[4]));
    rlist.add(map.get(distList[5]));
    return rlist;
  }
 
  
}
