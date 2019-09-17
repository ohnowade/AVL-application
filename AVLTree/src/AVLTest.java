//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AVLTest
// Files:           AVLTest.java
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;
import java.util.*;
//@SuppressWarnings("rawtypes")
/**
 * This class provide tests for AVL class
 * @author Congkai Tan
 *
 */
public class AVLTest extends BSTTest {

	AVL<String,String> avl;
	AVL<Integer,String> avl2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = bst = avl = createInstance();
		dataStructureInstance2 = bst2 = avl2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		avl = null;
		avl2 = null;
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected AVL<String, String> createInstance() {
		return new AVL<String,String>();
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected AVL<Integer, String> createInstance2() {
		return new AVL<Integer,String>();
	}

	/** 
	 * Insert three values in sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred.
	 */
	@Test
	void testAVL_001_insert_sorted_order_simple() {
		try {
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfRightChildOf(10).equals(20)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(30, "30");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 001: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values in reverse sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_002_insert_reversed_sorted_order_simple() {
		try {
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfLeftChildOf(30).equals(20)) 
				fail("avl insert to lef child of root does not work");
			
			avl2.insert(10, "10");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 002: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values so that a right-left rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_smallest_largest_middle_order_simple() {
		try {
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(30, "30");
			if (!avl2.getKeyOfRightChildOf(10).equals(30)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 003: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_004_insert_largest_smallest_middle_order_simple() {
		try {
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work");
			
			avl2.insert(10, "10");
			if (!avl2.getKeyOfLeftChildOf(30).equals(10)) 
				fail("avl insert to left child of root does not work");
			
			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 004: "+e.getMessage() );
		}
	}
	
	/**
	 * insert 7 nodes and keep track of the structure of the tree
	 */
	@Test
	void testAVL_005_insert_more_node() {
		try {
			// insert 32-21-1, and the re-balanced tree should have 21 as the root whose left 
			// and right children are 1 and 32 respectively, after a right rotation
			avl2.insert(32, "Magic Johnson");
			avl2.insert(21, "Tim Duncan");
			avl2.insert(1, "Tracy McGrady");
			if (!avl2.getKeyAtRoot().equals(21))
				fail("avl insert at root does not work");
			if (!avl2.getKeyOfLeftChildOf(21).equals(1))
				fail("avl insert to left child of root does not work");
			if (!avl2.getKeyOfRightChildOf(21).equals(32))
				fail("avl insert to right child of root does not work");
			
			// insert 33-98, and a left rotation would change 21's right child to 33,whose left
			// and right children are 32 and 98 respectively
			avl2.insert(33, "Alonzo Mourning");
			avl2.insert(98, "Dennis Rodman");
			if (!avl2.getKeyOfRightChildOf(21).equals(33))
				fail("avl insert to right child of root does not work");
			if (!avl2.getKeyOfRightChildOf(33).equals(98))
				fail("avl insert to right child of root does not work");
			
			// insert 24, a right-left rotation would make 32 the new root of the tree
			avl2.insert(24, "Rick Barry");
			if (!avl2.getKeyAtRoot().equals(32))
				fail("avl insert at root does not work");
			if (!avl2.getKeyOfLeftChildOf(32).equals(21))
				fail("avl insert to left child of root does not work");
			if (!avl2.getKeyOfRightChildOf(21).equals(24))
				fail("avl insert to right child of root does not work");

			// inserting 23 does not require any re-balancing operation
			avl2.insert(23, "Michael Jordan");
			if (!avl2.getKeyOfLeftChildOf(24).equals(23))
				fail("avl insert to left child of root does not work");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception 005: " + e.getMessage());
		}
	}
	
	/**
	 * This test inserts several nodes creating a balanced tree. Then it removes a node to make it unbalanced, and 
	 * check if the avl would be re-balanced
	 */
	@Test
	void testAVL_006_remove_remain_balanced() {
		try {
			// insert 5-3-6-9, which would make the tree balanced
			avl2.insert(5, "five");
			avl2.insert(3, "three");
			avl2.insert(6, "six");
			avl2.insert(9, "nine");
			
			// remove 3, which makes the tree unbalanced and a left rotation would be needed
			avl2.remove(3);
			
			// check the structure of the tree
			if (!avl2.getKeyAtRoot().equals(6))
				fail("avl insert at root does not work");
			if (!avl2.getKeyOfLeftChildOf(6).equals(5))
				fail("avl insert to left child of root does not work");
			if(!avl2.getKeyOfRightChildOf(6).equals(9))
				fail("avl insert to right child of root does not work");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 006: " + e.getMessage());
		}
	}
	
	/**
	 * This test inserts ten nodes with keys from less to largest and checks if the height
	 * is as expected
	 */
	@Test
	void testAVL_007_insert_10_check_height() {
		try {
			// insert 10 node with keys from smallest to largest which would make the tree unbalanced
			for (int i = 1; i <= 10; i++) {
				avl2.insert(i, "" + i);
			}
			
			// the expected height should be 4
			if (avl2.getHeight() != 4)
				fail("the correct height should be 4, but the height is " + avl2.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}
}
