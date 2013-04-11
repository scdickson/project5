

import java.awt.*;
import java.awt.geom.*;

public class Location {
  private static final int SIZE = 20;
  private final static float dash1[] = {3.0f};
  private final static BasicStroke dashed = new BasicStroke(1.0f, 
                                            BasicStroke.CAP_BUTT, 
                                            BasicStroke.JOIN_MITER, 
                                            3.0f, dash1, 0.0f);

  private Point _point;
  private boolean _selected;

  public Location(Point point) {
    _point = point;
  }

  public void draw(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval((int) _point.getX() - SIZE/2, 
               (int) _point.getY() - SIZE/2, 
               SIZE, SIZE);
  }

  public void drawSelect(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.setStroke(dashed);
    g.drawRect((int) _point.getX() - SIZE/2,
               (int) _point.getY() - SIZE/2,
               SIZE, SIZE);
  }

  /**
   * Return true if this point is inside of this location.
   */
  public boolean isThisYou(Vertex p) {
    int x = (int) _point.getX();
    int y = (int) _point.getY();
    int px = (int) p.getX();
    int py = (int) p.getY();
    int radius = SIZE/2;
    return px > x - radius && px < x + radius && 
           py > y - radius && py < y + radius;
  }
}
