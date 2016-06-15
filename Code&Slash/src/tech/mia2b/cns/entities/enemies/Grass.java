package tech.mia2b.cns.entities.enemies;

import javafx.scene.image.Image;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Entities;

public class Grass extends Entity {
	private Image image = Images.getSprite(4);
	private double y = 0;
	private double x = 0;
	private int width = 64, height = 64;

	public Grass(double x, double y) {
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
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
