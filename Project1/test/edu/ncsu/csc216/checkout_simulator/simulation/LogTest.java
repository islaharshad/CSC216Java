package edu.ncsu.csc216.checkout_simulator.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.RegularShoppingCart;
import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * Tests the log class
 * @author Islahuddin Arshad
 *
 */
public class LogTest {
     
	/**A new log is declared to test*/
	Log newLog;
	/**A register is declared to be tested by the log*/
	CheckoutRegister register;
	/** A cart is created to be tested by register and log*/
	Cart cart = new RegularShoppingCart(5, 6);;
	
	/**
	 * A register is created which processes the carts which log 
	 * info into it
	 */
	@Before
	public void setUp() {
		newLog = new Log();
		register = new CheckoutRegister(newLog);
		for (int i = 0; i < 6; i++) {
			register.addCartToLine(cart);
			newLog.logCart(cart); 
		}
		
	}
	
	/**Tests whether the log is working correctly
	 * by checking through the number of carts that
	 * have already logged in
	 */
	@Test
	public void testLog() {
		assertEquals(6, newLog.getNumCompleted());
	}
	

	
	/**
	 * Tests the average process time it takes a cart
	 * to process and then log its info into the register
	 */
	@Test
	public void testAverageProcessTime() {
		assertEquals(6.0, newLog.averageProcessTime(), 0);
	     newLog.logCart(cart);
	     assertEquals(7, newLog.getNumCompleted());
	}
}
