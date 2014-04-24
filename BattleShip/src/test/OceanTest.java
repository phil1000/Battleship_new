package test;

import static org.junit.Assert.*;
import main.Ocean;
import main.OceanImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OceanTest {

	private Ocean ocean = new OceanImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testfleet() {
		int num = 11;
		assertTrue("not the right number", ocean.getFleetSize() == num);
	}

	@Test
	public void testRandom(){
		int check = 10;
		boolean b = true;
		for (int i = 0; i<1000; i++){
			//if(ocean.randInt(0, 10)>=check){
			b = false;
			//}
		}
		assertFalse("The max random is too large", b != true);
	}
	
	@Test
	public void testGameOver(){
		boolean b = false;
		for(int i=0; i<10;i++){
			for (int j=0; j<10; j++){
				ocean.shootAt(i, j);
				if(ocean.isGameOver()){
					b = true;
				}
			}
		}
		assertTrue("the game did not end", b == true);
	}

}