import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;

// Represents an edge in the graph.
class Edge
	{
		public Vertex	dest;	// Second vertex in Edge
		public double	cost;	// Edge cost

		public Edge(Vertex d, double c)
			{
				dest = d;
				cost = c;
			}
	}

// Represents a vertex in the graph.
class Vertex
	{
		public String		name;		// Vertex name
		public List<Edge>	adj;		// Adjacent vertices
		public double		dist;		// Cost
		public Vertex		prev;		// Previous vertex on shortest path
		public int			scratch;	// Extra variable used in algorithm

		public Vertex(String nm)
			{
				name = nm;
				adj = new LinkedList<Edge>();
				reset();
			}

		public void reset()
			{
				dist = Graph.INFINITY;
				prev = null;
				scratch = 0;
			}

	}

// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
// --> Add additional edge
// void printPath( String w ) --> Print path after alg is run
// void unweighted( String s ) --> Single-source unweighted
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm. Exceptions are thrown if errors are detected.

public class Graph
	{
		public static final double	INFINITY	= Double.MAX_VALUE;
		private Map<String, Vertex>	vertexMap	= new HashMap<String, Vertex>();
		private String				s			= "";
		private double				theCost;

		/**
		 * Add a new edge to the graph.
		 */
		public void addEdge(String sourceName, String destName, double cost)
			{
				Vertex v = getVertex(sourceName);
				Vertex w = getVertex(destName);
				v.adj.add(new Edge(w, cost));
			}

		/**
		 * Driver routine to handle unreachables and print total cost. It calls
		 * recursive routine to print shortest path to destNode after a shortest
		 * path algorithm has run.
		 */
		public void printPath(String destName)
			{
				Vertex w = vertexMap.get(destName);
				if (w == null)
					{
						s = destName + " is unreachable";
					} else if (w.dist == INFINITY)
					{
						s = destName + " is unreachable";
					} else
					{
						// System.out.print("(Cost is: " + w.dist + ") ");
						theCost = w.dist;
						printPath(w);
						// System.out.println();
					}
			}

		/**
		 * If vertexName is not present, add it to vertexMap. In either case,
		 * return the Vertex.
		 */
		private Vertex getVertex(String vertexName)
			{
				Vertex v = vertexMap.get(vertexName);
				if (v == null)
					{
						v = new Vertex(vertexName);
						vertexMap.put(vertexName, v);
					}
				return v;
			}

		/**
		 * Recursive routine to print shortest path to dest after running
		 * shortest path algorithm. The path is known to exist.
		 */
		private void printPath(Vertex dest)
			{
				if (dest.prev != null)
					{
						printPath(dest.prev);
						// System.out.print(" to ");
					}
				// System.out.print(dest.name);
				s += " " + dest.name;
			}

		/**
		 * Initializes the vertex output info prior to running any shortest path
		 * algorithm.
		 */
		private void clearAll()
			{
				for (Vertex v : vertexMap.values())
					v.reset();
			}

		/**
		 * Single-source unweighted shortest-path algorithm.
		 */
		public void unweighted(String startName)
			{
				clearAll();

				Vertex start = vertexMap.get(startName);
				if (start == null)
					{
						s = startName + " Cannot reach your desination";
						return;
					}

				Queue<Vertex> q = new LinkedList<Vertex>();
				q.add(start);
				start.dist = 0;

				while (!q.isEmpty())
					{
						Vertex v = q.remove();

						for (Edge e : v.adj)
							{
								Vertex w = e.dest;
								if (w.dist == INFINITY)
									{
										w.dist = v.dist + 1;
										w.prev = v;
										q.add(w);
									}
							}
					}
			}

		public String getS()
			{
				return s;
			}

		public void setS(String s)
			{
				this.s = s;
			}

		public double getCost()
			{
				return theCost;
			}
	}
