
package tech.mia2b.cns.entities.enemies;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.main.Util;
import tech.mia2b.cns.world.Camera;
import tech.mia2b.cns.world.Entities;

public class BasicEnemy extends Entity {
	private Image image = Images.getSprite(5);
	private double y = 0;
	private double x = 0;
	private int direction = 45;
	private double cooldown = 0;
	private double freshCooldown = 1.0;
	private boolean collidable = false;
	private int WIDTH = 48, HEIGHT = 48;
	private int hp = 1000;
	private int speed = 100;

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

	public void action(double deltaTime) {
		
		if (hp < 0) {
			die();
		}
		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			for (Entity i : (entities)) {
				if (!i.isPlayer()){
					continue;
				}
					Rectangle hitBox = collisionBox(i);
					if (hitBox.intersects(x, y, WIDTH, HEIGHT)) {
						attack(i);	
					}	
				}
			}
		//move(deltaTime,speed,direction);
	}
	
		
		
	public boolean isAttackable() {
		return true;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
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
	private void attack(Entity player){
		player.takeDamage(100);
	}

	private void move(double lastActionDelta, int speed, double direction) {
		double nextX = nextXPosition(lastActionDelta, speed, direction);
		double nextY = nextYPosition(lastActionDelta, speed, direction);

		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			Util.quickSort(entities, this);
			for (Entity i : (entities)) {
				if (!i.isCollidable()|| i.equals(this)) {
					continue;
				}
				Rectangle hitBox = collisionBox(i);
				boolean none = true;
				while (hitBox.intersects(nextX, nextY, WIDTH, HEIGHT)) {
					none = true;
					if (hitBox.intersects(nextX, y, WIDTH, HEIGHT)) {
						nextX -= (Math.cos(Math.toRadians(direction))) / 2;
						none = false;
					}
					if (hitBox.intersects(x, nextY, WIDTH, HEIGHT)) {
						nextY -= (Math.sin(Math.toRadians(direction))) / 2;
						none = false;
					}
					if (none) {
						nextY = y;
						nextX = x;
					}

				}
			}
		}
		this.x = nextX;
		this.y = nextY;
	}

	private double nextXPosition(double lastActionDelta, int xSpeed, double direction) {
		return this.x + (Math.cos(Math.toRadians(direction)) * xSpeed * lastActionDelta);
	}

	private double nextYPosition(double lastActionDelta, int ySpeed, double direction) {
		return this.y + (Math.sin(Math.toRadians(direction)) * ySpeed * lastActionDelta);
	}
}
