//MId_Project_War_Clark
//Autumn Clark
//CS 2235
//Dr.Kirby

import java.util.function.BinaryOperator;

public class Card {
    public enum Suits {Hearts, Diamonds, Spades, Clubs, Joker};
    public enum Values {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace, The}
    //instance variables
    private Values _value; //Will represent 2-A as enum Two-Ace ; Joker is The
    private Suits _suit; //Will represent suit

    //Constructor
    public Card(Values v, Suits s){
        _value = v;
        _suit = s;
    }

    //Methods
    public Values GetValue(){
        return _value;
    }
    public Suits GetSuit(){
        return _suit;
    }

    public String toString(){
        String sb = "";

        if(this.GetValue() == Card.Values.The){ //Check if the card is a Joker
            sb += this.GetValue() + " " + this.GetSuit();
        } else {
            sb += this.GetValue() + " of " + this.GetSuit();
        }

        return sb;
    }
}
