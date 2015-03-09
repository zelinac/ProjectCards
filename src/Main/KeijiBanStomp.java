package Main;
import java.util.*;

import GameObject.*;
import PlayerObject.Computer;
import PlayerObject.Human;
import PlayerObject.Player;

public class KeijiBanStomp implements Iterable<Player>{

	public final int STARTING_NUM_CARDS = 6;
	private Deck deck;
	private boolean ended;
	private int firstPlayer;
	private ArrayList<Player> players;
//	private int numPlayers;
//	private int numComputers;
	
	//TODO reduce number of Scanners
	//TODO Reformat Scanners so that they can close properly
	//TODO support CPUs
	public KeijiBanStomp(int numPlayers, int numComputers) {
//		this.numPlayers = numPlayers;
//		this.numComputers = numComputers;

		newGame(1, 3);
	}
	
	public void newGame(int human, int computer) {
		if(human + computer < 2) {
			//TODO error catching
		}
		deck = new Deck("standard");
		players = new ArrayList<Player>();
		for(int i = 0; i < human; i++) {
			players.add(new Human(deck));
		}
		for(int i = 0; i < computer; i++) {
			players.add(new Computer(i + 1, deck));
		}
		firstPlayer = 0;
		quickRestart();
	}
	
	public void quickRestart() {
		ended = false;
		Collections.shuffle(players);
		for(Player p : players) {
			p.getHand().draw(deck, STARTING_NUM_CARDS);
		}
	}
	
	public void firstTurn(Scanner in) {
		int current = firstPlayer;
		Card topCard = deck.draw();
		System.out.println("Card pile: " + topCard);
		for (Player p: this) {
			Card c = p.yourTurn(in, topCard.getSuit());
			deck.discard(c);
			System.out.println(p + " selected: " + c);
			if (c.compareTo(topCard) > 1) {
				firstPlayer = current;
				topCard = c;
			}
			current++;
		}
		System.out.println(players.get(firstPlayer) + " wins\n");
	}
	
	public void play(Scanner in) {
		int current = firstPlayer;
		Card topCard = null;
		for (Player p : this) {
			if (current == firstPlayer) {
				topCard = p.yourTurn(in);
				deck.discard(topCard);
				System.out.println(p + " selected: " + topCard);
			} else {
					Card c = p.yourTurn(in, topCard.getSuit());
					deck.discard(c);
					System.out.println(p + " selected: " + c);
					if (c.compareTo(topCard) > 1) {
						firstPlayer = current;
						topCard = c;
					}
			}
			if(p.won()) {
				System.out.println(p + " has won the game. Press enter to start a new game.");
				ended = true;
				in.nextLine();
				quickRestart();
				firstTurn(in);
				return;
			}
			current = (current + 1) % players.size();
		}
		System.out.println(players.get(firstPlayer) + " wins\n");
	}

	
	public boolean gameOver() {
		return ended;
	}
	
//	public void setPlayers(int n) {
//		numPlayers = n;
//	}
//	
//	public void setComputers(int n) {
//		numComputers = n;
//	}
	
	@Override
	public Iterator<Player> iterator() {
		return new TurnOrder(true, firstPlayer);
	}
	
	private class TurnOrder implements Iterator<Player> {
		//Clockwise is counting up (e.g. 2, 3, 4 (loop back), 1)
		private int direction;
		private int first;
		private int index;
		private Player current;
		
		public TurnOrder(boolean clockwise, int start) {
			this.first = start;
			index = first;
			if(clockwise) {
				direction = 1;
			} else {
				direction = -1;
			}
			current = players.get(first);
		}
		
		@Override
		public boolean hasNext() {
			return index != first || players.get(index).equals(current);
		}

		@Override
		public Player next() {
			current = players.get(index);
			Player toReturn = current;
			index += direction;
			if(index >= players.size()) {
				index = 0;
			} else if(index < 0) {
				index = players.size() - 1;
			}
			return toReturn;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
