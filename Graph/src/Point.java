public class Point 
{
	private String name;
	private int id;
	private int x;
	private int y;
	
	public Point(String name, int id, int x, int y)
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
