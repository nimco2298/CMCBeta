/* 
 * File: SearchController.java
 * Description: This class get all the universities from DBController and
 * return one or more schools based user's input condition
 * 
 * @author Karld Bai
 * @version Feb 22 2018
 */
package controllers;
import entities.*;
import java.util.*;

/**
 * Performs all of the search methods for the project
 * 
 * @author Zhaochen Bai
 * @version March 15, 2018
 */
public class SearchController{
  
  private DBController db = new DBController();
  /*
   * @param schoolName the name of the school
   * @param state the state the school is at
   * @param location the location of the school
   * @param control the control
   * @param studentsLow the low value of the number of students
   * @param studentHigh the high value of the number of students
   * @param 
   * @return a list of searching result
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
      pa=false, pe=false, as=false, ss=false, qls=false, em=false;
    ArrayList<University> ulist = db.getUniversities();
    for(University u: ulist)
    {
      if(u.getName().contains(schoolName))
        n=true;
      if(u.getState().contains(state))
        s=true;
      if(u.getLocation().contains(location))
        l=true;
      if(u.getControl().contains(control))
        c=true;
      if(studentsLow<u.getStudents() && u.getStudents()<studentsHigh)
        ns=true;
      if(femPercLow<u.getFemPerc() && u.getFemPerc()<femPercHigh)
        pf=true;
      if(satVLow<u.getSatV() && u.getFemPerc()<satVHigh)
        sv=true;
      if(satMLow<u.getSatM() && u.getSatM()<satMHigh)
        sm=true;
      if(costLow<u.getCost() && u.getCost()<costHigh)
        exp=true;
      if(finAidPercLow<u.getFinAidPerc() && u.getFinAidPerc()<finAidPercHigh)
        pfa=true;
      if(applicantsLow<u.getApplicants() && u.getApplicants()<applicantsHigh)
        na=true;
      if(admittedLow<u.getAdmitted() && u.getAdmitted()<admittedHigh)
        pa=true;
      if(enrolledLow<u.getEnrolled() && u.getEnrolled()<enrolledHigh)
        pe=true;
      if(acadScaleLow<u.getAcadScale() && u.getAcadScale()<acadScaleHigh)
        as=true;
      if(socLifeScaleLow<u.getSocScale() && u.getSocScale()<socLifeScaleHigh)
        ss=true;
      if(qualLifeScaleLow<u.getQualScale() && u.getQualScale()<qualLifeScaleHigh)
        qls=true;
      for(String str: emphases){
        if(u.getEmphases().contains(str))
          em=true;
      }
      if(n && s && l && c && ns && pf && sv && sm && exp && pfa && na && pa && pe && as && ss && qls && em)
      {
        rlist.add(u);
      }
    }
    return rlist;
  }
  
  
  
  /*
   * @param a University object that the user selected
   * @return a list of searching result
   */
  public ArrayList<University> recSearch(University cu){
    
    ArrayList<University> rlist = new ArrayList<University>();
    ArrayList<University> ulist = db.getUniversities();
    
    int n=ulist.size();
    int[][] data = new int[n-1][11];
    int[] diff = new int[11];
    int[] distList = new int[n-1];
    Map<Integer, University> map=new HashMap<Integer, University>();
    
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
    int max,min,dist=0;
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
    for(University u: ulist){
      int j=0,index;
      index=ulist.indexOf(cu);
     if(!u.getName().equals(cu.getName()))
       dist++;
     if(!u.getState().equals(cu.getState()))
       dist++;
     if(!u.getLocation().equals(cu.getLocation()))
       dist++;
     if(!u.getControl().equals(cu.getControl()))
       dist++;
     for(int i=0; i<12; i++){
       dist+=(data[i][index]-data[i][j])/diff[i];
     }
     map.put(dist, u);
     distList[j]=dist;
      j++;
    }
    Arrays.sort(distList);
    rlist.add(map.get(distList[0]));
    rlist.add(map.get(distList[1]));
    rlist.add(map.get(distList[2]));
    rlist.add(map.get(distList[3]));
    rlist.add(map.get(distList[4]));
    
    
    
    
    return rlist;
  }
 
  
}
