package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entities.University;

public class SearchControllerTest {
	private SearchController sc;
	private ArrayList<String> emphases;
	private ArrayList<University> uList;
	@Before
	public void setUp() throws Exception {
		sc=new SearchController();
		emphases= new ArrayList<String>();
		uList = new ArrayList<University>();
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
		assertTrue("Should be 3",uList.size()==2);
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
	public void testRecSearch() {
		
	}

}
