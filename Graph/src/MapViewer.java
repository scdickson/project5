
public class MapViewer {

	public static void Directions(Vertex start, Vertex end) {
		for(Vertex temp : MapEditor.points){
			temp.setVisited(false);
			if(temp.equals(start)) temp.setVisited(true);
		}





	}


}
