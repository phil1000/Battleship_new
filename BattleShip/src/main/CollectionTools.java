package main;
import java.util.ArrayList;

/**
 * Supportive collection handling methods
 * Imported from quiz project by Guilherme Ribeiro
 */
public abstract class CollectionTools {
    /**
     * Orders the objects in an array with an index for each.
     * \nUsed for giving the user options.
     *
     *
     * @param orderType Ids the type of index as a char. S for string, 0 to start with 0, 1 to start with 1
     * @param collection The arrayList with the collection of strings or objects with overriden toString().
     * @param <T> String or objects with overriden toString(), ready to be presented.
     * @return A String with the formatted and indexed list of items in the collection, ready to be print.\nReturns null if the input is not one of the possible choices.
     */
    public static <T> String collectionPrinter(char orderType, ArrayList<T> collection){
        String result;
        switch(orderType){
            case'0':
                result = populateResult(0, collection);
                break;
            case'1':
                result = populateResult(1, collection);
                break;
            case'S':
                result = populateResult(collection);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    private static <T> String populateResult(int i, ArrayList<T> collection){
        String result = "";
        for (T current : collection){
            result += i +" -> "+ current.toString()+"\n";
            i++;
        }
        return result;
    }

    private static <T> String populateResult(ArrayList<T> collection){
        String result = "";
        int i =0;
        for (T current : collection){
            result += getKey(i) +" -> "+ current.toString()+"\n";
            i++;
        }
        return result;
    }

    private static String getKey(int i) {
        String[] keys = {
                "A",
                "B"
                , "C"
                , "D"
                , "E"
                , "F"
                , "G"
                , "H"
        };
        return keys[i];
    }

   //@Guilherme -> removed a bunch of unnecessary methods.
}