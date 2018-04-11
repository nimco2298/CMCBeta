package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import entity.*;
import controller.*;
/**
 * A JUnit class for the SearchController
 * 
 * @author Karld
 * @version April 10, 2018
 */
public class SearchControllerTest {
	private SearchController sc;
	private ArrayList<String> emphases;
	private ArrayList<University> uList;
	private DBController db;
	
	/**
	 * This method initializes the information for testing
	 */
	@Before
	public void setUp() throws Exception {
		sc=new SearchController();
		uList = new ArrayList<University>();
		db = new DBController();
		emphases= new ArrayList<String>();
		
		for(int i=1;i<=5;i++) {
			String name="Z"+i;
			University u=db.getUniversity(name);
			if(u!=null) {
				emphases= u.getEmphases();
				for(String s:emphases) {
					db.deleteEmphasis(u, s);
				}
				db.deleteUniversity(u);
				uList=db.getUniversities();
			}
		}
		
		
		
		emphases= new ArrayList<String>();
	}
	/**
	 * This method clears up the emphases and schools used for testing
	 */
	@After
	public void tearDown() throws Exception {
		if(db.getUniversity("Z1")!=null) {
			emphases= db.getUniversity("Z1").getEmphases();
			for(String s:emphases) {
				db.deleteEmphasis(db.getUniversity("Z1"), s);
			}
			db.deleteUniversity(db.getUniversity("Z1"));
		}
		if(db.getUniversity("Z1")!=null) {
			emphases= db.getUniversity("Z2").getEmphases();
			for(String s:emphases) {
				db.deleteEmphasis(db.getUniversity("Z2"), s);
			}
			db.deleteUniversity(db.getUniversity("Z2"));
		}
		if(db.getUniversity("Z1")!=null) {
			emphases= db.getUniversity("Z3").getEmphases();
			for(String s:emphases) {
				db.deleteEmphasis(db.getUniversity("Z3"), s);
			}
			db.deleteUniversity(db.getUniversity("Z3"));
		}
		if(db.getUniversity("Z1")!=null) {
			emphases= db.getUniversity("Z4").getEmphases();
			for(String s:emphases) {
				db.deleteEmphasis(db.getUniversity("Z4"), s);
			}
			db.deleteUniversity(db.getUniversity("Z4"));
		}
		if(db.getUniversity("Z1")!=null) {
			emphases= db.getUniversity("Z5").getEmphases();
			for(String s:emphases) {
				db.deleteEmphasis(db.getUniversity("Z5"), s);
			}
			db.deleteUniversity(db.getUniversity("Z5"));
		}
		
		
		
		
		
	}

	/**
	 * Test 
	 */
	@Test
	public void testSearchByName() {
		
		uList = sc.search("YALE",  "",  "",  "", 0,
	            99999,  0,  100,  0,  999, 
	            0,  999,  0,  99999,  0,  100,
	            0,  99999,  0,  99999, 
	            0,  99999,  0,  9,  0,
	            9,  0,  9, 
	            emphases);
		assertTrue("Should be YALE",uList.get(0).getName().equals("YALE"));
		assertTrue("Shouldn't have other universities",uList.size()==2);
	}
	
