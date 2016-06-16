package tech.mia2b.cns.entities.player;

import java.util.ArrayList;

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
	private int acceleration = maxSpeed * 3;
	private int WIDTH = 32, HEIGHT = 32;
	private boolean player = true;
	private double cooldown = 0;
	private double freshCooldown = 0.1;
	private double passiveCooldown = 1;
	private double frashPassiveCooldown = 3;
	private double lerp = 8;
	private double hp = 10000;
	private double mouseDirection = 0;

	private Image image = Images.getSprite(0);

	public FirstPlayer(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	public boolean isPlayer(){
		return player;
	}
	public void action(double deltaTime) {
		//System.out.println(x + " | " + y + " | "+ direction);
		if(hp<=0){
			System.out.println(this+ " died");
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
			mouseDirection = Math.toDegrees(direction(
					Input.getMouseX()+Camera.getCameraX()-Camera.getBufferWidth()-x//-(WIDTH/2)
					,
					Input.getMouseY()+Camera.getCameraY()-Camera.getBufferHeight()-y//-(HEIGHT/2)
					));
			Entities.addEntity(new BasicAttack(x + (WIDTH / 4), y + (HEIGHT / 4),mouseDirection));
			Entities.addEntity(new BasicAttack(x + (WIDTH / 4), y + (HEIGHT / 4),mouseDirection+8));
			Entities.addEntity(new BasicAttack(x + (WIDTH / 4), y + (HEIGHT / 4),mouseDirection-8));
			cooldown = freshCooldown;
		} else {
			cooldown -= deltaTime;
		}
		passiveCooldown-=deltaTime;
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
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
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
						nextX -= (Math.cos(Math.toRadians(direction))) ;
						xSpeed = subToZero(xSpeed, acceleration * lastActionDelta * 0.25);
						none = false;
					}
					if (hitBox.intersects(x, nextY, WIDTH, HEIGHT)) {
						nextY -= (Math.sin(Math.toRadians(direction))) ;
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
		if(passiveCooldown<=0){
			for(int i = 0;i < 360;i+=5){
			Entities.addEntity(new BasicAttack(x,y,i));
			passiveCooldown= frashPassiveCooldown;
			}
		}else{
		hp -= damage;
		}
	}
	private void die() {
		Main.showReset();
		Entities.removeEntity(this);
	}
	
	public int getDistanceFrom(Entity ent){
		int deltaX = (int) (this.x - ent.getX());
		int deltaY = (int) (this.y - ent.getY());
		return (int)(Math.sqrt((deltaX * deltaX) + (deltaY * deltaY)));
	}
	
}