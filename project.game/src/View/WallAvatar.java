package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import src.Model.Wall;

public class WallAvatar extends Avatar
{

  private static final long serialVersionUID = 1L;

  private Wall m_wall;
  
  WallAvatar (Wall wall)
  {
    m_wall = wall;
  }
  
  @Override
  public void paint( Graphics g )
  {
    g.setColor( Color.pink );
    Rectangle rect = g.getClipBounds();
    g.fillRect( 0, 0, rect.width, rect.height );
    
  }

}
