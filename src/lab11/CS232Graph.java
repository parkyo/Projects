package lab11;

import java.util.ArrayList;

/**
 * Generic Graph interface that defines the operations supported by all of the
 * implementations of the Graph ADT.
 * 
 * Each vertex and each edge in the graph can have an object associated with it.
 * Each vertex can also have an integer mark associated with it. The marks are
 * useful for graph algorithms, which frequently mark vertices as visited or
 * unvisitied. Constants are provided in this interface for these two special
 * marks.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 27, 2016
 */
public interface CS232Graph<V, E> {

	/**
	 * Constant for marking vertices as unvisitied.
	 */
	public static final int UNVISITED = 0;

	/**
	 * Constant for marking vertices as visited.
	 */
	public static final int VISITED = 1;

	/**
	 * Get the number of vertices contained in this graph.
	 * 
	 * @return the number of vertices in the graph.
	 */
	public int numVertices();

	/**
	 * Get the number of edges contained in this graph.
	 * 
	 * @return the number of edges in the graph.
	 */
	public int numEdges();

	/**
	 * Get the object associated with a specified vertex.
	 * 
	 * @param v
	 *            the vertex.
	 * @return the object associated with the vertex.
	 * @throws IndexOutOfBoundsException
	 *             if v is not a valid vertex.
	 */
	public V getVertexObject(int v);

	/**
	 * Set the object associated with a specified vertex.
	 * 
	 * @param v
	 *            the vertex
	 * @param value
	 *            the object to be associated with the vertex.
	 * @throws IllegalArgumentException
	 *             if v is not a valid vertex.
	 */
	public void setVertexObject(int v, V value);

	/**
	 * Get the mark associated with a specified vertex.
	 * 
	 * @param v
	 *            the vertex.
	 * @return the mark associated with the vertex.
	 * @throws IllegalArgumentException
	 *             if v is not a valid vertex.
	 */
	public int getVertexMark(int v);

	/**
	 * Set the mark associated with a vertex.
	 * 
	 * @param v
	 *            the vertex.
	 * @param mark
	 *            the mark for the vertex.
	 * @throws IllegalArgumentException
	 *             if v is not a valid vertex.
	 */
	public void setVertexMark(int v, int mark);

	/**
	 * Add a specified edge to the graph. The specified value is associated with
	 * the edge. If the edge already exists in the graph then the specified
	 * value replaces the existing value.
	 * 
	 * @param v1
	 *            the start vertex for the edge.
	 * @param v2
	 *            the end vertex for the edge.
	 * @param value
	 *            the object associated with the edge.
	 * @throws IllegalArgumentException
	 *             if v1 or v2 is not a valid vertex, if v1 == v2, or if value is null.
	 */
	public void addEdge(int v1, int v2, E value);

	/**
	 * Get the object associated with an edge or null if the specified edge does
	 * not exist.
	 * 
	 * @param v1
	 *            the start vertex for the edge.
	 * @param v2
	 *            the end vertex for the edge.
	 * @return the object associated with the edge or null if the edge does not
	 *         exist.
	 * @throws IllegalArgumentException
	 *             if v1 or v2 is not a valid vertex.
	 */
	public E getEdge(int v1, int v2);

	/**
	 * Remove the specified edge from the graph.
	 * 
	 * @param v1
	 *            the start vertex for the edge.
	 * @param v2
	 *            the end vertex for the edge.
	 * @return the object associated with the edge.
	 * @throws IllegalArgumentException
	 *             if v1 or v2 is not a valid vertex.
	 */
	public E removeEdge(int v1, int v2);

	/**
	 * Get a list of the neighbors of a specified vertex.
	 * 
	 * @param v
	 *            the vertex for which to get the neighbors.
	 * @return a list of v's neighbors.
	 * @throws IllegalArgumentException
	 *             if v is not a valid vertex.
	 */
	public ArrayList<Integer> getNeighbors(int v);
}
