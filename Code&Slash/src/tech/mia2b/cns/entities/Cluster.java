package tech.mia2b.cns.entities;

import tech.mia2b.cns.entities.enemies.BasicEnemy;
import tech.mia2b.cns.entities.enemies.Grass;
import tech.mia2b.cns.entities.enemies.Wall;
import tech.mia2b.cns.world.Entities;

public class Cluster {
	public static void wallCluster(int x, int y, int size, int wh) {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Entities.addEntity(new Wall(x+(wh*i), y+(wh*j)));
			}
		}
	}
	public static void grassCluster(int x, int y, int size, int wh){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Entities.addEntity(new Grass(x+(wh*i), y+(wh*j)));
			}
		}
	}
	public static void enemyCluster(int x, int y, int size, int wh){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(((int)(Math.random()*20))<= 5){
					Entities.addEntity(new BasicEnemy(8+x+(wh*i), 8+y+(wh*j)));
				}
			}
		}
	}
}
