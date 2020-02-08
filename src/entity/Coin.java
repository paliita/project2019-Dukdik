package entity;

import java.util.Random;
import entity.base.Entity;
import entity.base.Hitable;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import resloader.ResourceLoader;
import ui.ScorePane;
import view.Game;
import view.GameSetting;

public class Coin extends Entity implements Hitable {

	private Rectangle[] coins;
	
	private double coinPositionCheck;
	private boolean slotAvailable;
	private static final int COIN_HEIGHT = 70;
	private static int lastCoinRelocated;
	private static boolean doubleCoinItemActivated = false;
	private Random randomGenerator;
	private int random;
	
	public Coin() {
		super();
		ResourceLoader.loadResource();
		lastCoinRelocated = GameSetting.BAR_NUMBER - 1;
	}
	
	@Override
	public void checkIfCollide(Hitable animall) {
		Animal animal = (Animal) animall;
		
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			if ((coins[i].getBoundsInParent().intersects(animal.getAnimal().getBoundsInParent()))) {
				reLocate(i);
				ResourceLoader.coinSound.play();
				if (!doubleCoinItemActivated) {
					ScorePane.score++;
					ScorePane.updateScore();
				}
				else if (doubleCoinItemActivated) {
					ScorePane.score += 2;
					ScorePane.updateScore();
				}
			}
		}
	}

	@Override
	public void setPosition() {
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			random = randomGenerator.nextInt(350) - 200; // what is it
			coins[i].setLayoutX(random + GameSetting.GAME_WIDTH/2);
			coins[i].setLayoutY(370 - COIN_HEIGHT + (i*GameSetting.REST_GAB));
		}
	}

	@Override
	public void move() {
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			coins[i].setLayoutY(coins[i].getLayoutY() - GameSetting.gameSpeed);
		}
		coinPositionCheck -= GameSetting.gameSpeed;
		isCoinPositionCheckerOutOfBorder();
		checkIfOutOfBorder();
		checkIfCollide(Game.animal);
	}

	public void checkIfOutOfBorder() {
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			if (coins[i].getLayoutY() < -coins[i].getHeight()) {
				reLocate(i);
			}
		}
	}
	
	public void isCoinPositionCheckerOutOfBorder() {
		if (coinPositionCheck <= 0 && slotAvailable == false) {
			slotAvailable = true;
		}
	}
	
	public void reLocate(int i) {
		int chance = randomGenerator.nextInt(3);
		random = randomGenerator.nextInt(200) - 100;
		coins[i].setLayoutY(coins[lastCoinRelocated].getLayoutY() + GameSetting.BAR_WIDTH + GameSetting.REST_GAB);
		if ((chance == 0) && (slotAvailable)) {
			coins[i].setLayoutX(GameSetting.GAME_HEIGHT * 2);
			slotAvailable = false;
			coinPositionCheck = (int) coins[i].getLayoutY();
			int xx = randomGenerator.nextInt(Game.item.length);
			Game.item[xx].calledAt(random + GameSetting.GAME_WIDTH/2, (int) (coins[lastCoinRelocated].getLayoutY() + GameSetting.BAR_WIDTH + GameSetting.REST_GAB));
		} else {
			coins[i].setLayoutX((random + GameSetting.GAME_WIDTH / 2));
		}
		lastCoinRelocated = i;
	}
	
	public Rectangle[] getCoins() {
		return coins;
	}
	
	@Override
	public void setGraphic() {
		randomGenerator = new Random();
		coins = new Rectangle[GameSetting.BAR_NUMBER];
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			coins[i] = new Rectangle(COIN_HEIGHT, COIN_HEIGHT);
			coins[i].setFill(new ImagePattern(ResourceLoader.coin));
		}
	}

	public static boolean isDoubleCoinItemActivated() {
		return doubleCoinItemActivated;
	}

	public static void setDoubleCoinItemActivated(boolean doubleCoinItemActivated) {
		Coin.doubleCoinItemActivated = doubleCoinItemActivated;
	}
	
	
}
