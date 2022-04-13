import java.util.NoSuchElementException;

public class Game {

    public static void main(String[] args) {
        BlackJackGame newGame = new BlackJackGame();
        try {
            newGame.startGame();
            newGame.getBalance();
            do {
                newGame.goBet();
                newGame.startDeal();
                newGame.playerDouble();
                newGame.playerHitOrStand();
                newGame.checkBlackjack();
                newGame.dealerTurn();
                newGame.settleBets();
                newGame.printMoney();

            } while (newGame.playAgain());
        } catch (NoSuchElementException noSuchElementException){
            System.out.println("Invalid command! Please restart program!");
        }
    }

}
