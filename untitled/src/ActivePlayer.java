import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActivePlayer extends Player {

//    private String name;
//    private ArrayList<Card> cards;

    Scanner input = new Scanner(System.in);
    ArrayList<Card> matchedSuits;
    int chosenIndex;

    //boolean kanUseHeart = false;

    public ActivePlayer(String name) {
        super(name);
        matchedSuits = new ArrayList<>();
        // this.name=name;
        //cards = new ArrayList<>();
    }

    public void playerHandNumbered() {
        for (int i = 0; i < getCards().size(); i++) {
            System.out.println((i + 1) + ": " + getCards().get(i).getSuitValue().getUnicode() + getCards().get(i).getSuits().getUnicode());
        }
    }


    public String suitOfRank(int j) {
        Card card = getCards().get(j - 1);
        return card.getSuits().getUnicode();
    }

    //
//    public boolean kanUseHeart(){
//        boolean kanUseHeart = true;
//        if (kanFollowSuit()){
//            kanUseHeart=false;
//        }
//        return kanUseHeart;
//    }

    //
//    public boolean kanUseHeart(){
//        if (kanUseHeart){
//           useHeart();
//        }
//        return kanUseHeart;
//    }

    //
//    public void useHeart() {
//            if (kanUseHeart()){
//                addChosenRankToPile();
//                setIsHeartBroken(true);
//            }
//            else {
//                System.out.println("you can not use heart suit since heart are not broken yet");
//            }
//    }


    public void useHeart() {

        addChosenRankToPile();
        setIsHeartBroken(true);
        System.out.println("you can not use heart suit since heart are not broken yet");

    }



//
    public boolean kanFollowSuit() {
        boolean hasLeadingSuit = false;
        Card topCard = topTrickPileCard();
        for (Card checkCard : getCards()) {
            if (checkCard.getSuits().getUnicode().equals(topCard.getSuits().getUnicode())) {
                matchedSuits.add(checkCard);
                hasLeadingSuit = true;
            }
        }
        return hasLeadingSuit;
    }


//
    public boolean followsSuit(int chosenIndex) {
        boolean followSuit = false;
                if (topTrickPileCard().getSuits().getUnicode().equals(suitOfRank(chosenIndex))) {
                    followSuit = true;

            }

        return followSuit;
    }

    public boolean hasMatchingSuit() {
        boolean hasLeadingSuit = false;
        Card topCard = topTrickPileCard();
        for (Card checkCard : getCards()) {
            if (checkCard.getSuits().getUnicode().equals(topCard.getSuits().getUnicode())) {
                matchedSuits.add(checkCard);
                hasLeadingSuit = true;
            }
        }
        return hasLeadingSuit;
    }

    //test


    public void addChosenRankToPile(){
        Card chosenCard = getCards().get(chosenIndex - 1);
        System.out.println("You chose: " + chosenCard.getSuitValue().getUnicode() + chosenCard.getSuits().getUnicode());
        addCardToPile(chosenCard);
        getCards().remove(chosenCard);
        //playerHandNumbered();
    }

//test

//throw new InputMismatchException(); how to fit this exception to below method?
//            public void findSuit () {
//                System.out.println("Your turn:");
//                playerHandNumbered();
//                do {
//                    System.out.println("Enter the number of the card you want to play:");
//                    chosenIndex = input.nextInt();
//                }
//                while (chosenIndex < 1 || chosenIndex > getCards().size());
//
//                if(kanFollowSuit()) {
//
//                    if (!followsSuit(chosenIndex)) {
//
//                        do {
//                            System.out.println("The leading suit of current trick is " + extractPileSuit() + " you have the leading suit you must play it ");
//                            chosenIndex = input.nextInt();
//
//                        }
//                        while (chosenIndex < 1 || chosenIndex > getCards().size() || !followsSuit(chosenIndex));
//                        addChosenRankToPile();
//
//                    } else if (followsSuit(chosenIndex)) {
//                        addChosenRankToPile();
//                    }
//
//
//                }
//                else {
//                    if(suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode())){
//                        useHeart();
//                    } else {
//                        addChosenRankToPile();
//                    }
//                }
//
//            }

    public void findSuit() {
        System.out.println("Your turn:");
        playerHandNumbered();
        //int chosenIndex;

        do {
            System.out.println("Enter the number of the card you want to play:");
            try {
                chosenIndex = input.nextInt();
                input.nextLine(); // Consume the newline character
                if (chosenIndex < 1 || chosenIndex > getCards().size()) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid card number.");
                input.nextLine(); // Clear the invalid input from the scanner
                chosenIndex = 0; // Reset chosenIndex to loop again
            }
        } while (chosenIndex < 1 || chosenIndex > getCards().size());

        if (hasMatchingSuit() && !followsSuit(chosenIndex)) {

                handleInvalidCardSelection();


        } else if (!hasMatchingSuit() && suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode())) {
            if(getIsIsHeartBroken()||hasOnlyHearts()){
            System.out.println("You can  use hearts .");
            addChosenRankToPile();
            setIsHeartBroken(true);}
            else {

                    handleInvalidCardSelection();

            }
        } else if (hasMatchingSuit() && followsSuit(chosenIndex)) {
            addChosenRankToPile();
        } else if (!hasMatchingSuit() && !followsSuit(chosenIndex)) {
            addChosenRankToPile();
        }

    }




    // Extracted method to handle invalid card selection when not following suit
    private void handleInvalidCardSelection() {
        System.out.println("The leading suit of the current trick is " + extractPileSuit() +
                ". You must play a card following this suit.");
        findSuit(); // Re-prompt the user to choose a valid card
    }

