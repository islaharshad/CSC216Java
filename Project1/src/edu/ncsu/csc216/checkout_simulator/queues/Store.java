package edu.ncsu.csc216.checkout_simulator.queues;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;

/**
 * The store class processes the carts leaving the
 * store area
 * @author Islahuddin Arshad
 *
 */
public class Store implements LineOfItems {
    /**The carts left in the shopping area*/
	private ShoppingCartQueue shopping;
	/**The register shopping carts are going to go to*/
	private CheckoutRegister[] register;
	
	/**
	 * Constructs the Store class where number of carts are 
	 * shown inside the "store" and are constantly added into 
	 * store by car factory. They will later be going to a register
	 * @param numCarts the number of carts in the store
	 * @param register the register the carts will be going to 
	 * after leaving the store
	 */
	public Store (int numCarts, CheckoutRegister[] register) {
		this.shopping = new ShoppingCartQueue();
		this.register = register;
		for (int i = 0; i < numCarts; i++) {
			shopping.add(CartFactory.createCart());
		}
 

	}
	/**
	 * Returns the number of carts still in the shopping queue.
	 * @return int the size of the carts still in the shopping area
	 */
	 public int size() {
		  
		 int cartsLeft = shopping.size();
		 return cartsLeft;
	 }
	 
     /**
      *  Returns true if the shopping queue is not empty.
      *  @return boolean whether the shopping area is not empty
      */
	 public boolean hasNext() {
		 if (shopping.isEmpty()) {
			 return false;
		 }
		 return true;
	 }
	 /**
	  * Removes the front cart from the shopping queue and sends it a getInLine message.
	  * The removed cart is returned.
	  * @return Cart the cart that is processed next
	  */
	 public Cart processNext() {
		 
		 Cart someCart = shopping.remove();
		 someCart.getInLine(register);
		 return someCart;
	 }
	 /**
	  * Tells when the cart at the front of the shopping queue 
	  * will depart that queue (and subsequently enter a CheckoutRegister queue).
	  * If the shopping queue is empty, return Integer.MAX_VALUE.
	  * @return int the depart time of the cart from the store
	  */
	 public int departTimeNext() {
		 
		 if (shopping.isEmpty()) {
			 return Integer.MAX_VALUE;
		 }
		 else {
			 return shopping.front().getArrivalTime();
		 }
	 }
	 
}
