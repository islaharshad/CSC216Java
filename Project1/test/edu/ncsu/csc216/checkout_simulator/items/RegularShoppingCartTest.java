package edu.ncsu.csc216.checkout_simulator.items;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests the regular cart
 * @author Islahuddin Arshad
 *
 */
public class RegularShoppingCartTest {

	 /** Creates a new register */
	private CheckoutRegister[] reg = new CheckoutRegister[11];
	/** Creates a new log object*/
	private Log myLog = new Log();
	
	
	/**
	 * Sets up to create the number of registers for 
	 * the regular carts to be checked according to their 
	 * methods
	 */
	@Before
	public void setUp() {
		for (int i = 0; i < 11; i++) {
			reg[i] = new CheckoutRegister(myLog);
		
	  }
	}
	
	/**
	 * Tests the regular cart constructor through the arrival and 
	 * process time/ setting it to the appropriate register
	 * and the exception catches
	 */
	@Test
	public void testConstructor() {
		Cart regular = null;
		
			regular = new SpecialHandlingCart(8, 9);
			
			assertEquals(8, regular.getArrivalTime());
			assertEquals(9, regular.getProcessTime());
			
			
			regular.setRegisterIndex(5);
			assertEquals(5, regular.getRegisterIndex());
			
			regular.setWaitTime(20);
			assertEquals(20, regular.getWaitTime());
			regular.setRegisterIndex(2);
			try {
				regular.setRegisterIndex(-1); 
			}
			catch (IllegalArgumentException e) {
				assertEquals(2, regular.getRegisterIndex());
			}
		
	} 
	
	/**
	 * Tests the validity of an regular cart
	 * to be able to get in line and update the 
	 * register index vis a vis
	 */
	@Test
	public void testGetInLine() {
		Cart regular = new RegularShoppingCart(8, 10);
		assertFalse(regular.isWaitingInRegisterLine());
		regular.getInLine(reg);
		assertTrue(regular.isWaitingInRegisterLine());
		assertEquals(1, regular.getRegisterIndex());
		Cart regular1 = new RegularShoppingCart(10, 12);
		regular1.getInLine(reg);
		assertEquals(2, regular1.getRegisterIndex());
		Cart regular2 = new RegularShoppingCart(10, 12);
		regular2.getInLine(reg);
		Cart regular3 = new RegularShoppingCart(11, 13);
    	regular3.getInLine(reg);
		Cart regular4 = new RegularShoppingCart(12, 14);
		regular4.getInLine(reg);
		assertEquals(5, regular4.getRegisterIndex());
		
	}
	
	/**
	 * Tests the color method of the regular cart.
	 * Should return blue
	 */
	@Test
	public void testGetColor() {
		Cart regular = new RegularShoppingCart(8, 10);
		assertEquals(Color.BLUE, regular.getColor());
		
	}
}
