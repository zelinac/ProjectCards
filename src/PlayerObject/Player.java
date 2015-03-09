package PlayerObject;

import java.util.ArrayList;
import java.util.Scanner;

import GameObject.Card;
import GameObject.Deck;
import GameObject.Hand;

public abstract class Player {
	private Hand hand;
	private String name;
	private Deck deck;
	//TODO Make general CardGame class
	
	public Player() {
		this("Player");
	}

	public Player(String n) {
		hand = new Hand();
		name = n;
	}

	public Player(Deck deck) {
		this();
		this.deck = deck;
	}
	
	public Player(String n, Deck deck) {
		this(n);
		this.deck = deck;
	}
	
	public Card pick(int cardNum) {
		return hand.pick(cardNum);
	}
	
	public Card play(int cardNum) {
		return hand.play(cardNum);
	}
	
	public Card draw() {
		Card toAdd = deck.draw();
		if(toAdd == null) {
			System.out.println("Refilling deck...");
			deck.refill();
			toAdd = deck.draw();
		}
		hand.add(toAdd);
		return toAdd;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean won() {
		return hand.size() == 0;
	}
	
	public ArrayList<Integer> validCardLocs(char trump) {
		ArrayList<Integer> valid = new ArrayList<Integer>();
		for(int i = 0; i < hand.size(); i++) {
			if(hand.getCards().get(i).getSuit() == trump) {
				valid.add(i);
			}
		}
		return valid;
	}

	public abstract Card yourTurn(Scanner in);
	public abstract Card yourTurn(Scanner in, char trump);

	public String toString() {
		return name + " (" + hand.size() + ")";
	}
}
