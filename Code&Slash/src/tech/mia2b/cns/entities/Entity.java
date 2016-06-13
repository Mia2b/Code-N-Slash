package tech.mia2b.cns.entities;

import javafx.scene.image.Image;

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
}
