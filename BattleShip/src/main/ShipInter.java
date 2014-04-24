package main;

//import Ocean;



public interface ShipInter {
    /*
    * returns the length of this particular ship. This method exists only to be overridden.
    * @return int the length of the ship
    */
    int getLength();

    /*
    *  returns the row the highest end sits in
    *  @return int BowRow
    */
    int getBowRow();

    /*
    * returns the column that the leftmost end sits in
    * @return int BowColumn
    */
    int getBowColumn();

    /*
    * returns true if the ship is horizontal
    * @return boolean isHorizontal
    */
    boolean isHorizontal();

    /*
    * sets the BowRow value
    * @param the row the ship's bow will be placed in
    * @throws IllegalArgumentException if the position would put the ship outside the array
    * @return void
    */
    void setBowRow(int row) throws IllegalArgumentException;

    /*
    * sets the BowColumn value
    * @param the column the ship's bow will be placed in
    * @throws IllegalArgumentException if the position would put the ship outside the array
    * @return void
    */
    void setBowColumn(int column) throws IllegalArgumentException;

    /*
    * sets whether the ship is placed horizontally or not
    * @param boolean whether the ship is horizontal or not
    * @return void
    */
    void setHorizontal(boolean horizontal);

    /*
    * returns the type of the ship object
    * @return String representing the name of the class
    */
    String getShipType();

    /*
    * returns true if it is okay to put a ship of this length with its bow at this location
    * does not actually modify the ocean itself
    * the ship may not overlap another ship, nor be adjacent to one (including diagonally)
    * @param int the row the ship's bow is to be placed in
    * @param int the column the ship's bow is to be placed in
    * @param boolean whether the ship is to be placed horizontally or not
    * @param Ocean the ocean the ship is to be placed in
    * @return true if the ship can be placed in the given ocean in the given space
    */
    boolean okToPlaceShipAt(int row, int column, boolean hori, Ocean ocean);

    /*
    * places the ship in the given location, giving values to row and column in the ship object,
    * and putting pointers in all appropriate cells in the ocean pointing to this ship object
    * @param int the row to place the ship in
    * @param int the column to place the ship in
    * @param boolean whether the ship is horizontal or not
    * @param Ocean the ocean to place the ship in
    * @throws IllegalArgumentException if the position is invalid (though not if occupied(us oktoplaceat method))
    * @return void
    */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) throws IllegalArgumentException;

    /*
    * shoots at a position in the (ships's?) array
    * @param row in ocean
    * @param column in ocean
    * @param Ocean the ocean your playing on????????
    * @throws RuntimeException if the given position is not occupied by this ship (or is outside the array).
    * @return boolean true if the ship is hit(so, always pretty much)
    */
    boolean shootAt(int row, int column) throws RuntimeException;

    /*
    * tells you whether a given ship ahs been sunk
    * @return boolean true if all positions on the ship have been hit
    */
    boolean isSunk();
}