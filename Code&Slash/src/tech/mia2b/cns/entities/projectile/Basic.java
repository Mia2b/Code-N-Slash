package tech.mia2b.cns.entities.projectile;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Entities;

public class Basic extends Entity {
	private Image image = new Image("textures/stars.jpg",16,16,false,false);
	private double life = 2;
	private double x = 0;
	private double y = 0;
	private double direction = 0;
	private double speed = 500;
	
	
	public Basic (double x, double y, double direction ){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void action(double deltaTime){
		if(life <= 0){
			Entities.removeEntity(this);
		}
		life -= 1*deltaTime;
		x += (Math.cos(Math.toRadians(direction)) * speed * deltaTime);
		y += (Math.sin(Math.toRadians(direction)) * speed * deltaTime);
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

	public boolean isCollidable() {
		return false;
	}

	public int getWidth() {
		return 16;
	}

	public int getHeight() {
		return 16;
	}
	
	public int getDistanceFrom(Entity ent){
		int deltaX = (int) (this.x - ent.getX());
		int deltaY = (int) (this.y - ent.getY());
		return (int)(Math.sqrt(deltaX * deltaX) + (deltaY * deltaY));
	}
}
