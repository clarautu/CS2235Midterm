//MId_Project_War_Clark
//Autumn Clark
//CS 2235
//Dr.Kirby

import java.util.ArrayDeque;

public class War {
    //Instance variables
    private boolean _isThreePLayers;
    private boolean _printCardCounts;
    private boolean _showPlayedCards;
    private Player _player1;
    private Player _player2;
    private Player _player3;
    private ArrayDeque<Card> _table = new ArrayDeque<>();
    private ArrayDeque<Card> _warChest = new ArrayDeque<>();
    private static int _numOfWars;
    private static int _numDoubleWars;
    private static int _totalSteps;

    //Constructor
    public War(boolean isThreePlayers, boolean printCardCounts, boolean showPlayedCards) {
        _isThreePLayers = isThreePlayers;
        _printCardCounts = printCardCounts;
        _showPlayedCards = showPlayedCards;
        Initialize();
        if (isThreePlayers) { //Play with three players
            PlayThree();
        } else { //Else pLay with two
            PlayTwo();
        }
        Report();
    }

    //Methods

    private void Initialize() {
        //Initialize global variables
        _totalSteps = 0;
        _numOfWars = 0;
        _numDoubleWars = 0;
        //Create the deck
        Deck newDeck = new Deck();
        newDeck.Shuffle();
        if (_isThreePLayers) { //Play the game with 3 players
            //Initialize the players and deal the cards
            _player1 = new Player();
            _player2 = new Player();
            _player3 = new Player();
            for (int i = 0; i < 18; i++) {
                _player1.AddCard(newDeck.DealCard());
                _player2.AddCard(newDeck.DealCard());
                _player3.AddCard(newDeck.DealCard());
            }
            //System.out.println("Player 1 cards: \n" + _player1 + "Num of cards: " + _player1.GetSize() + "\n");
            //System.out.println("Player 2 cards: \n" + _player2 + "Num of cards: " + _player2.GetSize() + "\n");
            //System.out.println("Player 3 cards: \n" + _player3 + "Num of cards: " + _player3.GetSize());
        } else { //Play the game with 2 players
            //Initialize the players and deal the cards
            _player1 = new Player();
            _player2 = new Player();
            for (int i = 0; i < 27; i++) {
                _player1.AddCard(newDeck.DealCard());
                _player2.AddCard(newDeck.DealCard());
            }
            //System.out.println("Player 1 cards: \n" + _player1 + "Num of cards: " + _player1.GetSize() + "\n");
            //System.out.println("Player 2 cards: \n" + _player2 + "Num of cards: " + _player2.GetSize());
        }
    }

    private void PlayTwo(){
        //Play cards to table
        do {
            _totalSteps++;
            int whoWon = WhoWon();
            if (whoWon == 0){ //We have a war
                WarTime();
            } else if (whoWon == 1){ //Player1 won
                _player1.AddCard(_table.remove());
                _player1.AddCard(_table.remove());
            } else { //Player2 won
                _player2.AddCard(_table.remove());
                _player2.AddCard(_table.remove());
            }
            if (_printCardCounts) {
                int x = _player1.GetSize();
                int y = _player2.GetSize();
                System.out.println("Step " + _totalSteps + ": Player1 has " + x + " cards; PLayer2 has " + y + " cards");
            }
            if (_totalSteps == 100000){
                int x;
                if (_player1.GetSize() > _player2.GetSize()){
                    x = 1;
                } else {
                    x = 2;
                }
                System.out.println("Players are caught in a loop and game will never end.\nPlayer" +
                        x + " has the most cards and wins.");
                return;
            }
        } while (!HasSomeoneLost());
    }

    private void PlayThree(){
        //Play cards to table
        do {
            _totalSteps++;
            int whoWon = WhoWon();
            if (whoWon == 0){ //We have a war
                WarTime();
            } else if (whoWon == 1){ //Player1 won
                _player1.AddCard(_table.remove());
                _player1.AddCard(_table.remove());
                _player1.AddCard(_table.remove());
            } else if (whoWon == 2){ //Player2 won
                _player2.AddCard(_table.remove());
                _player2.AddCard(_table.remove());
                _player2.AddCard(_table.remove());
            } else { //Player3 won
                _player3.AddCard(_table.remove());
                _player3.AddCard(_table.remove());
                _player3.AddCard(_table.remove());
            }
            if (_printCardCounts) {
                int x = _player1.GetSize();
                int y = _player2.GetSize();
                int z = _player3.GetSize();
                System.out.println("Step " + _totalSteps + ": Player1 has " + x + " cards; PLayer2 has " + y +
                        " cards; PLayer3 has "+ z + " cards");
            }
            if (_totalSteps == 100000){
                int x;
                if (_player1.GetSize() > _player2.GetSize()){
                    if (_player1.GetSize() > _player3.GetSize()){
                        x = 1;
                    } else {
                        x = 3;
                    }
                } else {
                    if (_player2.GetSize() > _player3.GetSize()){
                        x = 2;
                    } else {
                        x = 3;
                    }
                }
                System.out.println("Players are caught in a loop and game will never end.\nPlayer" +
                        x + " has the most cards and wins.");
                return;
            }
        } while (!HasSomeoneLost());
    }

