package ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import resloader.ResourceLoader;
import view.Game;
import view.GameSetting;

public class CharacterScene {
	public static Stage characterStage;
	public static Scene characterScene;
	private VBox characterPane;
	public static boolean chooseHorse;
	public static boolean chooseGiraffe;
	public static boolean chooseDog;
	HBox dogBox;
	HBox giraffeBox;
	HBox horseBox;
	
	private ImageView dogImage;
	private ImageView giraffeImage;
	private ImageView horseImage; 
	
	private Label dog;
	private Label giraffe;
	private Label horse;
	
	public CharacterScene() {
		InitializeStage();
		chooseHorse = false;
		chooseGiraffe = false;
		chooseDog = false;
		dogImage = new ImageView(ResourceLoader.dog1);
		giraffeImage = new ImageView(ResourceLoader.giraffe1);
		horseImage = new ImageView(ResourceLoader.horse1);
		dog = new Label("DOG");
		giraffe = new Label("GIRAFFE");
		horse = new Label("HORSE");
		
	}
	
	private void InitializeStage() {
		characterPane = new VBox();
		characterScene = new Scene(characterPane, GameSetting.GAME_WIDTH, GameSetting.GAME_HEIGHT);
		characterStage = new Stage();
		characterStage.setTitle("Animal Fall Down");
		characterStage.setScene(characterScene);
		characterStage.setResizable(false);
	}
	
	public void createCharacter() {
		setPane();
		animalBox();
		addCharacterLabel();
		addDogCharacter();
		addGiraffeCharacter();
		addHorseCharacter();
		addStartButton();
		characterStage.show();
	}
	
	private void setPane() {
		characterPane.setAlignment(Pos.CENTER);
		characterPane.setSpacing(20);
		characterPane.setPrefHeight(GameSetting.GAME_HEIGHT);
		characterPane.setPrefWidth(GameSetting.GAME_WIDTH);
		characterPane.setBackground(new Background(new BackgroundImage(ResourceLoader.mainBg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(100, 100, true, true, true, true))));

	}
	
	private void animalBox() {
		dogBox = new HBox();
		giraffeBox = new HBox();
		horseBox = new HBox();
		dogBox.setAlignment(Pos.CENTER);
		dogBox.setSpacing(20);
		giraffeBox.setAlignment(Pos.CENTER);
		giraffeBox.setSpacing(20);
		horseBox.setAlignment(Pos.CENTER);
		horseBox.setSpacing(20);
	}
	
	private void addCharacterLabel() {
		ImageView characterLabel = new ImageView(ResourceLoader.characterLabel);
		characterLabel.setFitHeight(130);
		characterLabel.setFitWidth(650);
		characterLabel.setPreserveRatio(true);
		characterPane.getChildren().add(characterLabel);
	}
	
