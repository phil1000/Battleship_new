package test;

/**
 * Created by Fliss on 23/02/14.
 */



import main.Ocean;
import main.OceanImpl;
import main.PatrolBoat;
import main.ShipInter;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ShipAbsTest {
    static ShipInter testShipper;
    static int bowRow;
    static int colRow;
    static boolean isHoriz;
    static Ocean testOcean;
    static ShipInter testPatrolBoat;

    @BeforeClass
    public static void setUp() {
    testShipper = new testShip();
    bowRow = 2;
    colRow = 2;
    isHoriz = true;
    testPatrolBoat = new PatrolBoat();
    testOcean = new OceanImpl();
    }

    @Test
    public void getBowRowTest() {
        assertEquals(bowRow, testShipper.getBowRow());
    }

    @Test
    public void getBowColTest() {
        assertEquals(colRow, testShipper.getBowColumn());
    }

    @Test
    public void getHorizontalTest() {
        assertEquals(isHoriz, testShipper.isHorizontal());
    }

    @Test
    public void setBowRow() {
        ShipInter newShip = testShipper;
        newShip.setBowRow(4);
        assertEquals(4, newShip.getBowRow());
    }

    @Test
    public void setBowColumnTest() {
        ShipInter newShip = testShipper;
        newShip.setBowColumn(4);
        assertEquals(4, newShip.getBowColumn());
    }

    @Test
    public void placeShipAtTest3() {
        testShipper.placeShipAt(bowRow, colRow, isHoriz, testOcean);
        assertNotEquals(testShipper, testOcean.getShipArray()[bowRow][colRow+2]);
    }

    @Test
    public void setHorizontalTest() {
        ShipInter newShip;
        newShip = testShipper;
        newShip.setHorizontal(false);
        assertEquals(false, newShip.isHorizontal());
    }

    @Test
    public void okToPlaceTest() {
        assertFalse(testShipper.okToPlaceShipAt(9, 9, true, testOcean));
    }

    @Test
    public void placeShipAtTest() {
        testShipper.placeShipAt(4, 4, false, testOcean);
        assertTrue(testOcean.isOccupied(4, 4));
    }

    @Test
    public void placeShipAtTest2() {
        testShipper.placeShipAt(4, 4, false, testOcean);
        assertTrue(testOcean.isOccupied(4, 5));
    }

    @Test
    public void shootAtTest() {
        assertTrue(testShipper.shootAt(2, 2));
    }

    @Test
    public void isSunkTest() {
        testPatrolBoat.shootAt(0, 0);
        assertTrue(testPatrolBoat.isSunk());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBowRowExceptionTest1() {
         testShipper.setBowRow(10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBowRowExceptionTest2() {
        testShipper.setBowRow(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBowColExceptionTest1() {
        testShipper.setBowColumn(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBowColExceptionTest2() {
        testShipper.setBowColumn(10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shootAtTest1() {
        testShipper.shootAt(0,9);
    }

    @Test (expected = IllegalArgumentException.class)
    public void placeShipAtExceptionTest1() {
        testShipper.placeShipAt(9,9,true,testOcean);
    }

    @Test (expected = IllegalArgumentException.class)
    public void placeShipAtExceptionTest2() {
        testShipper.placeShipAt(10,10,true,testOcean);
    }

    @Test (expected = IllegalArgumentException.class)
    public void placeShipAtExceptionTest3() {
        ShipInter anotherTestShip = testShipper;
        anotherTestShip.placeShipAt(2,2,false,testOcean);
    }

}

