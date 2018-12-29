package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * The Express cart inherites the cart class
 * and is a type of it
 * @author Islahuddin Arshad
 
 */
public class ExpressCart extends Cart {

	/**Color of a cart*/
	private static Color color;
	/**Constructs the express cart constructors which 
	 * sets the register to -1 initially
	 * @param arrivalTime arrival time of the cart
	 * @param processTime process time of the cart
	 */
	public ExpressCart(int arrivalTime, int processTime) {
		
		super(arrivalTime, processTime);
		super.setRegisterIndex(-1);
	}
	/**
	 * Makes a cart get in line within an array of 
	 * registers
	 * @param theRegister the current register for a cart
	 */
	public void getInLine(CheckoutRegister[] theRegister) {
		
		int shortest = 0;
		 
		for (int i = 0; i < theRegister.length; i++) {
			if (theRegister[i].size() < theRegister[shortest].size()) {
				
				shortest = i;
			}
		}
		super.setRegisterIndex(shortest);
		theRegister[shortest].addCartToLine(this);
	}
	
	/**
	 * Gets the color of the express cart
	 * @return Color color of the cart
	 */
	public Color getColor() {
		color = Color.GREEN;
		return color;
	}
	
}
