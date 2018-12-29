package edu.ncsu.csc216.checkout_simulator.simulation;

import edu.ncsu.csc216.checkout_simulator.items.Cart;

/**
 * The log class handles the log, which is the info the
 * carts give of their process and wait time after they have
 * been processed at a register
 * 
 * @author Islahuddin Arshad
 *
 */
public class Log {
	
	/**The number of carts that have completed their log*/
	private int numCompleted;
	/**The total wait time of the cart that is being logged*/
	private int totalWaitTime;
	/**The total process time of the cart that is being logged*/
	private int totalProcessTime;
	
	
	/**
	 * The number of carts that have logged
	 * @return in the number of carts logged
	 */
	public int getNumCompleted() {
		if (numCompleted == 0) {
			return 0;
		}
		
		return numCompleted;
	}
	/**
	 * The cart is logged here with the 
	 * process and wait time that have incremented
	 * throughout the simulation
	 * @param cart the cart that is being logged
	 */
	public void logCart(Cart cart) {
		
		if(cart == null) { 
			return;
		}
		
		totalWaitTime += cart.getWaitTime();
		totalProcessTime += cart.getProcessTime();
		numCompleted++;
		
	}
    /**
     * The average wait per cart put into the log else if there 
     * are not carts, 0 is returned
     * @return double the average wait time
     */
	public double averageWaitTime() {
		
		if(numCompleted == 0) {
			return 0;
		}
		return totalWaitTime * 1.0 / numCompleted;
	}
	/**
     * The process wait per cart put into the log else if there 
     * are not carts, 0 is returned
     * @return double the average process time
     */
	public double averageProcessTime() { 
		if(numCompleted == 0) {
			return 0; 
		}
		return totalProcessTime * 1.0 / numCompleted; 
	}
}
