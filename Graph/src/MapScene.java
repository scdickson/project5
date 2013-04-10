

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


  public MapScene(Image image) {
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

    // Draw the line
    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    if (_lineStart != null && _lineEnd != null) {
      g.drawLine(_lineStart.x, _lineStart.y, _lineEnd.x, _lineEnd.y);
    }
  }


  public void mousePressed(Point p) {
    // Mark the beginning of the line
    _lineEnd = null;
    _lineStart = p;
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
