package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.*;

import helpz.Constants;
import objects.Tower;
import scenes.Playing;

import static helpz.Constants.Towers;

public class ActionBar extends Bar {

	private Playing playing;
	private MyButton bMenu;
	private MyButton[] towerBtn;
	private Tower selectedTower;
	private Tower displayTower;

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
		drawDisplayTower(g);
	}

	private void drawDisplayTower(Graphics g) {


		if (displayTower != null) {
			g.drawImage(playing.getTowerManager().getTowerImg()[displayTower.getTowerType()], 420, 650, 50, 50,null );
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410,645,220,85);
			g.drawRect(420,650,50,50);
			g.drawImage(playing.getTowerManager().getTowerImg()[displayTower.getTowerType()], 420, 650, 50, 50, null );
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + Towers.GetName(displayTower.getTowerType()), 490,660);
			g.drawString("ID: " + displayTower.getId(), 490,675);

			drawSelectedTowerBorder(g);
		}
	}

	private void drawSelectedTowerBorder(Graphics g) {

		g.setColor(Color.BLUE);
		g.drawRect(displayTower.getX(), displayTower.getY(), 32,32);

	}

	public void displayTower(Tower t) {

		displayTower = t;

	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else {
			for(MyButton b:towerBtn)
				if (b.getBounds().contains(x,y))
					selectedTower = new Tower(0,0,-1,b.getId());
			playing.setSelectTower(selectedTower);
			return;
		}

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
