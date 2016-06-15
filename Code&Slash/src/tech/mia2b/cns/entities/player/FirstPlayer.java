package tech.mia2b.cns.entities.player;

import java.util.ArrayList;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.entities.projectile.BasicAttack;
import tech.mia2b.cns.main.Main;
import tech.mia2b.cns.main.Util;
import tech.mia2b.cns.world.Camera;
import tech.mia2b.cns.world.Entities;
import tech.mia2b.cns.world.Input;

public class FirstPlayer extends Entity {
	private double x = 0;
	private double y = 0;
	private double xSpeed = 0;
	private double ySpeed = 0;
	private double speed = 0;
	private int direction = 0;
	private int maxSpeed = 300;
	private int acceleration = maxSpeed * 2;
	private int WIDTH = 30, HEIGHT = 30;
	private boolean player = true;
	private double cooldown = 0;
	private double freshCooldown = 0.075;
	private double lerp = 6;
	private double hp = 10000;

	private Image image = Images.getSprite(0);

	public FirstPlayer() {
		image = new Image("textures/earth.png", 32, 32, false, false);
	}
	public boolean isPlayer(){
		return player;
	}
	public void action(double deltaTime) {
		if(hp<=0){
			die();
		}
		if (Input.hasKey("W")) { // ^ y
			if (ySpeed > 0)
				ySpeed -= acceleration * deltaTime * 2;
			else
				ySpeed -= acceleration * deltaTime;
		} else if (Input.hasKey("S")) { // v y
			if (ySpeed < 0)
				ySpeed += acceleration * deltaTime * 2;
			else
				ySpeed += acceleration * deltaTime;
		} else {
			ySpeed = subToZero(ySpeed, acceleration * deltaTime * 2);
		}
		ySpeed = keepInBound(ySpeed, maxSpeed);

		if (Input.hasKey("A")) { // < x
			if (xSpeed > 0)
				xSpeed -= acceleration * deltaTime * 2;
			else
				xSpeed -= acceleration * deltaTime;
		} else if (Input.hasKey("D")) { // > x
			if (xSpeed < 0)
				xSpeed += acceleration * deltaTime * 2;
			else
				xSpeed += acceleration * deltaTime;
		} else {
			xSpeed = subToZero(xSpeed, acceleration * deltaTime * 2);
		}
		xSpeed = keepInBound(xSpeed, maxSpeed);

		direction = (int) Math.toDegrees(direction(xSpeed, ySpeed));

		speed = Math.sqrt(Math.pow(ySpeed, 2) + Math.pow(xSpeed, 2));

		if (speed > maxSpeed) {
			speed = maxSpeed;
		} else if (speed < 0) {
			speed = 0;
		}

		move(deltaTime, (int) speed, direction);
		Camera.setCameraX(Camera.getCameraX() + ((((x + WIDTH / 2) - Camera.getCameraX()) * lerp) * deltaTime));
		Camera.setCameraY(Camera.getCameraY() + ((((y + HEIGHT / 2) - Camera.getCameraY()) * lerp) * deltaTime));

		if (Input.isMousePressed() && cooldown <= 0) {
			Entities.addEntity(new BasicAttack(x + (WIDTH / 4), y + (HEIGHT / 4),
					Math.toDegrees(direction(
							Input.getMouseX()+Camera.getCameraX()-Camera.getBufferWidth()-x//-(WIDTH/2)
							,
							Input.getMouseY()+Camera.getCameraY()-Camera.getBufferHeight()-y//-(HEIGHT/2)
							))));
			cooldown = freshCooldown;
		} else {
			cooldown -= deltaTime;
		}
		
		// Camera.setCameraX(x);
		// Camera.setCameraY(y);
		Main.setpro(hp/10000);
	}

	private double keepInBound(double i, double j) {
		if (i < -j) {
			return -j;
		}
		if (i > j) {
			return j;
		}
		return i;
	}

	private double subToZero(double i, double j) {
		double out = 0;
		if (i == 0) {
			out = 0;
		} else if (i > 0) {
			out = i - j;
			if (out < 0) {
				out = 0;
			}
		} else {
			out = i + j;
			if (out > 0) {
				out = 0;
			}
		}
		return out;
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

	public Image getImage() {
		return image;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	private void move(double lastActionDelta, int speed, double direction) {
		double nextX = nextXPosition(lastActionDelta, speed, direction);
		double nextY = nextYPosition(lastActionDelta, speed, direction);

		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			Util.quickSort(entities, this);
			for (Entity i : (entities)) {
				if (!i.isCollidable()) {
					continue;
				}
				Rectangle hitBox = collisionBox(i);
				boolean none = true;
				while (hitBox.intersects(nextX, nextY, WIDTH, HEIGHT)) {
					none = true;
					if (hitBox.intersects(nextX, y, WIDTH, HEIGHT)) {
						nextX -= (Math.cos(Math.toRadians(direction))) / 2;
						xSpeed = subToZero(xSpeed, acceleration * lastActionDelta * 0.25);
						none = false;
					}
					if (hitBox.intersects(x, nextY, WIDTH, HEIGHT)) {
						nextY -= (Math.sin(Math.toRadians(direction))) / 2;
						ySpeed = subToZero(ySpeed, acceleration * lastActionDelta * 0.25);
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
	public void takeDamage(double damage) {
		hp -= damage;
	}
	private void die() {
		Entities.removeEntity(this);
	}
	
}