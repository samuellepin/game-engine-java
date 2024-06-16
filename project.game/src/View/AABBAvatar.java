package src.View;

import java.awt.Color;
import java.awt.Graphics;

public class AABBAvatar extends Avatar
{
  private static final Color COLOR = new Color( 255, 255, 0, 180 );
  
  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.setColor( COLOR );
    g.drawRect( x, y, width, height );
  }
}
