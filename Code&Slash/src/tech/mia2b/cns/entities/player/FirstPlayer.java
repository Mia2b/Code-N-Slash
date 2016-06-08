package tech.mia2b.cns.entities.player;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Input;

public class FirstPlayer extends Entity {
	private double x = 64;
	private double y = 64;
	private double speed = 0;
	
	private int direction = 0;
	private int maxSpeed = 2048;
	private int acceleration = 1048;
	
	private Image image;
	
	public FirstPlayer(){
		image = new Image("earth.png",50,50, false, false);
	}
	
	public void action(double deltaTime){
		if (Input.hasKey("W") && Input.hasKey("A")) {
			direction = 225;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("A") && Input.hasKey("S")) {
			direction = 135;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("S") && Input.hasKey("D")) {
			direction = 45;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("D") && Input.hasKey("W")) {
			direction = 315;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("W")) {
			direction = 270;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("A")) {
			direction = 180;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("S")) {
			direction = 90;
			speed += acceleration * 1 * deltaTime;
			
		} else if (Input.hasKey("D")) {
			direction = 0; 
			speed += acceleration * 1.5 * deltaTime;
			
		}else {
			speed -= acceleration * 2 * deltaTime;
		}
		
		if(speed > maxSpeed){
			speed=maxSpeed;
		} else if(speed < 0){
			speed = 0;
		}
		System.out.println(x + "|"+ y);
		move(deltaTime,(int) speed, direction);
	}
	
	public Image getImage(){
		return image;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	private void move(double lastActionDelta, int speed, double direction) {
		double nextX = nextXPosition(lastActionDelta, speed, direction);
		double nextY = nextYPosition(lastActionDelta, speed, direction);
		
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
