package GameObject;

import java.util.Comparator;

public class Card implements Comparable<Card>{

	//Standard Deck Layout
	public static final char[] SUITS = {'C', 'D', 'H', 'S'};
	public static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	//Big 2 Style Layout
	public static final String[] RANKS2 = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
		
	//Add Jokers
	public static final String[] RANKS_JOKER = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "R"};
	
	public static final Comparator<Card> SUIT_OVER_RANK = new Comparator<Card> () {
		@Override
		public int compare(Card c1, Card c2) {
			int suitCompare = c1.compareTo(c2.getSuit());
			int rankCompare = c1.compareTo(c2.getRank());
			if(suitCompare != 0) {
				return suitCompare;
			}
			return rankCompare;
		}
	};
	
	private char suit;
	private String rank;
	private int value;
	
	public Card(char suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}
	
	public char getSuit() {
		return suit;
	}
	
	public String getRank() {
		return rank;
	}
	
	public int getValue() {
		return value;
	}
	
	public int compareTo(Card other) {
		return value - other.value;
	}
	
	public int compareTo(char suitOther) {
		return suit - suitOther;
	}
	
	public int compareTo(String rankOther) {
		int rankValue = 0;
		int rankOtherValue = 0;
		for (int i = 0; i < RANKS.length; i++) {
			if (rank.equals(RANKS[i])) {
				rankValue = i;
			}
			if (rankOther.equals(RANKS[i])) {
				rankOtherValue = i;
			}
		}
		return rankValue - rankOtherValue;
	}
	
	public String toString() {
		return rank + " of " + suit;
	}
}
