import java.util.ArrayList;
import java.util.Collections;

public class DeckFactory {

    private static final String [] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
    private static final String [] faceValues = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack","Queen", "King", "Ace"};
    private static ArrayList <Card> desk = new ArrayList<>();

    public DeckFactory() {
       desk = DeckFactory.generateRandomDeck();
    }

    public static ArrayList <Card> generateRandomDeck () {

        for (String suit : suits) {
            for (String faceValue : faceValues) {
                int value;
                if (faceValue.equals("Jack") || faceValue.equals("Queen") || faceValue.equals("King")) {
                    value = 10;
                } else if (faceValue.equals("Ace")) {
                    value = 11;
                } else value = Integer.parseInt(faceValue);
                desk.add(new Card(faceValue, suit, value, true));
            }
        }
        Collections.shuffle(desk);
    return desk;
    }

    public static ArrayList<Card> getDesk() {
        return desk;
    }
    public static void setDesk(ArrayList<Card> desk) {
        DeckFactory.desk = desk;
    }
    @Override
    public String toString() {
        return "DeckFactory{}";
    }
}
