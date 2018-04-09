package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entity.*;
import controller.*;

public class SearchControllerTest {
	private SearchController sc;
	private ArrayList<String> emphases;
	private ArrayList<University> uList;
	private DBController db;
	
	@Before
	public void setUp() throws Exception {
		sc=new SearchController();
		emphases= new ArrayList<String>();
		uList = new ArrayList<University>();
		db = new DBController();
	}

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
		assertTrue("Should be YALE",uList.size()==0);
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
		assertTrue("Should be 3",uList.size()==3);
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
		assertTrue("Should be YALE",uList.get(0).getName().equals("WESLEYAN"));
		assertTrue("Should be YALE",uList.get(1).getName().equals("YALE"));
		assertTrue("Should be YALE",uList.get(2).getName().equals("YANKTOWN COLLEGE"));
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
		assertTrue("Should be YALE",uList.get(0).getName().equals("UNIVERSITY OF ROCHESTER"));
	    assertTrue("Should be YALE",uList.get(1).getName().equals("UNIVERSITY OF WASHINGTON"));

	}
	@Test
	public void testRecSearchOrigin() {
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be CLARKSON UNIVERSITY", uList.get(0).getName().equals("CLARKSON UNIVERSITY"));
		assertTrue("Should be TOURO", uList.get(0).getName().equals("TOURO"));
		assertTrue("Should be HAMPSHIRE COLLEGE", uList.get(0).getName().equals("HAMPSHIRE COLLEGE"));
		assertTrue("Should be SUNY PLATTSBURGH", uList.get(0).getName().equals("SUNY PLATTSBURGH"));
		assertTrue("Should be MANHATTANVILLE COLLEGE", uList.get(0).getName().equals("MANHATTANVILLE COLLEGE"));
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
		assertTrue("Should be Z2", uList.get(0).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(0).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(0).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(0).getName().equals("Z5"));
		
		db.deleteUniversity(db.getUniversity("Z1"));
		db.deleteUniversity(db.getUniversity("Z2"));
		db.deleteUniversity(db.getUniversity("Z3"));
		db.deleteUniversity(db.getUniversity("Z4"));
		db.deleteUniversity(db.getUniversity("Z5"));
		
	}
	

	@Test
	public void testRecSearchWithStringDiff() {
		
		emphases.add("LIBERAL-ARTS");
		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z5",	"BEIJING","SMALL-CITY",	"PRIVATE",	10000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(0).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(0).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(0).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(0).getName().equals("Z5"));
		
		db.deleteUniversity(db.getUniversity("Z1"));
		db.deleteUniversity(db.getUniversity("Z2"));
		db.deleteUniversity(db.getUniversity("Z3"));
		db.deleteUniversity(db.getUniversity("Z4"));
		db.deleteUniversity(db.getUniversity("Z5"));
		
	}
	
	@Test
	public void testRecSearchWithMultiIntDiff() {
		
		emphases.add("LIBERAL-ARTS");
		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z5",	"NEW YORK","SMALL-CITY",	"PRIVATE",	10000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,5	,	emphases));
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(0).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(0).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(0).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(0).getName().equals("Z5"));
		
		db.deleteUniversity(db.getUniversity("Z1"));
		db.deleteUniversity(db.getUniversity("Z2"));
		db.deleteUniversity(db.getUniversity("Z3"));
		db.deleteUniversity(db.getUniversity("Z4"));
		db.deleteUniversity(db.getUniversity("Z5"));
		
	}
	@Test
	public void testRecSearchWithEmphasesDiff() {
		
		
		db.addNewUniversity(new University("Z3",	"NEW YORK","SMALL-CITY",	"PRIVATE",	13000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		db.addNewUniversity(new University("Z4",	"NEW YORK","SMALL-CITY",	"PRIVATE",	14000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		emphases.add("LIBERAL-ARTS");
		db.addNewUniversity(new University("Z1",	"NEW YORK","SMALL-CITY",	"PRIVATE",	11000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		emphases.add("ARTS");
		db.addNewUniversity(new University("Z2",	"NEW YORK","SMALL-CITY",	"PRIVATE",	12000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		emphases.remove("LIBERAL-ARTS");
		db.addNewUniversity(new University("Z5",	"NEW YORK","SMALL-CITY",	"PRIVATE",	15000,	55	,560,	520	,32239,	80	,4000,	70	,30	,3,	4	,4	,	emphases));
		uList=sc.recSearch(db.getUniversity("BARD"));
		assertTrue("Should be Z1", uList.get(0).getName().equals("Z1"));
		assertTrue("Should be Z2", uList.get(0).getName().equals("Z2"));
		assertTrue("Should be Z3", uList.get(0).getName().equals("Z3"));
		assertTrue("Should be Z4", uList.get(0).getName().equals("Z4"));
		assertTrue("Should be Z5", uList.get(0).getName().equals("Z5"));
		
		db.deleteUniversity(db.getUniversity("Z1"));
		db.deleteUniversity(db.getUniversity("Z2"));
		db.deleteUniversity(db.getUniversity("Z3"));
		db.deleteUniversity(db.getUniversity("Z4"));
		db.deleteUniversity(db.getUniversity("Z5"));
		
	}
	
}
