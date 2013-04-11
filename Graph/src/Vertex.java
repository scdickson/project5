//Sam Dickson and Niraj Venkat

public class Vertex 
{
	public String name;
	public int id;
	public int x;
	public int y;
	
	public Vertex(String name, int id, int x, int y)
	{
		this.name = name;
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

}
