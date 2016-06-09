package tech.mia2b.cns.entities.player;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Camera;
import tech.mia2b.cns.world.Input;

public class FirstPlayer extends Entity {
	private double x = 0;
	private double y = 0;
	private double xSpeed = 0;
	private double ySpeed = 0;
	private double speed = 0;
	private int direction = 0;
	private int maxSpeed = 1048;
	private int acceleration = 5096;
	
	private Image image = new Image("textures/space.png",50,50, false, false);;
	
	public FirstPlayer(){
		image = new Image("textures/space.png",50,50, false, false);
	}
	
	public void action(double deltaTime){
		Camera.setCameraX(x);
		Camera.setCameraY(y);
		if (Input.hasKey("W")) {				//^ y
			if(ySpeed > 0)
				ySpeed -= acceleration * deltaTime * 2;
			else
				ySpeed -= acceleration * deltaTime;
		} else 	if (Input.hasKey("S")) {				//v y
			if(ySpeed < 0)
				ySpeed += acceleration * deltaTime * 2;
			else
				ySpeed += acceleration * deltaTime;
		} else {
			ySpeed = subToZero(ySpeed,acceleration * deltaTime * 2);
		}
		ySpeed = keepInBound(ySpeed,maxSpeed);
		
		
		if (Input.hasKey("A")) {	//< x 
			if(xSpeed > 0)
				xSpeed -= acceleration * deltaTime * 2;
			else
				xSpeed -= acceleration * deltaTime;
		} else 	if (Input.hasKey("D")) {				//> x
			if(xSpeed < 0)
				xSpeed += acceleration * deltaTime * 2;
			else
				xSpeed += acceleration * deltaTime;
		} else {
			xSpeed = subToZero(xSpeed,acceleration * deltaTime * 2);
		}
		xSpeed = keepInBound(xSpeed,maxSpeed);
		
		
		
		direction = (int) Math.toDegrees(direction(xSpeed,ySpeed));
		
		speed = Math.sqrt(Math.pow(ySpeed, 2) + Math.pow(xSpeed, 2));
		
		System.out.println(direction + "|" + speed);
		if(speed > maxSpeed){
			speed=maxSpeed;
		} else if(speed < 0){
			speed = 0;
		}
	
		move(deltaTime,(int) speed, direction);
	}
	private double keepInBound(double i, double j){
		if(i < -j){
			return -j;
		}
		if(i >  j){
			return j;
		}
		return i;
	}
	private double subToZero(double i, double j){
		double out = 0;
		if(i == 0){
			out = 0;
		} else if(i > 0){
			out = i-j;
			if(out < 0){
				out = 0;
			}
		} else {
			out = i+j;
			if(out > 0){
				out = 0;
			}
		}
		return out;
	}
	
	private double  direction(double x, double y) {
	    if (x > 0)
	        return Math.atan(y/x);
	    if (x < 0)
	        return Math.atan(y/x)+Math.PI;
	    if (y > 0)
	        return Math.PI/2;
	    if (y < 0)
	        return -Math.PI/2;
	    return 0; // no direction
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
