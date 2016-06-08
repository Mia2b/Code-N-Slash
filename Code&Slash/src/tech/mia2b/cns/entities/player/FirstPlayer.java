package tech.mia2b.cns.entities.player;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Input;

public class FirstPlayer extends Entity {
	private double x = 0;
	private double y = 0;
	private double xSpeed = 0;
	private double ySpeed = 0;
	private double speed = 0;
	private int direction = 0;
	private int maxSpeed = 2048;
	private int acceleration = 1048;
	
	private Image image = new Image("textures/space.png",50,50, false, false);;
	
	public FirstPlayer(){
		image = new Image("textures/space.png",50,50, false, false);
	}
	
	public void action(double deltaTime){
		if (Input.hasKey("W") && Input.hasKey("A")) {	//<^ xy
			xSpeed -= acceleration * 0.707 * deltaTime;
			ySpeed -= acceleration * 0.707 * deltaTime;
			
		} else if (Input.hasKey("A") && Input.hasKey("S")) {	//<v xy
			xSpeed -= acceleration * 0.707 * deltaTime;
			ySpeed += acceleration * 0.707 * deltaTime;
			
		} else if (Input.hasKey("S") && Input.hasKey("D")) {	//v> xy
			xSpeed += acceleration * 0.707 * deltaTime;
			ySpeed += acceleration * 0.707 * deltaTime;
			
		} else if (Input.hasKey("D") && Input.hasKey("W")) {	//^> xy
			xSpeed += acceleration * 0.707 * deltaTime;
			ySpeed -= acceleration * 0.707 * deltaTime;
		} else	{
			
			if (Input.hasKey("W")) {				//^ y
				ySpeed -= acceleration * 1 * deltaTime;
				if(xSpeed < 0){
					xSpeed += acceleration * 3 * deltaTime;
				}else{
					xSpeed -= acceleration * 3 * deltaTime;
				}
			} else if (Input.hasKey("S")) {				//v y
				
				ySpeed += acceleration * 1 * deltaTime;
				if(xSpeed < 0){
					xSpeed += acceleration * 3 * deltaTime;
				}else{
					xSpeed -= acceleration * 3 * deltaTime;
				}
			} else if (Input.hasKey("A")) {				//< x 
				xSpeed -= acceleration * 1 * deltaTime;
				if(ySpeed < 0){
					ySpeed += acceleration * 3 * deltaTime;
				}else{
					ySpeed -= acceleration * 3 * deltaTime;
				}
			} else if (Input.hasKey("D")) {				//> x
				
				xSpeed += acceleration * 1 * deltaTime;
				
				if(ySpeed < 0){
					ySpeed += acceleration * 3 * deltaTime;
				}else{
					ySpeed -= acceleration * 3 * deltaTime;
				}
					
			}
			
			if(speed > maxSpeed){
				speed=maxSpeed;
			} else if(speed < 0){
				speed = 0;
			}
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
