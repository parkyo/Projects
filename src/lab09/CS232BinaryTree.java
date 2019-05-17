package lab09;

/**
 * Interface to be implemented by binary tree implementations.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 25, 2016
 */
public interface CS232BinaryTree<K, V> {

	/**
	 * Get the current size of the tree. The size of a tree is equal to the
	 * number of nodes (both leaf and internal) in the tree.
	 * 
	 * @return the size.
	 */
	public int size();

	/**
	 * Return true if the tree contains a node with the specified key.
	 * 
	 * @param key
	 *            the key.
	 * @return true if there is a node in the tree with the specified key. False
	 *         if not.
	 */
	public boolean contains(K key);

	/**
	 * Return the value of the node with the specified key (if any). If the
	 * specified key does not appear in the tree, return null.
	 * 
	 * @param key
	 *            the key
	 * @return the value associated with key, or null if the key does not appear
	 *         in the tree.
	 */
	public V get(K key);
	
	/**
	 * Set the value of the node with the specified key (if any).
	 * 
	 * @param key the key
	 * @param value the new value for the node.
	 * @throws NoSuchElementException if there is no node with the specified key.
	 */
	public void set(K key, V value);

	/**
	 * Add a node with the specified key, value pair to the tree.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void add(K key, V value);

	/**
	 * Remove the node with the specified key from the tree.
	 * 
	 * @param key
	 *            the key
	 * @return the value associated with the key, or null if the key does not
	 *         appear in the tree.
	 */
	public V remove(K key);

	/**
	 * Visit the nodes of the tree in preorder and invoke the specified visitor
	 * on each node.
	 * 
	 * @param visitor
	 *            the visitor.
	 */
	public void visitPreOrder(CS232Visitor<K, V> visitor);

	/**
	 * Visit the nodes of the tree in inorder and invoke the specified visitor
	 * on each node.
	 * 
	 * @param visitor
	 *            the visitor.
	 */
	public void visitInOrder(CS232Visitor<K, V> visitor);

	/**
	 * Visit the nodes of the tree in postorder and invoke the specified visitor
	 * on each node.
	 * 
	 * @param visitor
	 *            the visitor.
	 */
	public void visitPostOrder(CS232Visitor<K, V> visitor);

	/**
	 * Visit the nodes of the tree in levelorder and invoke the specified
	 * visitor on each node.
	 * 
	 * @param visitor
	 *            the visitor.
	 */
	public void visitLevelOrder(CS232Visitor<K, V> visitor);
}
