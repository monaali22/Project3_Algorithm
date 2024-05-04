package application;
import java.util.*;
public class Dijkstra {

	public Dijkstra () { //  no-argument constructor.
		
	}
	
	public List <City> shortestPath (City source , City Destenation , CityMap map){
		
		       // Inner class to compare between two City distances
				class BComparatorCites implements Comparator<City> {
					public int compare(City City1, City City2) {
						// Compare City based on their distances in the graph
						return (int) (map.distance.get(City1) - map.distance.get(City2));
					}
				}
				
				
				
				// Heap to store cites based on their distances (Min Heap):-
				PriorityQueue<City> Minheap = new PriorityQueue<>(new BComparatorCites());

				

				Minheap.add(source); // Add source city to the queue
				map.distance.put(source, 0.0); // Initial distance with zero
				
				// Map to store the previous city in the shortest path
				Map<City, City> Adjacent = new HashMap<>();
				
				
				while (!Minheap.isEmpty()) {
					
					City current = Minheap.poll(); // Remove and return the city with lowest distance from the heap

					if (current == Destenation)
						break;

					for (Edges edge : map.adjacencyMap.get(current)) { // Loop on the edges from the current city
						City adjacentCity = edge.destenation; // Neighbour is an adjacent city for the current
																// city(edge's destination)
						

						double distance = edge.weight; // distance is the weight between current city and neighbour
														// city(edge's weight)

						// Update the distance of the neighbour city if a shorter path is found
						if (map.distance.get(adjacentCity) > map.distance.get(current) + distance) {
							map.distance.put(adjacentCity, map.distance.get(current) + distance); // Update the distance
							Adjacent.put(adjacentCity, current); // Add to the HashMap previous
							Minheap.add(adjacentCity); // Add the neighbour city (has minimum distance) to the heap
						}
					}
					
				}
				
				
				
				
				
				if (Adjacent.get(Destenation) == null) // If no path
					return null;
				
				List<City> path = new ArrayList<>(); // path is a list that give the shortest path from destination to
				City current = Destenation; // current starts from the destination city until the source

				while (current != source) {
					path.add(current); // Add current city to the path list
					current = Adjacent.get(current); // Go to the previous city of current city by previous HashMap
				}

				path.add(source); // Add source city to the path list
				Collections.reverse(path); // Take path in reverse order (from source to destination)
				

				return path; // Return the path list	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	/*
	 * List<Edges> edges = map.adjacencyMap.get(current);
        for (int i = 0; i < edges.size(); i++) {
    Edges edge = edges.get(i);
    City adjacentCity = edge.destenation;
    // Rest of the loop body remains the same
}

	 */
	
	
}
