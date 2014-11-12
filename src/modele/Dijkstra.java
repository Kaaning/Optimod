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
	
	double[][] cost;
	ZoneGeographique zone;
	Tournee tour;
	List<Vertex> vertices;
	
	public Dijkstra (ZoneGeographique zone, Tournee tour) {
		
		this.zone = zone;
		this.tour = tour;
		
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
		
		cost = new double[this.getNbVertices()][this.getNbVertices()];
		
		for(int a = 0; a < this.getNbVertices(); a++) {
    		this.computePaths(vertices.get(a));
    		for(int i = 0; i < this.getNbVertices(); i++) {
    			if (i == a) {
    				cost[a][i] = 0.0;
    			} else {
    				double weight = 0.0;
	    			List<Vertex> path = getShortestPath(this.vertices.get(i));
	    			if(a > i) {
	    				System.out.println("A = " + a + ", I = " + i + ", Path is : " + path + " of length " + path.size());
	    			}
	    			if(path == null || path.size() == 1) {
    					weight = getMaxArcCost()+1;
    				} else {
		    			for(int j = 0; j < path.size()-1; j++) {
		    				//Vertex prev = path.get(j).previous;
		    				Vertex sommet = path.get(j);
		    				Vertex next = path.get(j+1);
		    				int index = -2;
		    				for(Edge e : sommet.adjacencies) {
		    					if(e.target == next)
		    						index = sommet.adjacencies.indexOf(e);
		    				}
		    				weight += sommet.adjacencies.get(index).arc.getCout();
		    			}
	    			}
	    			this.cost[a][i] = weight;
    			}
    			System.out.println("=====================");
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
            path.add(vertex);
            if(vertex.minDistance == 0.0) {
            	break;
            }
            if(vertex.previous == vertex) {
            	return null;
            }
        }
        
        /*
        System.out.println ("Bye bye world");
        */
        
        Collections.reverse(path);
        
        return path;
    }
    
    public double getTotalCost(List<Vertex> path) {
    	double res = 0;
    	//TODO : Insert code of constructor Dijkstra
    	return res;
    }
    
    public List<Etape> obtainEtapes() {
    	
    	return null; //return Dijkstra.verticesToEtapes(this.getShortestPath(this.target));
    }
    
    private static List<Etape> verticesToEtapes(List<Vertex> path) {
		List<Etape> res = new ArrayList<Etape>();
    	/*for(Iterator<Vertex> it = path.) {
			
		}*/
    	return res;
	}

	public static void main(String[] args) throws JDOMException, IOException, ParseException
    {
    	/*Noeud n0 = new Noeud(0, 0, 0);
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
    	
    	Troncon t01 = new Troncon("rue du Caca", 15.0, 6.2);
    	t01.setSource(n0);
    	t01.setCible(n1);
    	Troncon t02 = new Troncon("rue du Tabac", 27.5, 15.0);
    	t02.setSource(n0);
    	t02.setCible(n2);
    	Troncon t12 = new Troncon("rue du Papa", 28.0, 7.1);
    	t12.setSource(n1);
    	t12.setCible(n2);
    	
    	n0.ajouterTronconSortant(t01);
    	n1.ajouterTronconEntrant(t01);
    	
    	n0.ajouterTronconSortant(t02);
    	n2.ajouterTronconEntrant(t02);
    	
    	n1.ajouterTronconSortant(t12);
    	n2.ajouterTronconEntrant(t12);*/
    	
    	ZoneGeographique z = new ZoneGeographique("fic\\plan10x10-test.xml");
    	Tournee t = new Tournee("fic\\livraison10x10-test.xml", z);
    	
    	Dijkstra dj = new Dijkstra(z, t);
    	
    	System.out.println("Sommet s_0 : " + dj.getVertexByNodeId(t.getEntrepot()));
    	
    	System.out.println("Nodes list :");
    	//System.out.println(zone.getNoeuds().toString());
    	System.out.println("Vertices list :");
    	System.out.println(dj.vertices);
    	 
    	for(Vertex v : dj.vertices) {
    		System.out.println(v + "adjacencies : ");
    		for(Edge e : v.adjacencies) {
    			System.out.println(e.toString());
    		}
    	}
    	
    	// Asking for the minimal path from vertex 0 to vertex 2 :
    	Vertex source = dj.getVertexByNodeId(t.getEntrepot());
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
    		System.out.println();
    		System.out.println("- - - - - - - - - - - -");
    		System.out.println("Distance of the minimal path : " + target.minDistance);
    		System.out.println("- - - - - - - - - - - -");
    		System.out.println("Total number of nodes in the zone : " + z.getNoeuds().size());
    		System.out.println("Total number of nodes in the graph : " + dj.vertices.size());
    		System.out.println("Number of visited nodes : " + (path.size()-1));
    		System.out.println("Unvisited node(s) of the graph for this path :");
    		
    		if(path.size()-1 == dj.vertices.size()) {
    			System.out.println("NONE");
    		} else {
    			for(Vertex v : dj.vertices) {
	    			if(!path.contains(v) && v != source)
	    				System.out.println(v.toString());
    			}
    		}
    		System.out.println("=========================");
    	} else {
    		System.out.println("Error : No path has been found from " + source + " to " + target + " : the two nodes may be not connected through the given graph.");
    	}
    	
    	double[][] costs = dj.getCost();
    	for(int i = 0; i < dj.getNbVertices(); i++) {
    		for(int j = 0; j < dj.getNbVertices(); j++) {
    			System.out.print("[" + costs[i][j] + "]");
    		}
    		System.out.println();
    	}
    	System.out.println("=========================");
	    /*for (int i = 0; i < dj.getNbVertices(); i++) {
	    	System.out.println("Successors of " + dj.vertices.get(i) +" :");
	    	int[] succ = dj.getSucc(i);
	    	for(int j = 0; j < succ.length; j++) {
	    		System.out.print("[" + dj.getVertexById(succ[j]) + "]");
	    	}
	    	System.out.println();
	    }*/
    	System.out.println("=========================");
    	System.out.println("Minimum cost in this graph : " + dj.getMinArcCost());
    	System.out.println("=========================");
    	System.out.println("Maximum cost in this graph : " + dj.getMaxArcCost());
    	
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
