import java.util.ArrayList;

public class Dealer extends Player{

    private DeckFactory deckFactory;

    private String name;
    private ArrayList<Card> hand;
    private int handValue;

    public Dealer(DeckFactory deckFactory, String name, int handValue) {
        this.deckFactory = deckFactory;
        this.name = name;
        this.hand = new ArrayList<>();
        this.handValue = handValue;
    }

    public static Card drawCard() {
        Card tempCard = DeckFactory.getDesk().get(DeckFactory.getDesk().size() -1);
        DeckFactory.getDesk().remove(DeckFactory.getDesk().size() -1);
        return tempCard;
    }
    public void hit (boolean isVisible) throws NullPointerException{
       Card tempHit = drawCard();
       tempHit.setVisible(isVisible);
       hand.add(tempHit);
    }
    public int calculateHandValue () {
        handValue = 0;
        int aces = 0;
        for (Card card : hand) {
            if (card.getCardValue() == 11) {
                aces++;
            }
            handValue += card.getCardValue();
        }
        while (aces>0 && handValue >21){
            handValue-=10;
            aces--;
        }
        return handValue;
 }
    public void printHand () {
     int hiddenCards =0;
        for (Card card : hand) {
            if (!card.isVisible()) {
                System.out.print("[Hidden Card] ");
                hiddenCards += card.getCardValue();
            } else
                System.out.print("[" + card.getFace() + " of " + card.getSuit() + "] ");
        }
     System.out.print("Total points: "+ BlackJackGame.soutGreenColor(""+(calculateHandValue()-hiddenCards)));
 }
    public boolean isBlackjack(){
        return calculateHandValue() == 21;
    }
    public void opens(){
        getHand().get(1).setVisible(true);
    }
    public boolean peak() {
        opens();
        return isBlackjack();
    }

    public DeckFactory getDeckFactory() {
        return deckFactory;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public int getHandValue() {
        return handValue;
    }
    public void setDeckFactory(DeckFactory deckFactory) {
        this.deckFactory = deckFactory;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "hand=" + hand +
                '}';
    }
}
