package lab08;

import java.util.*;

/**
 * Linked implementation of a binary tree.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 25, 2016
 */
public class CS232LinkedBinaryTree<K, V> implements CS232BinaryTree<K, V> {

	// Note: Changed to public for this lab, so that my test code will run.
	public BTNode<K, V> root;
	protected int size;

	/**
	 * Construct a new empty LinkedBinaryTree.
	 */
	public CS232LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	/**
	 * Construct a new LinkedBinaryTree with the specified key, value pair
	 * stored at the root.
	 * 
	 * @param key
	 *            the key.
	 * @param value
	 *            the value.
	 * @throws IllegalArgumentException
	 *             if key is null.
	 */
	public CS232LinkedBinaryTree(K key, V value) {
		root = new BTNode<K, V>(key, value);
		size = 1;
	}
 
	/**
	 * Build a complete binary tree from the provided keys and values. The nodes
	 * of the tree filled in from the arrays in level order. So the first
	 * elements become the root, the second the left child of the root and the
	 * third the right child of the root, etc.
	 * 
	 * @param keys
	 *            the keys.
	 * @param vals
	 *            the values.
	 * @throws IllegalArgumentException
	 *             if keys and vals do not have the same length, or they are
	 *             empty.
	 */
	public CS232LinkedBinaryTree(K[] keys, V[] vals) {
		if (keys.length != vals.length) {
			throw new IllegalArgumentException(
					"Length of keys and vals must be the same.");
		}
		if (keys.length == 0) {
			throw new IllegalArgumentException(
					"keys and vals must not be empty.");
		}

		/*
		 * Construct the tree in level order by using a queue. The root is
		 * created from the first element of the arrays and is placed in the
		 * queue. Then while there are at least two elements left in the arrays,
		 * the node at the head of the queue is removed and the next two
		 * elements are assigned as its left and right children. Those children
		 * are placed into the queue, left first. If there is a single element
		 * left at the end, it is assigned as the left child of the node at the
		 * head of the queue.
		 */

		// Create the root.
		Queue<BTNode<K, V>> nodeQ = new LinkedList<BTNode<K, V>>();
		root = new BTNode<K, V>(keys[0], vals[0]);
		size++;
		nodeQ.add(root);

		// As long as there are 2 elements left.
		while (size < keys.length - 1) {
			BTNode<K, V> subRoot = nodeQ.remove();

			// Assign the next element as the left child.
			BTNode<K, V> leftChild = new BTNode<K, V>(keys[size], vals[size]);
			leftChild.parent = subRoot;
			subRoot.left = leftChild;
			nodeQ.add(leftChild);
			size++;

			// Assign the next element as the right child.
			BTNode<K, V> rightChild = new BTNode<K, V>(keys[size], vals[size]);
			rightChild.parent = subRoot;
			subRoot.right = rightChild;
			nodeQ.add(rightChild);
			size++;
		}

		// If there is one left, assign it as the left child.
		if (size < keys.length) {
			BTNode<K, V> subRoot = nodeQ.remove();

			BTNode<K, V> leftChild = new BTNode<K, V>(keys[size], vals[size]);
			leftChild.parent = subRoot;
			subRoot.left = leftChild;
			nodeQ.add(leftChild);
			size++;

			subRoot.right = null;
		}
	}

