import java.util.ArrayList;

public class Player {

    protected String name;
    protected ArrayList<Card> hand;
    protected int handValue;

    protected double bet;
    protected double money;

    public Player(String name, int handValue, double bet, double money) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.handValue = handValue;
        this.bet = bet;
        this.money = money;
    }

    public void betDouble(){
        bet=(bet*2);
    }
    public void placeBet (double placeBet){
        bet=placeBet;
    }
    public void hit (Dealer dealer) {
        hand.add(Dealer.drawCard());
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
        for (Card card : hand) {
            System.out.print("[" + card.getFace() + " of " + card.getSuit() + "] ");
        }
        System.out.print("Total points: "+ BlackJackGame.soutGreenColor(""+calculateHandValue()));
    }
    public void loss() {
        money -= bet;
        bet = 0;
    }
    public void push() {
        bet = 0;
    }
    public void win() {
        money+=bet*2;
        bet=0;
    }
    public boolean isBlackjack(){
        return calculateHandValue() == 21;
    }
    public void blackjack() {
        money += bet * 1.5;
        bet = 0;
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
    public double getBet() {
        return bet;
    }
    public double getMoney() {
        return money;
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
    public void setBet(double bet) {
        this.bet = bet;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                '}';
    }
}
