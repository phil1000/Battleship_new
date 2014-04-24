package main;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

public class ShipFactory {
	private static ShipFactory instance = null;
	private Properties props = null;
	private List<ShipInter> ships = null;

	private ShipFactory() {
		props = new Properties();

		try {
			props.load(new FileInputStream("ship.properties"));

			ships = new ArrayList<ShipInter>();
			//Create a new ship for each line item within the properties file
			for(int i = 1; i <= props.size(); i++) {
				String key = "ship"+i+".class";
				String value = props.getProperty(key);
				ShipInter ship = (ShipInter) Class.forName(value).newInstance();
				ships.add(ship);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	static {
		instance = new ShipFactory();
	}

	public static ShipFactory getInstance() {
		return instance;
	}

	public List<ShipInter> getShips() {
		return ships;
	}
}