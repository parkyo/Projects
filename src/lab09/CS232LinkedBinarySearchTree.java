package lab09;

/**
 * Linked implementation of a binary search tree. The binary search tree
 * inherits the methods from the binary tree. The add and remove methods must
 * must be overridden so that they maintain the BST Property. The contains, get
 * and set methods are overridden to improve their performance by taking
 * advantage of the BST property. The inherited size and traversal methods work
 * well as they are.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version March 10, 2016
 */
public class CS232LinkedBinarySearchTree<K extends Comparable<K>, V> extends
		CS232LinkedBinaryTree<K, V> {

	/*
	 * NOTE: We inherit the size and root fields, and the BTNode class from the
	 * LinkedBinaryTree class because they were declared as protected in that
	 * class.
	 */

	/**
	 * Construct an empty binary search tree.
	 */
	public CS232LinkedBinarySearchTree() {
		super();
	}

	/**
	 * Construct a binary search tree with a single node at the root.
	 * 
	 * @param key
	 *            the key for the root.
	 * @param value
	 *            the value for the root.
	 */
	public CS232LinkedBinarySearchTree(K key, V value) {
		super(key, value);
	}

	/**
	 * Construct a binary search tree using the provided keys and values. The
	 * key,value pairs are inserted into the tree in level order. If the
	 * resulting tree does not satisfy the BST Property, then an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param keys
	 *            the keys.
	 * @param values
	 *            the values.
	 * @throws IllegalArgumentException
	 *             if the keys and values do not have the same length, the keys
	 *             or the values are empty, or the keys are not specified in an
	 *             order that results in a valid binary search tree.
	 */
	public CS232LinkedBinarySearchTree(K[] keys, V[] values) {
		super(keys, values);

		if (!checkBSTProperty()) {
			throw new IllegalArgumentException(
					"Key, Value pairs did not satisfy BST property.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(K key) {
		return containsHelper(root, key);
	}

	/*
	 * Recursive helper method that checks if the key can be found in the
	 * subtree rooted at subTreeRoot.
	 */
	private boolean containsHelper(BTNode<K, V> subTreeRoot, K key) {
		if (subTreeRoot == null) {
			return false; // off the tree.
		} else if (subTreeRoot.key.equals(key)) {
			return true; // found it.
		} else if (key.compareTo(subTreeRoot.key) < 0) {
			/*
			 * The key we are looking for is less than the key at the
			 * subTreeRoot so if it is in the tree it will be in the left
			 * subtree.
			 */
			return containsHelper(subTreeRoot.left, key);
		} else {
			/*
			 * The key we are looking for is greater than or equal to the key at
			 * the subTreeRoot so if it is in the tree it will be in the right
			 * subtree.
			 */
			return containsHelper(subTreeRoot.right, key);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public V get(K key) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public void set(K key, V value) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public void add(K key, V value) {
		BTNode<K, V> nodeToAdd = getNewNode(key, value);
		addNodeToSubTree(root, nodeToAdd);
		size++;
	}

	/*
	 * Add the nodeToAdd to the subtree rooted at subTreeRoot.
	 */
	protected void addNodeToSubTree(BTNode<K, V> subTreeRoot,
			BTNode<K, V> nodeToAdd) {
		if (subTreeRoot == null) {
			root = nodeToAdd;
		}
		else if (nodeToAdd.key.compareTo(subTreeRoot.key) < 0) {
			/*
			 * Key of the new node is less than the key at subTreeRoot so we are
			 * going to add the new node into the left sub tree.
			 */
			if (subTreeRoot.left == null) {
				/*
				 * The left subtree is empty, so make the new node the left
				 * child of the subtree root.
				 */
				subTreeRoot.left = nodeToAdd;
				nodeToAdd.parent = subTreeRoot;
			} else {
				/*
				 * The left subtree is not empty, so add the new node to that
				 * sub tree.
				 */
				addNodeToSubTree(subTreeRoot.left, nodeToAdd);
			}
		} else {
			/*
			 * The key of the new node is greater than or equal to the key at
			 * the subTreeRoot so we are going to add the new node to the right
			 * sub tree. This is exactly the complement of the approach used
			 * above.
			 */
			if (subTreeRoot.right == null) {
				subTreeRoot.right = nodeToAdd;
				nodeToAdd.parent = subTreeRoot;
			} else {
				addNodeToSubTree(subTreeRoot.right, nodeToAdd);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public V remove(K key) {
		// Intentionally not implemented - see homework assignment.
		//throw new UnsupportedOperationException("Not yet implemented");
		if (size == 0) {
			// Tree is empty, nothing to do.
			return null;
		} else {
			/*
			 * Handle the three cases:
			 * 
			 * 1. Node is a leaf: Detach it from its parent.
			 * 
			 * 2. Node has one child: Promote it. This is handled in separate
			 * cases for a left child and a right child.
			 * 
			 * 3. Node has two children: We don't actually remove the node.
			 * Instead we remove remove the node with smallest key from its
			 * right subtree ("skrst"). We then replace the key,value pair in
			 * the node being "removed" with those from the skrst.
			 */
			BTNode<K, V> nodeToRemove = getNodeWithKey(root, key);
			V valToReturn = nodeToRemove.value;

			if (nodeToRemove.isLeaf()) {
				/*
				 * Case 1: Node is a leaf node. Note: A close look at
				 * promoteNode, will show that this call just replaces the node
				 * with null.
				 */
				promoteNode(nodeToRemove, null);
			} else if (nodeToRemove.right == null) {
				/*
				 * Case 2 (left child): Node being removed has only a left
				 * child, so that child will be promoted and replace the removed
				 * node. NOTE: nodeToRemove.left != null does not work as the if
				 * condition here!
				 */
				promoteNode(nodeToRemove, nodeToRemove.left);
			} else if (nodeToRemove.left == null) {
				/*
				 * Case 2 (right child): Node being removed has only a right
				 * child, so that child will be promoted and replace the removed
				 * node.
				 */
				promoteNode(nodeToRemove, nodeToRemove.right);
			} else {
				// Case 3:

				/*
				 * Find the node with the smallest key in the right sub tree of
				 * the node being "removed".
				 */
				BTNode<K, V> skrst = findSmallestKey(nodeToRemove.right);

				/*
				 * Remove the node with the node with the smallest key. Note: we
				 * know that the skrst has only a right child, otherwise we
				 * would have continued on to its left child in findSmallestKey.
				 * So this recursive call will immediately hit one of the above
				 * base cases! Note: This also decreases the size of the tree so
				 * no need for a size-- in this case.
				 */
				remove(skrst.key);

				// Replace the key value pair in the node being "removed".
				nodeToRemove.key = skrst.key;
				nodeToRemove.value = skrst.value;
			}

			return valToReturn;
		}
	}

	private BTNode<K, V> getNodeWithKey(BTNode<K, V> subTreeRoot, K key) {
		if (subTreeRoot == null) {
			return null; // off the tree.
		} else if (subTreeRoot.key.equals(key)) {
			return subTreeRoot; // found it.
		} else if (key.compareTo(subTreeRoot.key) < 0) {
			/*
			 * The key we are looking for is less than the key at the
			 * subTreeRoot so if it is in the tree it will be in the left
			 * subtree.
			 */
			return getNodeWithKey(subTreeRoot.left, key);
		} else {
			/*
			 * The key we are looking for is greater than or equal to the key at
			 * the subTreeRoot so if it is in the tree it will be in the right
			 * subtree.
			 */
			return getNodeWithKey(subTreeRoot.right, key);
		}
	}

	/*
	 * Remove the nodeToRemove by promoting the nodeToPromote. The nodeToPromote
	 * must be a child of nodeToRemove. The end effect is that nodeToPromote
	 * takes the place of nodeToRemove in the tree.
	 */
	private void promoteNode(BTNode<K, V> nodeToRemove,
			BTNode<K, V> nodeToPromote) {
		
		if (nodeToRemove.parent == null) {  // root is being removed.
			root = nodeToPromote;
		}
		else {
			if (nodeToRemove.parent.left == nodeToRemove) {
				/*
				 * The node being removed is a left child, so make the node
				 * being promoted the left child of the removed node's parent.
				 */
				nodeToRemove.parent.left = nodeToPromote;
			} else if (nodeToRemove.parent.right == nodeToRemove) {
				/*
				 * The node being removed is a right child, so make the node to
				 * promoted the right child of the removed node's parent.
				 */
				nodeToRemove.parent.right = nodeToPromote;
			} else {
				throw new IllegalArgumentException(
						"Node being promoted is not a child of node being removed.");
			}
		}
		

		/*
		 * When a leaf node is being removed, the nodeToPromote will be null. In
		 * that case, its parent cannot be set.
		 */
		if (nodeToPromote != null) {
			nodeToPromote.parent = nodeToRemove.parent;
		}

		nodeToRemove.parent = null;
		
		size--;
	}

	/*
	 * Find and return the node with the smallest key in the sub tree rooted at
	 * subTreeRoot. Note: The smallest key will always be in the leftmost node
	 * of the subtree. So we just keep going left until we run out of nodes!
	 */
	private BTNode<K, V> findSmallestKey(BTNode<K, V> subTreeRoot) {
		if (subTreeRoot.left == null) {
			return subTreeRoot;
		} else {
			return findSmallestKey(subTreeRoot.left);
		}
	}


	/*
	 * Helper method that verifies the BST property of this tree by traversing
	 * the tree and verifying that at each node the key of the left child is <
	 * the key of the node and that the key of the right child is >= the key of
	 * the node.  This is used by the 
	 */
	boolean checkBSTProperty() {
		return checkBSTPropertyHelper(root);
	}

	private boolean checkBSTPropertyHelper(BTNode<K, V> subTreeRoot) {
		if (subTreeRoot == null) {
			return true; // off tree.
		} else {
			if (leftChildOK(subTreeRoot) && rightChildOK(subTreeRoot)) {
				return checkBSTPropertyHelper(subTreeRoot.left)
						&& checkBSTPropertyHelper(subTreeRoot.right);
			} else {
				return false;
			}
		}
	}

	private boolean leftChildOK(BTNode<K, V> node) {
		if (node.left == null) {
			return true;
		} else {
			// true if key at node is > key at left child.
			return node.key.compareTo(node.left.key) > 0;
		}
	}

	private boolean rightChildOK(BTNode<K, V> node) {
		if (node.right == null) {
			return true;
		} else {
			// true if key at node is <= key at right child.
			return node.key.compareTo(node.right.key) <= 0;
		}
	}
}
