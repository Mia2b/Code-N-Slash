package tech.mia2b.cns.entities.enemies;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;

public class FirstEnemy extends Entity {
	private Image image = new Image("textures/space.png",50,50, false, false);
	private double y = 0;
	private double x = 0;
	private boolean collidable = true;
	public Image getImage(){
		return image;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	public boolean isCollidable(){
		return collidable;
	}
}
