package main;
/**
 * @author Ludo
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class OceanImpl implements Ocean {
	private ShipInter[][] shipArray; //TODO maybe a static field could make it safer !
	private int shotFired;
	private int hitCount;
	private int shipSunk;
	//private String ocean;
	//private String[][] oceanGrid;
	private List<ShipInter> fleet;
	private int fleetInitSize;
	private int maxGrid;
	//added this
	private boolean[][] beenShot;
	private Memento memento;

	public OceanImpl(){
		//Create an empty ocean fills the ship  array with EmptySeas.
		// Also initialises game variables such as how many shots have been fired.
		maxGrid = 10;
		//this.initOcean();
		shipArray = new ShipInter[maxGrid][maxGrid];//added by G
		//added this
		beenShot = new boolean[maxGrid][maxGrid];//added by G
		//\added this
		this.initShipArray();
		fleet = new ArrayList<ShipInter>();
		this.admiral();
		shotFired = 0;
		hitCount = 0;
		shipSunk = 0;
		fleetInitSize = fleet.size();
	}
	
	private void admiral(){
		//Setting the fleet
		
		fleet = ShipFactory.getInstance().getShips();
		/*
		System.out.println("num ret items = " + fleet.size()); // just a test message to show correct num ship being returned

		for(ShipInter ship : fleet) {
			System.out.println("Type=" + ship.getShipType() + " hit = " + ship);
		}*/
	}
	
	public int getFleetSize(){
		return fleet.size();
	}
	
	private int randInt(int min, int max){
		// the max is excluded, but the min is included from the random generation 
		// i.e. call random from 0 to 10 to fill up a grid [0 - 9]
		Random rand = new Random();
		return rand.nextInt(max-min);
	}
	
	public void placeAllShipsRandomly(){
		//place all the ships randomly on the initially empty ocean.
		//Place larger ships before smaller ones to avoid "no legal move"
		//Use Random class Java.util
		
		Iterator<ShipInter> it = fleet.iterator();
		while (it.hasNext()){
		ShipInter obj = it.next();
			boolean place = false;
			boolean horiz = false;
			int row = -1, column = -1; // initialised at -1 to make sure the random setting is working
			while (!place){
				row = randInt(0, maxGrid);
				column = randInt(0, maxGrid);
				horiz = (randInt(0,2)>0);
				place = obj.okToPlaceShipAt(row, column, horiz, this);
			}
			obj.placeShipAt(row, column, horiz, this);

			for (int i = 0;i<obj.getLength();i++){
				if(horiz){
					setShipArray(obj, row, column+i);
				}else{
					setShipArray(obj, row+i, column);
				}
			}
			//debug
			//toString();
			//System.out.println();
			//\debug
		}
		// Saving the grid as String in memento before sinking any ships
		saveToMemento();
	}
	
	public boolean isOccupied(int row, int column){
		//returns True if the given location contains a ship, false if not
		ShipInter es = new EmptySea();
		return(shipArray[row][column].getShipType().equals(es.getShipType()))?false:true;
		//return(oceanGrid[row][column]=="S" || oceanGrid[row][column]=="x")?true:false;
	}
	
	public boolean shootAt(int row, int column){
		//returns True if given location contains a ship still afloat, false if it does not
		//returns True if several shot fired at the same location as long as the ship is afloat, false otherwise
		//update the number of shot fired
		shotFired++;
		ShipInter s = shipArray[row][column];
		ShipInter es = new EmptySea();
		beenShot[row][column] = true;
		if(s.getShipType().equals((es.getShipType()))){
			s.shootAt(row, column);
			return false;
		}else{
			hitCount++;
			s.shootAt(row, column);
			if(s.isSunk()){
				fleet.remove(s);
				System.out.println(fleetSize());
			}
			return true;
		}
	}
	
	public int getShotsFired(){
		//returns the number of shot fired
		return shotFired;
	}
	
	public int getMaxGrid(){
		return maxGrid;
	}
	
	public int getHitCount(){
		//returns the number of hit recorded (even several shot at the same place)
		return hitCount;
	}
	
	public int getShipsSunk(){
		//returns the number of ship sunk;
		shipSunk = fleetInitSize - fleet.size();
		return shipSunk;
	}
	
	public int fleetSize(){
		return fleet.size();
	}
	
	public boolean isGameOver(){
		return (fleet.isEmpty()); //? true:false;
	}
	
	public ShipInter[][] getShipArray(){
		//returns the grid of ship
		return shipArray;
	}
	
	/*
	* 	@return the string representing the ocean
	* 	row number on the left from 0 to 9
	*	column number on the top from 0 to 9
	*	S: hit on ship
	*	-: hit on water
	*	x: sunken ship
	*	.: location that has not been fired at 
	 */
	
	@Override
	public String toString(){
		String strOcean = "";
        if(isGameOver()){
        	strOcean = restoreFromMemento(memento);
        }else{
			int[] header = {0,1,2,3,4,5,6,7,8,9};
	        strOcean = "* 0 1 2 3 4 5 6 7 8 9";
	        for(int i = 0; i <10; i++){
	        	strOcean += "";
	        	strOcean += header[i];
	            for(int t = 0; t <10; t++){
	            	if(beenShot[i][t]){
	            		strOcean += " "+getShipArray()[i][t].toString();
	            	}else{
	            		strOcean += " .";
	            		}
	            }
	        }
        }
		return strOcean;
	}
	
	/*private void initOcean(){
		oceanGrid = new String[maxGrid][maxGrid];
		for(int i = 0; i<maxGrid-1;i++){
			for (int j=0;j<maxGrid-1;j++)
				oceanGrid[i][j]=".";
		}
	}*/
	
	private void initShipArray(){
		ShipInter es = new EmptySea();
		for(int i = 0; i<maxGrid;i++){
			for (int j=0;j<maxGrid;j++)
				setShipArray(es, i, j);
		}
	}
	
	public void setShipArray(ShipInter ship, int row, int column){
		this.shipArray[row][column]= ship;
	}
	
	public ShipInter identifyShip(int row, int column){
		//TODO Check if it used, if not to be deleted from Interface also !!!!!!!!
		Iterator<ShipInter> it = fleet.iterator();
		while (it.hasNext()){
			ShipInter obj = it.next(); 
			for(int i = 0; i<obj.getLength();i++){
				if (obj.isHorizontal()){
					if (obj.getBowRow() == row && obj.getBowColumn()+i == column){
						return obj;
					}
				}else{
					if (obj.getBowRow()+i == row && obj.getBowColumn() == column){
						return obj;
					}
				}
			}
		}
		ShipInter es= new EmptySea(); //TODO replace new Emptysea by the actual Emptysea from that location
		return es;
	}
	
	/*
	 * from here: MEMENTO of the toString()
	 * restore the grid at the state of beginning before ships being sunk
	 */
	
	public static class Memento{
		private final String ocean;
		
		private Memento(String oceanOrigin){
			ocean = oceanOrigin;
		}
		
		private String getSavedGrid(){
			return ocean;
		}
	}
	
	private void setBeenShotTrue(){
		for(int i = 0; i<10;i++){
			for(int j = 0; j<10;j++){
			beenShot[i][j]= true;
			}
		}
	}
	
	private void setBeenShotFalse(){
		for(int i = 0; i<10;i++){
			for(int j = 0; j<10;j++){
			beenShot[i][j]= false;
			}
		}
	}
	
	public Memento saveToMemento(){
		setBeenShotTrue();
		String oceanGrid = toString();
		setBeenShotFalse();
		
		return new Memento(oceanGrid);
	}
	
	public String restoreFromMemento(Memento memento){
		return memento.getSavedGrid();
	}
	
}
