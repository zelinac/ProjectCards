package GameState;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Manager {

	private ArrayList<GameState> states;
	private int currentState;

	public static final int MENU = 0;
	public static final int SINGLEPLAYER = 1;
	public static final int MULTIPLAYER = 2;
	
	
	public Manager() {
		states = new ArrayList<GameState>();
		states.add(new MenuState(this));
	}
	
	public void setState(int state) {
		currentState = state;
		states.get(currentState).init();
	}
	
	public int getState() {
		return currentState;
	}
	
	public String getAnswer() {
		return states.get(currentState).answer();
	}
	
	public void selectAnswer() {
		states.get(currentState).select();
	}
	
	public void update() {
		states.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		states.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		states.get(currentState).keyPressed(k);
	}
	
	public void mouseMoved(MouseEvent e) {
		states.get(currentState).mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		states.get(currentState).mouseClicked(e);
	}
	
	public void newGame() {
		currentState = MENU;
		for(int i = states.size() - 1; i > 0; i--)
		{
			states.remove(i);
		}
		//UNFINISHED - add all the states after menu back in
	}
}
