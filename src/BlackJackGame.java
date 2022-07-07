import java.io.IOException;
import java.util.Scanner;

public class BlackJackGame {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private final Scanner scan = new Scanner(System.in);
    private DeckFactory deckFactory;
    private final Dealer dealer = new Dealer(deckFactory, "Marvin", 0);
    private final Player player = new Player("", 0, 0, 0);

    private boolean playerGoDoubleFlag;
    private boolean playerBlackJackFlag;
    private boolean playerBustedFlag;

    public static String soutGreenColor(String input){
        return (ANSI_GREEN+input+ANSI_RESET);
    }
    public String playerPurpleColored(){
        return (ANSI_PURPLE+player.getName()+ANSI_RESET);
    }
    public boolean scanNext(String input) {
        return input.equalsIgnoreCase(scan.next());
    }

    public void clearHands() {
        dealer.getHand().clear();
        player.getHand().clear();
    }
    public void startGame() {
        System.out.println(ANSI_PURPLE+"WELCOME TO BLACK JACK GAME!"+ANSI_RESET);
        System.out.print("Please enter your name: ");
        player.setName(scan.next());
        System.out.println("Hello " +playerPurpleColored()+ "! ");
        deckFactory = new DeckFactory();
    }

    public void getBalance() {
        System.out.println("Please add your balance: ");
        player.setMoney(scan.nextDouble());

    }

    public void goBet() {
        boolean balanceAdded;
        do {
            if (player.getMoney() > 0) {
                do {
                    System.out.println(playerPurpleColored() + " , please place your bet: ");
                    player.placeBet(scan.nextDouble());
                    balanceAdded = false;
                    if (player.getBet() > player.getMoney()){
                        System.out.println("Sorry , not enough money! ");
                        getBalance();
                        balanceAdded = true;
                    }
                }
                while (!(player.getBet() > 0 && player.getBet() <= player.getMoney()));
            } else {
                System.out.println("Sorry , not enough money! ");
                getBalance();
                balanceAdded = true;
            }
        } while (balanceAdded);
    }

    public void startDeal() {
        clearHands();
        playerBlackJackFlag = false;
        dealer.hit(true);
        dealer.hit(false);
        System.out.print("Dealer " + dealer.getName() + "'s cards: ");
        System.out.println();
        dealer.printHand();
        player.hit(dealer);
        player.hit(dealer);
        System.out.println();
        System.out.print(playerPurpleColored()+"'s cards: ");
        System.out.println();
        player.printHand();
        System.out.println();
        if (player.isBlackjack()) {
            playerBlackJackFlag = true;
        }
    }

    public void playerDouble() {

        if (!playerBlackJackFlag) {
            playerGoDoubleFlag = false;
            System.out.println("Do you wont to double? " + soutGreenColor("(y/n)"));


            /*if (scanNext("y")) {
                playerGoDoubleFlag = true;
                player.betDouble();
                System.out.println("Thx , your total bet is: " + soutGreenColor("" + player.getBet()));
                player.hit(dealer);
                System.out.print(playerPurpleColored() + "'s cards: ");
                player.printHand();
                System.out.println();
                System.out.println();
            }*/
            switch (scan.next().toLowerCase()){
                case  ("y"):
                    playerGoDoubleFlag = true;
                    player.betDouble();
                    System.out.println("Thx , your total bet is: " + soutGreenColor("" + player.getBet()));
                    player.hit(dealer);
                    System.out.print(playerPurpleColored() + "'s cards: ");
                    player.printHand();
                    System.out.println();
                    System.out.println();
                    break;
                case ("n"):
                    break;
                default:
                    System.out.println("Wrong command!");
                    playerDouble();
                    break;

            }

                }

        }


    public void playerHitOrStand() {
        playerBustedFlag = false;
        if (player.getBet() > 0 && !playerGoDoubleFlag && !playerBlackJackFlag) {
            String hs;
            do {
                System.out.print("Hit or Stand? "+soutGreenColor("(h/s)"));
                System.out.println();
                hs = scan.next().toLowerCase();
                if (hs.equals("h") && player.calculateHandValue() <= 21) {
                    player.hit(dealer);
                    player.printHand();
                    System.out.println();
                }
            } while (!hs.equals("s") && player.calculateHandValue() <= 21);
        }
        if (player.calculateHandValue() > 21) {
            playerBustedFlag = true;
        }
    }

    public void checkBlackjack() {
        if (!playerBustedFlag){
            if (dealer.isBlackjack()) {
                System.out.println(soutGreenColor("Dealer has BlackJack!"));
                dealer.opens();
                dealer.printHand();
                System.out.println();
                if (player.isBlackjack()) {
                    System.out.println(playerPurpleColored() + soutGreenColor(" pushes!"));
                    player.push();
                } else {
                    System.out.println(playerPurpleColored() + soutGreenColor(" loses!"));
                    player.loss();
                }
            } else {
                if (!dealer.peak()) {
                    System.out.println("Dealer peeks and does not have a BlackJack!");
                }
                if (player.isBlackjack()) {
                    System.out.println(playerPurpleColored() + soutGreenColor(" has blackjack!"));
                }

            }
    }
}
    public void dealerTurn() {
      if (player.getBet()>0 && !playerBustedFlag){
          dealer.opens();
          dealer.printHand();
          System.out.println();
        if (!dealer.isBlackjack()) {
            while (dealer.calculateHandValue() <= 16) {
                System.out.println("Dealer has " + soutGreenColor(""+dealer.calculateHandValue()) + " and hits!");
                dealer.hit(true);
                dealer.printHand();
                System.out.println();
            }
            if (dealer.calculateHandValue() > 21) {
                System.out.println(soutGreenColor("Dealer busts!"));
            } else {
                System.out.println(soutGreenColor("Dealer stands!"));
            }
        }
        }
    }

    public void settleBets() {
        System.out.println();
            if (player.getBet() > 0 ) {
                if( player.calculateHandValue() > 21 ) {
                    System.out.println(playerPurpleColored() + soutGreenColor(" busted!"));
                    player.loss();
                } else if (player.calculateHandValue() == dealer.calculateHandValue() ) {
                    System.out.println(playerPurpleColored() + soutGreenColor(" pushed!"));
                    player.push();
                } else if (player.calculateHandValue() < dealer.calculateHandValue() && dealer.calculateHandValue() <= 21 ) {
                    System.out.println(playerPurpleColored() + soutGreenColor(" lost!"));
                    player.loss();
                } else if (player.isBlackjack()) {
                    System.out.println(playerPurpleColored() + soutGreenColor(" won with blackjack!"));
                    player.blackjack();
                } else {
                    System.out.println(playerPurpleColored() + soutGreenColor(" won!"));
                    player.win();
                }
            }
    }
    public void printMoney(){
        System.out.println("Your balance is: "+ soutGreenColor(""+player.getMoney()));
    }
    public boolean playAgain(){
        boolean playState = true;
        System.out.println("Do you want to play again? "+soutGreenColor("(y/n)"));
        if (scanNext("n")){
            playState =false;
        }
        return playState;
    }
    }

