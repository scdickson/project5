import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class XML {
	
	public void setBitmap(String path){
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try{
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();
		Node nNode = doc.getElementsByTagName("location").item(0);
		Element eElement = (Element) nNode;
		MapEditor.imagePath = eElement.getAttribute("bitmap");
		MapEditor.scale_feet_per_pixel = Double.parseDouble(eElement.getAttribute("scale_feet_per_pixel"));
		
	}

	public void openMap(String path){
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("location");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					int id = Integer.parseInt(eElement.getAttribute("id"));
					String name = eElement.getAttribute("name");
					int x = Integer.parseInt(eElement.getAttribute("x"));
					int y = Integer.parseInt(eElement.getAttribute("y"));
					MapEditor.points.add(new Vertex(name, id , x, y));				
				}
			}
			/* Prints all the vertices
			for(Vertex temp : MapEditor.points){
				System.out.println("ID: " + temp.id);
				System.out.println("Name: " + temp.name);
				System.out.println("X coord: " + temp.x);
				System.out.println("Y coord: " + temp.y);
			}
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveMap(String path, String image, double fpp){
		File f = new File(path);
		if(f.exists()) f.delete();

		PrintWriter out = null;
		try {
			f.createNewFile();
			out = new PrintWriter(new FileWriter(path)); 

		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<mapfile bitmap=\""+image+"\" scale-feet-per-pixel=\""+fpp+"\">");
		for(Vertex v : MapEditor.points){
			out.println("<location id=\""+v.id+"\" name=\""+v.name+"\" x=\""+v.x+"\" y=\""+v.y+"\" />");
		}
		out.println("</mapfile>");
	}

}


