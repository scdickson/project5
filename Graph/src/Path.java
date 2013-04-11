
public class Path 
{
	private Vertex from;
	private Vertex to;
	
	public Path(Vertex from, Vertex to)
	{
		this.from = from;
		this.to = to;
	}
	
	public Vertex getStart()
	{
		return from;
	}
	
	public Vertex getEnd()
	{
		return to;
	}
	
	public void setStart(Vertex from)
	{
		this.from = from;
	}
	
	public void setEnd(Vertex to)
	{
		this.to = to;
	}
	
	public String toString()
	{
		return "Vertex: from=(" + from.toString() + "), to=(" + to.toString() + ")";
	}

}
