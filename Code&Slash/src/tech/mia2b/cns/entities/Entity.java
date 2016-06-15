package tech.mia2b.cns.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Entity implements Cloneable{
	
	public Entity(){
		
	}
	public Entity(int x, int y){
		
	}
	
	public void action(double deltaTime){
		
	}
	
	public Image getImage(){
		return null;
	}
	
	public double getX(){
		return 0;
	}
	
	public double getY(){
		return 0;
	}

	public boolean isCollidable() {
		return false;
	}

	public int getDistanceFrom(Entity ent) {
		return 0;
	}

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}
	public Rectangle collisionBox(Entity i) {
		return new Rectangle(i.getX(), i.getY(), i.getWidth() + 1, i.getHeight() + 1);

	}
	public boolean isAttackable() {
		return false;
	}
	public void takeDamage(double baseDamage) {
		
	}
}
