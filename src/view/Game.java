package view;



import entity.Animal;

import entity.base.Item;
import entity.base.ThreadTimer;
import entity.item.DoubleCoinItem;
import entity.item.SlowItem;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import resloader.ResourceLoader;

import ui.DeadSubscene;
import ui.ScorePane;
import ui.TimerPane;
import entity.Bar;
import entity.Coin;
public class Game {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	public static Bar bar;
	private Coin coin;
	public static Stage gameStage;
	public static TimerPane timerLabel;
	public static ScorePane scoreLabel;
	public static Animal animal;
	public static Item[] item;
	public static boolean isPressRight, isPressLeft, isAlive;
	public static AnimationTimer GAMETIMER;
	public static ThreadTimer currentItemTimer;

	public Game() {
		InitializeStage();
		createKeyListener();
	}
	
	private void InitializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setTitle("Animal Fall Down");
		gameStage.setScene(gameScene);
		gameStage.setResizable(false);
	}
	
	public void createNewGame() {
		Menu.mainStage.hide();
		ResourceLoader.loadResource();
		createGameEntities();
		createGameUi();
		createGameLoop();
		ResourceLoader.titleSound.stop();
		createBackgroundSound();
		isAlive = true;
		isPressRight = false;
		isPressLeft = false;
		gameStage.show();
	}
	
	private void createBackgroundSound() {
		ResourceLoader.bgSound.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				ResourceLoader.bgSound.seek(javafx.util.Duration.ZERO);
			}
		});
		ResourceLoader.bgSound.play();
	}
	
	private void createGameEntities() {
		gamePane.setBackground(new Background(new BackgroundImage(ResourceLoader.gameBg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(100, 100, true, true, true, true))));
	
		coin = new Coin();
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			gamePane.getChildren().add(coin.getCoins()[i]);
		}
		
		bar = new Bar();
		for (int i = 0; i < GameSetting.BAR_NUMBER; i++) {
			gamePane.getChildren().addAll(bar.getBarLeft()[i], bar.getBarRight()[i]);
		}
		
		animal = new Animal();
		gamePane.getChildren().add(animal.getAnimal());
		// Initialize Item
		item = new Item[2];
		item[0] = (Item) new DoubleCoinItem();
		item[1] = (Item) new SlowItem();
		for (Item x : item) {
			gamePane.getChildren().add(x.getItem());
		}
	}
	
	private void createGameUi() {
		// Initialize pointsPane
		scoreLabel = new ScorePane();
		gamePane.getChildren().add(scoreLabel.getScoreLabel());
		// Initialize timerPane
		timerLabel = new TimerPane();
		gamePane.getChildren().add(timerLabel.getTimerLabel());
		System.out.println("gameui");
	}
	
	private void createKeyListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT ) {
					isPressLeft = true;
					animal.left();
				}
				if (event.getCode() == KeyCode.RIGHT) {
					isPressRight = true;
					animal.right();
				}
			}
		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				isPressRight = false;
				isPressLeft = false;
			}
		});
	}

	
	private void createGameLoop() {
		GAMETIMER = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (isAlive == false) {
					Dead();
				} else {
					
					bar.move();
					animal.move();
					coin.move();
					for (Item x : item) {
						x.move();
						x.isSkillActivated();
					}

				}
			}
		};
		GAMETIMER.start();
	}
	
	private void Dead() {
		Game.GAMETIMER.stop();
		ScorePane.updateHighScore();
		gamePane.getChildren().removeAll(scoreLabel.getScoreLabel(), timerLabel.getTimerLabel());
		DeadSubscene deadSubscene = new DeadSubscene();
		deadSubscene.moveSubScene();
		gamePane.getChildren().addAll(deadSubscene, animal.getBomb());
		ResourceLoader.bgSound.stop();
		ResourceLoader.deadSound.play();
		ResourceLoader.titleSound.stop();
		try {
			if (currentItemTimer != null) {
				currentItemTimer.getTimer().interrupt();
				timerLabel.getTimer().interrupt();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
}

