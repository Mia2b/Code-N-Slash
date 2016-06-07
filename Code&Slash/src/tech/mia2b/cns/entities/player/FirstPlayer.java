package tech.mia2b.cns.entities.player;

import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;

public class FirstPlayer extends Entity {
	private double x = 0;
	private double y = 0;
	private Image image = new Image("textures/earth.png",50,50, false, false);
	
	public FirstPlayer(){
		
	}
	
	public void action(double deltaTime){
		
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

}
