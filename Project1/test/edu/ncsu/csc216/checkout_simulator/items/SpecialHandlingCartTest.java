package edu.ncsu.csc216.checkout_simulator.items;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;




/**
 * Tests the SpecialHandlingCartClass by setting up
 * registers throughout the scenario with different 
 * indexes
 * @author Islahuddin Arshad
 *
 */
public class SpecialHandlingCartTest {

	/**Creates the register that is in the start*/
	private CheckoutRegister[] min = new CheckoutRegister[3];
	/**Creates the register that is in the middle*/
	private CheckoutRegister[] middle = new CheckoutRegister[7];
	/**Creates the register that is in the end*/
	private CheckoutRegister[] max = new CheckoutRegister[9];
	/**Creates a new log at the beginning*/
	private Log minLog = new Log();
	/**Creates a new log at the middle*/
	private Log middleLog = new Log();
	/**Creates a new log at the end*/
	private Log maxLog = new Log();
	
	/**
	 * Sets up the special cart connecting the
	 * log to its appropriate checkout register
	 */
	@Before
	public void setUp() {
		for (int i = 0; i < 3; i++) {
			min[i] = new CheckoutRegister(minLog);
		}
		for (int i = 0; i < 7; i++) {
			middle[i] = new CheckoutRegister(middleLog);
		}
		for (int i = 0; i < 9; i++) {
			max[i] = new CheckoutRegister(maxLog);
		}
	}
	
	/**
	 * Tests the special cart constructor through the arrival and 
	 * process time/ setting it to the appropriate register
	 * and the exception catches
	 */
	@Test
	public void testConstructor() {
		SpecialHandlingCart special = null;
		
			special = new SpecialHandlingCart(8, 9);
			
			assertEquals(8, special.getArrivalTime());
			assertEquals(9, special.getProcessTime());
			
			
			special.setRegisterIndex(5);
			assertEquals(5, special.getRegisterIndex());
			
			special.setWaitTime(20);
			assertEquals(20, special.getWaitTime());
			special.setRegisterIndex(2);
			
			special.setRegisterIndex(-1);
			assertFalse(special.isWaitingInRegisterLine());
			
			SpecialHandlingCart special0 = null; 
			try {
				special0 = new SpecialHandlingCart(-1, 12);
				special0.getArrivalTime();
				fail();
			}
			catch (IllegalArgumentException e) {
				assertNull(special0);
			}
			
			SpecialHandlingCart special20 = null; 
			try {
				special20 = new SpecialHandlingCart(1, -12);
				special20.getProcessTime();
				fail();
			}
			catch (IllegalArgumentException e) {
				assertNull(special20);
			}
				
			
		
	}
	
	/**
	 * Tests the validity of an special cart
	 * to be able to get in line and update the appropriate
	 * (as described in the setup) register index vis a vis
	 */
	@Test
	public void testGetInLine() {
		SpecialHandlingCart special = new SpecialHandlingCart(20, 7);
		
		
		    special.getInLine(min);
		    assertEquals(2, special.getRegisterIndex());
		    
    	    special.getInLine(middle);
		    assertEquals(5, special.getRegisterIndex());
		    
    	    special.getInLine(max);
		    assertEquals(6, special.getRegisterIndex());
		   	    
	}
	
	/**
	 * Tests the color method of the special cart.
	 * Should return red
	 */
	@Test
	public void testGetColor() {
		Cart special = new SpecialHandlingCart(8, 10);
		assertEquals(Color.RED, special.getColor());
		
	}
}
