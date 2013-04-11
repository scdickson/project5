import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XML {

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
			
			for(Vertex temp : MapEditor.points){
				System.out.println("ID: " + temp.id);
				System.out.println("Name: " + temp.name);
				System.out.println("X coord: " + temp.x);
				System.out.println("Y coord: " + temp.y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
