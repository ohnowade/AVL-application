import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: add imports as needed for your JUnit tests

/**
 * ABSTRACT super-class with DataStructureADT JUnit tests.
 * 
 * This class contains methods for testing the basic functionality of a DataStructureADT
 * implementation.   Such a d.s. type was designed and tested in Program 1.
 * 
 * This class will now be the super-class of SearchTreeADTTest.  This means that 
 * SearchTreeADTTest inherit all of tests (public and protect methods) from 
 * DataStructureADTTest.  
 * 
 * For Program 2, almost all tests from your p1 DataStructureADTTest class 
 * can be copied and run here without changes.  There are some required changes.
 * 
 * TODO: 1. Copy your DataStructureADTTest methods to this class 
 *       2. Edit your tests to handle the changed names, types, and exception handling requirements
 *          for insert, remove, and get methods.
 *          
 * See @DataStructureADT for more details 
 *          
 * NOTE: this class has changed the visibility of dataStructureInstance
 * and added dataStructureInstance2, and dataStructureInstance3.
 * 
 * dataStructureInstance is still a DataStructure<String,String>.
 * dataStructureInstance2 is a DataStructure<Integer,String>.
 * dataStructureInstance3 is a DataStructure<Integer,Integer>.
 * DO NOT CHANGE THE TYPES, NAMES, OR VISIBLITY OF THOSE FIELDS
 * 
 * @author Debra Deppeler (deppeler@cs.wisc.edu)
 */
abstract class DataStructureADTTest {

	// CHANGED FROM P1: fields are protected (so they may be accessed from sub-classes)
	protected DataStructureADT<String,String> dataStructureInstance;

	// ADDED FROM P1: added another dataStructureInstance type <Integer,String>
	protected DataStructureADT<Integer,String> dataStructureInstance2;


	// CHANGED FROM P1: methods are protected (so they may be accessed from sub-classes)
	protected abstract DataStructureADT<String,String> createInstance();

	// ADDED FROM P1: added method to create another dataStructureInstance type <Integer,String> 
	protected abstract DataStructureADT<Integer,String> createInstance2();

	@BeforeAll
	static void setUpBeforeClass() {
		// UNUSED - may be removed if not using
	}

	@AfterAll
	static void tearDownAfterClass() {
		// UNUSED - may be removed if not using
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
		dataStructureInstance2 = createInstance2();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
		dataStructureInstance2 = null;
	}

	/**
	 * Provided Utility Method for comparing List<K> with other List<K>
	 * 
	 * Helper assert method for comparing lists of various element types.
	 * List must have the same number of elements, 
	 * be of the same type, and have elements that are the same
	 * in the same order.
	 * 
	 * @param list1<?> 
	 * @param list2<?>
	 */
	public void assertEquals(List<?> list1, List<?> list2) {
		assertTrue(list1!=null);
		assertTrue(list2!=null);
		assertTrue(list1.size()==list2.size());
		for (int i=0; i < list1.size(); i++ ) {
			assertTrue(list1.get(i).equals(list2.get(i)));			
		}
	}

	@Test
	void testDS00_empty_ds_size() {
		// It it works for one test, should work for all
		assertTrue(dataStructureInstance.numKeys()==0);
		assertTrue(dataStructureInstance2.numKeys()==0);
	}

	@Test
	void testDS01_insert_one_ds_size() throws IllegalNullKeyException {
		try {
			// It it works for one test, should work for all
			dataStructureInstance.insert("mykey1", "myvalue1");
			dataStructureInstance2.insert(2, "myvalue2");

			assertTrue(dataStructureInstance.numKeys()==1);
			assertTrue(dataStructureInstance2.numKeys()==1);

		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception DS01: "+e.getMessage());
		}
	}

	/**
	 * Check if after a node is inserted and removed from the ADT the size would be 0
	 */
	@Test
	void testDS02_after_insert_one_remove_one_size_is_0() {
		try {
			dataStructureInstance.insert(new String("Wade"), new String("James"));
			dataStructureInstance.remove(new String("Wade"));
			
			if (dataStructureInstance.numKeys() != 0)
				fail("data structure should be empty, with size=0, but size =" + dataStructureInstance.numKeys());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS02: " + e.getMessage());
		}
	}

