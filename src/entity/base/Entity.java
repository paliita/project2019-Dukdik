package entity.base;

public abstract class Entity {

	public Entity() {
		setGraphic();
		setPosition();
	}

	public abstract void setPosition();

	public abstract void move();

	public abstract void setGraphic();

}