	private void addDogCharacter() {
		setOnImage(dogImage);
		dogImage.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				ResourceLoader.coinSound.play();
				dogImage.setScaleX(1.2);
				dogImage.setScaleY(1.2);
				chooseDog = true;
				chooseGiraffe = false;
				chooseHorse = false;
				unExpand();
				highlightText();
				unHighlightText();
			}
		});
		setOnText(dog, dogImage);
		dog.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				ResourceLoader.coinSound.play();
				dogImage.setScaleX(1.2);
				dogImage.setScaleY(1.2);
				chooseDog = true;
				chooseGiraffe = false;
				chooseHorse = false;
				unExpand();
				highlightText();
				unHighlightText();
			}
		});
		dogBox.getChildren().addAll(dogImage,dog);
		characterPane.getChildren().add(dogBox);
		
	}
	
	private void addGiraffeCharacter() {
		setOnImage(giraffeImage);
		giraffeImage.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				ResourceLoader.coinSound.play();
				giraffeImage.setScaleX(1.2);
				giraffeImage.setScaleY(1.2);
				chooseGiraffe = true;
				chooseDog = false;
				chooseHorse = false;
				unExpand();
				highlightText();
				unHighlightText();
			}
		});
		setOnText(giraffe, giraffeImage);
		giraffe.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				ResourceLoader.coinSound.play();
				giraffeImage.setScaleX(1.2);
				giraffeImage.setScaleY(1.2);
				chooseGiraffe = true;
				chooseDog = false;
				chooseHorse = false;
				unExpand();
				highlightText();
				unHighlightText();
			}
		});
		giraffeBox.getChildren().addAll(giraffeImage, giraffe);
		characterPane.getChildren().add(giraffeBox);
	}
	
	private void addHorseCharacter() {
		setOnImage(horseImage);
		horseImage.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				ResourceLoader.coinSound.play();
				horseImage.setScaleX(1.2);
				horseImage.setScaleY(1.2);
				chooseHorse = true;
				chooseDog = false;
				chooseGiraffe = false;
				unExpand();
				highlightText();
				unHighlightText();
			}
		});
		setOnText(horse, horseImage);
		horse.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				ResourceLoader.coinSound.play();
				horseImage.setScaleX(1.2);
				horseImage.setScaleY(1.2);
				chooseHorse = true;
				chooseDog = false;
				chooseGiraffe = false;
				unExpand();
				highlightText();
				unHighlightText();
			}
		});
		horseBox.getChildren().addAll(horseImage, horse);
		characterPane.getChildren().add(horseBox);
	}
	
	private void addStartButton() {
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
				if (chooseDog || chooseGiraffe || chooseHorse) {
					ResourceLoader.coinSound.play();
					characterStage.hide();
					ResourceLoader.titleSound.stop();
					Game game = new Game();
					System.out.println("Game Start!");
					game.createNewGame();
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Choose character first");
					alert.show();
				}
			}	
		});
		characterPane.getChildren().add(startButton);
	}
	
	private void setOnImage(ImageView image) {
		image.setOnMouseEntered(e -> {
			image.setScaleX(1.05);
			image.setScaleY(1.05);
			image.setCursor(Cursor.HAND);
		});
		image.setOnMouseExited(e -> {
			image.setScaleX(1.0);
			image.setScaleY(1.0);
			image.setCursor(Cursor.DEFAULT);
			
		});
	}
	
	private void setOnText(Label label, ImageView image) {
		label.setFont(Font.font(null,FontWeight.BLACK,40));
		label.setTextFill(Color.DARKGREY);
		label.setOnMouseEntered(e -> {
			image.setScaleX(1.05);
			image.setScaleY(1.05);
			label.setCursor(Cursor.HAND);
		});
		label.setOnMouseExited(e -> {
			image.setScaleX(1.0);
			image.setScaleY(1.0);
			label.setCursor(Cursor.DEFAULT);
			
		});
	}
	
	private void unExpand() {
		if (!chooseDog) {
			dogImage.setScaleX(1.0);
			dogImage.setScaleY(1.0);
		}
		if (!chooseGiraffe) {
			giraffeImage.setScaleX(1.0);
			giraffeImage.setScaleY(1.0);
		}
		if (!chooseHorse) {
			horseImage.setScaleX(1.0);
			horseImage.setScaleY(1.0);
		}
	}
	
	private void highlightText() {
		if (chooseDog) {
			dog.setFont(Font.font(null,FontWeight.BOLD,50));
			dog.setTextFill(Color.CHOCOLATE);
		}
		else if (chooseGiraffe) {
			giraffe.setFont(Font.font(null,FontWeight.BLACK,50));
			giraffe.setTextFill(Color.CHOCOLATE);
		}
		else if (chooseHorse) {
			horse.setFont(Font.font(null,FontWeight.BLACK,50));
			horse.setTextFill(Color.CHOCOLATE);
		}
	}
	
	private void unHighlightText() {
		if (!chooseDog) {
			dog.setFont(Font.font(null,FontWeight.BLACK,40));
			dog.setTextFill(Color.DARKGREY);
		}
		if (!chooseGiraffe) {
			giraffe.setFont(Font.font(null,FontWeight.BLACK,40));
			giraffe.setTextFill(Color.DARKGREY);
		}
		if (!chooseHorse) {
			horse.setFont(Font.font(null,FontWeight.BLACK,40));
			horse.setTextFill(Color.DARKGREY);
		}
	}
}
