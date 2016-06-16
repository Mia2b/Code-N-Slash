package tech.mia2b.cns.world.gen;

import tech.mia2b.cns.entities.Cluster;
import tech.mia2b.cns.entities.enemies.Wall;
import tech.mia2b.cns.world.Entities;;
public class MazeCreator {
	public static void createMaze(int size, int clusterSize){
		int wh = 64;
		int blockSize = wh*clusterSize;
		int [][] mapArray = new MazeGenerator(size).getIntMaze();
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				if(mapArray[i][j] == 1){
					Cluster.wallCluster((int)((i*blockSize)-(size*blockSize/2)-(blockSize/1.5)),(int)((j*blockSize)-(size*blockSize/2)-(blockSize/1.5)), clusterSize,wh);
					//Entities.addEntity(new Wall(i*128,j*128));
				}else{
					Cluster.grassCluster((int)((i*blockSize)-(size*blockSize/2)-(blockSize/1.5)),(int)((j*blockSize)-(size*blockSize/2)-(blockSize/1.5)), clusterSize,wh);
				}
				
					
				
			}
		}
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				if(mapArray[i][j] != 1){
					Cluster.enemyCluster((int)((i*blockSize)-(size*blockSize/2)-(blockSize/1.5)),(int)((j*blockSize)-(size*blockSize/2)-(blockSize/1.5)), clusterSize,wh);
				}
			}
		}
	}
}