	/**
	 * Check if an exception would be thrown if two nodes with the same key were inserted
	 */
	@Test
	void testDS03_duplicate_exception_is_thrown() {
		try {
			// Insert two data that have the same keys
			dataStructureInstance.insert(new String("Wang"), new String("Daisy"));
			dataStructureInstance.insert(new String("Wang"), new String("Wade"));
			//the code would reach this part if an Exception is not catched and then thrown
			fail("An exception should have been thrown when two data with the same key have been inserted");
		} catch (DuplicateKeyException e) {
			return;
		}	catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS03: " + e.getMessage());
		}
	}

	/**
	 * Check if a KeyNotFoundException is returned when a node with a key that 
	 * is not present is to be found
	 */
	@Test
	void testDS04_remove_throw_KeyNotFoundException_when_key_not_present() {
		try {
			dataStructureInstance.insert(new String("Kendrick"), new String("Lamar"));
			// false should be returned when a key is not present is to be removed
			dataStructureInstance.remove(new String("JCole"));
			fail("When a pair with a key that is not present in the ADT, a KeyNotFoundException " + ""
					+ "should be returned");
		} catch (KeyNotFoundException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS04: " + e.getMessage());
		}
	}

	/**
	 * Check if true would be returned when a present key is to be found
	 */
	@Test
	void testDS05_contains_returns_true_when_key_present() {
		try {
			dataStructureInstance.insert(new String("Post"), new String("Malone"));
			
			if (!dataStructureInstance.contains(new String("Post")))
				fail("The contain method should return true when a pair with present key is to be found");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS05: " + e.getMessage());
		}
	}

	/**
	 * Check if false would be returned when a key that is not present is to be found
	 */
	@Test
	void testDS06_contains_returns_false_when_key_not_present() {
		try {
			dataStructureInstance.insert("Lil", "Pump");
			
			if (dataStructureInstance.contains(new String("LilPeep")))
				fail("The contain method should return false when a pair with the key that is not present is to be found");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS06: " + e.getMessage());
		}
	}

	/**
	 * Check if 500 nodes could be inserted in the ADT and if the size would be updated to 500
	 */
	@Test
	void testDS07_after_insert_500_size_is_500_and_remove_all_size_is_0() {
		try {
			for (int i = 0; i < 500; i++) {
				dataStructureInstance.insert(new String("" + i), new String("" + i));
			}
			
			if (dataStructureInstance.numKeys() != 500) 
				fail("After 500 nodes are inserted, the data structure should have had a size 500, but the size is " 
						+ dataStructureInstance.numKeys());
			
			for (int i = 0; i < 500; i++) {
				dataStructureInstance.remove("" + i);
			}
			
			if (dataStructureInstance.numKeys() != 0)
				fail("After all of the 500 nodes are removed, the data structure should have a size of 0,"
						+ " but the size is " + dataStructureInstance.numKeys());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS07: " + e.getMessage());
		}
	}

	/**
	 * Check if true would be returned if a node with present key is to be removed
	 */
	@Test
	void testDS08_remove_returns_true_when_key_present() {
		try {
		dataStructureInstance.insert(new String("Lil"), new String("Wayne"));
		dataStructureInstance.insert(new String("Kodak"), new String("Black"));
		
		if (!dataStructureInstance.remove(new String("Kodak")))
			fail("When the key is present, true should be returned when it is to be removed");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS08: " + e.getMessage());
		}
	}

	/**
	 * Check if the right values are retrieved by get method
	 */
	@Test
	void testDS09_get_returns_expected_value() {
		try {
		dataStructureInstance.insert(new String("Travis"), new String("Scott"));
		dataStructureInstance.insert(new String("21"), new String("Savage"));
		dataStructureInstance.insert(new String("Kanye"), new String("West"));
		dataStructureInstance.insert(new String("Marshall"), new String("Mathers"));

		String value1 = dataStructureInstance.get("Travis");
		String value2 = dataStructureInstance.get("Kanye");

		if (!(value1.equals("Scott") && value2.equals("West")))
			fail("The values returned by the get method for particular keys are not as expected");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS09: " + e.getMessage());
		}
	}

	/**
	 * Check if a KeyNotFoundException would be found if a key that is not present is
	 * to be retrieved
	 */
	@Test
	void testDS10_get_throws_KeyNotFoundException_when_key_not_present() {
		try {
			dataStructureInstance.insert(new String("Travis"), new String("Scott"));
			dataStructureInstance.insert(new String("21"), new String("Savage"));
			dataStructureInstance.insert(new String("Kanye"), new String("West"));
			dataStructureInstance.insert(new String("Marshall"), new String("Mathers"));
			
			dataStructureInstance.get("Lil");
			
		} catch (KeyNotFoundException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception DS10: " + e.getMessage());
		}
	}

	/**
	 * Check if an IllegalNullKeyException would be thrown if a null key is passed in as an argument
	 */
	@Test
	void testDS11_null_keys_throw_exception() {
		try {
			dataStructureInstance.insert(null, new String("null"));
			fail("An IllegalArgumentException should have been thrown when the key of the node to be inserted is null");
		} catch (IllegalNullKeyException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception DS11: " + e.getMessage());
		}
	}
	
	/**
	 * Check if an IllegalNullKeyException would be thrown when a null key is passed in to 
	 * contains() method
	 */
	@Test
	void testDS12_contains_throw_IllegalNullKeyException() {
		try {
		dataStructureInstance2.insert(new Integer(3), "Wade" );
		dataStructureInstance2.insert(new Integer(6), "James");
		dataStructureInstance2.insert(new Integer(1), "Bosh");
		
		dataStructureInstance2.contains(null);
		fail("An IllegalNullKeyException should have been thrown when a null key is passed in");
		} catch (IllegalNullKeyException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception DS12: " + e.getMessage());
		}
	}

}
