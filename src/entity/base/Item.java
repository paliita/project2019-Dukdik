package entity.base;

import entity.Animal;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resloader.ResourceLoader;
import view.Game;
import view.GameSetting;


public abstract class Item extends Entity implements Hitable{

	private static final int ITEM_HEIGHT = 70;
	private Rectangle item;
	private static boolean isCalled;
	private static boolean isActivated;
	public static final int ITEM_TIME = 5000;
	
	public Item() {
		super();
		setCalled(false);
		setActivated(false);
	}
	
	public Rectangle getItem() {
		return item;
	}

	@Override
	public void checkIfCollide(Hitable animall) {
		Animal animal = (Animal) animall;
		if ((item.getBoundsInParent().intersects(animal.getAnimal().getBoundsInParent()))) {
			reLocate();
			ResourceLoader.coinSound.play();
			isActivated = true;
		}
	}

	@Override
	public void setPosition() {
		item.setLayoutX(-GameSetting.GAME_HEIGHT);
		item.setLayoutY(-GameSetting.GAME_WIDTH);
		
	}

	@Override
	public void setGraphic() {
		item = new Rectangle();
		item = new Rectangle(ITEM_HEIGHT, ITEM_HEIGHT, Color.BLACK);
	}

	@Override
	public void move() {
		if (isCalled) {
			item.setLayoutY(item.getLayoutY() - GameSetting.gameSpeed);
			checkIfCollide(Game.animal);
		}
	}

	public void checkIfOutOfBorder() {
		if (item.getLayoutY() < -item.getHeight()) {
			reLocate();
		}
	}

	public void reLocate() {
		this.setPosition();
	}
	
	public void calledAt(int posx, int posy) {
		item.setLayoutX(posx);
		item.setLayoutY(posy);
		isCalled = true;
	}
	
	public abstract void isSkillActivated();

	public static boolean isCalled() {
		return isCalled;
	}

	public static void setCalled(boolean isCalled) {
		Item.isCalled = isCalled;
	}

	public static boolean isActivated() {
		return isActivated;
	}

	public static void setActivated(boolean isActivated) {
		Item.isActivated = isActivated;
	}

	

	
}
