package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * Creates the abstract Cart class
 * @author Islahuddin Arshad
 *
 */
public abstract class Cart {
    /**The initial register index*/
	private static final int INITIAL_REGISTER_IDX = 0;
	/**The arrival time of cart*/
	private int arrivalTime; 
	/**The wait time of the cart*/
	private int waitTime;
	/**Th process time of the cart*/
	private int processTime;
	/**The register index the cart is attributed to*/
	private int registerIndex;
	/**The whether the cart is waiting in line or not*/
	private boolean waitingProcessing;
	
	/**
	 * Constructs the cart with the arrival and process time
	 * @param arrivalTime the arrival time
	 * @param processTime the wait time
	 */
	public Cart(int arrivalTime, int processTime) {
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Gets the arrival time and checks whether it is less than
	 * 0 or not
	 * @throws IllegalArgumentException if the arrival time is less 
	 * than zero or not
	 * @return int the arrival time
	 */
	public int getArrivalTime() {
		if (arrivalTime < 0) {
			throw new IllegalArgumentException();
		}
		return arrivalTime;
	}
	/**
	 * Gets the wait time of the cart
	 * @return int the wait time
	 */
	public int getWaitTime() {
		return waitTime;
	}
	/**
	 * Gets the process time and checks whether it is less than
	 * 0 or not
	 * @throws IllegalArgumentException if the process time is less 
	 * than zero or not
	 * @return int the process time
	 */
	public int getProcessTime() {
		if (processTime < 0) {
			throw new IllegalArgumentException();
		}
		return processTime;
	}
	/**
	 * Sets the wait time of a cart
	 * @param waitTime of the cart
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	/**
	 * Gets the register index of the cart
	 * @return int the wait time
	 */
	public int getRegisterIndex() {
		
		return registerIndex;
	}
	/**
	 * If the cart is waiting in line
	 * @return boolean returns true if waiting in line
	 */
	public boolean isWaitingInRegisterLine() {
		
		return waitingProcessing;
	}
	
	/**
	 * Removes a cart from the waiting line
	 */
	public void removeFromWaitingLine() {
		waitingProcessing = false;
	}
	
	/**
	 * Sets the register index of a cart and 
	 * if the cart waiting in line is greater or
	 * equal to zero
	 * @param theIndex the register index of a cart
	 */
	protected void setRegisterIndex(int theIndex) {
		
		this.registerIndex = theIndex;
		this.waitingProcessing = registerIndex >= INITIAL_REGISTER_IDX;
		

		
	}
	/**
	 * Abstract method for get in line
	 * @param theRegister the register of a cart
	 */
	public abstract void getInLine(CheckoutRegister[] theRegister);
	
	/**
	 * Gets the color of the cart type
	 * @return Color color of the cart type
	 */
	public abstract Color getColor();
	
		
}
