package tech.mia2b.cns.world;

import java.util.ArrayList;

import tech.mia2b.cns.entities.Entity;

public class Entities {
	private static volatile ArrayList<Entity> entities = new ArrayList<Entity>();

	public static ArrayList<Entity> getEntities (){
		return entities;
	}
	
	public static void addEntity(Entity entity){
		entities.add(entity);
	}
}
