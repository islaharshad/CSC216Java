package edu.ncsu.csc216.checkout_simulator.queues;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * The checkout register handles the log and the
 * register that needs to have and how each cart is processed
 * based on its time in a line
 * @author Islahuddin Arshad
 *
 */
public class CheckoutRegister implements LineOfItems {

	/**
	 * The time it requires for a register to process all of its
	 * carts
	 */
	private int timeWhenAvailable;
	/**
	 * The log is used to log info of the cart as it is
	 * processed out
	 */
	private Log log;
	/**
	 * The line represents the specific line
	 * of carts that await at the register
	 */
	private ShoppingCartQueue line;
	/**
	 * Initialized log and constructs a
	 * new line and sets the timeWhenAvailable to
	 * zero
	 * @param log the log of the register
	 */
	public CheckoutRegister(Log log) {
		this.log = log;
		this.line = new ShoppingCartQueue();
		timeWhenAvailable = 0;
	}
	/**
	 * Returns the number carts still in line.
	 * @return int the size of carts still in line
	 */
	public int size() {
		 int cartsLeft = line.size();
		 return cartsLeft;
	}
	
	/**
	 * Removes the front cart from the line, logging its information in the process. 
	 * The removed cart is returned. 
	 * @return Cart the cart be processed next
	 */
	public Cart processNext() {
		Cart someCart = line.remove();
		someCart.removeFromWaitingLine();
		log.logCart(someCart);
		return someCart;
	}
	
	/**
	 * Returns true if the line is not empty. 
	 * @return boolean whether the line is empty or not
	 */
	public boolean hasNext() {
		 if (line.isEmpty()) {
			 return false;
		 }
		 return true;
	}
	
	/**
	 * Tells when the cart at the front of the line (currently being checked out)
	 * will finish and leave the simulation.
	 * If the line is empty, it returns Integer.MAX_VALUE.
	 * @return int the total depart time for the cart being checked out
	 */
	public int departTimeNext() {
		 if (line.isEmpty()) {
			 return Integer.MAX_VALUE;
		 }
		 else {
			 return line.front().getProcessTime() + line.front().getArrivalTime() + line.front().getWaitTime();
		 }
	 }
	
	/**
	 * Adds a cart to the end of the line, updating the cart's waitTime as well as the time when 
	 * the line will be clear of all carts currently in line. 
	 * @param someCart the cart being added to the line
	 */
	public void addCartToLine(Cart someCart) {
		
		
			
		if (someCart.getArrivalTime() >= timeWhenAvailable) {
				timeWhenAvailable = someCart.getArrivalTime() + someCart.getProcessTime();
				someCart.setWaitTime(0);
		}
		
		
		else {
			someCart.setWaitTime(timeWhenAvailable - someCart.getArrivalTime());
			timeWhenAvailable += someCart.getProcessTime();
			
		   
		
	   }
		
		line.add(someCart);
    }
}

