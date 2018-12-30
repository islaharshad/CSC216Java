	package edu.ncsu.csc216.pack_scheduler.util;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * An arrayList class which represents a collection of all the courses
 * and students in the University
 * @author Isla Huddin, Prem Subedi, Edwerdo Bravo
 */
public class ArrayListTest {


	/** Tests arrayList constructor */
    @Test
	public void testArrayList() {
		
		ArrayList<String> a = new ArrayList<String>();
		assertEquals(0, a.size());
	}
    
    /** Tests add method */
    @Test
    public void testAdd() {
    	ArrayList<String> a = new ArrayList<String>();
    	a.add(0, "apple");
    	a.add(0, "banana");
    	assertEquals(2, a.size());
    	assertEquals("apple", a.get(1));
    	
       try{
    	   a.add(2, "banana");
       }
       catch(IllegalArgumentException e){
    	   assertEquals(2, a.size());
       }
       try {
    	   a.add("apple");    	   
       }
       catch(IllegalArgumentException e) {  
    	   assertEquals(2, a.size());
       }
       
       try {
    	   a.add(null);
    	   fail();
       }
       catch(NullPointerException e) {
    	   assertEquals(2, a.size());
       }
       
       try {
    	   a.add(10, "b");
    	
       }
       catch (IndexOutOfBoundsException e) {
    	   assertEquals(2, a.size());
       }
       
       try {
    	   a.add(-3, "b");
    	   fail();
       }
       catch (IndexOutOfBoundsException e) {
    	   assertEquals(2, a.size());
       }
       a.add(1, "cider");
       assertEquals("cider", a.get(1));
       
       
  ArrayList<String> b = new ArrayList<String>();
       
       b.add(0, "apple");
       b.add(0, "orange");
       b.add(1, "banana");
       b.add(3, "kiwi");
      // b.add(1, "apple");
       
       for (int i = 0; i < b.size(); i++) {
    	   System.out.println(b.get(i));
       }
       
       
       
    }
    
    /** Tests resize method */
    @Test
	public void testResize() {
    	ArrayList<String> a = new ArrayList<String>();
    	String[] list = new String[4];
    	a.resize();
    	assertEquals(4, list.length);
    	
    }
    /** test get() method */
    @Test
    public void testGet() {
    	ArrayList<String> a = new ArrayList<String>();
    	a.add("abc");
    	assertEquals("abc", a.get(0));
    	try {
    		a.get(4);
    		fail();
    	}
    	catch(IndexOutOfBoundsException e) {
    		assertEquals("abc", a.get(0));
    	}
    }
    
    /** Tests remove() method */
    @Test 
    public void testRemove() {
    	ArrayList<String> a = new ArrayList<String>();
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
     	
    }
    
    /** Test set() method */
    @Test
    public void testSet() {
    	ArrayList<String> a = new ArrayList<String>();
    	
    	a.add("apple");
    	a.add("banana");
    	a.add("elephant");
    	a.add("baby");
    	a.set(2, "baby1");
    	a.set(0, "cool");
    	a.set(1, "joe");
    	
        assertEquals(4, a.size());
        assertEquals("baby1", a.set(2, "baby2"));
        assertEquals("joe", a.get(1));
        
        try {
        	a.set(20, "abba");
        	fail();
        }
        catch(IndexOutOfBoundsException e) {
        	e.getMessage();
        }
        
        try {
        	a.set(1, null);
        	fail();
        }
        catch(NullPointerException e) {
        	e.getMessage();
        }
        
        try { 
        	a.set(-1, "zero");
        	fail();
        }
        catch(IndexOutOfBoundsException e) {
        	e.getMessage();
        } 
        
        try {
        	a.set(0, "cool");
        	
        }
        catch(IllegalArgumentException e) {
        	e.getMessage();
        }
    }
}
