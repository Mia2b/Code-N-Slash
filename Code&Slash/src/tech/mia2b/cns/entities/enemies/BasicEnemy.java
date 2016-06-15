
package tech.mia2b.cns.entities.enemies;

import javafx.scene.image.Image;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Entities;

public class BasicEnemy extends Entity {
	private Image image = Images.getSprite(5);
	private double y = 0;
	private double x = 0;
	private boolean collidable = false;
	private int width = 48, height = 48;
	private int hp = 1000;

	public BasicEnemy(double x, double y) {
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

	public void action(double deltaTime){
		if(hp<0){
			die();
		}
	}
	public boolean isAttackable() {
		return true;
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

	public int getDistanceFrom(Entity ent) {
		int deltaX = (int) (this.x - ent.getX());
		int deltaY = (int) (this.y - ent.getY());
		return (int) (Math.sqrt(deltaX * deltaX) + (deltaY * deltaY));
	}

	private void die() {
		Entities.removeEntity(this);
	}

	public void takeDamage(double damage) {
		hp -= damage;
	}
}
