package PlayerObject;

import java.util.ArrayList;
import java.util.Scanner;

import GameObject.Card;
import GameObject.Deck;

//Difficulty: easy
public class Computer extends Player {

	public Computer(int num) {
		super("Computer " + num);
	}

	public Computer(int num, Deck d) {
		super("Computer " + num, d);
	}
	
	@Override
	public Card yourTurn(Scanner in, char trump) {
		ArrayList<Integer> valid = validCardLocs(trump);
		int drewCards = 0;
		while(valid.size() == 0) {
			draw();
			valid = validCardLocs(trump);
			drewCards++;
		}
		if(drewCards > 0) {
			System.out.println(this + " drew " + drewCards + " card(s)");
		}
		return play(valid.get((int) (Math.random() * valid.size())));
	}
	@Override
	public Card yourTurn(Scanner in) {
		return play((int) (Math.random() * getHand().size()));
	}
}