// Other methods remain the same or can be further refactored similarly


//    public void findStartCard() {
//        System.out.println("Your turn:");
//        playerHandNumbered();
//        int chosenIndex;
//
//        do {
//            System.out.println("Enter the number of the card you want to play:");
//            chosenIndex = input.nextInt();
//        } while (chosenIndex < 1 || chosenIndex > getCards().size());
//
//        if (!suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode())) {
//            addChosenRankToPile(chosenIndex);
//        } else {
//            if (kanUseHeart) {
//                addChosenRankToPile(chosenIndex);
//            } else {
//                do {
//                    System.out.println("Heart is not broken. Choose another card:");
//                    chosenIndex = input.nextInt();
//                } while (suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode()));
//                addChosenRankToPile(chosenIndex);
//            }
//        }
//    }



//this
//    public  void findStartCard() {
//        System.out.println("Your turn:");
//        playerHandNumbered();
//        do {
//            System.out.println("Enter the number of the card you want to play:");
//            chosenIndex = input.nextInt();
//        }
//        while (chosenIndex < 1 || chosenIndex > getCards().size());
//
//        if(!(suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode()))) {
//
//            addChosenRankToPile();
//
//
//        } else {
//            if(suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode())){
//                if(getIsIsHeartBroken()){
//                    addChosenRankToPile();
//
//                } else if (suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode())&& !getIsIsHeartBroken()) {
//
//                    for (Card card: getCardList()){
//                        if(!card.getSuits().getUnicode().equals(Suit.SPADE.getUnicode())||!card.getSuits().getUnicode().equals(Suit.DIAMOND.getUnicode())
//                        ||!card.getSuits().getUnicode().equals(Suit.CLUB.getUnicode())){
//                            System.out.println("heart is not broken but since you have only heart you can use it");
//                            addChosenRankToPile();
//                        }
//                    }
//                }
//                     do {System.out.println("Heart is not broken choose another card:");
//                     chosenIndex = input.nextInt();}
//                     while (suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode()));
//                     addChosenRankToPile();
//
//
//
//            }
//        }
//    }
//



    public void findStartCard() {
        System.out.println("Your turn:");
        playerHandNumbered();


        do {
            System.out.println("Enter the number of the card you want to play:");
            chosenIndex = input.nextInt();
        } while (chosenIndex < 1 || chosenIndex > getCards().size());



        if (suitOfRank(chosenIndex) != Suit.HEART.getUnicode()) {
            addChosenRankToPile();
        } else {
            //boolean isHeartsBroken = getIsIsHeartBroken();
            //boolean hasOnlyHearts = hasOnlyHearts();

            if (getIsIsHeartBroken() || !hasOnlyHearts()) {
                addChosenRankToPile();
            } else {

                do {System.out.println("Heart is not broken choose another card:");
                     chosenIndex = input.nextInt();}
                     while (suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode()));
                     addChosenRankToPile();
            }
        }
    }

    public boolean hasOnlyHearts() {
        for (Card card : getCardList()) {
            if (!(card.getSuits().getUnicode() .equals(Suit.HEART.getUnicode()))) {
                return false;
            }
        }
        return true;
    }

// Other methods like getChosenCard(), addChosenRankToPile(), and other necessary methods.




    //    public void useHeart() {
//            if (suitOfRank(chosenIndex).equals(Suit.HEART.getUnicode())) {
//                if (kanUseHeart()){
//                    addChosenRankToPile();
//                }
//                else {
//                    System.out.println("you can not use heart suit since heart are not broken yet");
//                }
//            }
//    }




}
