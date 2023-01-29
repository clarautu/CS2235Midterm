//MId_Project_War_Clark
//Autumn Clark
//CS 2235
//Dr.Kirby

import java.util.ArrayDeque;

public class Player {
    //Instance variables
    private ArrayDeque<Card> _playerDeck;

    //Constructors
    public Player(){
        _playerDeck = new ArrayDeque<>();
    }
    //Methods
    public void AddCard(Card c){
        _playerDeck.addLast(c);
    }
    public Card PLayCard(){
        return _playerDeck.removeFirst();
    }
    public String toString(){
        String sb = "";
        for (var card : _playerDeck){
            if(card.GetValue() == Card.Values.The){ //Check if the card is a Joker
                sb += card.GetValue() + " " + card.GetSuit() + "\n";
            } else {
                sb += card.GetValue() + " of " + card.GetSuit() + "\n";
            }
        }
        return sb;
    }
    public int GetSize(){
        return _playerDeck.size();
    }
}
