

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

/**
 * This class is where you keep track of all your locations and edges
 * and you draw them in the draw() method.
 */
public class MapScene implements Scene {
  private ChangeListener _listener;
  private Image _image;

  private Point _lineStart;
  private Point _lineEnd;
  public static final int SIZE = 20;

  public MapScene(Image image) {
    _image = image;
  }

  public void setImage(Image image)
  {
	  _image = image;
  }

  /**
   * Call this method whenever something in the map has changed that
   * requires the map to be redrawn.
   */
  private void changeNotify() {
    if (_listener != null) _listener.stateChanged(null);
  }


  /**
   * This method will draw the entire map.
   */
  
  public void draw(Graphics2D g) {
    // Draw the map image
    g.drawImage(_image, 0, 0, null);

    //Update Lines
    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    
    
    if (_lineStart != null && _lineEnd != null) 
    {
    	g.drawLine(_lineStart.x, _lineStart.y, _lineEnd.x, _lineEnd.y);
    }
    
    if(MapEditor.displayPaths.isSelected())
    {
	    //for(Path p : MapEditor.paths)
	    for(int i = 0; i < MapEditor.paths.size(); i+=2)
    	{
	    	Path p = MapEditor.paths.get(i);
	    	if(p.getStart() != null && p.getEnd() != null)
	    	{
	    		if(p.isSelected)
	    		{
	    			g.setColor(Color.YELLOW);
	    		}
	    		else if(p.isDirectionEnabled)
	    		{
	    			g.setColor(Color.GREEN);
	    		}
	    		else if(p.getStart().beingModified || p.getEnd().beingModified)
	    		{
	    			g.setColor(Color.PINK);
	    		}
	    		else
	    		{
	    			g.setColor(Color.BLUE);
	    		}
	    		g.drawLine(p.getStart().getX(),p.getStart().getY(),p.getEnd().getX(),p.getEnd().getY());
	    		
	    	}
	    }
    }
    
    //Update points
    if(MapEditor.displayVertices.isSelected())
    {
		for(Vertex v : MapEditor.points)
		{
			if(v.isSelected)
			{
				g.setColor(Color.YELLOW);
			}
			else if(v.beingModified)
			{
				g.setColor(Color.PINK);
			}
			else
			{
				g.setColor(Color.RED);
			}
		    g.fillOval((int) v.getX() - SIZE/2, (int) v.getY() - SIZE/2, SIZE, SIZE);
		    if(MapEditor.printNames.isSelected())
		    {
		    	g.setColor(Color.BLACK);
		    	g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		    	g.drawString(v.getName(), (int) v.getX()-(v.getName().length()/2 * SIZE/4)-5, (int) v.getY()-10);
		    }
		}
    }
    
    
  }


  public void mouseMoved()
  {
	  changeNotify(); 
  }
  
  public void mousePressed(Point p) {
    // Mark the beginning of the line
    _lineEnd = null;
    _lineStart = p;
  }

  public void mouseReleased()
  {
	  _lineStart = null;
	  _lineEnd = null;
	  changeNotify();  
  }
  
  public void mouseClicked()
  {
	  changeNotify();
  }
  
  public void mouseDragged(Point p) {
    // Mark the end of the line
    _lineEnd = p;
    changeNotify(); // redraw the map
  }

  public int getWidth() { return _image.getWidth(null); }
  public int getHeight() { return _image.getHeight(null); }


  public void addChangeListener(ChangeListener listener) {
    _listener = listener;
  }
}
