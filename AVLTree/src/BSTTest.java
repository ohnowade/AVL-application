//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BSTTest
// Files:           BSTTest.java
// Course:          CS 400 SP19
//
// Author:          Congkai Tan
// Email:           ctan46@wisc.edu
// Lecturer's Name: Deb Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class provides tests for BST class
 * @author Congkai Tan
 *
 */
public class BSTTest extends DataStructureADTTest {

	BST<String,String> bst;
	BST<Integer,String> bst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// The setup must initialize this class's instances 
		// and the super class instances.
		// Because of the inheritance between the interfaces and classes,
		// we can do this by calling createInstance() and casting to the desired type
		// and assigning that same object reference to the super-class fields.
		dataStructureInstance = bst = createInstance();
		dataStructureInstance2 = bst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = bst = null;
		dataStructureInstance2 = bst2 = null;
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected BST<String, String> createInstance() {
		return new BST<String,String>();
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected BST<Integer, String> createInstance2() {
		return new BST<Integer,String>();
	}

	/**
	 * Test that empty trees still produce a valid but empty traversal list
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_001_empty_traversal_orders() {
		try {

			List<String> expectedOrder = new ArrayList<String>();

			// Get the actual traversal order lists for each type		
			List<String> inOrder = bst.getInOrderTraversal();
			List<String> preOrder = bst.getPreOrderTraversal();
			List<String> postOrder = bst.getPostOrderTraversal();
			List<String> levelOrder = bst.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			/*
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);
			*/

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: "+e.getMessage());
		}

	}

	/**
	 * Test that trees with one key,value pair produce a valid traversal lists
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_002_check_traversals_after_insert_one() {

		try {

			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			bst2.insert(10,"ten");
			if (bst2.numKeys()!=1) 
				fail("added 10, size should be 1, but was "+bst2.numKeys());

			List<Integer> inOrder = bst2.getInOrderTraversal();
			List<Integer> preOrder = bst2.getPreOrderTraversal();
			List<Integer> postOrder = bst2.getPostOrderTraversal();
			List<Integer> levelOrder = bst2.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			/*
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);
			*/

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 002: "+e.getMessage());
		}

	}


	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_003_check_inOrder_for_balanced_insert_order() {
		// insert 20-10-30 BALANCED
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 003: "+e.getMessage());
		}
	}

	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Pre-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_004_check_preOrder_for_balanced_insert_order() {
		// insert 20-10-30 balanced
		try {
			bst2.insert(20, "J.Cole");
			bst2.insert(10, "Kendrick Lamar");
			bst2.insert(30, "Drake");

			// Expected pre-order 20-10-30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);
			expectedOrder.add(10);
			expectedOrder.add(30);

			// Get Pre-Order and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: " + e.getMessage());
		}
	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Post-Order traversal order: 10-30-20
	 */
	@Test
	void testBST_005_check_postOrder_for_balanced_insert_order() {
		// insert 20-10-30 balanced
		try {
			bst2.insert(20, "Dwyane Wade");
			bst2.insert(10, "LeBron James");
			bst2.insert(30, "Chris Bosh");

			// Expected post-order 10-30-20
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			expectedOrder.add(30);
			expectedOrder.add(20);

			// Get Post-Order and check
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 005: " + e.getMessage());
		}
	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Level-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_006_check_levelOrder_for_balanced_insert_order() {
		try {
			// insert 20-10-30 balanced
			bst2.insert(20, "Challenger");
			bst2.insert(10, "Mustang");
			bst2.insert(30, "Camaro");

			// Expected level-order 20-10-30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);
			expectedOrder.add(10);
			expectedOrder.add(30);

			// Get level-order and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 006: " + e.getMessage());
		}
	}

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_007_check_inOrder_for_not_balanced_insert_order() {
		// insert 10-20-30 un-balanced
		try {
			bst2.insert(10, "Offset");
			bst2.insert(20, "Quavo");
			bst2.insert(30, "Takeoff");

			// Expected in-order 10-20-30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			expectedOrder.add(20);
			expectedOrder.add(30);

			// Get in-order and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 007: " + e.getMessage());
		}
	}

	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Pre-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_008_check_preOrder_for_not_balanced_insert_order() {
		// insert 10-20-30 un-balanced
		try {
			bst2.insert(10, "Jay-Z");
			bst2.insert(20, "Eminem");
			bst2.insert(30, "Nas");

			// Expected pre-order 10-20-30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			expectedOrder.add(20);
			expectedOrder.add(30);

			// Get pre-order and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 008: " + e.getMessage());
		}
	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Post-Order traversal order: 30-20-10
	 */
	@Test
	void testBST_009_check_postOrder_for_not_balanced_insert_order() {
		// insert 10-20-30 un-balanced
		try {
			bst2.insert(10, "Curry");
			bst2.insert(20, "Thompson");
			bst2.insert(30, "Durant");

			// Expected post-order 30-20-10
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(30);
			expectedOrder.add(20);
			expectedOrder.add(10);
			
			// Get post-order and check
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 009: " + e.getMessage());
		}
	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Level-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
		// insert 10-20-30 un-balanced
		try {
			bst2.insert(10, "Offset");
			bst2.insert(20, "Quavo");
			bst2.insert(30, "Takeoff");

			// Expected level-order 10-20-30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			expectedOrder.add(20);
			expectedOrder.add(30);

			// Get level-order and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			assertEquals(expectedOrder, actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 010" + e.getMessage());
		}
	}


	// TODO: Add your own tests

	// Add tests to make sure that get and remove work as expected.
	/**
	 * Test that the get() method returns the correct value with the key passed in
	 */
	@Test
	void testBST_011_get_right_value() {
		// insert "Yellow"-"Iridescent"-"Radioactive"-"Sugar" un-balanced
		try {
			bst.insert("Yellow", "Coldplay");
			bst.insert("Iridescent", "Linkin Park");
			bst.insert("Radioactive", "Imagine Dragons");
			bst.insert("Sugar", "Maroon5");
			
			// Expected value of the node with the key "Iridescent"
			String expectedVal = "Linkin Park";
			
			// Get value by get() method and check
			String actualVal = bst.get("Iridescent");
			if (!expectedVal.equals(actualVal))
				fail("The value retrieved by get() method is not as expected.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 011: " + e.getMessage());
		}
	}
	
	/**
	 * Test that the tree remains the right structure after a node with only one child is removed
	 */
	@Test
	void testBST_012_tree_is_right_after_remove_node_with_one_child() {
		// insert 30-24-35-3-41
		try {
			bst2.insert(30, "Stephen Curry");
			bst2.insert(24, "Kobe Bryant");
			bst2.insert(35, "Kevin Durant");
			bst2.insert(3, "Dwyane Wade");
			bst2.insert(41, "Dirk Nowitzki");
			// remove 35
			bst2.remove(35);
			
			// Check if node 30's right child is 41
			if (!bst2.getKeyOfRightChildOf(30).equals(new Integer(41)))
					fail("The structure of the tree does not remain right after a node with only one child is removed");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 012: " + e.getMessage());
		}
	}
	
	/**
	 * Test that the tree remains the right structure after a node with two children has been removed
	 */
	@Test
	void testBST_013_tree_is_right_after_remove_node_with_two_children() {
		// insert 32-21-33-1-24-23-98
		try {
			bst2.insert(32, "Magic Johnson");
			bst2.insert(21, "Tim Duncan");
			bst2.insert(33, "Alonzo Mourning");
			bst2.insert(1, "Tracy McGrady");
			bst2.insert(24, "Rick Barry");
			bst2.insert(23, "Michael Jordan");
			bst2.insert(98, "Dennis Rodman");
			// remove 21
			bst2.remove(21);
			
			// check if 32's left child is 23, the in-order successor of 21
			if (!bst2.getKeyOfLeftChildOf(32).equals(new Integer(23)))
				fail("The structure of the tree does not remain correct after a node with two children is removed");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 013: " + e.getMessage());
		}
	}
	
	/**
	 * Test that the tree remains the right structure after a leaf has been removed
	 */
	@Test
	void testBST_014_tree_is_right_after_remove_leaf() {
	// insert 32-21-33-1-24-23-98
			try {
				bst2.insert(32, "Magic Johnson");
				bst2.insert(21, "Tim Duncan");
				bst2.insert(33, "Alonzo Mourning");
				bst2.insert(1, "Tracy McGrady");
				bst2.insert(24, "Rick Barry");
				bst2.insert(23, "Michael Jordan");
				bst2.insert(98, "Dennis Rodman");
				// remove 21
				bst2.remove(98);
				
				// check if 33'right child is null
				if (bst2.getKeyOfRightChildOf(33) != null)
					fail("The structure of the tree does not remain correct after a node with two children is removed");
			} catch (Exception e) {
				e.printStackTrace();
				fail("Unexpected exception 014: " + e.getMessage());
			}
	}
	
	/**
	 * This method inserts 7 nodes to the tree and checks if height is as expected
	 */
	@Test
	void testBST_015_check_height() {
		try {
			bst2.insert(32, "Magic Johnson");
			bst2.insert(21, "Tim Duncan");
			bst2.insert(33, "Alonzo Mourning");
			bst2.insert(1, "Tracy McGrady");
			bst2.insert(24, "Rick Barry");
			bst2.insert(23, "Michael Jordan");
			bst2.insert(98, "Dennis Rodman");
			
			if (bst2.getHeight() != 4)
				fail("The height is not correct");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 014: " + e.getMessage());
		}
	}
	
	/**
	 * This test inserts 500 nodes and check if get() method could retrieve the value contained
	 * in every one of them
	 */
	@Test
	void testBST_016_insert_many_and_get_back_out() {
		// insert 100 nodes
		try {
			for (int i = 0; i < 500; i++) {
				bst2.insert(i, "value " + i);
			}
			System.out.println(bst2.getHeight());
			// check if every node's value could be retrieved correctly
			for (int i = 0; i < 500; i++) {
				if (!bst2.get(i).equals("value " + i))
					fail("The value retrieved by get() method is not correct");
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}
}