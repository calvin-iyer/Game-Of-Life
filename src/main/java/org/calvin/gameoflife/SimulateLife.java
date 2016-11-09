package org.calvin.gameoflife;

public class SimulateLife extends Thread{
	
	private int[][] world;
	private int delay;
	private Thread lifeThread;
	private boolean runSimulation;
	private String deadMask;
	
	public SimulateLife(int[][] world, int delay, String deadMask) {
		this.world = world;
		this.delay = delay;
		runSimulation = false;
		this.deadMask = deadMask;
	}
	
	public void startSimulation() {
		runSimulation = true;
		lifeThread = new Thread(this);
		lifeThread.start();
	}
	
	public void stopSimulation() {
		runSimulation = false;
	}

	@Override
	public void run() {
		try {
			while(runSimulation) {
				checkRules();
				printWorld();
				checkWorldDeath();
				Thread.sleep(delay);
			}
		}catch(Exception e) {
			System.out.println("Something went wrong :(");
			e.printStackTrace();
		}
	}
	
	private void checkRules() {
		int n1,n2,n3,n4,n5,n6,n7,n8;
		n1 = n2 = n3 = n4 = n5 = n6 = n7 = n8 = -1;
		int rows = world.length;
		int cols = world[0].length;
		int sum = 0;
		for(int i =0;i<rows;i++) {
			for(int j = 0;j<cols;j++) {
				sum = 0;
				n1 = ((i-1) > -1) && ((j-1) > -1)?world[i-1][j-1]:-1;
				n2 = ((i-1) > -1) ? world[i-1][j]:-1;
				n3 = (((i-1) > -1)) && ((j+1) < cols) ? world[i-1][j+1] : -1;
				n4 = ((j+1) < cols) ? world[i][j+1] : -1;
				n5 = ((i+1) < rows) && ((j+1) < cols) ? world[i+1][j+1] : -1;
				n6 = ((i+1) < rows) ? world[i+1][j] : -1;
				n7 = ((i+1) < rows) && ((j-1) > -1) ? world[i+1][j-1] : -1;
				n8 = ((j-1) > -1) ? world[i][j-1] : -1;
				sum = n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8;
				if(sum < 2 || sum > 3) {
					world[i][j] = 0;
				} else if(sum == 3) {
					world[i][j] = 1;
				}
			}
		}
	}
	
	private void printWorld() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("===============================================================================");
		System.out.println("New World!");
		for(int i = 0;i<world.length;i++) {
			for(int j = 0;j<world[0].length;j++) {
				if(world[i][j] == 1) {
					System.out.print(world[i][j]+" ");
				} else {
					System.out.print(deadMask + " ");
				}
			}
			System.out.println();
		}
		System.out.println("===============================================================================");
	}
	
	private void checkWorldDeath() {
		boolean isDead = true;
		for(int i =0;i<world.length;i++) {
			for(int j = 0;j<world[0].length;j++) {
				if(world[i][j] == 1) {
					isDead = false;
					break;
				}
			}
		}
		runSimulation = !isDead;
		if(isDead) {
			System.out.println("The World has DIED!");
		}
 	}
}
