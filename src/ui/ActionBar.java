package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.*;
import java.text.DecimalFormat;

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

	private int gold = 100;
	private boolean showTowerCost = false;
	private int towerCostType;

	private DecimalFormat formatter ;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		formatter = new DecimalFormat("0.00");

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

		drawWaveInfo(g);
		
		drawGoldAmount(g);

		if(showTowerCost)
		drawTowerCost(g);
	}

	private void drawTowerCost(Graphics g) {

		g.setColor(Color.gray);
		g.fillRect(280, 650, 120, 50);
		g.setColor(Color.black);
		g.drawRect(280, 650, 120, 50);

		g.drawString("" + getTowerName(), 285, 670);
		g.drawString("Cost: " + getTowerCost() + "g", 285, 695);

	}

	private String getTowerName() {

		return Constants.Towers.GetName(towerCostType);

	}

	private int getTowerCost() {

		return Constants.Towers.GetTowerCost(towerCostType);

	}

	private void drawGoldAmount(Graphics g) {

		g.drawString("Gold: " + gold, 110, 725);

	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		drawWaveTimerInfo(g);
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);

	}

	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWave().size();
		g.drawString("Wave " + (current + 1) + " / " + size, 425, 770);

	}

	private void drawEnemiesLeftInfo(Graphics g) {
		int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
		g.drawString("Enemies Left: " + remaining, 425, 790);
	}

	private void drawWaveTimerInfo(Graphics g) {
		if (playing.getWaveManager().isWaveTimeStart()) {


			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formattedText = formatter.format(timeLeft);
			g.drawString("Time Left: " + formattedText, 425, 750);
		}
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
			drawDisplayTowerRange(g);
		}
	}

	private void drawDisplayTowerRange(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawOval(displayTower.getX() + 16 - (int)(displayTower.getRange() * 2)/2,displayTower.getY() + 16 - (int)(displayTower.getRange()*2)/2, (int)displayTower.getRange() * 2,(int)displayTower.getRange() * 2);
	}

	private void drawSelectedTowerBorder(Graphics g) {

		g.setColor(Color.BLUE);
		g.drawRect(displayTower.getX(), displayTower.getY(), 32,32);

	}

	public void displayTower(Tower t) {

		displayTower = t;

	}

	public void payForTower(int towerType) {

		this.gold -= Constants.Towers.GetTowerCost(towerType);

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
		showTowerCost =false;
		for (MyButton b: towerBtn)
			b.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for (MyButton b: towerBtn)
				if (b.getBounds().contains(x,y)) {
					b.setMouseOver(true);
					showTowerCost = true;
					towerCostType = b.getId();
				}
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
