package org.calvin.gameoflife;

import java.util.Random;

public class WorldBuilder {
	
	private int rows;
	private int cols;
	private int[][] world;
	
	public WorldBuilder(int rows,int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int[][] getWorld() {
		return world;
	}

	public void setWorld(int[][] world) {
		this.world = world;
	}
	
	public void regenerateWorld() {
		Random random = new Random();
		world = new int[rows][cols];
		int randomSpan = random.nextInt();
		randomSpan = randomSpan < 0 ? randomSpan*-1:randomSpan;
		int threshold = random.nextInt(randomSpan);
		boolean randNum;
		for(int i =0;i<rows;i++) {
			for(int j = 0;j<cols;j++) {
				randNum = random.nextBoolean();
				if(randNum) {
					world[i][j] = 1;
				} else {
					world[i][j] = 0;
				}
			}
		}
	}

}