	@Test
	public void testSearchByImcompleteName() {
		
		uList = sc.search("YAL",  "",  "",  "", 0,
	            99999,  0,  100,  0,  999, 
	            0,  999,  0,  99999,  0,  100,
	            0,  99999,  0,  99999, 
	            0,  99999,  0,  9,  0,
	            9,  0,  9, 
	            emphases);
		assertTrue("Should be YALE",uList.get(0).getName().equals("YALE"));
		assertTrue("Shouldn't have other universities",uList.size()==2);		
	}
	@Test
	public void testSearchByWrongName() {
		
		uList = sc.search("YALE?",  "",  "",  "", 0,
	            99999,  0,  100,  0,  999, 
	            0,  999,  0,  99999,  0,  100,
	            0,  99999,  0,  99999, 
	            0,  99999,  0,  9,  0,
	            9,  0,  9, 
	            emphases);
		assertTrue("Should be Empty",uList.size()==0);
	}
	@Test
	public void testSearchByStudent() {
		
		uList = sc.search("",  "",  "",  "", 0,
	            9999,  0,  100,  0,  999, 
	            0,  999,  0,  99999,  0,  100,
	            0,  99999,  0,  99999, 
	            0,  99999,  0,  9,  0,
	            9,  0,  9, 
	            emphases);
		
		assertTrue("Should be 3",uList.size()==1);
	}
	@Test
	public void testSearchByStringAndInt() {
		
		uList = sc.search("YA",  "",  "",  "", 10000,
	            10000,  0,  100,  0,  999, 
	            0,  999,  0,  99999,  0,  100,
	            0,  99999,  0,  99999, 
	            0,  99999,  0,  9,  0,
	            9,  0,  9, 
	            emphases);
		assertTrue("Should be WESLEYAN",uList.get(0).getName().equals("WESLEYAN"));
		assertTrue("Should be YALE",uList.get(1).getName().equals("YALE"));
		assertTrue("Should be YANKTOWN COLLEGE",uList.get(2).getName().equals("YANKTOWN COLLEGE"));
	}
	@Test
	public void testSearchByEmphases() {
		emphases.add("BIOLOGY");
		emphases.add("HISTORY");
		emphases.add("ENGINEERING");
		emphases.add("LIBERAL-ARTS");
		uList = sc.search("",  "",  "",  "", 0,
	            99999,  0,  100,  0,  999, 
	            0,  999,  0,  99999,  0,  100,
	            0,  99999,  0,  99999, 
	            0,  99999,  0,  9,  0,
	            9,  0,  9, 
	            emphases);
		assertTrue("Should be UNIVERSITY OF ROCHESTER",uList.get(0).getName().equals("UNIVERSITY OF ROCHESTER"));
	    assertTrue("Should be UNIVERSITY OF WASHINGTON",uList.get(1).getName().equals("UNIVERSITY OF WASHINGTON"));

	}
	@Test
	public void testRecSearchOrigin() {
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be CLARKSON UNIVERSITY", uList.get(0).getName().equals("CLARKSON UNIVERSITY"));
		assertTrue("Should be TOURO", uList.get(1).getName().equals("TOURO"));
		assertTrue("Should be HAMPSHIRE COLLEGE", uList.get(2).getName().equals("HAMPSHIRE COLLEGE"));
		assertTrue("Should be SUNY PLATTSBURGH", uList.get(3).getName().equals("SUNY PLATTSBURGH"));
		assertTrue("Should be MANHATTANVILLE COLLEGE", uList.get(4).getName().equals("MANHATTANVILLE COLLEGE"));
	}
	
	@Test
	public void testRecSearchWithIntDiff() {
		
		
		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z5",	"NEW YORK","SMALL-CITY",	"PRIVATE",	15000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(1).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(2).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(3).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(4).getName().equals("Z5"));
		

	}
	

	@Test
	public void testRecSearchWithStringDiff() {
		
	
		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z5",	"BEIJING","SMALL-CITY",	"PRIVATE",	10000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addEmphasis(db.getUniversity("Z1"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z2"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z3"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z4"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z5"), "LIBERAL-ARTS");
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(1).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(2).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(3).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(4).getName().equals("Z5"));
	
		
	}
	
	@Test
	public void testRecSearchWithMultiIntDiff() {
		

		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z5",	"NEW YORK","SMALL-CITY",	"PRIVATE",	10000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,5	,	emphases));
		db.addEmphasis(db.getUniversity("Z1"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z2"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z3"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z4"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z5"), "LIBERAL-ARTS");
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(1).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(2).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(3).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(4).getName().equals("Z5"));
		
	
	}
	@Test
	public void testRecSearchWithEmphasesDiff() {
		
		
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		
		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		
		db.addNewUniversity(new University("Z5",	"NEW YORK","SMALL-CITY",	"PRIVATE",	15000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		
		db.addEmphasis(db.getUniversity("Z1"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z2"), "LIBERAL-ARTS");
		db.addEmphasis(db.getUniversity("Z2"), "ARTS");
		db.addEmphasis(db.getUniversity("Z5"), "ARTS");
		uList=sc.recSearch(db.getUniversity("BARD"));
		
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(1).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(2).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(3).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(4).getName().equals("Z5"));

		
	}
	
}
