package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;

import scenes.Playing;

public class ActionBar extends Bar {

	private Playing playing;
	private MyButton bMenu;
	private MyButton[] towerBtn;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;

		initButtons();
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", 2, 642, 100, 30);

		towerBtn = new MyButton[3];
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		for (int i = 0; i < towerBtn.length;i++) {
			towerBtn[i] = new MyButton("", xStart + xOffset * i, yStart, w,h,i);
		}

	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);

		for (MyButton b : towerBtn) {
			g.setColor(Color.lightGray);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImg()[b.getId()],b.x,b.y,b.width,b.height,null );

			drawButtonFeedback(g,b);
		}
	}

	public void draw(Graphics g) {

		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for (MyButton b: towerBtn)
			b.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for (MyButton b: towerBtn)
				if (b.getBounds().contains(x,y))
					b.setMouseOver(true);
			return;
		}


	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else {
			for (MyButton b: towerBtn)
				if(b.getBounds().contains(x,y)) {
					b.setMousePressed(true);
					return;
				}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();

		for (MyButton b : towerBtn)
			b.resetBooleans();

	}

}
