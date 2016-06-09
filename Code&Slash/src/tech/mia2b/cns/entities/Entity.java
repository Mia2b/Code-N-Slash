package tech.mia2b.cns.entities;

import javafx.scene.image.Image;

public class Entity implements Cloneable{
	
	public Entity(){
		
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
		// TODO Auto-generated method stub
		return 0;
	}
}
