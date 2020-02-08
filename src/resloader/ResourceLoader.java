package resloader;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ResourceLoader {

	public static Image mainBg;
	public static Image gameBg;
	public static Image title;
	public static Image startButton;
	public static Image howtoButton;
	
	public static Image backButton;
	public static Image howto;
	
	public static Image characterLabel;
	public static Image giraffe1;
	public static Image horse1;
	public static Image dog1;
	
	public static Image bomb1;
	public static Image coin;
	public static Image doubleCoin;
	public static Image bar;
	
	public static Image deadBox;
	public static Image gameOver;
	
	public static Image newGame;
	public static Image slowItem;
	
	public static MediaPlayer titleSound;
	public static AudioClip moveSound;
	public static AudioClip jumpSound;
	public static AudioClip coinSound;
	public static AudioClip deadSound;
	public static AudioClip itemSound;
	public static MediaPlayer bgSound;
	public static MediaPlayer howtoSound;
	
	public static String font;

	public static void loadResource() {
		mainBg = new Image(ClassLoader.getSystemResource("image/sky.png").toString());
		gameBg = new Image(ClassLoader.getSystemResource("image/sky.png").toString());
		title = new Image(ClassLoader.getSystemResource("image/title2.png").toString());
		startButton = new Image(ClassLoader.getSystemResource("image/start.png").toString());			
		howtoButton = new Image(ClassLoader.getSystemResource("image/howto.png").toString());
		backButton = new Image(ClassLoader.getSystemResource("image/back.png").toString());
		howto = new Image(ClassLoader.getSystemResource("image/howtoscenee.png").toString());
			
		characterLabel = new Image(ClassLoader.getSystemResource("image/character.png").toString());
		dog1 = new Image(ClassLoader.getSystemResource("image/dog.png").toString());
		horse1 = new Image(ClassLoader.getSystemResource("image/horse.png").toString());
		giraffe1 = new Image(ClassLoader.getSystemResource("image/giraffe.png").toString());
		bar = new Image(ClassLoader.getSystemResource("image/bar.png").toString());	
		bomb1 = new Image(ClassLoader.getSystemResource("image/bomb1.png").toString());
		
		coin = new Image(ClassLoader.getSystemResource("image/coin.png").toString());
		doubleCoin = new Image(ClassLoader.getSystemResource("image/double.png").toString());
		
		deadBox = new Image(ClassLoader.getSystemResource("image/graybg.png").toString());
		gameOver = new Image(ClassLoader.getSystemResource("image/gameover2.png").toString());

		newGame = new Image(ClassLoader.getSystemResource("image/new game.png").toString());
		slowItem = new Image(ClassLoader.getSystemResource("image/slow.png").toString());
		
		titleSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/titleSound.wav").toString()));
		moveSound = new AudioClip(ClassLoader.getSystemResource("sound/moveSound.wav").toString());
		jumpSound = new AudioClip(ClassLoader.getSystemResource("sound/jumpSound.mp3").toString());
		itemSound = new AudioClip(ClassLoader.getSystemResource("sound/itemSound.wav").toString());
		coinSound = new AudioClip(ClassLoader.getSystemResource("sound/coinSound.wav").toString());
		deadSound = new AudioClip(ClassLoader.getSystemResource("sound/deadSound.wav").toString());
		bgSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/bgSound.wav").toString()));
		howtoSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/howto.wav").toString()));
	
		font = ClassLoader.getSystemResource("font/kenvector_future.ttf").toString();
	}

}
