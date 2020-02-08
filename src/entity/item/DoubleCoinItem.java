package entity.item;

import entity.Coin;
import entity.base.Item;
import entity.base.ThreadTimer;
import javafx.scene.paint.ImagePattern;
import resloader.ResourceLoader;
import view.Game;


public class DoubleCoinItem extends Item implements ThreadTimer{

	Thread Timer;
	
	public DoubleCoinItem() {
		super();
		this.getItem().setFill(new ImagePattern(ResourceLoader.doubleCoin));
	}
	
	@Override
	public void isSkillActivated() {
		if (Item.isActivated()) {
			ResourceLoader.itemSound.play();
			Game.currentItemTimer = this;
			Item.setActivated(false);
			Coin.setDoubleCoinItemActivated(true);
			System.out.println("Doublecoin activated");
			int time = Item.ITEM_TIME;
			Game.timerLabel.showTimerPane();
			Timer = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Game.timerLabel.updateTimer();
						Thread.sleep(time);
						//Coin.doubleCoinItemActivated = false;
						Coin.setDoubleCoinItemActivated(false);
						Game.currentItemTimer = null;
						Game.timerLabel.hideTimerPane();
					} catch (InterruptedException e) {
						Game.timerLabel.hideTimerPane();
					}
				}
			});
			Timer.start();
		}
	}

	@Override
	public Thread getTimer() {
		return Timer;
	}

}
