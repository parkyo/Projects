package lab09;

/**
 * Interface to be implemented by Visitors that act on the key, value pairs 
 * stored in nodes in binary trees.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Feb 25, 2016
 */
public interface CS232Visitor<K, V> {

	/**
	 * The visit method will be invoked at each node in a tree. The order in
	 * which the nodes are visited is determined by the method that is used
	 * (e.g. visitPreOrder, visitInOrder, visitPostOrder, visitLevelOrder)
	 * 
	 * @param key
	 *            the key stored at the node.
	 * @param value
	 *            the value stored at the node.
	 */
	public void visit(K key, V value);
}
