package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import resloader.ResourceLoader;
import ui.CharacterScene;
import ui.HowToScene;

public class Menu {
	
	private VBox mainPane;
	private Scene mainScene;
	public static Stage mainStage;
	
	public Menu() {
		mainPane = new VBox();
		mainScene = new Scene(mainPane, GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		mainStage = new Stage();
		mainStage.setTitle("Animal Fall Down");
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		ResourceLoader.loadResource();
		setPane();
		addGameLogo();
		createStartButton();
		createHowtoplayButton();
		createBackgroundSound();
		playBackgroundSound();
	}
	
	private void setPane() {
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setSpacing(20);
		mainPane.setPrefWidth(GameSetting.GAME_WIDTH);
		mainPane.setPrefHeight(GameSetting.GAME_HEIGHT);
		mainPane.setBackground(new Background(new BackgroundImage(ResourceLoader.mainBg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(100, 100, true, true, true, true))));

	}
	
	private void addGameLogo() {
		ImageView titieImage = new ImageView(ResourceLoader.title);
		titieImage.setFitHeight(300);
		titieImage.setFitWidth(700);
		mainPane.getChildren().add(titieImage);
	}
	
	private void createHowtoplayButton() {
		ImageView howToImage = new ImageView(ResourceLoader.howtoButton);
		Button howToButton = new Button();
		howToButton.setGraphic(howToImage);
		
		howToButton.setOnMouseEntered(e -> {
			howToButton.setScaleX(1.05);
			howToButton.setScaleY(1.05);
			howToButton.setCursor(Cursor.HAND);
		});
		howToButton.setOnMouseExited(e -> {
			howToButton.setScaleX(1.0);
			howToButton.setScaleY(1.0);
			howToButton.setCursor(Cursor.DEFAULT);
		});
		howToButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ResourceLoader.coinSound.play();
				ResourceLoader.titleSound.stop();
				Menu.mainStage.hide();
				HowToScene howTo = new HowToScene();
				howTo.createHowTo();
			}			
		});
		mainPane.getChildren().add(howToButton);
		
	}
	private void createStartButton() {
		ImageView startImage = new ImageView(ResourceLoader.startButton);
		Button startButton = new Button();
		startButton.setGraphic(startImage);
		
		startButton.setOnMouseEntered(e -> {
			startButton.setScaleX(1.05);
			startButton.setScaleY(1.05);
			startButton.setCursor(Cursor.HAND);
		});
		startButton.setOnMouseExited(e -> {
			startButton.setScaleX(1.0);
			startButton.setScaleY(1.0);
			startButton.setCursor(Cursor.DEFAULT);
		});
		startButton.setOnAction(e -> {
			startButton.setCursor(Cursor.DEFAULT);
		});
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ResourceLoader.coinSound.play();
				Menu.mainStage.hide();
				CharacterScene character  = new CharacterScene();
				character.createCharacter();
			}	
		});

		mainPane.getChildren().add(startButton);
	}
	
	private void createBackgroundSound() {
		ResourceLoader.titleSound.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				ResourceLoader.titleSound.seek(javafx.util.Duration.ZERO);
			}
		});
	}
	
	public static void playBackgroundSound() {
		ResourceLoader.titleSound.play();
	}

}
