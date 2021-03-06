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
		System.out.println(eElement.getAttribute("bitmap"));
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

			Node tNode = doc.getElementsByTagName("mapfile").item(0);
			Element tElement = (Element) tNode;
			MapEditor.imagePath = tElement.getAttribute("bitmap");
			MapEditor.scale_feet_per_pixel = Double.parseDouble(tElement.getAttribute("scale-feet-per-pixel"));
			
			
			NodeList nList = doc.getElementsByTagName("location");
			NodeList pList = doc.getElementsByTagName("path");

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
			
			for (int temp = 0; temp < pList.getLength(); temp++) {
				Node pNode = pList.item(temp);
				if (pNode.getNodeType() == Node.ELEMENT_NODE) {
					Element pElement = (Element) pNode;
					int idfrom = Integer.parseInt(pElement.getAttribute("idfrom"));
					int idto = Integer.parseInt(pElement.getAttribute("idto"));
					Path tmp = null;
					
					for(Vertex v : MapEditor.points)
					{
						if(v.getID() == idfrom)
						{
							if(tmp == null)
							{
								tmp = new Path(v, null);
							}
							else
							{
								tmp.setStart(v);
							}
						}
						else if(v.getID() == idto)
						{
							if(tmp == null)
							{
								tmp = new Path(null, v);
							}
							else
							{
								tmp.setEnd(v);
							}
						}
					}
					
					MapEditor.paths.add(tmp);				
				}
			}
			
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
		out.flush();
		out.println("<mapfile bitmap=\""+image+"\" scale-feet-per-pixel=\""+fpp+"\">");
		out.flush();
		for(Vertex v : MapEditor.points){
			out.println("<location id=\""+v.getID()+"\" name=\""+v.getName()+"\" x=\""+v.getX()+"\" y=\""+v.getY()+"\" />");
			out.flush();
		}
		for(Path p : MapEditor.paths)
		{
			out.println("<path idfrom=\"" + p.getStart().getID() + "\" idto=\"" + p.getEnd().getID() + "\" type=\"undirected\" />");
			out.flush();
		}
		out.println("</mapfile>");
		out.close();
	}

}


