package ui;

import view.GameSetting;
import view.Menu;
import resloader.ResourceLoader;
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
import javafx.stage.Stage;

public class HowToScene {
	public static Stage howToStage;
	public static Scene howToScene;
	private VBox howToPane;
	
	public HowToScene() {
		ResourceLoader.loadResource();
		InitializeStage();

	}
	
	private void InitializeStage() {
		howToPane = new VBox();
		howToScene = new Scene(howToPane, GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		howToStage = new Stage();
		howToStage.setScene(howToScene);
		howToStage.setResizable(false);
		createBackgroundSound();
		playBackgroundSound();
		
	}
	
	public void createHowTo() {
		setPane();
		addHowTo();
		addHomeButton();
		howToStage.show();
	}
	
	private void setPane() {
		howToPane.setAlignment(Pos.CENTER);
				howToPane.setSpacing(20);
				howToPane.setPrefWidth(GameSetting.GAME_WIDTH);
				howToPane.setPrefHeight(GameSetting.GAME_HEIGHT);
				howToPane.setBackground(new Background(new BackgroundImage(ResourceLoader.mainBg, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(100, 100, true, true, true, true))));

	}
	
	private void addHowTo() {
		ImageView howTo = new ImageView(ResourceLoader.howto);
		howToPane.getChildren().add(howTo);
	}
	
	private void addHomeButton() {
		ImageView homeImage = new ImageView(ResourceLoader.backButton);
		Button homeButton = new Button();
		homeButton.setGraphic(homeImage);
					
		homeButton.setOnMouseEntered(e -> {
			homeButton.setScaleX(1.05);
			homeButton.setScaleY(1.05);
			homeButton.setCursor(Cursor.HAND);
		});
		homeButton.setOnMouseExited(e -> {
			homeButton.setScaleX(1.0);
			homeButton.setScaleY(1.0);
			homeButton.setCursor(Cursor.DEFAULT);
		});
		homeButton.setOnAction(e -> {
			ResourceLoader.coinSound.play();
			ResourceLoader.howtoSound.stop();
			HowToScene.howToStage.hide();
			Menu main = new Menu();
			main.mainStage.show();
		});
		
		howToPane.getChildren().add(homeButton);
	}
	
	private void createBackgroundSound() {
		ResourceLoader.howtoSound.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				ResourceLoader.howtoSound.seek(javafx.util.Duration.ZERO);
			}
		});
	}
	
	public static void playBackgroundSound() {
		ResourceLoader.howtoSound.play();
	}
	

}
