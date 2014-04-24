package main;

import java.util.Scanner;

/**
 * Class to handle requests of information from the user.(brought over from a different project (Quiz) by GuilhermeRibeiro)
 */
public class UserInterface {

    /**
     * method to help mock System.console().readLine().
     *
     * @return user inputed String.
     */
    public String readFromUser() {
        //TODO improve the regex, to accept most inputs.
        
        Scanner ob=new Scanner(System.in);	//added by Ludo
        String input=ob.nextLine();			//added by Ludo
    	//String input = System.console().readLine();
        
        input = input.replaceAll("\"","");
        return input;
    }

    /**
     * Prints choices, requests choice from user and returns a char.
     *
     * @param aBunchOfChoices properly formatted choices with digits or Alphas for choice selections
     * @return a char that represents a choice.
     */
    public char getUserAnswer(String aBunchOfChoices) {
        System.out.println(aBunchOfChoices);

        return readFromUser().toUpperCase().charAt(0);
    }
    /**
     *     Overload of getUserAnswer for mocking, allows input of a mocked UserInterface obj.
     */
    public char getUserAnswer(String aBunchOfChoices, UserInterface ui) {
        System.out.println(aBunchOfChoices);
        return ui.readFromUser().toUpperCase().charAt(0);
    }


    /**
     * Sends user a message, replaces System.out.println()
     * @param sout message to the user
     */
    public String printToUser(String sout) {
        System.out.println(sout);
        return sout;
    }
}