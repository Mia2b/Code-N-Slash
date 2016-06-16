
package tech.mia2b.cns.entities.enemies;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.entities.projectile.EnemyAttack;
import tech.mia2b.cns.main.Util;
import tech.mia2b.cns.world.Camera;
import tech.mia2b.cns.world.Entities;

public class BasicEnemy extends Entity {
	private Image image = Images.getSprite(5);
	private double y = 0;
	private double x = 0;
	private int direction = 45;
	//private double cooldown = 0;
	//private double freshCooldown = 1.0;
	private boolean collidable = false;
	private int WIDTH = 48, HEIGHT = 48;
	private int hp = 1000;
	private int speed = 164;
	private boolean shouldMove = false;
	private boolean attacker = false;

	public BasicEnemy(double x, double y) {
		this.x = x;
		this.y = y;
		speed = (int) (Math.random()*450);
		
		if (speed < 150){
			attacker = true;
		}

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
		shouldMove = false;
		if (hp < 0) {
			die();
		}
		
		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			for (Entity i : (entities)) {
				if (!i.isPlayer() || !(i.getDistanceFrom(this) < 400)) {
					continue;
				}
				direction =  (int) Math.toDegrees(direction(i.getX()-x,i.getY()-y));
				shouldMove = true;
				Rectangle hitBox = collisionBox(i);
				if (hitBox.intersects(x, y, WIDTH, HEIGHT)) {
					attack(i,deltaTime);
				}
				if(attacker && (Math.random()*50) < 3){
					Entities.addEntity(new EnemyAttack(x+24,y+24,direction));
				}
				
			}
		}
		if(shouldMove){
			move(deltaTime,speed,direction);
		}
		

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

	
	private void die() {
		Entities.removeEntity(this);
	}

	public void takeDamage(double damage) {
		hp -= damage;
	}

	private void attack(Entity player,double deltaTime) {
		player.takeDamage(500*deltaTime);
	}

	private void move(double lastActionDelta, int speed, double direction) {
		double nextX = nextXPosition(lastActionDelta, speed, direction);
		double nextY = nextYPosition(lastActionDelta, speed, direction);

		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			Util.quickSort(entities, this);
			for (Entity i : (entities)) {
				if (!i.isCollidable() || i.equals(this)) {
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
	
	private double direction(double x, double y) {
		if (x > 0)
			return Math.atan(y / x);
		if (x < 0)
			return Math.atan(y / x) + Math.PI;
		if (y > 0)
			return Math.PI / 2;
		if (y < 0)
			return -Math.PI / 2;
		return 0; // no direction
	}
}
