package entity;


import resloader.ResourceLoader;
import ui.CharacterScene;
import entity.base.Entity;
import entity.base.Hitable;
import entity.base.OutOfBorderException;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Game;
import view.GameSetting;

public class Animal extends Entity implements Hitable {

	private Rectangle animal;
	private Rectangle bomb;
	
	private double currentVelocity;
	private double timer;
	
	private static boolean animalCollideBar;
	
	public Animal() {
		super();
		setAnimalCollideBar(false);
	}

	public void checkIfOutOfBorder() throws OutOfBorderException{
		if (animal.getLayoutY() < 0) {
			throw new OutOfBorderException("Out of bound");
		}
		else if (animal.getLayoutX() > GameSetting.GAME_WIDTH) {
			animal.setLayoutX(5);
		}
		else if (animal.getLayoutX() < 0) {
			animal.setLayoutX(GameSetting.GAME_WIDTH - 5);
		}
		else if (animal.getLayoutY() > GameSetting.GAME_HEIGHT - 60) {
			throw new OutOfBorderException("Out of bound");
		}
	}
	
	@Override
	public void checkIfCollide(Hitable barz) {
		Bar bar = (Bar) barz;
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			if ((bar.getBarLeft()[i].getBoundsInParent().intersects(animal.getBoundsInParent())
					|| bar.getBarRight()[i].getBoundsInParent().intersects(animal.getBoundsInParent()))) {
				setAnimalCollideBar(true);
			}
		}
	}

	
	public void setBombPosition() {
		bomb.setLayoutX(animal.getLayoutX() - animal.getWidth() / 2);
		bomb.setLayoutY(animal.getLayoutY() - animal.getHeight() / 2);
	}

	@Override
	public void setPosition() {
		animal.setLayoutY(300);
		animal.setLayoutX(GameSetting.GAME_WIDTH / 2 - 30);
	}

	@Override
	public void setGraphic() {
		animal = new Rectangle(60, 60);
		if (CharacterScene.chooseDog) {
			animal.setFill(new ImagePattern(ResourceLoader.dog1));
		}
		else if (CharacterScene.chooseGiraffe) {
			animal.setFill(new ImagePattern(ResourceLoader.giraffe1));
		}
		else if (CharacterScene.chooseHorse) {
			animal.setFill(new ImagePattern(ResourceLoader.horse1));
		}
		bomb = new Rectangle(120,120);
		bomb.setFill(new ImagePattern(ResourceLoader.bomb1));
		
	}

	@Override
	public void move() {
		checkIfCollide(Game.bar);
		if (Game.isPressRight == true) {
			if (animal.getLayoutX() < GameSetting.GAME_WIDTH * 2) {
				this.currentVelocity = calVelocity();
				animal.setLayoutX(animal.getLayoutX() + this.currentVelocity);
				this.timer += 0.01;
			}
			try {
				checkIfOutOfBorder();
			} catch (OutOfBorderException e) {
				
				if (animal.getLayoutY() < 0) {
					Game.isAlive = false;
				}
				else if (animal.getLayoutY() > GameSetting.GAME_HEIGHT - 60) {
					Game.isAlive = false;
				}
				animal.setLayoutX(GameSetting.GAME_WIDTH);
			}
		}
		else if (Game.isPressLeft== true) {
			if (animal.getLayoutX() < GameSetting.GAME_WIDTH * 2) {
				this.currentVelocity = calVelocity();
				animal.setLayoutX(animal.getLayoutX() - this.currentVelocity);
				this.timer += 0.01;
			}
			try {
				checkIfOutOfBorder();
			} catch (OutOfBorderException e) {
				
				if (animal.getLayoutY() < 0) {
					Game.isAlive = false;
				}
				else if (animal.getLayoutY() > GameSetting.GAME_HEIGHT - 60) {
					Game.isAlive = false;
				}
				animal.setLayoutX(0);
			}
		}
		if (!isAnimalCollideBar()) {
			if (animal.getLayoutY() < GameSetting.GAME_HEIGHT) {
				animal.setLayoutY(animal.getLayoutY() + 5);
			}
			try {
				checkIfOutOfBorder();
			} catch (OutOfBorderException e) {
				Game.isAlive = false;
			}
		}
		
		if (isAnimalCollideBar()) {
			if (animal.getLayoutY() < GameSetting.GAME_HEIGHT) {
				animal.setLayoutY(animal.getLayoutY() - GameSetting.gameSpeed);
				setAnimalCollideBar(false);
			}
			try {
				checkIfOutOfBorder();
			} catch (OutOfBorderException e) {
				Game.isAlive = false;
				
			}
		}
	}
	
	private double calVelocity() {
		return this.currentVelocity + 1.1 * this.timer;
	}
	
	public void right() {
		if (Game.isAlive) {
			Thread rightThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						for (int i = 0; i < 5; i++) {
							updateRight();
							Thread.sleep(15);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			rightThread.start();
			ResourceLoader.moveSound.play();
		}
	}
	
	public void left() {
		if (Game.isAlive) {
			Thread leftThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						for (int i = 0; i < 5; i++) {
							updateLeft();
							Thread.sleep(15);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			leftThread.start();
			ResourceLoader.moveSound.play();
		}
	}
	
	public void updateLeft() {
		if (animal.getLayoutX() > -GameSetting.GAME_WIDTH) {
			animal.setLayoutX(animal.getLayoutX() - 10);
			currentVelocity = 0;
			timer = 0;
		}
	}
	
	public void updateRight() {
		if (animal.getLayoutX() > -GameSetting.GAME_WIDTH) {
			animal.setLayoutX(animal.getLayoutX() + 10);
			currentVelocity = 0;
			timer = 0;
		}
	}

	public Rectangle getAnimal() {
		return animal;
	}

	public Rectangle getBomb() {
		setBombPosition();
		return bomb;
	}
	
	public void setAnimal(Rectangle animal) {
		this.animal = animal;
	}

	public static boolean isAnimalCollideBar() {
		return animalCollideBar;
	}

	public static void setAnimalCollideBar(boolean animalCollideBar) {
		Animal.animalCollideBar = animalCollideBar;
	}
}
