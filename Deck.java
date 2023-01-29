//MId_Project_War_Clark
//Autumn Clark
//CS 2235
//Dr.Kirby

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //Instance variables
    private ArrayList<Card> _deck;

    //Constructors
    public Deck(){
        //Create a deck
        _deck = new ArrayList<>(54);
        Card.Suits[] suits = {Card.Suits.Hearts, Card.Suits.Diamonds, Card.Suits.Spades, Card.Suits.Clubs};
        Card.Values[] values = {Card.Values.Two, Card.Values.Three, Card.Values.Four, Card.Values.Five,
                Card.Values.Six, Card.Values.Seven, Card.Values.Eight, Card.Values.Nine, Card.Values.Ten,
                Card.Values.Jack, Card.Values.Queen, Card.Values.King, Card.Values.Ace};
        //Add 2-A of each suit to deck
        for (var suit : suits) {
            for (var value: values) {
                Card card = new Card(value, suit);
                _deck.add(card);
            }
        }
        //Add jokers
        Card card = new Card(Card.Values.The, Card.Suits.Joker);
        _deck.add(card);
        _deck.add(card);
    }

    //Methods
    public String toString(){
        String sb = "";
        for (var card : _deck){
            sb += card.GetValue() + " " + card.GetSuit() + "\n";
        }
        return sb;
    }
    public int GetSize(){
        return _deck.size();
    }
    public void Shuffle(){
        Collections.shuffle(_deck);
    }
    public Card DealCard(){
        if (_deck.size() != 0)
            return _deck.remove(0);
        return null;
    }
}
