package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import resloader.ResourceLoader;

public class ScorePane {

	private static Label scoreLabel;
	public static int score;
	public static int bestScore = 0;
	
	public ScorePane() {
		scoreLabel = new Label();
		score = 0;
		scoreLabel.setPrefHeight(800);
		scoreLabel.setPrefWidth(400);
		scoreLabel.setStyle("-fx-background-color: transparent;");
		scoreLabel.setAlignment(Pos.TOP_LEFT);
		scoreLabel.setPadding(new Insets(10, 10, 10, 10));
		scoreLabel.setText("POINTS : 00");
		setFont();
		System.out.println("scorep");
	}
	
	private void setFont() {
		scoreLabel.setFont(Font.loadFont(ResourceLoader.font, 22));
	}

	public static void updateScore() {
		String textToSet = "POINTS : ";
		if (score < 10) {
			textToSet = textToSet + "0";
		}
		scoreLabel.setText(textToSet + score);
	}

	public static void updateHighScore() {
		if (score > bestScore) {
			bestScore = score;
		}
	}

	public Label getScoreLabel() {
		return scoreLabel;
	}

	public static void setscore(int score) {
		ScorePane.score = score;
	}
	
}
