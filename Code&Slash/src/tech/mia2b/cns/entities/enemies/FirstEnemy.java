package tech.mia2b.cns.entities.enemies;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;

public class FirstEnemy extends Entity {
	private Image image = new Image("textures/space.png", 50, 50, false, false);
	private double y = 0;
	private double x = 0;
	private boolean collidable = true;
	private int width = 50, height = 50;

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
}
