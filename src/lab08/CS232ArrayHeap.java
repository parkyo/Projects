package lab08;

/**
 * Implementation of the CS232PriorityQueue interface that uses a binary heap
 * with an array backing store.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version March 8, 2016
 */
public class CS232ArrayHeap<K extends Comparable<K>, V> implements
		CS232PriorityQueue<K, V> {

	/*
	 * NOTE: We could implement this directly on top of an array. However that
	 * would just mean reimplementing much of the work already done in
	 * ArrayList. In particular, by using the CS232ArrayList class we do not
	 * have to reimplement the array resizing operations.
	 */
	private CS232ArrayList<HeapNode<K,V>> tree;

	/**
	 * Construct a new empty CS232ArrayHeap.
	 */
	public CS232ArrayHeap() {
		tree = new CS232ArrayList<HeapNode<K,V>>();
	}

	/**
	 * Construct a new CS232ArrayHeap using the specified keys and values. The
	 * keys and values are added into the heap in level order.  If the keys do
	 * not appear in an order that represents a valid heap (as determined by
	 * their compareTo method), an IllegalArgumentException is thrown.
	 * 
	 * @param keys
	 *            the keys
	 * @param values
	 *            the values
	 * @throws IllegalArgumentException
	 *             if the keys and values do not have the same length, the keys
	 *             or the values are empty, or the keys are not specified in an
	 *             order that results in a valid heap.
	 */
	public CS232ArrayHeap(K[] keys, V[] values) {
		if (keys.length != values.length) {
			throw new IllegalArgumentException(
					"Length of keys and values must be the same.");
		}
		if (keys.length == 0) {
			throw new IllegalArgumentException(
					"keys and values must not be empty.");
		}

		// Add the key,value pairs to the tree.
		tree = new CS232ArrayList<HeapNode<K,V>>();
		for (int i = 0; i < keys.length; i++) {
			// add the node to the heap.
			tree.add(new HeapNode<K,V>(keys[i], values[i]));

			// verify that we have a valid heap!
			if (!checkHeapProperty()) {
					throw new IllegalArgumentException("Heap is not valid.");
			}
		}
	}

	/*
	 * Get the index where the left child of the node at index i is stored.
	 */
	private int getLeftChildIndex(int i) {
		return 2 * i + 1;
	}

	/*
	 * Get the index where the right child of the node at index i is stored.
	 */
	private int getRightChildIndex(int i) {
		return 2 * i + 2;
	}

	/*
	 * Get the index where the parent of the node at index i is stored.
	 */
	private int getParentIndex(int i) {
		return (i - 1) / 2;
	}
	
	/*
	 * Check if the node at index is a leaf node. A node is a leaf if both of
	 * its children are not in the tree (i.e. have an index that is >= than the
	 * number of node in the tree.
	 */
	private boolean isLeaf(int i) {
		int leftChildIndex = getLeftChildIndex(i);
		int rightChildIndex = getRightChildIndex(i);
		return leftChildIndex >= tree.size() && rightChildIndex >= tree.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void add(K key, V value) {
		/*
		 * Place the node at the end of the heap (i.e. in the first empty spot
		 * in level order). Because the heap is always a complete tree, and we
		 * are using the array based binary tree representation this is just the
		 * first empty element in the array.
		 * 
		 * This new node may not be in a proper location (i.e. it will be larger
		 * than its parent.) To fix this we "percolate" the newly added node up
		 * the tree.
		 */

		// Intentionally not implemented - see homework assignment.
		// throw new UnsupportedOperationException("Not yet implemented");

		// Create and add the node to the end of the tree.
		int addedNodeIndex = tree.size();
		HeapNode<K,V> addedNode = new HeapNode<K,V>(key, value);
		tree.add(addedNode);

		percolateUp(addedNodeIndex);
	}

	/*
	 * Percolate a node up the heap. A node is percolated when its key is larger
	 * than the key of its parent. The node is percolated by swapping its
	 * position in the heap with its parent node until its key is smaller than
	 * its parent's key. NOTE: "smaller" is determined by using he compareTo
	 * method in the key (which we know it has because the K type parameter is
	 * bounded to be a subtype of Comparable).
	 */
	private void percolateUp(int percolatingNodeIndex) {
		HeapNode<K,V> percolatingNode = tree.get(percolatingNodeIndex);
		int parentIndex = getParentIndex(percolatingNodeIndex);
		HeapNode<K,V> parent = tree.get(parentIndex);

		while (percolatingNode.key.compareTo(parent.key) > 0) {
			// key at node is larger than key at parent so keep percolating.

			swap(parentIndex, percolatingNodeIndex);

			/*
			 * Adjust so we use the new parent of the added node next time
			 * through the loop.
			 */
			percolatingNodeIndex = parentIndex;
			parentIndex = getParentIndex(percolatingNodeIndex);
			parent = tree.get(parentIndex);
		}
	}

	/*
	 * Helper method that swaps two elements of the tree.
	 */
	private void swap(int indexA, int indexB) {
		HeapNode<K,V> nodeA = tree.get(indexA);
		HeapNode<K,V> nodeB = tree.get(indexB);
		tree.set(indexA, nodeB);
		tree.set(indexB, nodeA);
	}

	/**
	 * {@inheritDoc}
	 */
	public V remove() {
		if (tree.size() == 0) {
			return null; // empty tree.
		} else {
			/*
			 * To remove to the top of the heap, we replace the root element
			 * with the last leaf in the tree. This will likely mean that the
			 * key at the root is not in the correct location (i.e. it will have
			 * children larger than it.) To fix this we let the key at the root
			 * "trickle down" the tree.
			 */

			/*
			 * Swap the last leaf to the root then remove the last leaf (i.e.
			 * the root). Save the value from the root so we can return it at
			 * the end.
			 */
			swap(0, tree.size() - 1);
			V maxVal = tree.remove(tree.size() - 1).value;

			// Trickle the node down from the root...
			trickleDown(0);

			return maxVal;
		}
	}

	/*
	 * Trickle the node at tricklingNodeIndex down the tree. The node trickles
	 * down if it's key is smaller than the key of either child. To trickle down
	 * a node switches places with its larger child.
	 */
	private void trickleDown(int tricklingNodeIndex) {
		if (!isLeaf(tricklingNodeIndex)) {

			/*
			 * Get the index of child of the trickling node that has the larger
			 * key
			 */
			int largerChildIndex = getLargerChildIndex(tricklingNodeIndex);

			HeapNode<K, V> tricklingNode = tree.get(tricklingNodeIndex);
			HeapNode<K, V> largerChildNode = tree.get(largerChildIndex);

			/*
			 * If the key of the trickling node is smaller than the larger key
			 * among its children, then swap it with that child, and trickle
			 * down from there.
			 */
			if (tricklingNode.key.compareTo(largerChildNode.key) < 0) {
				swap(tricklingNodeIndex, largerChildIndex);
				trickleDown(largerChildIndex);
			}
		}
	}

	/*
	 * Get the index of the child with the larger key value. We can
	 * assume that there is at least one child.
	 */
	private int getLargerChildIndex(int parentIndex) {
		int leftChildIndex = getLeftChildIndex(parentIndex);
		int rightChildIndex = getRightChildIndex(parentIndex);

		if (leftChildIndex >= tree.size()) {
			return rightChildIndex; // no left child.
		} else if (rightChildIndex >= tree.size()) {
			return leftChildIndex; // no right child
		} else {
			// two children.
			HeapNode<K, V> leftChild = tree.get(leftChildIndex);
			HeapNode<K, V> rightChild = tree.get(rightChildIndex);
			if (leftChild.key.compareTo(rightChild.key) > 0) {
				// left child has larger key.
				return leftChildIndex;
			} else {
				// right child has larger key.
				return rightChildIndex;
			}
		}
	}


	/**
	 * Adjust the priority of the node with the specified value. This method
	 * throws an IllegalStateException if the heap is empty. This method has no
	 * effect, if the specified value does not appear in the heap.
	 * 
	 * @param value
	 *            the value for which to adjust the priority.
	 * @param newKey
	 *            the new key (i.e. priority) for the value.
	 * @throws IllegalStateException
	 *             if the heap is empty.
	 */
	public void adjustPriority(V value, K newKey) {
		// Intentionally not implemented - see homework assignment.
		// throw new UnsupportedOperationException("Not yet implemented");

		/*
		 * Find the node with the value (Hint - just search the array!), replace
		 * its key and then move the node to a valid location within the tree.
		 * Hint: if you factor out your percolate functionality from the add
		 * method in a way similar to how the trickleDown method was factored
		 * out of remove, you can use those to methods to move the node to a
		 * proper location.
		 */

		if (tree.size() == 0) {
			throw new IllegalStateException(
					"Cannot change priority in an empty tree");
		} else {
			// linear search for the value
			int foundIndex = -1;
			HeapNode<K,V> foundNode = null;
			int i = 0;
			while (i < tree.size() && foundNode == null) {
				HeapNode<K,V> cur = tree.get(i);
				if (cur.value.equals(value)) {
					foundIndex = i;
					foundNode = cur;
				}
				i++;
			}

			// if we found it, change the key and move the node.
			if (foundNode != null) {
				foundNode.key = newKey;
				trickleDown(foundIndex); // will do nothing if in place.
				percolateUp(foundIndex); // will do nothing if in place.

			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public V peek() {
		if (tree.size() == 0) {
			return null;
		} else {
			return tree.get(0).value;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return tree.size();
	}

	/**
	 * Helper method that checks that the heap property is preserved. That is
	 * that every parent's key is larger than its children's keys, as defined by
	 * the compareTo method. This is used by the tests to check the internal
	 * structure of the heap.
	 */
	boolean checkHeapProperty() {
		return checkHeapPropertyHelper(0);
	}

	private boolean checkHeapPropertyHelper(int nodeIndex) {
		/*
		 * Traverse the heap, checking the heap property at each node.
		 */
		if (nodeIndex >= tree.size()) {
			return true; // off tree.
		} else {
			/*
			 * Note: Works on root because (0-1)/2 = 0 so, root is compared to
			 * itself.
			 */
			HeapNode<K,V> node = tree.get(nodeIndex);
			int parentIndex = getParentIndex(nodeIndex);
			HeapNode<K,V> parentNode = tree.get(parentIndex);

			if (node.key.compareTo(parentNode.key) > 0) {
				return false; // child larger than parent
			} else {
				int lci = getLeftChildIndex(nodeIndex);
				int rci = getRightChildIndex(nodeIndex);

				return checkHeapPropertyHelper(lci)
						&& checkHeapPropertyHelper(rci);
			}
		}
	}

	/*
	 * Node used to hold the key,value pair at each location in the heap.
	 */
	private static class HeapNode<K,V> {
		public K key;
		public V value;

		public HeapNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
