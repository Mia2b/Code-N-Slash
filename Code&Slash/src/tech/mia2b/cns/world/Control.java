package tech.mia2b.cns.world;


import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tech.mia2b.cns.entities.Entity;

public class Control {
	private static GraphicsContext gc;
	private static int ViewWidth = 1920;
	private static int ViewHeight = 1080;
	static Image image = new Image("textures/Ground.png");
   
	public static void update(double deltaTime){
		Camera.setVisibleEntities();
		for(Entity entity: new ArrayList<Entity>(Camera.getVisibleEntities())){
			entity.action(deltaTime);
		}
	}
	
	public static void render(){
		//gc.drawImage(image, 0, 0);
		for(Entity entity: new ArrayList<Entity>(Camera.getVisibleEntities())){
			gc.drawImage(entity.getImage(), entity.getX()-Camera.getCameraX()+ Camera.getBufferWidth(), entity.getY()-Camera.getCameraY()+ Camera.getBufferHeight());
		}
		
	}
	
	public static void setGraphicsContext(GraphicsContext graphicsContext){
		gc = graphicsContext;
	}
	
	public static void setViewWidth(int width){
		ViewWidth = width;
	}
	
	public static void setViewHeight(int height){
		ViewHeight = height;
	}
	
	public static int getViewWidth(){
		return ViewWidth;
	}
	
	public static int getViewHeight(){
		return ViewHeight;
	}
}
