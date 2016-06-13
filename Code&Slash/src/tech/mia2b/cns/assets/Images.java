package tech.mia2b.cns.assets;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Images {
	private static boolean loaded = false;
	private static final String path = "textures/";
	private static ArrayList<Image> images = new ArrayList<Image>();
	
	
	public static void loadSprites(){
		if(!loaded){
			images.add(new Image(path + "earth.png",32,32,false,false));
			images.add(new Image(path + "space.png",64,64,false,false));
			images.add(new Image(path + "stars.png",32,32,false,false));
			images.add(new Image(path + "sun.png",16,16,false,false));
			loaded = true;
		}
	}
	public static Image getSprite(int index){
		return images.get(index);
	}
	
}
