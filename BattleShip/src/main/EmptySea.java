/**
 * 
 */
package main;

/**
 * @author Guilherme
 *
 */
public class EmptySea extends UseableShip {
	
	public EmptySea(){
		this.length = 1;
		this.hit = new boolean[length];
	}
	
	@Override
	public boolean shootAt(int row, int column) {
		return false;
	}
	
	@Override
	public boolean isSunk() {
		return false;
	}
	@Override 
	public String toString(){
		return "-";
	}
}
