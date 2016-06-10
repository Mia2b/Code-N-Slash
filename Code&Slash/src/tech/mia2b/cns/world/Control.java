package tech.mia2b.cns.world;


import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import tech.mia2b.cns.entities.Entity;

public class Control {
	private static GraphicsContext gc;
	private static int ViewWidth = 1920;
	private static int ViewHeight = 1080;
   
	public static void update(double deltaTime){
		for(Entity entity: new ArrayList<Entity>(Entities.getEntities())){
			entity.action(deltaTime);
		}
	}
	
	public static void render(){
		//gc.clearRect(0, 0, getViewWidth(),getViewHeight());
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
