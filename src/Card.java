public class Card {

    private String face;
    private String suit;
    private int cardValue;
    private boolean isVisible;

    public Card(String face, String suit, int cardValue, boolean isVisible) {
        this.face = face;
        this.suit = suit;
        this.cardValue = cardValue;
        this.isVisible = isVisible;
    }

    public String getFace() {
        return face;
    }
    public String getSuit() {
        return suit;
    }
    public int getCardValue() {
        return cardValue;
    }
    public boolean isVisible() {
        return isVisible;
    }
    public void setFace(String face) {
        this.face = face;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public String toString() {

        return "Card{" +
                "face='" + face + '\'' +
                ", suit='" + suit + '\'' +
                ", cardValue=" + cardValue +
                ", isVisible=" + isVisible +
                '}';
    }
}