    private boolean HasSomeoneLost(){
        if (_isThreePLayers){
            if (_player1.GetSize() == 0){
                //Player1 lost
                System.out.println("Player1 lost.");
                return true;
            }
            if (_player2.GetSize() == 0){
                //Player2 lost
                System.out.println("Player2 lost.");
                return true;
            }
            if (_player3.GetSize() == 0){
                //Player3 lost
                System.out.println("Player3 lost.");
                return true;
            }
        } else {
            if (_player1.GetSize() == 0){
                //Player1 lost
                System.out.println("Player1 lost.");
                return true;
            }
            if (_player2.GetSize() == 0){
                //Player2 lost
                System.out.println("Player2 lost.");
                return true;
            }
        }
        return false;
    }

    private void Report(){
        System.out.println("Total steps: " + _totalSteps + "\nNumber of wars: " + _numOfWars +
                "\nNumber of double wars: " + _numDoubleWars);
    }

    private void WarTime(){
        _numOfWars++; //Count number of wars
        if (_isThreePLayers){ //Handle the war if three players

            _warChest.addFirst(_table.remove()); //Add the table cards to the war chest
            _warChest.addFirst(_table.remove());
            _warChest.addFirst(_table.remove());
            if (_player1.GetSize() < 3){
                NotEnoughCards(_player1);
            } else {
            _warChest.addFirst(_player1.PLayCard()); //Add 2 cards from player1 to the war chest
            _warChest.addFirst(_player1.PLayCard());
            }
            if (_player2.GetSize() < 3){
                NotEnoughCards(_player2);
            } else {
            _warChest.addFirst(_player2.PLayCard()); //Add 2 cards from player2 to the war chest
            _warChest.addFirst(_player2.PLayCard());
            }
            if (_player3.GetSize() < 3){
                NotEnoughCards(_player3);
            } else {
            _warChest.addFirst(_player3.PLayCard()); //Add 2 cards from player3 to the war chest
            _warChest.addFirst(_player3.PLayCard());
            }
            //Play new cards to the table
            int whoWon = WhoWon();
            if (whoWon == 0){ //We have a war
                _numDoubleWars++;
                _numOfWars--; //Count a double war and remove the extra count from wars
                WarTime();
            } else if (whoWon == 1){ //Player1 won
                _player1.AddCard(_table.remove());
                _player1.AddCard(_table.remove());
                _player1.AddCard(_table.remove());
                for (Card card : _warChest){ //Add all the cards in the war chest to player1
                    _player1.AddCard(card);
                }
                _warChest = new ArrayDeque<>(); //Reset the war chest
            } else if (whoWon == 2){ //Player2 won
                _player2.AddCard(_table.remove());
                _player2.AddCard(_table.remove());
                _player2.AddCard(_table.remove());
                for (Card card : _warChest){ //Add all the cards in the war chest to player2
                    _player2.AddCard(card);
                }
                _warChest = new ArrayDeque<>(); //Reset the war chest
            } else { //PLayer3 won
                _player3.AddCard(_table.remove());
                _player3.AddCard(_table.remove());
                _player3.AddCard(_table.remove());
                for (Card card : _warChest){ //Add all the cards in the war chest to player2
                    _player3.AddCard(card);
                }
                _warChest = new ArrayDeque<>(); //Reset the war chest
            }
        } else { //Handle the war if two players

            _warChest.addFirst(_table.remove()); //Add the table cards to the war chest
            _warChest.addFirst(_table.remove());
            if (_player1.GetSize() < 3){
                NotEnoughCards(_player1);
            } else {
            _warChest.addFirst(_player1.PLayCard()); //Add 2 cards from player1 to the war chest
            _warChest.addFirst(_player1.PLayCard());
            }
            if (_player2.GetSize() < 3) {
                NotEnoughCards(_player2);
            } else {
            _warChest.addFirst(_player2.PLayCard()); //Add 2 cards from player2 to the war chest
            _warChest.addFirst(_player2.PLayCard());
            }
            //Play new cards to the table
            int whoWon = WhoWon();
            if (whoWon == 0){ //We have a war
                _numDoubleWars++;
                _numOfWars--; //Count a double war and remove the extra count from wars
                WarTime();
            } else if (whoWon == 1){ //Player1 won
                _player1.AddCard(_table.remove());
                _player1.AddCard(_table.remove());
                for (Card card : _warChest){ //Add all the cards in the war chest to player1
                    _player1.AddCard(card);
                }
                _warChest = new ArrayDeque<>(); //Reset the war chest
            } else { //Player2 won
                _player2.AddCard(_table.remove());
                _player2.AddCard(_table.remove());
                for (Card card : _warChest){ //Add all the cards in the war chest to player2
                    _player2.AddCard(card);
                }
                _warChest = new ArrayDeque<>(); //Reset the war chest
            }
        }
    }

