package edu.ncsu.csc216.bbtp;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.bbtp.model.TestingTypeList;

/**
 * Tests the BBTP class
 * 
 * @author Samuel
 *
 */
public class BBTPTest {

	/**
	 * Tests the constuctor of the BBTP class
	 */
	@Test
	public void testBBTP() {
		BBTP b = new BBTP();
		assertEquals(1, b.getNumTestCaseLists());
		assertEquals("New List", b.getTestCaseList(0).getName());
		assertEquals(0, b.getTestingTypeList().size());
		assertFalse(b.isChanged());
	}

	/**
	 * Tests the isChanged method of the BBTP class
	 */
	@Test
	public void testIsChanged() {
		BBTP b = new BBTP();
		TestingTypeList ty = new TestingTypeList();
		assertFalse(b.isChanged());
		b.update(ty, "string");
		assertTrue(b.isChanged());
		b.setChanged(false);
		assertFalse(b.isChanged());
	}

	/**
	 * Tests the setFilename method of the BBTP class
	 */
	@Test
	public void testSetFilename() {
		BBTP b = new BBTP();
		try {
			b.setFilename(null);
		} catch (Exception e) {
			// pass
		}

		try {
			b.setFilename("");
		} catch (Exception e) {
			// pass
		}

		b.setFilename("newFile");
		assertEquals("newFile", b.getFilename());
	}

	/**
	 * Tests the getNumTestCaseLists method of the BBTP class
	 */
	@Test
	public void testGetNumTestCaseLists() {
		BBTP b = new BBTP();
		assertEquals(1, b.getNumTestCaseLists());
		b.addTestCaseList();
		assertEquals(2, b.getNumTestCaseLists());
		b.addTestCaseList();
		assertEquals(3, b.getNumTestCaseLists());
		b.addTestCaseList();
		assertEquals(4, b.getNumTestCaseLists());

	}

	/**
	 * Tests the getTestCaseList method of the BBTP class
	 */
	@Test
	public void testGetTestCaseList() {
		BBTP b = new BBTP();
		b.addTestCaseList();
		b.addTestCaseList();
		b.addTestCaseList();
		b.getTestCaseList(3).setName("Fred");
		assertEquals("Fred", b.getTestCaseList(3).getName());
	}

	/**
	 * Tests the getTestingTypeList method of the BBTP class
	 */
	@Test
	public void testGetTestingTypeList() {
		BBTP b = new BBTP();
		assertEquals("Testing Types", b.getTestingTypeList().getName());
	}

	/**
	 * Tests the addTestCaseList method of the BBTP class
	 */
	@Test
	public void testAddTestCaseList() {
		BBTP b = new BBTP();

		assertEquals(1, b.getNumTestCaseLists());
		b.removeTestCaseList(0);
		assertEquals(0, b.getNumTestCaseLists());
		b.addTestCaseList();

		assertEquals(1, b.getNumTestCaseLists());
		b.addTestCaseList();
		assertEquals(2, b.getNumTestCaseLists());
		b.addTestCaseList();
		assertEquals(3, b.getNumTestCaseLists());
		b.addTestCaseList();
		assertEquals(4, b.getNumTestCaseLists());
	}

	/**
	 * Tests the removeTestCaseList method of the BBTP class
	 */
	@Test
	public void testRemoveTestCaseList() {
		BBTP b = new BBTP();
		b.addTestCaseList();
		b.addTestCaseList();
		b.addTestCaseList();
		assertEquals(4, b.getNumTestCaseLists());
		b.removeTestCaseList(3);
		assertEquals(3, b.getNumTestCaseLists());
		b.removeTestCaseList(1);
		assertEquals(2, b.getNumTestCaseLists());
		b.removeTestCaseList(1);
		assertEquals(1, b.getNumTestCaseLists());

	}

	/**
	 * Tests the saveDataFile method of the BBTP class
	 */
	@Test
	public void testSaveDataFile() {
		BBTP b = new BBTP();
		try {
			b.saveDataFile(null);
		} catch (Exception e) {
			// pass;
		}
		b.setFilename("fred");
		assertTrue(b.saveDataFile("test-files/fred.bbtp"));

		BBTP c = new BBTP();
		assertTrue(c.openDataFile("test-files/fred.bbtp"));

		assertEquals(b.getTestCaseList(0).getName(), c.getTestCaseList(0).getName());
	}

}
