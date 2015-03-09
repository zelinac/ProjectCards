package GameObject;
import java.util.*;

public class Hand {
	
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public String toString() {
		String result = "";
		for (Card card: hand) {
			result += card.toString() + "   ";
		}
		return result;
	}
	
	public void add(Card card) {
		hand.add(card);
		Collections.sort(hand, Card.SUIT_OVER_RANK);
	}
	
	public void draw(Deck deck, int num) {
		for(int i = 0; i < num; i++) {
			Card c = deck.draw();
			if(c != null) {
				add(c);
			}
		}
	}
	
	public void draw(Deck deck) {
		Card c = deck.draw();
		if(c != null) {
			hand.add(c);
		}
	}
	
	public Card play(int n) {
		return hand.remove(n);
	}
	
	public Card pick(int n) {
		return hand.get(n);
	}
	
	public int size() {
		return hand.size();
	}
	
	public ArrayList<Card> getCards() {
		return hand;
	}
}
