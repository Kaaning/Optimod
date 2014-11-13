package modele;

import java.io.IOException;
import java.text.ParseException;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Vector;

import org.jdom2.JDOMException;

import tsp.Graph;
import tsp.TSP;

/**
 * @author Adrien Garcia
 */

class Vertex implements Comparable<Vertex>
{
	// Node corresponding to the vertex
    public final Noeud node;
    
    // Vertex's ID, that may (certainly) be different from his node's one
	//public final int vId;
	
    // All the outgoing edges from the vertex
    public List<Edge> adjacencies;
    
    // Weight of the minimum path found by Dijkstra's algorithm from the source vertex of Dijkstra's object
    public int minDistance;
    
    // Previous vertex into this minimum path
    public Vertex previous;
    
    /**
	 * Constructeur de <code>this</code>
	 * @param node object (type <code>Noeud</code>) associated to <code>this</code> in the graph
	 */
    public Vertex(Noeud node) {
    	this.node = node;
    	adjacencies = new ArrayList<Edge>(); //[node.getTronconsSortants().size()];
    	minDistance = (int)Double.POSITIVE_INFINITY;
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

class Edge implements Comparable<Edge>
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
	
	List< List<Livraison> > ordDeliv;
	List< List<Vertex> > ordVertices;
	int[][] cost;
	ZoneGeographique zone;
	Tournee tour;
	List<Vertex> vertices;
	
	public Dijkstra (ZoneGeographique zone, Tournee tour) {
		
		this.zone = zone;
		this.tour = tour;
		ordDeliv = new ArrayList< List<Livraison> >();
		ordVertices = new ArrayList< List<Vertex> >();
		
		for(PlageHoraire p : tour.getPlages()) {
			List<Livraison> lDeliv = new ArrayList<Livraison>();
			List<Vertex> lVert = new ArrayList<Vertex>();
			for(Livraison l : p.getLivraisons()) {
				lDeliv.add(l);
				Noeud n = l.getNoeud();
				lVert.add(new Vertex(n));
			}
		}
		
		vertices = new Vector<Vertex>();
		
		System.out.println("Entrepôt en " + tour.getEntrepot());
		
		//vertices.add(new Vertex(zone.findNoeudById(tour.getEntrepot())));
		
		for (int i = 0; i < this.zone.getNoeuds().size(); i++) {
			Vertex v = new Vertex (this.zone.getNoeuds().get(i));
			vertices.add(v);
		}
		
		for (Vertex v : vertices) {
			for (Troncon t : v.node.getTronconsSortant()) {
				Edge e = new Edge (getVertexByNodeId(t.getCible().getId()), t);
				v.adjacencies.add(e);// (vTemp != null ? new Edge(vTemp, v.node.getTronconsSortants().get(j)) : null) );
			}
		}
		
		cost = new int[this.getNbVertices()][this.getNbVertices()];
		
		for(int a = 0; a < this.getNbVertices(); a++) {
    		this.computePaths(vertices.get(a));
    		for(int i = 0; i < this.getNbVertices(); i++) {
    			if (i == a) {
    				cost[a][i] = 0;
    			} else {
    				int weight = 0;
	    			List<Vertex> path = getShortestPath(this.vertices.get(i));
	    			if(path == null || path.size() == 1) {
    					weight = -1;
    				} else {
		    			/*for(int j = 0; j < path.size()-1; j++) {
		    				//Vertex prev = path.get(j).previous;
		    				Vertex sommet = path.get(j);
		    				Vertex next = path.get(j+1);
		    				int index = -2;
		    				for(Edge e : sommet.adjacencies) {
		    					if(e.target == next)
		    						index = sommet.adjacencies.indexOf(e);
		    				}
		    				weight += sommet.adjacencies.get(index).arc.getCout();
		    			}*/
    					weight = (int)getTotalCost(path);
	    			}
	    			this.cost[a][i] = weight;
    			}
    		}
    	}
		
	}
	
	public Vertex getVertexByNodeId(int id) {
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
        // Minimum distance from <source> to <source> equals 0
    	source.minDistance = 0;
    	
    	// List of vertices which are not yet the ending of their path(s)
    	// i.e. they remain to be tested for the obtention of the minimal paths
        Queue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);
      	
		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
	
	        // Visit each edge exiting u
	        for (Edge e : u.adjacencies) {
	        	Vertex v = e.target;
	            int weight = (int)e.arc.getCout();
	            int distanceThroughUToV = u.minDistance + weight;
				
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
    
    //
    public List<Vertex> getShortestPath(Vertex target)
    {
    	List<Vertex> path = new ArrayList<Vertex>();
    	
    	/* Trace
    	System.out.println("Hello world");
    	System.out.println(target);
    	System.out.println(target.previous);
    	// Fin trace */
    	
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
        	System.out.println("Checking");
        	path.add(vertex);
            if(vertex.minDistance == 0.0) {
            	System.out.println("Finished");
            	break;
            }
            if(vertex.previous == vertex) {
            	System.out.println("Isolated");
            	return null;
            }
        }
        
        /*
        System.out.println ("Bye bye world");
        */
        
        Collections.reverse(path);
        
        return path;
    }
    
    public int getTotalCost(List<Vertex> path) {
    	int res = -1;
    	for(int j = 0; j < path.size()-1; j++) {
			//Vertex prev = path.get(j).previous;
			Vertex sommet = path.get(j);
			Vertex next = path.get(j+1);
			int index = -2;
			for(Edge e : sommet.adjacencies) {
				if(e.target == next)
					index = sommet.adjacencies.indexOf(e);
			}
			res += sommet.adjacencies.get(index).arc.getCout();
    	}
    	return res;
    }
    
    public List<Etape> obtainEtapes() {
    	TSP tsp = new TSP(this);
    	tsp.solve(2000, (int)(this.getMaxArcCost()*this.getNbVertices())+1);
    	int[] order = tsp.getNext();
    	return Dijkstra.verticesToEtapes(Dijkstra.intsToVertices(order));
    }
    
    private static List<Vertex> intsToVertices(int[] order) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Etape> verticesToEtapes(List<Vertex> path) {
		List<Etape> res = new ArrayList<Etape>();
    	/*for(Iterator<Vertex> it = path.) {
			
		}*/
    	return res;
	}

	public static void main(String[] args) throws JDOMException, IOException, ParseException
    {
<<<<<<< HEAD
=======
		ZoneGeographique z = new ZoneGeographique("fic\\plan10x10-test.xml");
    	Tournee t = new Tournee("fic\\livraison10x10-test.xml", z);
    	
    	Dijkstra dj = new Dijkstra(z, t);
    	Vertex source = dj.getVertexByNodeId(0);
    	Vertex target = dj.getVertexByNodeId(2);
    	
    	System.out.println("Computing paths form vertex " + source + " to all the vertices of the graph ...");
    	dj.computePaths(source);
    	System.out.println("Getting the best path to " + target + " ...");
    	List<Vertex> path = dj.getShortestPath(target);
    	if(path != null && path.size() != 1) {
    		System.out.println("=========================");
    		System.out.println("RESULT HAS BEEN FOUND !");
    		System.out.print("BEGINNING ");
    		for(Vertex v : path) {
    			System.out.print(" -> " + v);
    		}
    		System.out.println(" -> END");
    	} else {
    		System.out.println("No result has been found");
    		if(path == null) {
    			System.out.println("NULL");
    		}
    		if(path.size() == 1) {
    			System.out.println("Size = " + path.size());
    			System.out.println(path);
    		}
    	}
    	
    	System.out.println("Cost from 0 to 2 : " + dj.cost[0][2]);
    	System.out.println("==========================================");
    	
    	for(Vertex v : dj.vertices) {
    		System.out.println(v.minDistance);
    	}
    	
    	/*for(int i = 0; i < dj.getNbVertices(); i++) {
    		for(int j = 0; j < dj.getNbVertices(); j++) {
    			System.out.print("[" + dj.getCost()[i][j] + "]");
    		}
    		System.out.println();
    	}*/
>>>>>>> parent of 32c68d8... Revert "Update modele"
    	
    }

	@Override
	public int getMaxArcCost() {
		int res = (int)Double.NEGATIVE_INFINITY;
		for(Vertex v : this.vertices) {
			for(Edge e : v.adjacencies) {
				if(e.arc.getCout() > res)
					res = (int)e.arc.getCout();
			}
		}
		return res;
	}

	@Override
	public int getMinArcCost() {
		int res = (int)Double.POSITIVE_INFINITY;
		for(Vertex v : this.vertices) {
			for(Edge e : v.adjacencies) {
				if(e.arc.getCout() < res)
					res = (int)e.arc.getCout();
			}
		}
		return res;
	}
	
	@Override
	public int getNbVertices() {
		return this.vertices.size();
	}

	@Override
	public int[][] getCost() {
		return this.cost;
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
