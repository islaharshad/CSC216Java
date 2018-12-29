package edu.ncsu.csc216.checkout_simulator.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * This class tests the store class
 * @author Islahuddin Arshad
 *
 */
public class StoreTest {
    /**creates the checkout register array */
	private CheckoutRegister[] coRegister = new CheckoutRegister[2];
	
	/** Construction of the store class to be used */
	private Store newStore = new Store(10, coRegister);
	/** the log created */
	private Log newLog;
	
	
	/**
	 * Creates the registers that have info logged into them
	 * so that they can be incorporated into the store tests
	 */
	@Before
	public void setUp() {
		coRegister[0] = new CheckoutRegister(newLog);
		coRegister[1] = new CheckoutRegister(newLog);
	}
	/**
	 * Tests the store constructor
	 * by checking the how many carts remain in the
	 * store
	 */
	@Test
	public void testConstructor() {
		 
		assertEquals(10, newStore.size());
	}
	
	/**
	 * Tests hasNext() in store class by
	 * what cart needs to be processed next out 
	 * of the store
	 */
	@Test
	public void testHasNext() { 
		assertTrue(newStore.hasNext());
		Store store1 = new Store(0, coRegister);  
		assertFalse(store1.hasNext());
	}
	/**
	 * Tests departTimeNext() in store class by
	 * checking what time a cart arrives into the
	 * checkout register lanes
	 */
	@Test
	public void testDepartTimeNext() { 
		Store store1 = new Store(0, coRegister);
		assertEquals(0, store1.size());
		assertEquals(Integer.MAX_VALUE, store1.departTimeNext());
		
	}
	
	
}
