import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapViewer {

		private final List<Vertex> nodes;
		private final List<Path> edges;
		private Set<Vertex> settledNodes;
		private Set<Vertex> unSettledNodes;
		private Map<Vertex, Vertex> predecessors;
		private Map<Vertex, Integer> distance;

		public MapViewer() {
			// Create a copy of the array so that we can operate on this array
			this.nodes = MapEditor.points;
			this.edges = MapEditor.paths;
		}

		public void initiateDirections(Vertex source) {
			settledNodes = new HashSet<Vertex>();
			unSettledNodes = new HashSet<Vertex>();
			distance = new HashMap<Vertex, Integer>();
			predecessors = new HashMap<Vertex, Vertex>();
			distance.put(source, 0);
			unSettledNodes.add(source);
			while (unSettledNodes.size() > 0) {
				Vertex node = getMinimum(unSettledNodes);
				settledNodes.add(node);
				unSettledNodes.remove(node);
				findMinimalDistances(node);
			}
		}

		private void findMinimalDistances(Vertex node) {
			List<Vertex> adjacentNodes = getNeighbors(node);
			for (Vertex target : adjacentNodes) {
				if (getShortestDistance(target) > getShortestDistance(node)
						+ getDistance(node, target)) {
					distance.put(target, getShortestDistance(node)
							+ getDistance(node, target));
					predecessors.put(target, node);
					unSettledNodes.add(target);
				}
			}

		}

		private int getDistance(Vertex node, Vertex target) {
			for (Path Path : edges) {
				if (Path.getStart().equals(node)
						&& Path.getEnd().equals(target)) {
					return Path.getWeight();
				}
			}
			throw new RuntimeException("You done goofed son");
		}

		private List<Vertex> getNeighbors(Vertex node) {
			List<Vertex> neighbors = new ArrayList<Vertex>();
			for (Path Path : edges) {
				if (Path.getStart().equals(node)
						&& !isSettled(Path.getEnd())) {
					neighbors.add(Path.getEnd());
				}
			}
			return neighbors;
		}

		private Vertex getMinimum(Set<Vertex> vertexes) {
			Vertex minimum = null;
			for (Vertex vertex : vertexes) {
				if (minimum == null) {
					minimum = vertex;
				} else {
					if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
						minimum = vertex;
					}
				}
			}
			return minimum;
		}

		private boolean isSettled(Vertex vertex) {
			return settledNodes.contains(vertex);
		}

		private int getShortestDistance(Vertex destination) {
			Integer d = distance.get(destination);
			if (d == null) {
				return Integer.MAX_VALUE;
			} else {
				return d;
			}
		}

		/*
		 * This method returns the path from the source to the selected target and
		 * NULL if no path exists
		 */
		public LinkedList<Vertex> getDirections(Vertex target) {
			LinkedList<Vertex> path = new LinkedList<Vertex>();
			Vertex step = target;
			// Check if a path exists
			if (predecessors.get(step) == null) {
				return null;
			}
			path.add(step);
			while (predecessors.get(step) != null) {
				step = predecessors.get(step);
				path.add(step);
			}
			// Put it into the correct order
			Collections.reverse(path);
			return path;
		}

		//Sort MapEditor.paths in ascending order. Uses a simple bubble sort.
		public static void sortPaths()
		{
			for(int i = 0; i < MapEditor.paths.size(); i++)
			{
				for(int j = 1; j < (MapEditor.paths.size() - i); j++)
				{
					if(MapEditor.paths.get(j-1).getWeight() > MapEditor.paths.get(j).getWeight())
					{
						Path tmp = MapEditor.paths.get(j-1);
						MapEditor.paths.set(j-1, MapEditor.paths.get(j));
						MapEditor.paths.set(j, tmp);
					}
				}
			}
		}
		
	public static void main(String [] args){
		XML parser = new XML();
		parser.openMap("Resources/dorms.xml");
		
		for(Vertex v : MapEditor.points){
			System.out.println(v);
		}
		for(Path p : MapEditor.paths){
			System.out.println(p);
		}
		
	    MapViewer dijkstra = new MapViewer();
	    dijkstra.initiateDirections(MapEditor.points.get(15));
	    LinkedList<Vertex> bunch1 = dijkstra.getDirections(MapEditor.points.get(12));
	    //LinkedList<Vertex> bunch2 = dijkstra.getDirections(MapEditor.points.get(2));
	    //LinkedList<Vertex> bunch3 = dijkstra.getDirections(MapEditor.points.get(3));
	    System.out.println(bunch1);
	    //System.out.println(bunch2);
	    //System.out.println(bunch3);

	}

}
