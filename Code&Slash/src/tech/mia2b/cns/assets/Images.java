package tech.mia2b.cns.assets;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Images {
	private static boolean loaded = false;
	private static final String path = "textures/";
	private static ArrayList<Image> images = new ArrayList<Image>();
	
	
	public static void loadSprites(){
		if(!loaded){
			images.add(new Image(path + "earth.png",32,32,false,false));//0
			images.add(new Image(path + "wall.png",64,64,false,false));//1
			images.add(new Image(path + "stars.png",32,32,false,false));//2
			images.add(new Image(path + "mysun.png",16,16,false,false));//3
			images.add(new Image(path + "grass.png",64,64,false,false));//4
			images.add(new Image(path + "baddie.png",48,48,false,false));//5
			images.add(new Image(path + "slimeball.png",16,16,false,false));//6
			loaded = true;
		}
	}
	public static Image getSprite(int index){
		return images.get(index);
	}
	
}

