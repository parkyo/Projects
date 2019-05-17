package lab11;

/**
 * An abstract base class that provides some functionality common to all
 * implementations of the Graph ADT. In particular this class provides
 * management of the vertex objects and vertex markings as well as the basic
 * size functionality (numVertices and numEdges).
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 28, 2016
 * 
 * @param <V>
 *            the type of object associated with the vertices.
 * @param <E>
 *            the type of object associated with the edges.
 */
public abstract class CS232AbstractGraph<V, E> implements CS232Graph<V, E> {

	protected V[] vertexObjects;
	protected int[] vertexMarks;

	protected int numEdges;

	/**
	 * Initialize the fields managed by this abstract base class that keep track
	 * of the vertex objects, vertex markings and the number of edges in the
	 * graph. All edges are initially marked unvisited and initially have no
	 * object associated with them.
	 * 
	 * @param numVertices
	 *            the number of vertices in the graph.
	 */
	public CS232AbstractGraph(int numVertices) {
		vertexObjects = (V[]) new Object[numVertices];
		vertexMarks = new int[numVertices];
		numEdges = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numVertices() {
		return vertexObjects.length;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numEdges() {
		return numEdges;
	}

	/**
	 * {@inheritDoc}
	 */
	public V getVertexObject(int v) {
		checkVertex(v);
		return vertexObjects[v];
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVertexObject(int v, V value) {
		checkVertex(v);
		vertexObjects[v] = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getVertexMark(int v) {
		checkVertex(v);
		return vertexMarks[v];
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVertexMark(int v, int mark) {
		checkVertex(v);
		vertexMarks[v] = mark;
	}

	/**
	 * Helper method to check if a vertex is valid.
	 * 
	 * @param v
	 *            the vertex
	 * @throws IllegalArgumentException
	 *             if the specified vertex is not valid.
	 */
	protected void checkVertex(int v) {
		if (v < 0 || v >= numVertices()) {
			throw new IllegalArgumentException("Invalid vertex: " + v);
		}
	}

	/**
	 * Helper method to check if a pair of vertices are both valid.
	 * 
	 * @param v1
	 *            one vertex
	 * @param v2
	 *            another vertex
	 * @throws IllegalArgumentException
	 *             if either of the vertices is not valid.
	 */
	protected void checkVertices(int v1, int v2) {
		checkVertex(v1);
		checkVertex(v2);
	}
}
