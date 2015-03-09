package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Main.KeijiBanStomp;

public class KBSState extends GameState{
	private KeijiBanStomp kbs;
	@Override
	public void init() {
		setup();
		kbs = new KeijiBanStomp(1, 3);
	}

	public void setup() {
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String answer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}

}
