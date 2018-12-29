package edu.ncsu.csc216.checkout_simulator.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.ExpressCart;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests the checkOutRegister class
 * @author Islahuddin Arshad
 *
 */
public class CheckoutRegisterTest {

	/**Creates carts for the register to test*/
	private Cart[] carts = new Cart[4];
	/**Creates the chekcout register*/
	private CheckoutRegister register;
	/**Creates the log used by the register*/
	private Log myLog;
	
	/**
	 * Sets up the checkOut register by creating carts
	 * to be added to the register that will log them
	 */
	@Before
	public void setUp() {
		myLog = new Log();
		register = new CheckoutRegister(myLog);
		carts[0] = new ExpressCart(1, 5);
		carts[1] = new ExpressCart(2, 10);
		carts[2] = new ExpressCart(3, 15);
		carts[3] = new ExpressCart(4, 20);
	}
	
	/**
	 * Tests whether the carts, once added, to a line,
	 * will be able to be processed next by the register
	 */
	@Test
	public void testProcessNext() {
		register.addCartToLine(new ExpressCart(27, 200));
		Cart c = new ExpressCart(156, 180);
		register.addCartToLine(c);
		
		register.addCartToLine(new ExpressCart(160, 235));

		
		assertEquals(71, c.getWaitTime());
		
	}
	
	/**
	 * Tests the size of the register once the 
	 * carts are added
	 */
	@Test
	public void testSize() {
		register.addCartToLine(carts[0]);
		register.addCartToLine(carts[1]);
		register.addCartToLine(carts[2]);
		
		register.processNext();
		assertEquals(2, register.size());
	}
	
	/**
	 * Tests whether the register does or does not
	 * have the next cart to process
	 */
	@Test
	public void testHasNext() {
		assertFalse(register.hasNext()); 
		register.addCartToLine(carts[0]);
		register.addCartToLine(carts[1]);
		register.addCartToLine(carts[3]);
		assertTrue(register.hasNext());
		
	}
	
	/**
	 * Tests the time it takes for the cart to be processed
	 * and depart a register after it has checked out
	 */
	@Test
	public void testDepartTimeNext() {
		assertEquals(Integer.MAX_VALUE, register.departTimeNext());  
		register.addCartToLine(carts[0]);
		register.addCartToLine(carts[1]);
		assertEquals(5, carts[0].getProcessTime());
		
	}
		
	
	
	
	
}
