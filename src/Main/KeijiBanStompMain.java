package Main;
import java.util.*;

import GameObject.*;

public class KeijiBanStompMain {
	
	public static void main(String args[]) {
		Scanner console = new Scanner(System.in);
		System.out.print("How many players? ");
		int numPlayers = console.nextInt();
		while (numPlayers > 4) {
			System.out.print("Sorry, you may only have up to 4 players. How many players? ");
			numPlayers = console.nextInt();
		}
		int numComputers = 0;
		if (numPlayers < 4) {
			System.out.print("Do you want to fill up the remaining seats with computers? ");
			String response = console.next();
			if (response.toLowerCase().charAt(0) == 'y') {
				numComputers = 4 - numPlayers;
			}
		}
		KeijiBanStomp game = new KeijiBanStomp(numPlayers, numComputers);
		play(console, game);
	}
	
	public static void play(Scanner console, KeijiBanStomp game) {
//		Hand winner = game.firstTurn(console);
//		game.play(winner);
	}
}
