package tech.mia2b.cns.entities.enemies;

import javafx.scene.image.Image;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;

public class FirstEnemy extends Entity {
	private Image image = Images.getSprite(1);
	private double y = 0;
	private double x = 0;
	private boolean collidable = true;
	private int width = 64, height = 64;

	public FirstEnemy(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public Image getImage() {
		return image;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getDistanceFrom(Entity ent){
		int deltaX = (int) (this.x - ent.getX());
		int deltaY = (int) (this.y - ent.getY());
		return (int)(Math.sqrt(deltaX * deltaX) + (deltaY * deltaY));
	}
}
