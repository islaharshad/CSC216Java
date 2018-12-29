package edu.ncsu.csc216.checkout_simulator.items;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests the express cart
 * @author Islahuddin Arshad
 *
 */
public class ExpressCartTest {

    /** Creates a new register */
	private CheckoutRegister[] reg = new CheckoutRegister[11];
	/** Creates a new log object*/
	private Log myLog = new Log();
	
	/**
	 * Sets up to create the number of registers for 
	 * the express carts to be checked according to their 
	 * methods
	 */
	@Before
	public void setUp() {
		for (int i = 0; i < 11; i++) {
			reg[i] = new CheckoutRegister(myLog);
		
	  }	
	}
	
	/**
	 * Tests the express cart constructor through the arrival and 
	 * process time/ setting it to the appropriate register
	 * and the exception catches
	 */
	@Test
	public void testConstructor() {
		Cart express = null;
		
			express = new ExpressCart(8, 9); 
			
			
			assertEquals(8, express.getArrivalTime());
			assertEquals(9, express.getProcessTime());
			
			
			express.setRegisterIndex(0);
			assertEquals(0, express.getRegisterIndex());
			
			express.setWaitTime(20);
			assertEquals(20, express.getWaitTime());
			express.setRegisterIndex(2);
			try {
				express.setRegisterIndex(-1);
			}
			catch (IllegalArgumentException e) {
				assertEquals(2, express.getRegisterIndex());
			}
			
			
	}
	
	/**
	 * Tests the validity of an express cart
	 * to be able to get in line and update the 
	 * register index vis a vis
	 */
	@Test
	public void testGetInLine() {
		Cart express = new ExpressCart(8, 10);
		express.getInLine(reg);
		assertTrue(express.isWaitingInRegisterLine());
		assertEquals(0, express.getRegisterIndex());
		Cart express1 = new ExpressCart(10, 12);
		express1.getInLine(reg); 
		assertEquals(1, express1.getRegisterIndex());
		Cart express2 = new ExpressCart(10, 12);
		express2.getInLine(reg);
		Cart express3 = new ExpressCart(11, 13);
		express3.getInLine(reg);
		Cart express4 = new ExpressCart(12, 14);
		express4.getInLine(reg);
		assertEquals(4, express4.getRegisterIndex());
		
	}
	/**
	 * Tests the color method of the express cart.
	 * Should return green
	 */
	@Test
	public void testGetColor() {
		Cart express = new ExpressCart(8, 10);
		assertEquals(Color.GREEN, express.getColor());
		
	}
}


