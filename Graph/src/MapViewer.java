
public class MapViewer {

	public static Vertex lowestDistance(Vertex start){
		double weight = Double.POSITIVE_INFINITY;
		Vertex lowest = start;

		for(Path p: MapEditor.paths){
			if((p.getStart().equals(start) || p.getEnd().equals(start))
					&& p.getWeight()<weight){
				weight = p.getWeight();
				if(p.getStart().equals(start)) lowest = p.getEnd();
				if(p.getEnd().equals(start)) lowest = p.getStart();
			}
		}
		return lowest;
	}

	public static void Directions(Vertex start, Vertex end) {
		for(Vertex v : MapEditor.points){
			v.setVisited(false);
		}

		boolean allVisited = false;		
		for(Vertex v : MapEditor.points){
			if(!allVisited && !v.getVisited()){
				Vertex lowest = lowestDistance(v);
				v.setVisited(true);

				//Check
				System.out.println(v.toString());
				if(lowest.equals(v)) System.out.println("You done goofed son.");
				else System.out.println(lowest.toString());
				System.out.println("-");

				allVisited = true;
				for(Vertex temp : MapEditor.points) if(!temp.getVisited()) allVisited = false;
			}
		}

	
	
	}

	public static void main(String [] args){
		Vertex v1 = new Vertex("1", 1 , 233, 456);
		MapEditor.points.add(v1);
		Vertex v2 = new Vertex("2", 2 , 565, 876);
		MapEditor.points.add(v2);
		Vertex v3 = new Vertex("3", 3 , 876, 987);
		MapEditor.points.add(v3);
		Vertex v4 = new Vertex("4", 4 , 238, 239);
		MapEditor.points.add(v4);
		MapEditor.paths.add(new Path(v1,v2));
		MapEditor.paths.add(new Path(v1,v3));
		MapEditor.paths.add(new Path(v1,v4));
		MapEditor.paths.add(new Path(v2,v3));
		MapEditor.paths.add(new Path(v2,v4));
		MapEditor.paths.add(new Path(v3,v4));
		Directions(v1, v4);

	}

}
