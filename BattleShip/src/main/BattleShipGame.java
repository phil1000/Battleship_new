package main;

import java.util.ArrayList;

/**
 * Created by Guilherme on 23/04/2014.
 *
 * main method and a variable of type Ocean.
 *
 * In this class you will
 *
   set up the game;
   accept shots from the user;
   display the results;
   print final scores; and
   ask the user if s/he wants to play again.

 All input/output is done here. All computation will be carried out in the Ocean class
 and the various Ship classes.
 To aid the user, row numbers should be displayed along the left edge of the grid, and
 column numbers should be displayed along the top. Numbers should be 0 to 9, not 1
 to 10. The top left corner square should be 0,0. Use different characters to indicate
 locations that contain a hit, locations that contain a miss, and locations that have never
 been fired upon.
 Use methods appropriately; don't cram everything into one or two methods, but try
 to divide up the work into sensible parts with reasonable names. Test every non-private
 method in the Ship class. Also, test the methods in each subclass of Ship
 */

public class BattleShipGame {
    private UserInterface ui;
    private boolean played;
    private Ocean currentOcean;
    private int currentScore;
    private int highestScore;

    /**
     * Runs the program
     * @param args (convention)
     */
    public static void main(String[] args) {
    	BattleShipGame bsg = new BattleShipGame();
        bsg.run();
    }

    /**
     * Sets up the game
     */
    public BattleShipGame(){
        ui = new UserInterface();
        played = false;
        highestScore = 0;
    }

    /**
     * sets up a menu
     */
    public void run(){
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Play the Game");
        menuItems.add("Close the Program");
        menuItems.add("Show the highest score");
        String menu = CollectionTools.collectionPrinter('S', menuItems);
        runMenu(menu);
    }

    /**
     * requests a choice from the user and launches the associated method
     * recognizes if user has played already and asks him if he wants to play again.
     */
    public void runMenu(String menu){
        ui.printToUser(!played ? "Hello, would you like to play a game of BattleShip?" : "Hello again!, \nwould you like to play another game of BattleShip?");
        char choice = ui.getUserAnswer(menu);

        switch(choice){
            case 'A':
                try{
                    setUpTheGame();
                } catch(UserCancelsException e){
                    ui.printToUser("Shame you couldn't finish the game, too much of a challenge is it...?");
                }
                run();
                break;
            case 'B':
                closeProgram();
                break;
            case 'C':
                printHighestScore();
                run();
                break;
            default:
                ui.printToUser("Couldn't understand the input, please choose again.");
                run();
                break;
        }
    }

    private void printHighestScore() {
        if(highestScore == 0){
            ui.printToUser("game hasn't been played yet");
        }else {
            ui.printToUser("Your highest score is " + highestScore);
        }
    }

    private void closeProgram() {
        printHighestScore();
        ui.printToUser("Thank you for playing, bye.");
    }

    /**
     * handles launching the game methods
     *
     * @throws UserCancelsException if the user exits the game before the game is finished, this exception handles it.
     */
    public void setUpTheGame() throws UserCancelsException{
        played = true;
        ui.printToUser("Close the game at any time by inputting letters instead of numbers.");
        currentOcean = new OceanImpl();
        currentOcean.placeAllShipsRandomly();
       
        currentScore = 0;
        playTheGame();
        if(currentScore<highestScore
                ||highestScore == 0){
            highestScore = currentScore;
            ui.printToUser("New High Score! : " + highestScore);
        }
    }

    private void playTheGame() throws UserCancelsException {
        while(!currentOcean.isGameOver()){
            displayGrid();
            int[] shots;
            try{
                shots = takeShots();
            } catch(NumberFormatException e){
                throw new UserCancelsException();
            }catch (Exception e){
                ui.printToUser("A problem outside of the scope of the user happened.");
                e.printStackTrace();
                throw new UserCancelsException();
            }
            processShots(shots);
            displayResults();
        }
        displayGrid();
        printFinalScores();
    }

    private void processShots(int[] shots) {
        boolean target = currentOcean.shootAt(shots[0],shots[1]);
        if(!target){
            ui.printToUser(" WATER! ");
            //debug
            System.out.println(currentOcean.identifyShip(shots[0], shots[1]).getShipType());
        }else{
            ui.printToUser("You have hit something");
            ShipInter shotShip = currentOcean.identifyShip(shots[0],shots[1]);
            if(shotShip.isSunk()){
                ui.printToUser("You sunk a " + shotShip.getShipType() + "!");
            }
        }
    }

    /**
     * accept shots from the user;
     * @return an array where [0] is row value and [1] is column value.
     */
    public int[] takeShots(){
        ui.printToUser("Please enter the row value");
        int row = Integer.parseInt(String.valueOf(ui.readFromUser().charAt(0)));
        ui.printToUser("Please enter the column value");
        int column = Integer.parseInt(String.valueOf(ui.readFromUser().charAt(0)));
        return new int[]{row, column};
    }

    /**
     *
     * Displays the current state of the grid.
     *
     */
    public void displayGrid(){
    	int max = currentOcean.getMaxGrid(); // modified by Ludo
    	int line = max*2 +1; 				// modified by Ludo
    	for(int i=0; i<max+1;i++){			// modified by Ludo
    	   int lineStart = 0 + line*i;		// modified by Ludo
    	   int lineEnd = line + line*i;		// modified by Ludo
    	   System.out.println(currentOcean.toString().substring(lineStart, lineEnd)); // modified by Ludo
       }
    }

    /**
     *  display the results;
     */
    public void displayResults(){
        ui.printToUser("You have fired " + currentOcean.getShotsFired() + " so far.");
        ui.printToUser("You have managed a total of " + currentOcean.getHitCount() + " successful hits.") ;
        ui.printToUser("You have sunk a total of " + currentOcean.getShipsSunk() + " ships.");
    }
    /**
     *print final scores;
     */
    public void printFinalScores(){
        ui.printToUser("Your final score is: " + currentScore);
    }
}
