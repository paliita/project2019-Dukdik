package entity;

import java.util.Random;

import entity.base.Entity;
import entity.base.Hitable;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import resloader.ResourceLoader;
import view.GameSetting;

public class Bar extends Entity implements Hitable{

	private Rectangle[] barRight;
	private Rectangle[] barLeft;
	private static int lastBarRelocated;
	private int random;
	private Random randomGenerator;
	
	public Bar() {
		super();
		lastBarRelocated = GameSetting.BAR_NUMBER - 1;
	}

	@Override
	public void checkIfCollide(Hitable x) {
		
	}

	@Override
	public void setPosition() {
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			random = randomGenerator.nextInt(20) * 20;
			barLeft[i].setLayoutX(-random + GameSetting.BAR_HEIGHT + GameSetting.BAR_GAB);
			barRight[i].setLayoutX(-random);
			barLeft[i].setLayoutY(370 + (i*GameSetting.REST_GAB));
			barRight[i].setLayoutY(370 + (i*GameSetting.REST_GAB));
		}
	}
	
	@Override
	public void move() {
		for (int i = 0; i < barLeft.length; i++) {
			barLeft[i].setLayoutY(barLeft[i].getLayoutY() - GameSetting.gameSpeed);
			barRight[i].setLayoutY(barRight[i].getLayoutY() - GameSetting.gameSpeed);
		}
		checkIfOutOfBorder();
	}

	public void checkIfOutOfBorder() {
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			random = randomGenerator.nextInt(20) * 20;
			if (barLeft[i].getLayoutY() < -barLeft[i].getHeight()) {
				reLocate(i);
			}
		}
	}

	public void reLocate(int i) {
		barLeft[i].setLayoutY(barLeft[lastBarRelocated].getLayoutY() + GameSetting.REST_GAB + barLeft[i].getHeight());
		barRight[i].setLayoutY(barLeft[lastBarRelocated].getLayoutY() + GameSetting.REST_GAB + barLeft[i].getHeight());
		barLeft[i].setLayoutX(-random + GameSetting.BAR_HEIGHT + GameSetting.BAR_GAB);
		barRight[i].setLayoutX(-random);
		lastBarRelocated = i;
	}

	@Override
	public void setGraphic() {
		randomGenerator = new Random();
		barLeft = new Rectangle[GameSetting.BAR_NUMBER];
		barRight = new Rectangle[GameSetting.BAR_NUMBER];
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			barLeft[i] = new Rectangle(GameSetting.BAR_HEIGHT, GameSetting.BAR_WIDTH);
			barRight[i] = new Rectangle(GameSetting.BAR_HEIGHT, GameSetting.BAR_WIDTH);
			barLeft[i].setFill(new ImagePattern(ResourceLoader.bar)); //fix the color
			barRight[i].setFill(new ImagePattern(ResourceLoader.bar)); //fix
		}
	}
	
	public Rectangle[] getBarRight() {
		return barRight;
	}

	public void setBarRight(Rectangle[] barRight) {
		this.barRight = barRight;
	}

	public Rectangle[] getBarLeft() {
		return barLeft;
	}

	public void setBarLeft(Rectangle[] barLeft) {
		this.barLeft = barLeft;
	}

}
