import java.util.Scanner;

public class Referee {
    private CardStack dealerCards, userCards, deck;
    private int dealerCardsValue, userCardsValue;
    private double userMoney;

    public Referee(){
        dealerCards = new CardStack();
        userCards = new CardStack();
        deck = CardStack.makeDeck(CardStack.EQUAL_FACES);
        dealerCardsValue = 0;
        userCardsValue = 0;
        userMoney = 500;
    }

    public void playAGame() {
        boolean gameIsStillPlaying;
        boolean playAgain = true;
        Scanner keyboardReader = new Scanner(System.in);
        while (playAgain) {
//resets values
            gameIsStillPlaying = true;
            dealerCards = new CardStack();
            userCards = new CardStack();
            dealerCardsValue = 0;
            userCardsValue = 0;

            while (gameIsStillPlaying) {
//bet money
                System.out.println("You have $" + userMoney);
                System.out.println("Make a bet!");
                double bet = keyboardReader.nextDouble();
                while (bet > userMoney){
                    System.out.println("You don't have enough money. Bet again.");
                    bet = keyboardReader.nextDouble();
                }

//deal two cards to the dealer (1 face up and 1 face down)
                Card dealerFaceDownCard = deck.dealCard();
                dealerCards.addCard(dealerFaceDownCard);
                dealerCardsValue += dealerFaceDownCard.getValue();

                Card dealerFaceUpCard = deck.dealCard();
                dealerCards.addCard(dealerFaceUpCard);
                dealerCardsValue += dealerFaceUpCard.getValue();

                //checks to see if card A should be 1 or 11
                if (dealerFaceDownCard.getValue() == 1) {
                    if (dealerCardsValue + 10 <= 21) {
                        dealerCardsValue += 10;
                    }
                }

                if (dealerFaceUpCard.getValue() == 1) {
                    if (dealerCardsValue + 10 <= 21) {
                        dealerCardsValue += 10;
                    }
                }

                System.out.println("Dealer reveals " + dealerFaceUpCard);

//deal two cards to the user, face up
                Card userCard1 = deck.dealCard();
                userCards.addCard(userCard1);
                userCardsValue += userCard1.getValue();

                Card userCard2 = deck.dealCard();
                userCards.addCard(userCard2);
                userCardsValue += userCard2.getValue();

                //checks to see if card A should be 1 or 11
                if (userCard1.getValue() == 1) {
                    if (userCardsValue + 10 <= 21) {
                        userCardsValue += 10;
                    }
                }

                if (userCard2.getValue() == 1) {
                    if (userCardsValue + 10 <= 21) {
                        userCardsValue += 10;
                    }
                }

                System.out.println("Your hand is: ");
                System.out.println(userCards.toString());

                //System.out.println("You have a " + userCard1 + " and a " + userCard2);
                System.out.println("Your total card value is " + userCardsValue);

                if (userCardsValue == 21) {
                    System.out.println("BLACK JACK!");
                    bet = 1.5 * bet;
                    userMoney += bet;
                    System.out.println("You now have $" + userMoney);
                    gameIsStillPlaying = false;
                }

//Ask the user to stay or hit
                System.out.println("Type h to hit or s to stay");

                //hit
                while (keyboardReader.next().equals("h")) {

                    Card newCard = deck.dealCard();
                    userCards.addCard(newCard);
                    userCardsValue += newCard.getValue();

                    //checks to see if card A should be 1 or 11
                    if (newCard.getValue() == 1) {
                        if (userCardsValue + 10 <= 21) {
                            userCardsValue += 10;
                        }
                    }

                    System.out.println("Your hand is: ");
                    System.out.println(userCards.toString());
                    //System.out.println("Your new card is " + newCard);
                    System.out.println("Your total card value is " + userCardsValue);

                    //if user cards > 21 then dealer wins
                    if (userCardsValue > 21) {
                        System.out.println("Dealer wins! You lost.");
                        System.out.println("Dealer wins $" + bet);
                        userMoney -= bet;
                        System.out.println("You now have $" + userMoney);
                        gameIsStillPlaying = false;
                        break;

                    }

                    keyboardReader = new Scanner(System.in);
                    System.out.println("Type h to hit or s to stay");


                }

                //stay
                if (gameIsStillPlaying == true) {
                    //System.out.println("Dealer reveals his other card: " + dealerFaceDownCard);
                    System.out.println("Dealer's hand is: ");
                    System.out.println(dealerCards.toString());
                    System.out.println("Dealer's card value is " + dealerCardsValue);

                    //while dealer cards < 17 then dealer must hit
                    while (dealerCardsValue < 17) {
                        Card newCard = deck.dealCard();
                        dealerCards.addCard(newCard);
                        dealerCardsValue += newCard.getValue();

                        //checks to see if card A should be 1 or 11
                        if (newCard.getValue() == 1) {
                            if (userCardsValue + 10 <= 21) {
                                userCardsValue += 10;
                            }
                        }

                        System.out.println("Dealer hits");
                        //System.out.println("Dealer's new card is " + newCard);
                        System.out.println("Dealer's hand is: ");
                        System.out.println(dealerCards.toString());
                        System.out.println("Dealer's card value is " + dealerCardsValue);
                    }

                    //if dealer cards > 21 then player wins
                    if (dealerCardsValue > 21) {
                        System.out.println("Congratulations! You win $" + bet);
                        userMoney += bet;
                        System.out.println("You now have $" + userMoney);
                        gameIsStillPlaying = false;
                    }

                    //if neither player has busted, the higher score wins
                    if (dealerCardsValue > userCardsValue && dealerCardsValue <= 21) {
                        System.out.println("Dealer wins! You lost.");
                        System.out.println("Dealer wins $" + bet);
                        userMoney -= bet;
                        System.out.println("You now have $" + userMoney);
                        gameIsStillPlaying = false;
                    }

                    if (userCardsValue > dealerCardsValue && userCardsValue <= 21) {
                        System.out.println("Congratulations! You win $" + bet);
                        userMoney += bet;
                        System.out.println("You now have $" + userMoney);
                        gameIsStillPlaying = false;
                    }

                    if (userCardsValue == dealerCardsValue) {
                        System.out.println("It's a tie. No one wins. Keep your money.");
                        gameIsStillPlaying = false;
                    }
                }

            }
            if (userMoney == 0){
                System.out.println("You are out of money. Goodbye!");
                playAgain = false;
            }

            else {
                System.out.println("Play again? Type y or n");
                if (keyboardReader.next().equals("n")){
                    playAgain = false;
                }
            }
        }
    }
}
