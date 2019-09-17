  //////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AVL
// Files:           AVL.java
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
/**
 * This class implement an AVL Tree that will automatically remain balanced as nodes are inserted or removed
 * @author Congkai Tan
 *
 * @param <K> - the key of nodes
 * @param <V> - values contained in each node
 */
public class AVL<K extends Comparable<K>,V> extends BST<K, V> {
	/* (non-Javadoc)
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		//AVLInsertHelper(this.root, key, value);
		super.insert(key, value);
		this.root = reBalance(this.root);
	}
	
	/* (non-Javadoc)
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		boolean result = super.remove(key);
		this.root = reBalance(this.root);
		return result;
	}
	/**
	 * The helper method that helps update the balance factor of a node
	 * @param node - the node whose balance factor is to be updated
	 * @return balance factor of the node
	 */
	private int balanceFactor(BSTNode<K, V> node) {
		return getHeightHelper(node.left) - getHeightHelper(node.right);
	}
	
	/**
	 * the helper method that helps rebalance the tree by checking balance factor of each node and using
	 * the four defined basic rotation
	 * @param root - the root of current subtree
	 * @return the root of the subtree that has been rebalanced
	 */
	private BSTNode<K, V> reBalance(BSTNode<K, V> root) {
		if (root == null)
			return null;
		root.left = reBalance(root.left);
		root.right = reBalance(root.right);
		// identify the type of rotation needed
		if (balanceFactor(root) > 1) {
			if (balanceFactor(root.left) > 0) {
				root = rightRotate(root);
			} else {
				root = leftRightRotate(root);
			}
		} else if (balanceFactor(root) < -1) {
			if (balanceFactor(root.right) < 0) {
				root = leftRotate(root);
			} else {
				root = rightLeftRotate(root);
			}
		}
		// Check if the current subtree has been rebalanced, and balance it again if it still remains unbalanced
		if (balanceFactor(root) > 1 || balanceFactor(root) < -1)
			root = reBalance(root);
		return root;
	}
	
	/**
	 * Helper method to act basic rightRotate
	 * @param grandParent - the largest node among the three in rotation
	 * @return the grandParent node
	 */
	private BSTNode<K, V> rightRotate(BSTNode<K, V> grandParent) {
		BSTNode<K, V> parent = grandParent.left;
		// Save the right child of parent node
		grandParent.left = parent.right;
		// right-rotate grandParent node
		parent.right = grandParent;
		return parent;
	}

	/**
	 * Helper method to act basic leftRotate
	 * @param grandParent - the largest node among the three in rotation
	 * @return the grandParent node
	 */
	private BSTNode<K, V> leftRotate(BSTNode<K, V> grandParent) {
		BSTNode<K, V> parent = grandParent.right;
		// Save the left child of parent node
		grandParent.right = parent.left;
		// left-rotate grandParent node
		parent.left = grandParent;
		return parent;
	}
	
	/**
	 * Helper method to act basic rightLeftRotate
	 * @param grandParent - the largest node among the three in rotation
	 * @return the grandParent node
	 */
	private BSTNode<K, V> rightLeftRotate(BSTNode<K, V> grandParent) {
		grandParent.right = rightRotate(grandParent.right);
		grandParent = leftRotate(grandParent);
		return grandParent;
	}
	
	/**
	 * Helper method to act basic leftRightRotate
	 * @param grandParent - the largest node among the three in rotation
	 * @return the grandParent node
	 */
	private BSTNode<K, V> leftRightRotate(BSTNode<K, V> grandParent) {
		grandParent.left = leftRotate(grandParent.left);
		grandParent = rightRotate(grandParent);
		return grandParent;
	}
	
	

}
