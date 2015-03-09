package PlayerObject;

import java.util.ArrayList;
import java.util.Scanner;

import GameObject.Card;
import GameObject.Deck;


public class Human extends Player {

	public Human(Deck d) {
		super(d);
	}
	
	public Card yourTurn(Scanner in) {
		//Showing all cards
		System.out.println("Your move? Use 1 through " + getHand().size() + " to select.");
		System.out.println(getHand());
		String cardChosen = in.nextLine();
		int num = Integer.parseInt(cardChosen) - 1;
		return play(num);
	}
	
	@Override
	public Card yourTurn(Scanner in, char trump) {
		boolean played = false;
		Card c = null;
		//Showing all cards
		while (!played) {
			while(validCardLocs(trump).size() == 0) {
				System.out.print("Cannot play a card. Press enter to draw");
				in.nextLine();
				Card drawn = draw();
				System.out.println("Drew " + drawn);
				if(drawn.getSuit() == trump) {break;}
			}
			ArrayList<Integer> valid = validCardLocs(trump);
			String s = "";
			for(int i = 0; i < getHand().size(); i++) {
				if(valid.contains(i)) {
					s += "[" + pick(i) + "]   ";
				} else {
					s += pick(i) + "   ";
				}
			}
			System.out.println(s);
			String validIndex = "" + (valid.get(0) + 1);
			for(int i = 1; i < valid.size(); i++) {
				validIndex += ", " + (valid.get(i) + 1);
			}
			
			System.out.print("Your move? Valid moves are " + validIndex + ". ");

			String cardChosen = in.nextLine();
			while(!isNum(cardChosen)) {
				System.out.println("Cannot play, try again.");
				cardChosen = in.nextLine();
			}
			int num = Integer.parseInt(cardChosen) - 1;
			if(num >= 0 && num < getHand().size() && pick(num).getSuit() == trump) {
				played = true;
				c = play(num);
			} else {
				System.out.println("Cannot play, try again.");
			}
		}
		return c;
	}
	
	public boolean isNum(String s) {
		try{
			if(s.length() == 0) {
				return false;
			}
			Integer.parseInt(s);
			return true;
		} catch(ClassCastException cce) {
			return false;
		}
	}
}
