package src.View;

import java.awt.Color;
import java.awt.Graphics;

public class CircularHitboxAvatar extends Avatar
{
  public static final Color COLOR = new Color( 200, 0, 255, 100 );
  
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.setColor( COLOR );
    g.fillOval( x, y, width, height );
  }
}
