package lab09;

/**
 * Linked implementation of a AVL balanced binary search tree. The AVL tree
 * inherits the methods from the binary search tree. The add and remove methods
 * must must be overridden so that they maintain the BST Property. All of the
 * other methods work well as they are.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version March 24, 2016
 */
public class CS232LinkedAVLTree<K extends Comparable<K>, V> extends
		CS232LinkedBinarySearchTree<K, V> {

	/**
	 * Construct an empty AVL balanced binary search tree.
	 */
	public CS232LinkedAVLTree() {
		super();
	}

	/**
	 * Construct an AVL balanced binary search tree with a single node at the
	 * root.
	 * 
	 * @param key
	 *            the key for the root.
	 * @param value
	 *            the value for the root.
	 */
	public CS232LinkedAVLTree(K key, V value) {
		super(key, value);
	}

	/**
	 * Construct an AVL balanced binary search tree using the provided keys and
	 * values. The key,value pairs are inserted into the tree in level order. If
	 * the resulting tree does not satisfy the BST Property, then an
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
	public CS232LinkedAVLTree(K[] keys, V[] values) {
		super(keys, values);

		/*
		 * We need to explicitly set the heights and balances of the nodes
		 * because that is not done by the CS232BinarySearchTree constructor.
		 */
		setAllNodeHeightsAndBalances((AVLNode<K, V>) root);
	}

	private void setAllNodeHeightsAndBalances(AVLNode<K, V> subTreeRoot) {
		/*
		 * Do a post order traversal. Any leaf has its height set to 1 and
		 * balance set to 0. Any non-leaf has its height set to the height of
		 * its higher child + 1 and its balance set to the difference in height
		 * of its children.
		 */
		if (subTreeRoot != null) {
			setAllNodeHeightsAndBalances((AVLNode<K, V>) subTreeRoot.left);
			setAllNodeHeightsAndBalances((AVLNode<K, V>) subTreeRoot.right);

			subTreeRoot.height = computeNodeHeightFromChildren(subTreeRoot);
			subTreeRoot.balance = computeNodeBalance(subTreeRoot);
		}
	}

	/**
	 * Compute the height of a node as one more than the height of its highest
	 * child. This method assumes that the height of any child of node has
	 * already been set.
	 * 
	 * @param root
	 *            a non-null node.
	 * @return the height of the node.
	 */
	private int computeNodeHeightFromChildren(AVLNode<K, V> node) {
		return Math.max(getNodeHeight((AVLNode<K, V>) node.left),
				getNodeHeight((AVLNode<K, V>) node.right)) + 1;
	}

	/**
	 * Compute the balance of a node as the difference in height between its
	 * children. This method assumes that the height of any child of node has
	 * already been set.
	 * 
	 * @param root
	 *            a non-null node.
	 * @return the balance of node.
	 */
	private int computeNodeBalance(AVLNode<K, V> node) {
		return getNodeHeight((AVLNode<K, V>) node.left)
				- getNodeHeight((AVLNode<K, V>) node.right);
	}

	/**
	 * Get the height of a given node. If the node is null, we'll define the
	 * height as 0. This works with the getNodeHeightFromChildren method so that
	 * the height of a leaf node is set to 1.
	 */
	private int getNodeHeight(AVLNode<K, V> node) {
		if (node == null) {
			return 0;
		} else {
			return node.height;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void add(K key, V value) {
		// add to the AVL tree same as we add to a BST.
		AVLNode<K, V> nodeToAdd = getNewNode(key, value);
		addNodeToSubTree(root, nodeToAdd);
		size++;

		// The add may have unbalanced the tree, so rebalance from that node up.
		rebalance((AVLNode<K, V>) nodeToAdd.parent);
	}

	/*
	 * Rebalance the sub tree to so that its root has a balance factor of -1, 0,
	 * 1.
	 */
	private void rebalance(AVLNode<K, V> startNode) {
		/*
		 * Traverse up the tree from the given node, recompute the height and
		 * balance at every node along the way. Also if an encountered node is
		 * not balanced then perform the necessary rotations to rebalance that
		 * node.
		 */
		if (startNode != null) { // not at the tree root.
			AVLNode<K, V> parent = (AVLNode<K, V>) startNode.parent;

			startNode.height = computeNodeHeightFromChildren(startNode);
			startNode.balance = computeNodeBalance(startNode);

			if (!startNode.isBalanced()) {
				// Tree is unbalanced at subTreeRoot, so we need to rotate.

				if (startNode.isRightHeavy()) {
					AVLNode<K, V> right = (AVLNode<K, V>) startNode.right;
					if (right.isRightHeavy() || right.balance==0) {
						// Case RR: right child's right sub-tree is the problem
						rotateLeft(startNode);
					} else {
						// Case RL: right child's left sub-tree is the problem.
						rotateRight((AVLNode<K, V>) startNode.right);
						rotateLeft(startNode);
					}
				} else if (startNode.isLeftHeavy()) {
					AVLNode<K, V> left = (AVLNode<K, V>) startNode.left;
					if (left.isLeftHeavy() || left.balance==0) {
						// Case LL: left child's left sub-tree is the problem
						rotateRight(startNode);
					} else {
						// Case LR: left child's right sub-tree is the problem.
						rotateLeft((AVLNode<K, V>) startNode.left);
						rotateRight(startNode);
					}
				}
			}

			/*
			 * Move up to the parent and repeat. Note: For insert when we move
			 * up we will never encounter another unbalanced node, but we will
			 * update the heights and balance factors as we go. For remove, we
			 * may well need to do rotations at several points.
			 */
			rebalance(parent);
		}
	}

	/*
	 * Perform a left rotation about the subTreeRoot.
	 */
	private void rotateLeft(AVLNode<K, V> subTreeRoot) {
		// local variables reflect the notation used on the slides.
		AVLNode<K, V> x = subTreeRoot;
		AVLNode<K, V> xr = (AVLNode<K, V>) x.right;
		AVLNode<K, V> xrl = (AVLNode<K, V>) xr.left; // may be null.
		AVLNode<K, V> px = (AVLNode<K, V>) x.parent;

		// x becomes xr's left child.
		xr.left = x;
		x.parent = xr;

		// xrl becomes x's right child
		x.right = xrl;
		if (xrl != null) {
			xrl.parent = x;
		}

		// xr becomes root of sub tree
		if (px == null) {
			// we rotated about the root, so xr is new root.
			root = xr;
		} else {
			// set the appropriate pointer in parent to xr.

			if (px.left == x) {
				px.left = xr;
			} else {
				px.right = xr;
			}
		}
		xr.parent = px;

		// recompute heights and balances that changed.
		x.height = computeNodeHeightFromChildren(x);
		x.balance = computeNodeBalance(x);

		xr.height = computeNodeHeightFromChildren(xr);
		xr.balance = computeNodeBalance(xr);
	}

	private void rotateRight(AVLNode<K, V> subTreeRoot) {
		// local variables reflect the notation used on the slides.
		AVLNode<K, V> x = subTreeRoot;
		AVLNode<K, V> xl = (AVLNode<K, V>) x.left;
		AVLNode<K, V> xlr = (AVLNode<K, V>) xl.right; // may be null.
		AVLNode<K, V> px = (AVLNode<K, V>) x.parent;

		// x becomes xl's right child.
		xl.right = x;
		x.parent = xl;

		// xlr becomes x's left child
		x.left = xlr;
		if (xlr != null) {
			xlr.parent = x;
		}

		// xl becomes root of sub tree
		if (px == null) {
			// we rotated about the root, so xr is new root.
			root = xl;
		} else {
			// set the appropriate pointer in parent to xl.

			if (px.left == x) {
				px.left = xl;
			} else {
				px.right = xl;
			}
		}
		xl.parent = px;

		// recompute heights and balances that changed.
		x.height = computeNodeHeightFromChildren(x);
		x.balance = computeNodeBalance(x);

		xl.height = computeNodeHeightFromChildren(xl);
		xl.balance = computeNodeBalance(xl);
	}

	/**
	 * {@inheritDoc}
	 */
	public V remove(K key) {
		/*
		 * Remove the node using the standard BST remove operation.
		 * 
		 * Then we need to rebalance from the parent of the removed node to the
		 * root. Note that the removed node is the node with the key in the case
		 * of it having 0 or 1 children. But in the case where it has 2 children
		 * the node removed is the leftmost child in the right sub-tree of the
		 * node with the key.
		 */
		BTNode<K, V> nodeToRemove = getNodeWithKey((AVLNode<K, V>) root, key);

		V value = null;

		if (nodeToRemove != null) {
			BTNode<K, V> parentOfRemoved = (AVLNode<K, V>) nodeToRemove.parent;
			if (nodeToRemove.left != null && nodeToRemove.right != null) {
				// two children.

				// this could be the node being removed... but shouldn't be a problem.
				parentOfRemoved = findSmallestKey(nodeToRemove.right).parent;
			}

			value = super.remove(key);

			rebalance((AVLNode<K, V>) parentOfRemoved);
		}

		return value;
	}

	/*
	 * Get the node in the tree with the specified key. Note: If we inherit a
	 * working version of this method from the CS232LinkedBinaryTree class we
	 * don't have to rewrite it. But this isn't in that class because it was
	 * part of the homework.
	 */
	protected BTNode<K, V> getNodeWithKey(BTNode<K, V> root, K key) {
		if (root == null) {
			return null;
		} else if (key.compareTo(root.key) == 0) {
			return root;
		} else if (key.compareTo(root.key) < 0) {
			return getNodeWithKey(root.left, key);
		} else {
			return getNodeWithKey(root.right, key);
		}
	}

	/*
	 * Get the node in the tree with smallest key. Note: If we inherit a working
	 * version of this method from the CS232LinkedBinaryTree class we don't have
	 * to rewrite it. But this isn't in that class because it was part of the
	 * homework.
	private BTNode<K, V> findSmallestKey(BTNode<K, V> subTreeRoot) {
		if (subTreeRoot.left == null) {
			return subTreeRoot;
		} else {
			return findSmallestKey(subTreeRoot.left);
		}
	}

	private BTNode<K, V> findLargestKey(BTNode<K, V> subTreeRoot) {
		if (subTreeRoot.right == null) {
			return subTreeRoot;
		} else {
			return findLargestKey(subTreeRoot.right);
		}
	}
	/**
	 * Override the getNewNode method inherited from the
	 * CS232LinkedBinarySearchTree class (which inherited it from the
	 * CS232LinkedBinaryTree class) so that it returns an AVLNode instead of a
	 * BTNode. This will cause all of the relevant parts of the inherited code
	 * to use AVLNodes (e.g. the constructors and add method).
	 * 
	 * @param key
	 *            the key for the new node.
	 * @param value
	 *            the value for the new node.
	 * @return the new node
	 */
	
	
	private BTNode<K, V> findSmallestKey(BTNode<K, V> subTreeRoot) {
		if (subTreeRoot.left == null) {
			return subTreeRoot;
		} else {
			return findSmallestKey(subTreeRoot.left);
		}
	}

//	
	protected AVLNode<K, V> getNewNode(K key, V value) {
		return new AVLNode<K, V>(key, value);
	}

	/**
	 * Node class that adds information about the height and balance of the node
	 * that is necessary for the AVL tree.
	 */
	protected static class AVLNode<K, V> extends BTNode<K, V> {
		public int height;
		public int balance;

		public AVLNode(K key, V value) {
			super(key, value);
			height = 1;
			balance = 0;
		}

		public boolean isLeftHeavy() {
			return balance >= 1;
		}

		public boolean isRightHeavy() {
			return balance <= -1;
		}

		public boolean isBalanced() {
			return balance >= -1 && balance <= 1;
		}
	}
}
