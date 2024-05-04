package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CityMap {


	Map <City , List<Edges>> adjacencyMap ;      // Map to store the graph representation with Cites and list of edges
	Map <City , Double> distance ;     // Map to store the weight(distance) from a source city to each cites
	
	
	public CityMap() {
		adjacencyMap = new HashMap<>();
		distance = new HashMap<>();
		
	}
	
	//___________________________________________________________________________________________________________________________
	
	public void addCity (City newCity ) {
		adjacencyMap.put(newCity, new LinkedList<>());          // add new City to the Graph with empty list of edges 
		distance.put(newCity, Double.POSITIVE_INFINITY);  // Initial distance 
		
	}
	
	
	//____________________________________________________________________________________________________________________________
	
	public void addEdges(City source , City destination , double weigth) {
		adjacencyMap.get(source).add(new Edges (source , destination , weigth )); // Add the new edge to the list of edges for the source city 
		
	}
}
