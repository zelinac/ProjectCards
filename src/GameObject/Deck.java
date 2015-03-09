package GameObject;
import java.util.Iterator;
import java.util.Random;

public class Deck implements Iterable<Card> {
	private Card[] deck;
	private Deck pile;
	private int size;
	
	//Empty Deck
	public Deck() {
		deck = new Card[2];
		size = 0;
	}
	
	public Deck(String type) {
		deck = new Card[2];
		size = 0;
		pile = new Deck();
		if (type.equals("standard")) { //aces high
			for (int i = 0; i < Card.SUITS.length; i++) {
				for (int j = 0; j < Card.RANKS.length; j++) {
					add(new Card(Card.SUITS[i], Card.RANKS[j], 4 * j + i));
				}
			}
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void add(Card card) {
		if (card == null)
			throw new NullPointerException();
		size++;
		int replace = (int) (Math.random() * size());
		deck[size - 1] = deck[replace];
		resize();
		deck[replace] = card;
	}
	
	public Card draw() {
		if (isEmpty()) {
//			throw new java.util.NoSuchElementException();
			System.out.println("This deck is empty");
			return null;
		}
		size--;
		Card temp = deck[size()];
		deck[size()] = null;
		resize();
		return temp;
	}
	
	public Card sample() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return deck[(int) (Math.random() * size())];
	}

	private void resize() {
		if (deck.length == size()) {
			Card[] temp = new Card[size() * 2];
			for (int i = 0; i < size(); i++)
				temp[i] = deck[i];
			deck = temp;
		} else if (deck.length >= 2 && deck.length / 4 > size()) {
			Card[] temp = new Card[deck.length / 2];
			for (int i = 0; i < size(); i++)
				temp[i] = deck[i];
			deck = temp;
		}
	}

	public Iterator<Card> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Card> {
		private int index = 0;
		private int length = size();
		private Card[] list;

		public ListIterator() {
			list = new Card[length];
			for (int i = 0; i < length; i++)
				list[i] = deck[i];
			if (length - 1 > 0)
				shuffle();
		}

		@Override
		public boolean hasNext() {
			return index < length;
		}

		@Override
		public Card next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			Card card = list[index];
			index++;
			return card;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	// Implementing FisherYates shuffle
	public void shuffle() {
		Random random = new Random();
		for (int i = size() - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			// Simple swap
			Card a = deck[index];
			deck[index] = deck[i];
			deck[i] = a;
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Card c: this) {
			s.append(c + "\n");
		}
		return s.toString();
	}
	
	public void discard(Card c) {
		pile.add(c);
	}
	
	public void refill() {
		int size = pile.size();
		for(int i = 0; i < size; i++) {
			this.add(pile.draw());
		}
	}
}