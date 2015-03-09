package Main;

import java.util.Scanner;

public class Runner {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		KeijiBanStomp kbs = new KeijiBanStomp(1, 1);
		kbs.firstTurn(in);
		while(!kbs.gameOver()) {
			kbs.play(in);
		}
		in.close();
	}
}