package edu.ncsu.csc216.checkout_simulator.simulation;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.queues.LineOfItems;
import edu.ncsu.csc216.checkout_simulator.queues.Store;

/**
 * The simulator class handles procession of carts
 * leaving the store and entering the registers
 * @author Islahuddin Arshad
 *
 */
public class Simulator {

	/**Minimum amount of registers in the checkout area*/
	private static final int MIN_NUM_REGISTERS = 3;
	/**Maximum amount of registers in the checkout area*/
	private static final int MAX_NUM_REGISTERS = 12;
	/**Number of registers in the checkout area*/
	private int numRegisters;
	/**Number of carts in the checkout area*/
	private int numCarts;
	/**Steps taken by the simulator until the processing is finished*/
	private int stepsTaken = 0;
	/**The current cart in that is being processed*/
	private Cart currentCart;
	/**The checkout register that will process the cart so that step in
	 * incremented in the simulation
	 */
	private CheckoutRegister[] register;
	/**The store from where the carts are coming from*/
	private Store theStore;
	/**The event calender records all the steps of simulation*/
	private EventCalendar theCalender;
	/**The log records the average wait and average process time for 
	 * each cart that is being processed
	 */
	private Log myLog;
	
	/** 
	 * The simulator takes in the number of registers
	 * and carts to check their bounds (not less than three or
	 * greater than twelve or if the carts are less than one)
	 * and creates the register objects
	 * @param numRegisters the number of registers in the 
	 * simulator will have to look through to process each cart
	 * in
	 * @param numCarts The number of carts the simulator has to look
	 * to process
	 * @throws IllegalArgumentException it is thrown if wrong number of register
	 * and if number of carts are less than one
	 */
	public Simulator(int numRegisters, int numCarts) {
		
		if (numRegisters < MIN_NUM_REGISTERS || numRegisters > MAX_NUM_REGISTERS) {
			throw new IllegalArgumentException("Numbers of registers must be between 3 and 12 inclusive.");
		}
		
		 
		if (numCarts < 1) {
			throw new IllegalArgumentException("There must be at least one shopping cart in the simulation.");
		}


		this.numCarts = numCarts;
		this.numRegisters = numRegisters;
		this.register = new CheckoutRegister[this.numRegisters];
		this.myLog = new Log();
		for (int i = 0; i < this.numRegisters; i++){
			register[i] = new CheckoutRegister(myLog);
		}
		this.theStore = new Store(numCarts, register);
		this.theCalender = new EventCalendar(register, theStore);
		
	}
	/**
	 * Gets the simulation colors for each of the three carts
	 * @return Color the color of the carts
	 */
	public static Color[] simulationColors() {
		Color[] color = new Color[3];
		color[0] = Color.BLUE;
		color[1] = Color.GREEN;
		color[2] = Color.RED;
		
		return color;
	}
	/**
	 * Gets the simulation labels for each of the three carts
	 * @return String[] the label of the carts
	 */
	public static String[] simulationLabels() {
		String[] labels = new String[3];
		
		labels[0] = "Express Cart";
		labels[1] = "Regular Cart";
		labels[2] = "Special Handling Cart";
		
		return labels;
	}
	/**
	 * Increments the steps in the simulator when the event
	 * calender is updated. If that is the case, the current cart
	 * is "stepped" or processed by the simulator
	 */
	public void step() {
		currentCart = null;
		LineOfItems items = theCalender.nextToBeProcessed();
		stepsTaken++;
		
		if (items.hasNext()) {
			currentCart = items.processNext();
		}
	} 
	/**
	 * Get the number of steps taken by the simulator
	 * @return int the number of steps taken
	 */
	public int getStepsTaken() {
		return stepsTaken;
	} 
	/**
	 *The total number of steps taken are twice the number of 
	 *carts because for each cart, it is removed from the shopping area
	 *and after processing, removed from the simulation
	 * @return int the number of steps taken to process the carts
	 */
	public int totalNumberOfSteps() {
		
		return numCarts * 2;
	}
	/**
	 * If the number of carts are fully processed or not
	 * True if the simulation has not yet finished, false if it has.
	 * @return boolean whether more steps are required to go through
	 * a simulation
	 */
	public boolean moreSteps() {
		if(myLog.getNumCompleted() < numCarts) {
			return true;
		}
		return false;
	}
	/**
	 * Gets the current index of the cart being processed else returns -1
	 * @return int returns the current index else returns -1
	 */
	public int getCurrentIndex() {
		
		if (currentCart == null) {
			return -1;
		}
		else {
			return currentCart.getRegisterIndex();
		}
	}
	/**
	 * Gets the current color of the cart being processed
	 * but returns null if the current cart is null
	 * @return Color the color the current cart
	 */
	public Color getCurrentCartColor() {
		if (currentCart == null) {
			return null;
		}
		return currentCart.getColor();
	}
	/**
	 * Returns true if the most recently handled cart completed checking out and left a register line; 
	 * Returns false if the most recently handled cart left the shopping area to enter a register line 
	 * or if there is no current cart (null).
	 * @return whether the current cart still remains in the simulation for the simulator to process 
	 */
	public boolean itemLeftSimulation() {
		if (currentCart == null) {
			return false;
		} 
		else {
			return !currentCart.isWaitingInRegisterLine();
		}
		
			
		}
		
	
	/**
	 * The average wait time of the current cart is returned
	 * @return double the average wait time of the cart
	 */
	public double averageWaitTime() {
		return myLog.averageWaitTime();
	}
	/**
	 * The average process time of the current cart is returned
	 * @return double the process wait time of the cart
	 */
	public double averageProcessTime() {
		return myLog.averageProcessTime();
	}
}
