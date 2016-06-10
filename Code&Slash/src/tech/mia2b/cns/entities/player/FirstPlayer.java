package tech.mia2b.cns.entities.player;


import java.util.ArrayList;


import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
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
	private int maxSpeed = 1024;
	private int acceleration = 2048;
	private int WIDTH = 32, HEIGHT = 32;
	
	private Image image = new Image("textures/earth.png",32,32, false, false);;
	
	public FirstPlayer(){
		image = new Image("textures/earth.png",32,32, false, false);
	}
	
	public void action(double deltaTime){
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
		
		if(speed > maxSpeed){
			speed=maxSpeed;
		} else if(speed < 0){
			speed = 0;
		}
		move(deltaTime,(int) speed, direction);
		Camera.setCameraX(Camera.getCameraX() + ((((x+WIDTH/2) - Camera.getCameraX())*8)*deltaTime));
		Camera.setCameraY(Camera.getCameraY() + ((((y+HEIGHT/2) - Camera.getCameraY())*8)*deltaTime));
		
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
		
		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			quickSort(entities);
			for (Entity i : (entities)) {
				if (!i.isCollidable()){
					continue;
				}
					Rectangle hitBox = collisionBox(i);
					boolean none = true;
					while (hitBox.intersects(nextX, nextY, WIDTH, HEIGHT)) {
						none = true;
						if (hitBox.intersects(nextX, y, WIDTH, HEIGHT)) {
							nextX -= (Math.cos(Math.toRadians(direction)))/2;
							xSpeed = subToZero(xSpeed,acceleration * lastActionDelta * 0.25);
							none = false;
						}
						if (hitBox.intersects(x, nextY, WIDTH, HEIGHT)) {
							nextY -= (Math.sin(Math.toRadians(direction)))/2;
							ySpeed = subToZero(ySpeed,acceleration * lastActionDelta * 0.25);
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
	
	void quickSort(ArrayList<Entity> out) {
		mainQuickSort(out, 0, out.size() - 1);
	}

	void mainQuickSort(ArrayList<Entity> out, int left, int right) {
		int index = quickSortPartition(out, left, right);
		if (left < (index - 1))
			mainQuickSort(out, left, index - 1);
		if (right > index)
			mainQuickSort(out, index, right);
	}

	int quickSortPartition(ArrayList<Entity> out, int left, int right) {
		int center = out.get((left + right) / 2).getDistanceFrom(this);
		while (left <= right) {
			while (out.get(left).getDistanceFrom(this) < center) {
				left++;
			}
			while (out.get(right).getDistanceFrom(this) > center) {
				right--;
			}
			if (left <= right) {
				Entity temp = out.get(left);
				out.set(left, out.get(right));
				out.set(right, temp);
				left++;
				right--;
			}
		}
		return left;
	}
	
	private Rectangle collisionBox(Entity i) {
		return new Rectangle(i.getX(), i.getY(), i.getWidth() + 1, i.getHeight() + 1);

	}

}
