//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BST
// Files:           BST.java
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
import java.util.ArrayList;  // allowed for creating traversal lists
import java.util.List;       // required for returning List<K>

/**
 * This class implements a binary search tree that can insert, remove, get nodes and check if a key
 * is presented. It is also able to generate different orders of traversal for its node and has the
 * information about the number of keys contained and height.
 * @author Congkai Tan
 *
 * @param <K> - key of nodes
 * @param <V> - values contained in each node
 */
public class BST<K extends Comparable<K>,V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K,V> root;
	protected int numKeys; // number of keys in BST

	// Must have a public, default no-arg constructor
	public BST() { 
		root = null;
		numKeys = 0;
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getPreOrderTraversal()
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		List<K> preOrder = new ArrayList<K>();
		preOrderHelper(preOrder, this.root);
		return preOrder;
	}

	/**
	 * A helper method that helps get the pre-order traversal of the tree
	 * @param preOrder - the list storing the pre-order traversal of the tree
	 * @param root - the root of current subtree
	 */
	private void preOrderHelper(List<K> preOrder, BSTNode<K, V> root) {
		if (root == null)
			return;
		preOrder.add(root.key);
		preOrderHelper(preOrder, root.left);
		preOrderHelper(preOrder, root.right);
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getPostOrderTraversal()
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		List<K> postOrder = new ArrayList<K>();
		postOrderHelper(postOrder, this.root);
		return postOrder;
	}

	/**
	 * A helper method that helps get the post-order traversal of the tree
	 * @param preOrder - the list storing the post-order traversal of the tree
	 * @param root - the root of current subtree
	 */
	private void postOrderHelper(List<K> postOrder, BSTNode<K, V> root) {
		if (root == null)
			return;
		postOrderHelper(postOrder, root.left);
		postOrderHelper(postOrder, root.right);
		postOrder.add(root.key);
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getLevelOrderTraversal()
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		List<K> levelOrder = new ArrayList<K>();
		levelOrderHelper(levelOrder, this.root);
		return levelOrder;
	}

	/**
	 * A helper method that helps get the post-order traversal of the tree
	 * @param preOrder - the list storing the post-order traversal of the tree
	 * @param root - the root of current subtree
	 */
	private void levelOrderHelper(List<K> levelOrder, BSTNode<K, V> root) {
		int height = this.getHeight();
		for (int i = 1; i <= height; i++) {
			addEachLevelHelper(levelOrder, i, this.root);
		}
	}
	
	/**
	 * The helper method for levelOrderHelper() to process each level of the 
	 * tree whose level-order traversal is to be generated
	 * @param levelOrder - the list storing the level-order traversal of the tree
	 * @param level - to keep track of how many node are there possibly at current level
	 * @param root - the root of current subtree
	 */
	private void addEachLevelHelper(List<K> levelOrder, int level, BSTNode<K, V> root) {
		if (root == null)
			return;
		if (level == 1) {
			levelOrder.add(root.key);
		} else if (level > 1){
			addEachLevelHelper(levelOrder, level - 1, root.left);
			addEachLevelHelper(levelOrder, level - 1, root.right);
		}
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getInOrderTraversal()
	 */
	@Override
	public List<K> getInOrderTraversal() {
		List<K> inOrder = new ArrayList<K>();
		inOrderHelper(inOrder, this.root);
		return inOrder;
	}

	/**
	 * A helper method that helps get the in-order traversal of the tree
	 * @param preOrder - the list storing the in-order traversal of the tree
	 * @param root - the root of current subtree
	 */
	private void inOrderHelper(List<K> inOrder, BSTNode<K, V> root) {
		if (root == null)
			return;
		inOrderHelper(inOrder, root.left);
		inOrder.add(root.key); 
		inOrderHelper(inOrder, root.right);
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// Check if the key passed in is null
		if (key == null)
			throw new IllegalNullKeyException();
		// If the key is valid, insert the node
		insertHelper(this.root, key ,value);
		// After the key is inserted, number of keys should be updated
		numKeys++;
	}

	/**
	 * A private helper method that helps insert a new node to the BST
	 * @param root - the root of the subtree
	 * @param key - the key of the node to be inserted
	 * @param value - the value of the node to be inserted
	 * @throws DuplicateKeyException
	 */
	private void insertHelper(BSTNode<K, V> root, K key, V value) throws DuplicateKeyException {
		// Check if the root of the tree is null
		if (root == null) {
			this.root = new BSTNode<K, V>(key, value, null, null);
		} else {
			// If the key is less than the key of the root, check if the node could be inserted as 
			// the left child of the root
			if (key.compareTo(root.key) < 0) {
				if (root.left == null) {
					root.left = new BSTNode<K, V>(key, value, null, null);
				} else {
					insertHelper(root.left, key, value);
				}
			} else if (key.compareTo(root.key) > 0) {
				// If the key is larger than the key of the root, check if the node could be inserted as 
				// the right child of the root
				if (root.right == null) {
					root.right = new BSTNode<K, V>(key, value, null, null);
				} else {
					insertHelper(root.right, key, value);
				}
			} else {
				// If the key is the same as the key of the root, a DuplicateKeyException should be thrown
				throw new DuplicateKeyException();
			}
		}

	}

	/* (non-Javadoc)
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// Check if the key is null first
		if (key == null)
			throw new IllegalNullKeyException();
		this.root = removeHelper(this.root, key);
		numKeys--;
		return true;
	}

	/**
	 * A helper method that helps delete a node in the tree
	 * @param root - the root of current subtree
	 * @param key - the key of the node to be removed
	 * @return - the rest part of the tree after the node has been removed
	 * @throws KeyNotFoundException
	 */
	private BSTNode<K, V> removeHelper(BSTNode<K, V> root, K key) throws KeyNotFoundException{
		// This indicates that the key has not been found, so a KeyNotFoundException should be thrown
		if (root == null)
			throw new KeyNotFoundException();
		// After the key is located
		// When the key has no child, it can just be deleted
		if (key.compareTo(root.key) == 0) {
			if (root.left == null && root.right == null) {
				return null;
			} 
			// When the key has only one child, the child should be connected to its parent
			else if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} 
			// If the key has two children, copy its in-order successor to it and delete its successor
			else {
				BSTNode<K, V> temp = inOrderSuccessor(root);
				root.key = temp.key;
				root.value = temp.value;
				root.right = removeHelper(root.right, temp.key);
				return root;
			}
		} else if (key.compareTo(root.key) < 0) {
			root.left = removeHelper(root.left, key);
			return root;
		} else {
			root.right = removeHelper(root.right, key);
			return root;
		}
	}

	/**
	 * A helper method that helps find the ni-order successor of the passed-in node
	 * @param node - the node whose successor is to be located
	 * @return the passed-in node's in-order successor
	 */
	private BSTNode<K, V> inOrderSuccessor(BSTNode<K, V> node) {
		BSTNode<K, V> temp = node.right;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}
	/* (non-Javadoc)
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		return getHelper(this.root, key).value;
	}

	/**
	 * A helper method that helps locate the node with the passed-in key
	 * @param root - the root of current subtree
	 * @param key - the key of the node to be found
	 * @return the node to be located
	 * @throws KeyNotFoundException
	 */
	private BSTNode<K, V> getHelper(BSTNode<K, V> root, K key) throws KeyNotFoundException {
		// if the key cannot be found, a KeyNotFoundException should be thrown
		if (root == null) 
			throw new KeyNotFoundException();
		if (key.compareTo(root.key) < 0) {
			return getHelper(root.left, key);
		} else if (key.compareTo(root.key) > 0) {
			return getHelper(root.right, key);
		} else {
			return root;
		}
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		try {
			getHelper(this.root, key);
			return true;
		} catch (KeyNotFoundException e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
		return numKeys;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyAtRoot()
	 */
	@Override
	public K getKeyAtRoot() {
		if (this.root == null)
			return null;
		return this.root.key;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		BSTNode<K, V> leftChild = getHelper(this.root, key).left;
		if (leftChild == null) {
			return null;
		} else {
			return leftChild.key;
		}
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		BSTNode<K, V> rightChild = getHelper(this.root, key).right;
		if (rightChild == null) {
			return null;
		} else {
			return rightChild.key;
		}
	}

	/* (non-Javadoc)
	 * @see BSTADT#getHeight()
	 */
	@Override
	public int getHeight() {
		int height = getHeightHelper(this.root);
		return height;
	}

	/**
	 * A helper method that helps find the height of a node
	 * @param root - the root of current subtree
	 * @return the height of the node
	 */
	protected int getHeightHelper(BSTNode<K, V> root) {
		if (root == null)
			return 0;
		return 1 + Math.max(getHeightHelper(root.left), getHeightHelper(root.right));
	}

}
