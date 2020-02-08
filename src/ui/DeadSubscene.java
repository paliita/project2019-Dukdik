package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import resloader.ResourceLoader;
import ui.base.HeaderLabel;
import ui.base.MenuSubscene;
import view.Game;
import view.Menu;

public class DeadSubscene extends MenuSubscene{
	
	public DeadSubscene() {
		super(600,600);
		setGraphic();
	}
	
	private void setGraphic() {
		// set GameOverText position.
		ImageView gameOver = new ImageView(ResourceLoader.gameOver);
		gameOver.setFitHeight(140);
		gameOver.setFitWidth(500);
		gameOver.setX(55);
		gameOver.setY(25);
		createHighestScoreLabel();
		createCurrentScoreLabel();
		this.getPane().getChildren().addAll(gameOver, createRetrybutton(), createBackbutton());
			
	}

	private Button createBackbutton() {
		Button backButton = new Button();
		backButton.setLayoutX(336.6);
		backButton.setLayoutY(470);
		ImageView homeImage = new ImageView(ResourceLoader.backButton);
		backButton.setGraphic(homeImage);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ResourceLoader.coinSound.play();
				Game.gameStage.close();
				Menu menu = new Menu();
				menu.mainStage.show();
				ResourceLoader.titleSound.play();
			}
		});
		backButton.setOnMouseEntered(e -> {
			backButton.setScaleX(1.05);
			backButton.setScaleY(1.05);
			backButton.setCursor(Cursor.HAND);
		});
		backButton.setOnMouseExited(e -> {
			backButton.setScaleX(1.0);
			backButton.setScaleY(1.0);
			backButton.setCursor(Cursor.DEFAULT);
		});
		return backButton;
	}

	private Button createRetrybutton() {
		Button retryButton = new Button();
		retryButton.setLayoutX(73.3);
		retryButton.setLayoutY(470);
		ImageView homeImage = new ImageView(ResourceLoader.newGame);
		retryButton.setGraphic(homeImage); //change pic
		retryButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ResourceLoader.coinSound.play();
				Game.gameStage.close();
				Game game = new Game();
				System.out.println("Retry");
				game.createNewGame();
			}
		});
		retryButton.setOnMouseEntered(e -> {
			retryButton.setScaleX(1.05);
			retryButton.setScaleY(1.05);
			retryButton.setCursor(Cursor.HAND);
		});
		retryButton.setOnMouseExited(e -> {
			retryButton.setScaleX(1.0);
			retryButton.setScaleY(1.0);
			retryButton.setCursor(Cursor.DEFAULT);
		});
		return retryButton;
	}

	private void createHighestScoreLabel() {
		HeaderLabel highestScoreLabel = new HeaderLabel("BEST");
		highestScoreLabel.setLayoutX(202);
		highestScoreLabel.setLayoutY(160);
		InfoLabel highestScore = new InfoLabel(String.valueOf(ScorePane.bestScore));
		highestScore.setLayoutY(200);
		this.getPane().getChildren().addAll(highestScoreLabel, highestScore);

	}

	private void createCurrentScoreLabel() {
		HeaderLabel scoreLabel = new HeaderLabel("SCORE");
		scoreLabel.setLayoutX(202);
		scoreLabel.setLayoutY(310);
		InfoLabel score = new InfoLabel(String.valueOf(ScorePane.score));
		score.setLayoutY(350);
		this.getPane().getChildren().addAll(scoreLabel, score);
	}

	
}
