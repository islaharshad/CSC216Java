package edu.ncsu.csc216.checkout_simulator.simulation;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;
import edu.ncsu.csc216.checkout_simulator.items.ExpressCart;
import edu.ncsu.csc216.checkout_simulator.items.RegularShoppingCart;
import edu.ncsu.csc216.checkout_simulator.items.SpecialHandlingCart;


/**
 * Tests the simulator class
 * @author Islahuddin Arshad
 *
 */
public class SimulatorTest {
	
	/**Declares a cart in the simulator*/
	private Cart currentCart;
	
	
	/**Declares a simulator*/
	private Simulator sim;
	
	/**
	 * Sets up by clearing the cart factory and creating a 
	 * new simulator and creating a new cart type
	 */
	@Before
	public void setUp() {
		CartFactory.resetFactory();
		sim = new Simulator(12, 20);
		currentCart = new RegularShoppingCart(12, 12);
	}
	
	
	/**
	 * Tests the number of steps it takes the register 
	 * to finish processing
	 */
	@Test
	public void testTotalNumberOfSteps() {
	   
	    
		assertEquals(40, sim.totalNumberOfSteps());
		
		
		
	}
	/**
	 * Tests what happens if the wrong current index is 
	 * invoked
	 */
	@Test
	public void testGetCurrentIndex() {
		
		
		assertEquals(-1, sim.getCurrentIndex());
		
	}
	
	/**
	 * Tests how many items are left in the simulation after
	 * steps have taken place and by carts that are 
	 * removed from the simulation
	 */
	@Test
	public void testItemLeftInSimulation() {
		sim.step();
		assertFalse(sim.itemLeftSimulation());
		currentCart = null;
		assertFalse(sim.itemLeftSimulation());
		Cart regular = new RegularShoppingCart(13, 15);
		regular.removeFromWaitingLine();
		assertFalse(sim.itemLeftSimulation());
		
	}
	
	/**
	 * Tests getting the average wait time of a cart
	 */
	@Test
	public void testAverageWaitTime() {
		assertEquals(0.0, sim.averageWaitTime(), 0);
		
	}
	
	/**
	 * Tests getting the process wait time of a cart
	 */
	@Test
	public void testProcessWaitTime() {
		assertEquals(0.0, sim.averageProcessTime(), 0);
		
	}
	
	/**
	 * Tests the current cart color if the cart is null
	 */
	@Test
	public void testGetCurrentCartColor() {
		assertNull(sim.getCurrentCartColor());
	}
	
	/**
	 * When the cart is added to a register line,
	 * checks whether more steps are needed
	 */
	@Test
	public void testMoreSteps() {
		currentCart.isWaitingInRegisterLine();
		sim.step();
		assertTrue(sim.moreSteps());
	}
	
	/**
	 * Tests the colors of all cart types in the
	 * simulation
	 */
	@Test
	public void testSimulationColors() {
		Color[] color = new Color[3];
		color[0] = Color.BLUE;
		color[1] = Color.GREEN;
		color[2] = Color.RED;
		
		assertArrayEquals(color, Simulator.simulationColors());
		
		Cart e = new ExpressCart(12, 12);
		Cart s = new SpecialHandlingCart(12, 12);
		Cart r = new RegularShoppingCart(12, 12);
		assertEquals(Color.GREEN, e.getColor());
		assertEquals(Color.RED, s.getColor());
		assertEquals(Color.BLUE, r.getColor());
	}
	
	/**
	 * Tests the labels of all cart types in the simulation
	 */
	@Test
	public void testSimulationLabels() {
        String[] labels = new String[3];
		
		labels[0] = "Express Cart";
		labels[1] = "Regular Cart";
		labels[2] = "Special Handling Cart";
		
		assertArrayEquals(labels, Simulator.simulationLabels());
	}
}
