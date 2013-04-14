import java.awt.Rectangle;


public class Path 
{
	private Vertex from;
	private Vertex to;
	private int path_weight;
	boolean isSelected = false;
	
	public Path(Vertex from, Vertex to)
	{
		this.from = from;
		this.to = to;
		isSelected = false;
		
		if(from != null && to != null)
		{
			path_weight = weight();

		}
	}
	
	public Vertex getStart()
	{
		return from;
	}
	
	public Vertex getEnd()
	{
		return to;
	}
	
	public int getWeight()
	{
		return path_weight;
	}	
	
	public void setStart(Vertex from)
	{
		this.from = from;
		
		if(from != null && to != null)
		{
			path_weight = weight();

		}
	}
	
	public void setEnd(Vertex to)
	{
		this.to = to;
		
		if(from != null && to != null)
		{
			path_weight = weight();

		}
	}
	
	public String toString()
	{
		return "Path: from=(" + from.toString() + "), to=(" + to.toString() + "), distance=" + path_weight;
	}
	
	private int weight()
	{
		return (int)(MapEditor.scale_feet_per_pixel * Math.sqrt(Math.pow((to.getX()-from.getX()), 2) + Math.pow((to.getY()-from.getY()), 2)));
	}
	
	public boolean equals(Object obj)
	{
		Path other = (Path) obj;
		return (((other.getEnd().equals(this.getEnd())) && (other.getStart().equals(this.getStart()))) || ((other.getEnd().equals(this.getStart())) && (other.getStart().equals(this.getEnd()))));
	}
	


}
