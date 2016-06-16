package tech.mia2b.cns.entities.projectile;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import tech.mia2b.cns.assets.Images;
import tech.mia2b.cns.entities.Entity;
import tech.mia2b.cns.world.Camera;
import tech.mia2b.cns.world.Entities;

public class EnemyAttack extends Entity {
	private Image image = Images.getSprite(6);
	private double life = 5;
	private double x = 0;
	private double y = 0;
	private double direction = 0;
	private double speed = 300;
	private double baseDamage = 300;
	
	
	public EnemyAttack (double x, double y, double direction ){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void action(double deltaTime){
		if(life <= 0){
			Entities.removeEntity(this);
		}
		life -= 1*deltaTime;
		x += (Math.cos(Math.toRadians(direction)) * speed * deltaTime);
		y += (Math.sin(Math.toRadians(direction)) * speed * deltaTime);
		
		ArrayList<Entity> entities = new ArrayList<Entity>(Camera.getVisibleEntities());
		if (!entities.isEmpty()) {
			for (Entity i : (entities)) {
				if (i.isPlayer() || i.isCollidable()){
					Rectangle hitBox = collisionBox(i);
					if (hitBox.intersects(x, y, 16, 16)) {
						attack(i);	
					}	
				}else{
					continue;
				}
					
				}
			}
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

	public boolean isCollidable() {
		return false;
	}

	public int getWidth() {
		return 16;
	}

	public int getHeight() {
		return 16;
	}
	
	public int getDistanceFrom(Entity ent){
		int deltaX = (int) (this.x - ent.getX());
		int deltaY = (int) (this.y - ent.getY());
		return (int)(Math.sqrt(deltaX * deltaX) + (deltaY * deltaY));
	}
	private void attack(Entity ent){
		ent.takeDamage(baseDamage);
		Entities.removeEntity(this);
	}
}

