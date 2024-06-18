package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Entity;

public class WallAvatar extends Avatar
{

  public WallAvatar( Entity e )
  {
    super( e );
  }

  private static final Color COLOR = Color.black;
  
  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    g.setColor( COLOR );
    g.fillRect( x, y, width, height );
    super.paintHitbox( g, x, y, width, height );
  }
  
}
