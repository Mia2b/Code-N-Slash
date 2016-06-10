package tech.mia2b.cns.world.gen;

import tech.mia2b.cns.entities.enemies.FirstEnemy;
import tech.mia2b.cns.world.Entities;;
public class MazeCreator {
	public static void createMaze(int size){
		int [][] mapArray = new MazeGenerator(size).getIntMaze();
		int midY = 32;//((mapArray.length/2)*32);
		int midX = 0;//((mapArray[0].length/2)*32);
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				if(mapArray[i][j] == 1){
					Entities.addEntity(new FirstEnemy(i*50,j*50));
				}
				if(mapArray[i][j] == 2){
					
				}
			}
		}
	}
}
