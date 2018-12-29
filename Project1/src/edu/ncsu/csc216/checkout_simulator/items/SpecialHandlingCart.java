package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * The special cart is constructed and inherited from the
 * cart class
 * @author Islahuddin Arshad
 *
 */
public class SpecialHandlingCart extends Cart {

	/**The color of the cart object*/
	private static Color color;
	
	/**
	 * Constructs the special cart with its arrival and process
	 * time and sets the register index to zero by default
	 * @param arrivalTime the arrival time of the cart
	 * @param processTime the process time of the cart
	 */
	public SpecialHandlingCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		super.setRegisterIndex(-1);
	}
	/**
	 * Checks that special cart can't be in express line and 
	 * can only be on the right side of the register indexes 
	 * and must be in special register indexed
	 * @param theRegister the register of the special cart
	 */
	public void getInLine(CheckoutRegister[] theRegister) {
		
		int shortest = 0;
		
		int numberOfSpecialRegisters = theRegister.length / 4;
		
		if (theRegister.length % 4 == 0) {
			shortest = theRegister.length - numberOfSpecialRegisters;
		}
		
		else {
			shortest = theRegister.length - numberOfSpecialRegisters - 1;
		}
		

		for (int i = shortest + 1; i < theRegister.length; i++) {
            if (theRegister[i].size() < theRegister[shortest].size()) {
				shortest = i;
			}
		}
		super.setRegisterIndex(shortest);
		theRegister[shortest].addCartToLine(this);
	}
		
		
		
	/**
	 * The color of the special cart which is red
	 * @return Color the color of the cart
	 */
	public Color getColor() {
		color = Color.RED;
		return color;
	}
}