	/**
	 * Construct a new LinkedBinaryTree. The specified key, value pair will be
	 * stored at the root. The root of the LinkedBinaryTree left will be stored
	 * as the left child of the root of the new tree. The root of the
	 * LinkedBinaryTree right will be stored as the right child of the root of
	 * the new tree.
	 * 
	 * @param leftSubTree
	 *            the tree that should appear as the left child of the new tree.
	 * @param key
	 *            the key for the root of the new tree.
	 * @param value
	 *            the value for the root of the new tree.
	 * @param rightSubTree
	 *            the tree that should appear as the right child of the new
	 *            tree.
	 */
	public CS232LinkedBinaryTree(CS232LinkedBinaryTree<K, V> leftSubTree,
			K key, V value, CS232LinkedBinaryTree<K, V> rightSubTree) {
		// Intentionally not implemented - see homework assignment.
		// throw new UnsupportedOperationException("Not yet implemented");

		this(key, value); // make the root

		// link the left subtree into the root.
		root.left = leftSubTree.root;
		if (leftSubTree.root != null) {
			leftSubTree.root.parent = root;
		}

		// link the right subtree into the root.
		root.right = rightSubTree.root;
		if (rightSubTree.root != null) {
			rightSubTree.root.parent = root;
		}

		size = size + leftSubTree.size() + rightSubTree.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public K getRootKey() {
		return root.key;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public V getRootValue() {
		return root.value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(K key) {
		return subTreeContains(root, key);
	}

	/*
	 * Helper method: check if the subtree rooted at subTreeRoot contains the
	 * specified key.
	 */
	private boolean subTreeContains(BTNode<K, V> subTreeRoot, K key) {
		if (subTreeRoot == null) {
			return false; // empty tree can't contain key
		} else if (subTreeRoot.key.equals(key)) {
			return true; // root contains key!
		} else {
			/*
			 * The subtree contains the key if either the left subtree or the
			 * right subtree contains it.
			 */
			return subTreeContains(subTreeRoot.left, key)
					|| subTreeContains(subTreeRoot.right, key);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public V get(K key) {
		// Intentionally not implemented - see homework assignment.
		// throw new UnsupportedOperationException("Not yet implemented");

		BTNode<K, V> vNode = getNodeFromSubTree(root, key);
		if (vNode != null) {
			return vNode.value;
		} else {
			return null;
		}
	}

	/*
	 * Helper method: get the node containing the key from the subtree rooted at
	 * subTreeRoot.
	 */
	private BTNode<K, V> getNodeFromSubTree(BTNode<K, V> subTreeRoot, K key) {
		if (subTreeRoot == null) {
			return null; // can't get it from an empty tree.
		} else if (subTreeRoot.key.equals(key)) {
			return subTreeRoot; // root contains key
		} else {
			// try to get it from the left subtree.
			BTNode<K, V> node = getNodeFromSubTree(subTreeRoot.left, key);
			if (node != null) {
				// fount it in the left subtree.
				return node;
			}

			// Didn't find it in the left subtree so try the right.
			node = getNodeFromSubTree(subTreeRoot.right, key);
			return node;
		}
	}

	/**
	 * Add a new node with the specified key and value to the tree. The new node
	 * is added at the first empty child location encountered in level order.
	 * 
	 * @param key
	 *            the key.
	 * @param value
	 *            the value.
	 */
	public void add(K key, V value) {
		/*
		 * Use a queue to perform a level order traversal of the tree until a
		 * node with a missing child is found. At each node (starting with the
		 * root), if it has no left child, add the new node as the left child
		 * and be done. If the node has no right child, add the new node as the
		 * right child and be done. If the node has both children, add them each
		 * to the queue and repeat with the node from the head of the queue.
		 */

		BTNode<K, V> newNode = new BTNode<K, V>(key, value);

		if (root == null) {
			// Empty tree, the new node is the root.
			root = newNode;
		} else {
			Queue<BTNode<K, V>> nodeQ = new LinkedList<BTNode<K, V>>();
			nodeQ.add(root); // start with the root.

			boolean added = false;
			while (!added) {
				BTNode<K, V> cur = nodeQ.remove();
				if (cur.left == null) {
					// no left child, add here and be done.
					cur.left = newNode;
					newNode.parent = cur;
					added = true;
				} else if (cur.right == null) {
					// no right child, add here and be done.
					cur.right = newNode;
					newNode.parent = cur;
					added = true;
				} else {
					// node has both children, so put them in the queue.
					// and repeat.
					nodeQ.add(cur.left);
					nodeQ.add(cur.right);
				}
			}
		}

		size++;
	}

	/**
	 * Remove the node with the specified key from the tree. If the node
	 * containing the key is a leaf it is simply removed from the tree. If the
	 * node has one or more descendants, its rightmost deepest descendant (i.e.
	 * the last one encountered in a level order traversal) takes its place in
	 * the tree.
	 * 
	 * Note: Instead of removing the node, the key and value from the rightmost
	 * deepest descendant are moved to the node and then that descendant leaf
	 * node is removed.
	 * 
	 * @param key
	 *            the key of the node to be removed.
	 * @return the value of the removed node or null if no node is removed.
	 */
	public V remove(K key) {
		/*
		 * First find the node to remove (ntr). If it doesn't exist we can
		 * return null and be done. If the ntr does exist, start a levelorder
		 * traversal from the ntr and find the last node in that traversal. It
		 * will be the rightmost deepest descendant (rdd) of the ntr. Swap the
		 * key and value from the rdd into the ntr and unlink the rdd from the
		 * tree.
		 */

		BTNode<K, V> ntr = getNodeFromSubTree(root, key);
		if (ntr == null) {
			// node is not in the tree, done!
			return null;
		} else {
			/*
			 * Do the levelorder traverse from the node to be removed to find
			 * the rightmost deepest descendant (rdd).
			 */
			Queue<BTNode<K, V>> nodeQ = new LinkedList<BTNode<K, V>>();
			nodeQ.add(ntr); // start with the node to be replaced
			BTNode<K, V> rdd = null;
			while (nodeQ.size() != 0) {
				rdd = nodeQ.remove();
				if (rdd.left != null) {
					nodeQ.add(rdd.left);
				}
				if (rdd.right != null) {
					nodeQ.add(rdd.right);
				}
			}

			// Save the value to be returned.
			V replacedValue = ntr.value;

			// Move the key,value from rdd to the ntr.
			ntr.key = rdd.key;
			ntr.value = rdd.value;

			// Unlink the rdd from the tree.
			if (rdd.parent.left == rdd) {
				rdd.parent.left = null;
			} else {
				rdd.parent.right = null;
			}

			size--;

			return replacedValue;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visitPreOrder(CS232Visitor<K, V> visitor) {
		subTreeVisitPreOrder(root, visitor);
	}

	private void subTreeVisitPreOrder(BTNode<K, V> subTreeRoot,
			CS232Visitor<K, V> visitor) {
		if (subTreeRoot != null) {
			visitor.visit(subTreeRoot.key, subTreeRoot.value);
			subTreeVisitPreOrder(subTreeRoot.left, visitor);
			subTreeVisitPreOrder(subTreeRoot.right, visitor);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visitInOrder(CS232Visitor<K, V> visitor) {
		subTreeVisitInOrder(root, visitor);
	}

	private void subTreeVisitInOrder(BTNode<K, V> subTreeRoot,
			CS232Visitor<K, V> visitor) {
		if (subTreeRoot != null) {
			subTreeVisitInOrder(subTreeRoot.left, visitor);
			visitor.visit(subTreeRoot.key, subTreeRoot.value);
			subTreeVisitInOrder(subTreeRoot.right, visitor);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visitPostOrder(CS232Visitor<K, V> visitor) {
		subTreeVisitPostOrder(root, visitor);
	}

	private void subTreeVisitPostOrder(BTNode<K, V> subTreeRoot,
			CS232Visitor<K, V> visitor) {
		if (subTreeRoot != null) {
			subTreeVisitPostOrder(subTreeRoot.left, visitor);
			subTreeVisitPostOrder(subTreeRoot.right, visitor);
			visitor.visit(subTreeRoot.key, subTreeRoot.value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void visitLevelOrder(CS232Visitor<K, V> visitor) {
		/*
		 * A queue is used to perform a levelorder traversal. The root of the
		 * tree is placed in the queue. While the queue is not empty node at the
		 * head of the queue is visited. After visiting the node, its children
		 * if any are placed into the queue, left then right, to be visited
		 * later.
		 */

		Queue<BTNode<K, V>> nodeQ = new LinkedList<BTNode<K, V>>();
		nodeQ.add(root); // start at the root.

		while (nodeQ.size() > 0) {
			// visit the node at the head of the queue
			BTNode<K, V> cur = nodeQ.remove();
			visitor.visit(cur.key, cur.value);

			// add the left child, if any, to the queue.
			if (cur.left != null) {
				nodeQ.add(cur.left);
			}

			// add the right child, if any, to the queue.
			if (cur.right != null) {
				nodeQ.add(cur.right);
			}
		}
	}

	/**
	 * Count the number of leaf nodes in this tree.
	 * 
	 * @return
	 */
	public int countLeafNodes() {
		return countLeafNodes(root);
	}

	/*
	 * Helper method that counts the number of leaves in the subtree rooted at
	 * subTreeRoot.
	 */
	private int countLeafNodes(BTNode<K, V> subTreeRoot) {
		if (subTreeRoot == null) {
			return 0; // empty tree, so no leaves.
		} else if (subTreeRoot.isLeaf()) {
			return 1; // sub tree is only a leaf, so 1 leaf.
		} else {
			/*
			 * Otherwise, the result is the number of leaves in the left subtree
			 * plus the number of leaves in the right subtree.
			 */
			return countLeafNodes(subTreeRoot.left)
					+ countLeafNodes(subTreeRoot.right);
		}
	}

	/*
	 * Class that represents a node in a binary tree. Each node holds a key,
	 * value pair as well as references to its left and right children and its
	 * parent.
	 */
	// Note: Changed to public for this lab, so that my test code will run.
	public static class BTNode<K, V> {
		public K key;
		public V value;
		public BTNode<K, V> left;
		public BTNode<K, V> right;
		public BTNode<K, V> parent;

		public BTNode(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			parent = null;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		/*
		 * NOTE: The parent pointer isn't strictly necessary for some of the
		 * basic binary tree uses and operations. However, as this class serves
		 * as the basis for later work on Heaps and AVL Trees where parent
		 * pointers are necessary it is included here from the start. This
		 * allows the Heap and AVL Trees to inherit much of the functionality
		 * (e.g. traversals) from earlier implementations.
		 */
	}
}
