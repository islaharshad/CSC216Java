package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList.ListNode;

public class LinkedAbstractListTest {

	
	

	/** Tests arrayList constructor */
    @Test
	public void testLinkedAbstractList() {
		
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
		assertEquals(0, a.size());
		try{
			LinkedAbstractList<String> b = new LinkedAbstractList<String>(-1234);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			e.getMessage();
		}
	}
    
    
    
    /** Tests add method */
    @Test
    public void testAdd() {
    	LinkedAbstractList<String> a = new LinkedAbstractList<String>(4);
    	
    	a.add("obj1");
    	assertEquals("obj1", a.get(0));
    	
    	a.add(0, "obj2");
    	assertEquals("obj2", a.get(0));
    	
    	assertEquals("obj1", a.get(1));
    	
    	a.add(2,"obj3");
    	assertEquals("obj3", a.get(2));
    	
    	a.add(3, "obj4");
    	assertEquals("obj4", a.get(3));
    	
    	LinkedAbstractList<String> b = new LinkedAbstractList<String>(4);
    	
    	b.add(0, "two");
    	b.add(0, "zero");
    	b.add(1, "one");
    	
    	assertEquals("one", b.get(1));
    	assertEquals("zero", b.get(0));
    	
    	//assertTrue(b.elementIsContained("five"));
    	
    	
       try{
    	   a.add(2, "obj1");
    	   fail();
       }
       catch(IllegalArgumentException e){
    	   assertEquals(4, a.size());
       }
       try {
    	   a.add("obj1");    
    	   fail();
       }
       catch(IllegalArgumentException e) {  
    	   assertEquals(4, a.size());
       }
       
       try {
    	   a.add(null);
    	   fail();
       }
       catch(NullPointerException e) {
    	   assertEquals(4, a.size());
       }
       
       try {
    	   a.add(10, "b");
    	   fail();
       }
       catch (IndexOutOfBoundsException e) {
    	   assertEquals(4, a.size());
       }
       
       try {
    	   a.add(-3, "b");
    	   fail();
       }
       catch (IndexOutOfBoundsException e) {
    	   assertEquals(4, a.size());
       }
       
       try{
    	   a.add("obj5");
    	   fail();
       }
       catch(IllegalArgumentException e)
       {
    	   assertEquals(4, a.size());
       }
       
    }
    
    
    /** test get() method */
    @Test
    public void testGet() {
    	
    	//Initializes array
    	
    	LinkedAbstractList<String> a = new LinkedAbstractList<String>(4);
    	a.add("1");
    	

    	//Tests get method at a valid index
    	assertEquals("1", a.get(0));
    	
    	//Case where index is greater than size.
    	try {
    		a.get(4);
    		fail();
    	}
    	catch(IndexOutOfBoundsException e) {
    		assertEquals("1", a.get(0));
    	}
    	try{ //equal to size
    		a.get(a.size());
    		fail();
    	}
    	catch(IndexOutOfBoundsException e){
    		assertEquals("1", a.get(0));
    	}
    	
    	try{ //less than 0
    		a.get(-10);
    		fail();
    	}
    	catch(IndexOutOfBoundsException e){
    		assertEquals("1", a.get(0));
    	}
    }
    
    /** Tests remove() method */
    @Test 
    public void testRemove() {
    	LinkedAbstractList<String> a = new LinkedAbstractList<String>(10);
    	a.add("apple");
    	a.add("banana");
    	a.add("elephant");
    	a.add("baby");
    	assertEquals("apple", a.remove(0));
    	assertEquals(3, a.size());
    	 try {
      	   a.remove(-1);
      	   fail();
         }
         catch (IndexOutOfBoundsException e) {
        	 assertEquals(3, a.size());
         }
    	 
    	 try {
        	   a.remove(4);
        	   fail();
           }
           catch (IndexOutOfBoundsException e) {
          	 assertEquals(3, a.size());
           }
    	try {
    		a.remove(3);
    		fail();
    	}
    	catch (IndexOutOfBoundsException e) {
    		e.getMessage();
    	}
    	assertEquals(a.remove(1), "elephant");
    	a = new LinkedAbstractList<String>(10);
    	a.add("apple");
    	a.add("banana");
    	a.add("elephant");
    	a.add("baby");
    	assertEquals("baby", a.remove(3));
    	a = new LinkedAbstractList<String>(10);
    	a.add("apple");
    	a.add("banana");
    	a.add("elephant");
    	a.add("baby");
    	assertEquals("elephant", a.remove(2));
    	
    }
    
    /** Test set() method */
    @Test
    public void testSet() {
    	
    	//****First we initialize array with elements
    	LinkedAbstractList<String> a = new LinkedAbstractList<String>(6);
    	
    	a.add("apple");
    	a.add("banana");
    	a.add("cow");
    	a.add("date");
       
    	
    	
    	//**Then ensure set returns right element at index 0,
    	//**random index between 0 and size-1 (2 in this case), and index size - 1	

    	assertEquals("apple", a.set(0, "apricot"));
    	assertEquals("apricot", a.get(0));
    	assertEquals("cow", a.set(2, "cantaloupe"));
    	assertEquals("cantaloupe", a.get(2));
    	//String randomIntObject = a.get(randomInt);
    //	assertEquals(1, a.set(randomInt, "cantaloupe"));
    	//assertEquals("date", a.set(a.size() - 1, "dewberry"));
   
    	
    	
    	//**Then ensure set modifies LinkedAbstractList
    	//assertEquals("apricot", a.get(0));
    	//assertEquals("cantaloupe", a.get(randomInt));
    	//assertEquals("dewberry", a.get(a.size() - 1));
    	
    	
    	
    	//**Then ensure set throws exceptions
    	try{   //NullPointerException
    		a.set(0, null);
    		fail();
    	}
    	catch(NullPointerException e){
    		assertEquals(4, a.size());
    	}
    	
    	try{ //IllegalArgumentException
    		a.set(2,"date");
    		fail();
    	}
    	catch(IllegalArgumentException e){
    		assertEquals(4, a.size());
    	}
    	
    	try{ //IndexOutOfBounds (negative case)
    		a.set(-10, "outOfBoundsRebelObject");
    		fail();
    	}
    	catch(IndexOutOfBoundsException e)
    	{
    		assertEquals(4, a.size());
    	}
    	
    	try{ //IndexOutOfBounds (equal case)
    		a.set(a.size(), "outOfBoundsRebelObject");
    		fail();
    	}
    	catch(IndexOutOfBoundsException e){
    		assertEquals(4,  a.size()); 
    	}
    	try{ //IndexOutOfBounds (greater case)
    		a.set(a.size() + 10, "outOfBoundsRebelObject");
    		fail();
    	}
    	catch(IndexOutOfBoundsException e){
    		assertEquals(4, a.size());
    	}
    }
    
    /** Test indexAt method */
    @Test
    public void indexAt() {
    	
    	//****First we initialize array with elements
    	LinkedAbstractList<String> a = new LinkedAbstractList<String>(6);
    	
    	a.add("a");
    	a.add("b");
    	assertEquals(1, a.indexAt("b"));
    }

    
}















