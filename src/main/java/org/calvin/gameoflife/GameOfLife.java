package org.calvin.gameoflife;

/**
 * Hello world!
 *
 */
public class GameOfLife 
{
    public static void main( String[] args )
    {
        WorldBuilder wb = new WorldBuilder(20, 20);
        wb.regenerateWorld();
        
        SimulateLife sl = new SimulateLife(wb.getWorld(), 2000,"");
        sl.startSimulation();
    }
}
