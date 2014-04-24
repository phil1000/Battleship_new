package main;

//import Ocean;
//import Ship;

public abstract class Ship implements ShipInter {
    private boolean isHorizontal;
    protected int length;
    private int bowRow;
    private int bowColumn;
    protected boolean[] hit;


    
    public abstract int getLength();

    
    public int getBowRow() {
        return bowRow;
    }

    
    public int getBowColumn() {
        return bowColumn;
    }

    
    public boolean isHorizontal() {
        return isHorizontal;
    }

    
    public void setBowRow(int row) throws IllegalArgumentException {
        if(row>9 || row<0) {
            throw new IllegalArgumentException();
        }
        this.bowRow = row;
    }

    
    public void setBowColumn(int column)throws IllegalArgumentException {
        if(column>9 || column<0) {
            throw new IllegalArgumentException();
        }
        this.bowColumn = column;
    }

    
    public void setHorizontal(boolean horizontal){
        this.isHorizontal = horizontal;
    }

    
    public abstract String getShipType();

    
    public boolean okToPlaceShipAt(int row, int column, boolean hori, Ocean ocean) {
            return okToPlaceShipRec(row, column, hori, ocean, getLength());
    }

    private boolean okToPlaceShipRec(int row, int column, boolean hori, Ocean ocean, int length)  {
        if (length == 0) {
            return true;
        }
        if (row>9 || row<0 || column<0 || column>9) {
            return false;
        }
        for(int i = Math.max(0, row-1); i<=Math.min(9,row +1); i++) {
                for (int j = Math.max(0, column-1); j<=Math.min(column +1, 9); j++) {
                    if (ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
        }
        int myInt = (hori) ? 1 : 0;
        return okToPlaceShipRec(row+(1-myInt), column+myInt, hori, ocean, length-1);
    }

    
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) throws IllegalArgumentException {
        int hori = (horizontal) ? 1:0;
        if (row<0 || column<0) {
            throw new IllegalArgumentException("Ship extends outside grid");
        }
        
        if (row + (length*(1-hori))>10 || column + (length*hori)>10 ) {
            throw new IllegalArgumentException("Ship extends outside grid");
       	}
        
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);
        placeShipRec(row, column, hori, ocean.getShipArray(), length);
    }

    private void placeShipRec(int row, int column, int hori, ShipInter[][] ocean, int lengthLeft) {
        if (lengthLeft == 0) {
            return;
        }
        ocean[row][column] = this;
        placeShipRec(row+(1-hori), column+hori, hori, ocean, lengthLeft-1);
    }

    
    public boolean shootAt(int row, int column) throws RuntimeException {
        int hori = (isHorizontal()) ? 1 : 0;
        if (row < getBowRow() || column<getBowColumn()) {
            throw new RuntimeException("Yo dude, we fucked up, this ship isn't in that cell");
        }
        if (bowRow + (1-hori)*length < row || bowColumn + (hori * length) <column) {
            throw new RuntimeException("Yo dude, we fucked up, this ship isn't in that cell");
        }
        for (int i = 0; i<length; i++) {
            if ((getBowColumn()+hori*i) == column && (getBowRow() + ((1-hori)*i) == row)) {
                hit[i] = true;
                return true;
            }
        }

        return false;
    }

    
    public boolean isSunk() {
        for(int i = 0; i<hit.length; i++) {
            if (hit[i] == false) { //put an extra =, there was only one before. 
                return false;
            }
        }
        return true;
    }
}