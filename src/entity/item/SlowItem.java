package entity.item;

import entity.base.Item;
import entity.base.ThreadTimer;
import javafx.scene.paint.ImagePattern;
import resloader.ResourceLoader;
import view.Game;
import view.GameSetting;

public class SlowItem extends Item implements ThreadTimer {

	private Thread Timer;
	
	public SlowItem() {
		super();
		this.getItem().setFill(new ImagePattern(ResourceLoader.slowItem));
	}

	@Override
	public Thread getTimer() {
		return Timer;
	}
	
	public void setTimer(Thread timer) {
		Timer = timer;
	}

	@Override
	public void isSkillActivated() {
		if (Item.isActivated()) {
			ResourceLoader.itemSound.play();
			Game.currentItemTimer = this;
			Item.setActivated(false);
			GameSetting.gameSpeed = 2;
			System.out.println("SlowItem activated");
			int time = Item.ITEM_TIME;
			Game.timerLabel.showTimerPane();
			Timer = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Game.timerLabel.updateTimer();
						Thread.sleep(time);
						GameSetting.gameSpeed = 3;
						Game.currentItemTimer = null;
						Game.timerLabel.hideTimerPane();
					} catch (InterruptedException e) {
						GameSetting.gameSpeed = 3;
						Game.timerLabel.hideTimerPane();
					}
				}
			});
			Timer.start();
		}
		
	}

}
