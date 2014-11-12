package modele;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Vector;

class Vertex implements Comparable<Vertex>
{
	// Node corresponding to the vertex
    public final Noeud node;
    
    // Vertex's ID, may be different from the node's one
	//public final int vId;
	
    // All the outgoing edges from the vertex
    public List<Edge> adjacencies;
    
    // Weight of the minimum path found by Dijkstra's algorithm from the source vertex of Dijkstra's object
    public double minDistance;
    
    // Previous vertex into this minimum path
    public Vertex previous;
    
    /**
	 * Constructeur de <code>this</code>
	 * @param node object (type <code>Noeud</code>) associated to <code>this</code> in the graph
	 */
    public Vertex(Noeud node) {
    	this.node = node;
    	adjacencies = new ArrayList<Edge>(); //[node.getTronconsSortants().size()];
    	minDistance = Double.POSITIVE_INFINITY;
    	previous = this;
    }
    
    // Useless
    /**
	 * Constructeur par copie de <code>this</code>
	 * @param <code>Vertex</code> object to copy into <code>this</code>
	 */
    public Vertex(Vertex v) {
    	this.node = v.node;
    	this.adjacencies = v.adjacencies;
    	this.minDistance = v.minDistance;
    	this.previous = v.previous;
    }
    // End of uselessness
    
    public String toString() {
    	return "<Vertex " + node.getId() + ">";
    }
    
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge
{
	public final Vertex target;
	public final Troncon arc;
    
	public Edge(Vertex target, Troncon arc) {
    	this.target = target;
    	this.arc = arc;
    }
	
	// Useless
	public Edge(Edge e) {
    	this.target = e.target;
    	this.arc = e.arc;
    }
	// Again
	
	// And again
	 public int compareTo(Edge other)
    {
        return Double.compare(arc.getCout(), other.arc.getCout());
    }
	 //
	 
	 public String toString() {
    	return "<Edge to " + target.toString() + ">";
    }
}

public class Dijkstra implements Graph {
	
	List<Vertex> vertices;
	Vertex source;
	Vertex target;
	
	public Dijkstra (List<Noeud> nodes) {
		
		vertices = new Vector<Vertex>();
		
		for (int i = 0; i < nodes.size(); i++) {
			Vertex v = new Vertex (nodes.get(i));
			vertices.add(v);
		}
		
		for (Vertex v : vertices) {
			for (Troncon t : v.node.getTronconsSortants()) {
				Edge e = new Edge (getVertexById(t.getCible().getId()), t);
				v.adjacencies.add(e);// (vTemp != null ? new Edge(vTemp, v.node.getTronconsSortants().get(j)) : null) );
			}
		}
		
	}
	
	public Vertex getVertexById(int id) {
		for(Vertex v : vertices){
			if(id == v.node.getId())
				return v;
		}
		return null;
	}
	
	
	// +computePaths :
	// No return value -> all the vertices connected to <source> vertex are visited and updated
	// At the end of this method, all the vertices point to their previous one in the minimal path from <source>
	// If v.previous == v, the vertex is an isolated vertex of the graph (at least, not reachable by <source>)
	// -> To get the minimal path from <source> to a vertex <target>, use getShortestPath(target), which returns a List<Vertex>
    public void computePaths(Vertex source) {
        // Distance mini
    	source.minDistance = 0.;
        Queue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
	
	        // Visit each edge exiting u
	        for (Edge e : u.adjacencies) {
	        	Vertex v = e.target;
	            double weight = e.arc.getCout();
	            double distanceThroughUToV = u.minDistance + weight;
				
	            // IF passing through u gets a lower weighted path :
	            if (distanceThroughUToV < v.minDistance) {
				    // Removes the non-optimal state for vertex v..
	            	vertexQueue.remove(v);
				    // ..updates the minimal path to it..
				    v.minDistance = distanceThroughUToV ;
				    // .. and now, indicates that u is visited just before v.
				    v.previous = u;
				    // Finally, adds back the updated vertex into the queue
				    vertexQueue.add(v);
				}
	            // ELSE : none of the vertices reachable by u get a shorter path by passing through it
	            // -> end of the paths which have reached u grom the source : all the other adjacent vertices are reachable by a shorter way than through u
	        }
	        
	    }
    }
    
    // By using the
    public List<Vertex> getShortestPath(Vertex target)
    {
    	List<Vertex> path = new ArrayList<Vertex>();
    	
    	// Trace
    	System.out.println("Hello world");
    	System.out.println(target);
    	System.out.println(target.previous);
    	// Fin trace
    	
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
            if(vertex.minDistance == 0.0) {
            	break;
            }
            if(vertex.previous == vertex) {
            	return null;
            }
        }
        
