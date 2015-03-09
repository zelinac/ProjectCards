package GameState;

import java.awt.*;
import java.awt.event.*;

public class MenuState extends GameState {
	private int currentChoice = 0;
	private String[] options = {"Single Player Mode", "Multiplayer Mode", "Quit"};
	private Background bg;
	
	public MenuState(Manager m) {
		this.m = m;		
		bg = new Background("/Images/bg-temp.jpg", 0);
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
		// draw menu
		g.setColor(Color.GRAY);
		g.setFont(GameState.TITLEFONT);
		g.drawString("Card Game", 120, 90);
		
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		for(int i  = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 20, 400 + i * 30);
		}
//		g.setFont(new Font("Trebuchet MS", Font.ITALIC, 18));
//		g.setColor(new Color(0, 133, 0));
//		g.drawString("Jed C. and Zelina C.", 480, 470);
	}

	public void select() {
		if(currentChoice == 0) {
			m.setState(Manager.SINGLEPLAYER);
		}
		if(currentChoice == 1) {
			m.setState(Manager.MULTIPLAYER);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	public int getIndex() {
		return Manager.MENU;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int y = e.getY();
		if(y < 405)
			currentChoice = 0;
		else if(y >= 405 && y < 435)
			currentChoice = 1;
		else
			currentChoice = 2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		select();
		e.consume();
	}

	@Override
	public String answer() {
		// TODO Auto-generated method stub
		return null;
	}
}
