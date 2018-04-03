package entities;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UniversityTest {
	private University u;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<String> emphases=new ArrayList<String>();
		emphases.add("COMPUTER SCIENCE");
		emphases.add("MUSIC");
		emphases.add("ECONOMICS");
		u=new University("UNIVERSITY OF KARLD", "MINNESOTA", 
				"URBAN", "PRIVATE", 2000, 45,600,750, 22500,
                25, 1200, 600, 500, 5, 3, 4,  emphases);
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testGetName() {
		assertEquals(u.getName(), "UNIVERSITY OF KARLD");
	}

	@Test
	public void testGetState() {
		assertEquals(u.getState(),"MINNESOTA");
	}

	@Test
	public void testGetLocation() {
		assertEquals(u.getLocation(), "URBAN");
	}

	@Test
	public void testGetControl() {
		assertEquals(u.getControl(),"PRIVATE");
	}

	@Test
	public void testGetStudents() {
		assertEquals(u.getStudents(),2000);
	}

	@Test
	public void testGetFemPerc() {
		assertEquals(u.getFemPerc(),45);
	}

	@Test
	public void testGetSatV() {
		assertEquals(u.getSatV(),600);
	}

	@Test
	public void testGetSatM() {
		assertEquals(u.getSatM(),750);
	}

	@Test
	public void testGetCost() {
		assertEquals(u.getCost(),22500);
	}

	@Test
	public void testGetFinAidPerc() {
		assertEquals(u.getFinAidPerc(),25);
	}

	@Test
	public void testGetApplicants() {
		assertEquals(u.getApplicants(),1200);
	}

	@Test
	public void testGetAdmitted() {
		assertEquals(u.getAdmitted(),600);
	}

	@Test
	public void testGetEnrolled() {
		assertEquals(u.getEnrolled(),500);
	}

	@Test
	public void testGetAcadScale() {
		assertEquals(u.getAcadScale(),5);
	}

	@Test
	public void testGetSocScale() {
		assertEquals(u.getSocScale(),3);
	}

	@Test
	public void testGetQualScale() {
		assertEquals(u.getQualScale(),4);
	}

	@Test
	public void testGetEmphases() {
		assertTrue(u.getEmphases().contains("COMPUTER SCIENCE"));
		assertTrue(u.getEmphases().contains("MUSIC"));
		assertTrue(u.getEmphases().contains("ECONOMICS"));
	}


	@Test
	public void testSetName() {
		u.setName("UNIVERSITY OF TEST");
		assertEquals(u.getName(), "UNIVERSITY OF TEST");
	}

	@Test
	public void testSetState() {
		u.setState("WASHINGTON");
		assertEquals(u.getState(), "WASHINGTON");
	}

	@Test
	public void testSetLocation() {
		u.setLocation("RURAL");
		assertEquals(u.getLocation(), "RURAL");
	}

	@Test
	public void testSetControl() {
		u.setControl("PUBLIC");
		assertEquals(u.getControl(), "PUBLIC");
	}

	@Test
	public void testSetStudents() {
		u.setStudents(3000);
		assertEquals(u.getStudents(), 3000);
	}

	@Test
	public void testSetFemPerc() {
		u.setFemPerc(40);
		assertEquals(u.getFemPerc(),40);
	}

	@Test
	public void testSetSatV() {
		u.setSatV(500);
		assertEquals(u.getSatV(), 500);
	}

	@Test
	public void testSetSatM() {
		u.setSatM(790);
		assertEquals(u.getSatM(), 790);
	}

	@Test
	public void testSetCost() {
		u.setCost(24000);
		assertEquals(u.getCost(), 24000);
	}

	@Test
	public void testSetFinAidPerc() {
		u.setFinAidPerc(15);
		assertEquals(u.getFinAidPerc(), 15);
	}

	@Test
	public void testSetApplicants() {
		u.setApplicants(1500);
		assertEquals(u.getApplicants(), 1500);
	}

	@Test
	public void testSetAdmitted() {
		u.setAdmitted(1000);
		assertEquals(u.getAdmitted(), 1000);
	}

	@Test
	public void testSetEnrolled() {
		u.setEnrolled(600);
		assertEquals(u.getEnrolled(), 600);
	}

	@Test
	public void testSetAcadScale() {
		u.setAcadScale(4);
		assertEquals(u.getAcadScale(), 4);
	}

	@Test
	public void testSetSocScale() {
		u.setSocScale(2);
		assertEquals(u.getSocScale(), 2);
	}

	@Test
	public void testSetQualScale() {
		u.setQualScale(5);
		assertEquals(u.getQualScale(), 5);
	}

	@Test
	public void testAddEmphases() {
		u.addEmphases("MATH");
		assertTrue(u.getEmphases().contains("MATH"));
	}

	@Test
	public void testRemoveEmphases() {
		u.removeEmphases("MUSIC");
		assertFalse(u.getEmphases().contains("MUSIC"));
	}

	

}