        //
        System.out.println ("Bye bye world");
        //
        
        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args)
    {
    	Noeud n0 = new Noeud(0, 0, 0);
    	Noeud n1 = new Noeud(1, 1, 1);
    	Noeud n2 = new Noeud(2, 2, 2);
    	Noeud n3 = new Noeud(3, 3, 3);
    	Noeud n4 = new Noeud(4, 4, 4);
    	Noeud n5 = new Noeud(5, 5, 5);
    	
    	List<Noeud> ln = new ArrayList<Noeud>();
    	ln.add(n0);
    	ln.add(n1);
    	ln.add(n2);
    	ln.add(n3);
    	ln.add(n4);
    	ln.add(n5);
    	
    	Troncon t01 = new Troncon("rue du Caca", 1.5, 6.2);
    	t01.setSource(n0);
    	t01.setCible(n1);
    	/*Troncon t02 = new Troncon("rue du Tabac", 27.5, 15.0);
    	t02.setSource(n0);
    	t02.setCible(n2);*/
    	Troncon t12 = new Troncon("rue du Papa", 2.8, 7.1);
    	t12.setSource(n1);
    	t12.setCible(n2);
    	
    	n0.ajouterTronconSortant(t01);
    	//n1.ajouterTronconEntrant(t01);
    	//n0.ajouterTronconSortant(t02);
    	//n2.ajouterTronconEntrant(t02);
    	n1.ajouterTronconSortant(t12);
    	//n2.ajouterTronconEntrant(t12);
    	
    	Dijkstra dj = new Dijkstra(ln);
    	
    	System.out.println("Nodes list :");
    	System.out.println(ln.toString());
    	System.out.println("Vertices list :");
    	System.out.println(dj.vertices);
    	
    	for(Vertex v : dj.vertices) {
    		System.out.println(v + "adjacencies : ");
    		for(Edge e : v.adjacencies) {
    			System.out.println(e.toString());
    		}
    	}
    	
    	// Asking for the minimal path from vertex 0 to vertex 2 :
    	dj.source = dj.vertices.get(0);
    	dj.target = dj.vertices.get(2);
    	
    	System.out.println("Computing paths form vertex " + dj.source + " to all the vertices of the graph ...");
    	dj.computePaths(dj.source);
    	System.out.println("Getting the best path to " + dj.target + " ...");
    	List<Vertex> path = dj.getShortestPath(dj.target);
    	if(path != null) {
    		System.out.println("=========================");
    		System.out.println("RESULT HAS BEEN FOUND !");
    		for(Vertex v : path) {
    			System.out.print((v == path.get(0)?"BEGINNING -> ":"") + v + (v == path.get(path.size()-1)?" -> END":" -> "));
    		}
    		System.out.println();
    		System.out.println("- - - - - - - - - - - -");
    		System.out.println("Distance of the minimal path : " + dj.target.minDistance);
    		System.out.println("- - - - - - - - - - - -");
    		System.out.println("Total number of nodes in the graph : " + dj.vertices.size());
    		System.out.println("Number of visited nodes : " + path.size());
    		System.out.println("Not visited node(s) for this path :");
    		
    		if(path.size() == dj.vertices.size()) {
    			System.out.println("NONE");
    		} else {
    			for(Vertex v : dj.vertices) {
	    			if(v.previous == v && v != dj.source)
	    				System.out.println(v.toString());
    			}
    		}
    		System.out.println("=========================");
    	} else {
    		System.out.println("Error : No path has been found from " + dj.source + " to " + dj.target + " : the two nodes may be not connected through the given graph.");
    	}
    	
    	double[][] costs = dj.getCost();
    	for(int i = 0; i < dj.getNbVertices(); i++) {
    		for(int j = 0; j < dj.getNbVertices(); j++) {
    			System.out.print("[" + costs[i][j] + "]");
    		}
    		System.out.println();
    	}
    	System.out.println("=========================");
    	
    	int[] succ_0 = dj.getSucc(0);
    	for(int i = 0; i < succ_0.length; i++) {
    		System.out.print("[" + succ_0[i] + "]");
    	}
    	System.out.println("=========================");
    	System.out.println("Minimum cost in this graph : " + dj.getMinArcCost());
    	System.out.println("=========================");
    	System.out.println("Maximum cost in this graph : " + dj.getMaxArcCost());
    	System.out.println("=========================");
    }

	@Override
	public double getMaxArcCost() {
		double res = Double.NEGATIVE_INFINITY;
		for(Vertex v : this.vertices) {
			for(Edge e : v.adjacencies) {
				if(e.arc.getCout() > res)
					res = e.arc.getCout();
			}
		}
		return res;
	}

	@Override
	public double getMinArcCost() {
		double res = Double.POSITIVE_INFINITY;
		for(Vertex v : this.vertices) {
			for(Edge e : v.adjacencies) {
				if(e.arc.getCout() < res)
					res = e.arc.getCout();
			}
		}
		return res;
	}
	
	@Override
	public int getNbVertices() {
		return this.vertices.size();
	}

	@Override
	public double[][] getCost() {
		int n = this.getNbVertices();
		double[][] res = new double[n][n];
		
		for(int i = 0; i < n; i++) {
			Vertex v = this.vertices.get(i);
			for(int j = 0; j < n; j++) {
				res[i][j] = this.getMaxArcCost()+1;
			}
			for(Edge e : v.adjacencies) {
				res[i][this.vertices.indexOf(e.target)] = e.arc.getCout();
			}
			
		}
		return res;
	}

	@Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException {
		if(i < 0 || i > this.vertices.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Vertex v = this.vertices.get(i);
		int[] res = new int[v.adjacencies.size()];
		for(int a = 0; a < res.length; a++) {
			res[a] = this.vertices.indexOf(v.adjacencies.get(a).target);
		}
		return res;
	}

	@Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		return this.getSucc(i).length;
	}
}