    //Used when players don't have enough cards for a war ; Alters how many cards they add, if any, to the war chest
    private void NotEnoughCards(Player player){
        if (player.GetSize() == 2){ //Player only has 2 cards
            _warChest.addFirst(player.PLayCard()); //PLayer only adds 1 card to the war chest
        } else if (player.GetSize() == 1){ //PLayer only has 1 card
            //Player gets a pass on adding anything to the war chest
        } else { //Double war occurred and player now has no cards
            player.AddCard(_warChest.removeFirst()); //Give player the last card played to the war chest to give them another shot
        }
    }

    //Returns 0 if a war, 1 if player1 won, 2 if player2 won, and 3 if player3 won
    private int WhoWon(){
        if (_isThreePLayers){ //Playing with three players
            Card player1 = _player1.PLayCard();
            Card player2 = _player2.PLayCard();
            Card player3 = _player3.PLayCard();
            if (player1.GetValue().ordinal() == player2.GetValue().ordinal()){ //Player1 and player2 values are equal
                if (player1.GetValue().ordinal() == player3.GetValue().ordinal()){ //Player3 value also matches
                    _table.addFirst(player1);
                    _table.addFirst(player2);
                    _table.addFirst(player3);
                    ShowCards(0);
                    return 0; //Three way war
                } else { //No three way war
                    _table.addFirst(player1);
                    _table.addFirst(player2);
                    _table.addFirst(player3);
                    ShowCards(1);
                    return 1;
                }
            } else if (player1.GetValue().ordinal() > player2.GetValue().ordinal()){ //Player1 value is higher than player2
                if (player1.GetValue().ordinal() > player3.GetValue().ordinal()){ //Player1 value is higher than player3 too
                    _table.addFirst(player1);
                    _table.addFirst(player2);
                    _table.addFirst(player3);
                    ShowCards(1);
                    return 1; //Player1 won
                } else {
                    _table.addFirst(player1);
                    _table.addFirst(player2);
                    _table.addFirst(player3);
                    ShowCards(3);
                    return 3; //Player3 won
                }
            } else if (player2.GetValue().ordinal() > player3.GetValue().ordinal()){ //Player2 value is higher than player3
                _table.addFirst(player1);
                _table.addFirst(player2);
                _table.addFirst(player3);
                ShowCards(2);
                return 2; //Player2 won
            } else {
                _table.addFirst(player1);
                _table.addFirst(player2);
                _table.addFirst(player3);
                ShowCards(3);
                return 3; //Player3 won
            }
        } else { //Playing with two players
            _table.addFirst(_player1.PLayCard());
            _table.addFirst(_player2.PLayCard());
            if (_table.peekLast().GetValue() == _table.peekFirst().GetValue()) { //Card values are the same; We have a war
                ShowCards(0);
                return 0;
            } else if (_table.peekLast().GetValue().ordinal() > _table.peekFirst().GetValue().ordinal()) { //PLayer1's card has a higher value
                ShowCards(1);
                return 1;
            } else { //Player2's card has a higher value
                ShowCards(2);
                return 2;
            }
        }
    }

    private void ShowCards(int whoWon){
        if (_showPlayedCards){
            if (_isThreePLayers) {
                Card player1 = _table.removeLast();
                Card player2 = _table.removeLast();
                Card player3 = _table.removeLast();
                if (whoWon == 3) { //Player3 won
                    System.out.println("PLayer3's " + player3 + " beat player1's " + player1 +
                            " and player2's " + player2);
                } else if (whoWon == 2) { //Player2 won
                    System.out.println("PLayer2's " + player2 + " beat player1's " + player1 +
                            " and player3's " + player3);
                } else if (whoWon == 1) { //Player1 won
                    System.out.println("PLayer1's " + player1 + " beat player2's " + player2 +
                            " and player3's " + player3);
                } else { //War; No one wwn
                    System.out.println("WAR!");
                }
                _table.addFirst(player1);
                _table.addFirst(player2);
                _table.addFirst(player3);
            } else {
                if (whoWon == 2) { //Player2 won
                    System.out.println("Player1's " + _table.peekFirst() + " beat Player2's " + _table.peekLast());
                } else if (whoWon == 1) { //Player1 won
                    System.out.println("Player2's " + _table.peekLast() + " beat Player1's " + _table.peekFirst());
                } else { //War; No one wwn
                    System.out.println("WAR!");
                }
            }
        }
    }

    public static void main (String[] args){
        double avgSteps = 0;
        double avgWars = 0;
        double avgDoubleWars = 0;
        int x = 1000;
        for (int i = 0; i < x; i++) {
            War warGame = new War(true, false, false);
            avgSteps += _totalSteps;
            avgWars += _numOfWars;
            avgDoubleWars += _numDoubleWars;
        }
        avgSteps = avgSteps / x;
        avgWars = avgWars / x;
        avgDoubleWars = avgDoubleWars / x;

        System.out.println("\n\n\nFor 3 players:\nAverage steps: " + avgSteps + "\nAverage num of wars: " + avgWars +
                "\nAverage num of double wars: " + avgDoubleWars);
    }
}
