package main;

public interface Ocean {

	/*
	 * places all the ships randomly on the initially empty ocean.
	 * places larger ships before smaller ones to avoid "no legal move"
	 * Use Random class Java.util
	 * @return returns void
	 */
	 void placeAllShipsRandomly();
		
	/*
	 * @return returns boolean, True if the given location contains a ship, False if not
	 * @param the coordinate of the game grid as row and column id
	 */
	boolean isOccupied(int row, int column);
	 
	/*
	 * @return returns boolean, True if the given location contains a ship afloat, False if there is no ship or if the ship is sunk
	 * @return it returns True if several shot are fired at the same location as long as the ship is afloat, returns False if the ship is sunk
	 * updates the number of shot fired
	 * @param the coordinate of the game grid as row and column id
	 * @throws IllegalArgumentException if the coordinates are outside of the grid
	*/
	 boolean shootAt(int row, int coloumn);

	/*
	 * @return returns int, the number of shot fired
	 */
	 int getShotsFired();
	 
	/*
	 * @return returns int, the number of hit recorded (even several shot at the same place)
	 */
	int getHitCount();
	
	/*
	 * @return returns int, the number of ship sunk 
	 */
	int getShipsSunk();
	
	/*
	 * @return returns true if all ships have been sunk
	 */
	boolean isGameOver();
	
	/*
	 * @return returns Ship double Array as the grid of ship 
	 */
	ShipInter[][] getShipArray();
	
	/*
	 * @return returns string, representing the ocean
	 * display the row number on the left from 0 to 9
	 * display the column number on top from 0 to 9
	 * S: hit on ship
	 * -: hit on water
	 * x: sunken ship
	 * .: location that has not been fired at
	*/
	@Override
	 String toString();
	
	/*
	 * @return returns Ship that is located in these row/column coordinate 
	 */
	ShipInter identifyShip(int row, int column);
	
	/*
	 * @return returns the max size of the grid; 
	 */
	int getMaxGrid();
	
	/*
	 * @return returns fleet size; 
	 */
	int getFleetSize();
}
