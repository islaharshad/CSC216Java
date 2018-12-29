package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * The regular shopping cart is the regular shopping 
 * cart that inherits its features from the cart class
 * @author Islahuddin Arshad
 *
 */
public class RegularShoppingCart extends Cart {
    /**The color of the cart*/
	private static Color color;
	/**Constructs the regular cart and sets the 
	 * register index initially to -1
	 * @param arrivalTime the arrival time of the cart
	 * @param processTime the process time of the cart
	 */
	public RegularShoppingCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		super.setRegisterIndex(-1); 
	}
	/**
	 * Allows the regular cart to get in the shortest line
	 * of all
	 * @param theRegister the register the cart is going into
	 */
	public void getInLine(CheckoutRegister[] theRegister) {
		
		int shortest = 1;
		 
		for (int i = 2; i < theRegister.length; i++) {
			if (theRegister[i].size() < theRegister[shortest].size()) {
				
				shortest = i;
			}
		}
		super.setRegisterIndex(shortest);
		theRegister[shortest].addCartToLine(this);
	
	}
	/**
	 * The color of the cart
	 * @return Color the color of the cart
	 */
	public Color getColor() {
		color = Color.BLUE;
		return color;
	}
}
