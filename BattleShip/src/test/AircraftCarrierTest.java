package test;

import static org.junit.Assert.*;
import main.AircraftCarrier;
import main.ShipInter;

import org.junit.Before;
import org.junit.Test;

public class AircraftCarrierTest {
	//TODO <Test> write a new test case for after the ship class has been finished, for each ship
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testGetLength() {
		//expected
		int expected = 5;
		//actual
		int actual = (new AircraftCarrier()).getLength();
		//test
		assertEquals("length isn't set up propperly for Aircraft Carrier",expected,actual);	
	}

	@Test
	public final void testGetShipType() {
		
		//expected
		String expected = "Aircraft Carrier";
		//actual
		String actual = new AircraftCarrier().getShipType();
		//test
		assertEquals("ShipType isn't set up propperly for Aircraft Carrier",expected,actual);	
	}

	@Test
	public final void testAircraftCarrier() {
		ShipInter ac = new AircraftCarrier();
		
		//test
		assertTrue(ac instanceof ShipInter);
	}

}
