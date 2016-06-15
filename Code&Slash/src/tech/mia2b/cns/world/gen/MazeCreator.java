package tech.mia2b.cns.world.gen;

import tech.mia2b.cns.entities.Cluster;
import tech.mia2b.cns.entities.enemies.Wall;
import tech.mia2b.cns.world.Entities;;
public class MazeCreator {
	public static void createMaze(int size, int clusterSize){
		int wh = 64;
		int [][] mapArray = new MazeGenerator(size).getIntMaze();
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				if(mapArray[i][j] == 1){
					Cluster.wallCluster(i*(wh*clusterSize)-(size/2 * (wh*clusterSize)),j*(wh*clusterSize)-(size/2 * (wh*clusterSize)), clusterSize,wh);
					//Entities.addEntity(new Wall(i*128,j*128));
				}else{
					Cluster.grassCluster(i*(wh*clusterSize)-(size/2 * (wh*clusterSize)),j*(wh*clusterSize)-(size/2 * (wh*clusterSize)), clusterSize,wh);
				}
				
					
				
			}
		}
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				if(mapArray[i][j] != 1){
					Cluster.enemyCluster(i*(wh*clusterSize)-(size/2 * (wh*clusterSize)),j*(wh*clusterSize)-(size/2 * (wh*clusterSize)), clusterSize,wh);
				}
			}
		}
	}
}
