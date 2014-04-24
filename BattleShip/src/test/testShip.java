package test;

import main.Ship;
import main.UseableShip;

/**
 * Created by Fliss on 23/02/14.
 */
public class testShip extends UseableShip {

    public testShip() {
        hit = new boolean[2];
        length = 2;
        setHorizontal(true);
        setBowColumn(2);
        setBowRow(2);
    }
    @Override
    public int getLength() {
        return 2;
    }

    @Override
    public String getShipType() {
        return "Test";
    }
}

