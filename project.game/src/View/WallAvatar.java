package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;

public class WallAvatar extends Avatar
{
  private int m_idx;

  public WallAvatar( Entity e, int idx )
  {
    super( e );
    m_idx = idx;
  }

  private static final Color COLOR = Color.black;

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    BufferedImage[] img = AvatarFactory.getInstance().getObstaclesSprite();
    if( img != null )
    {
      g.drawImage( img[ m_idx ], x, y, width, height, null );
    }
    else
    {
      g.setColor( COLOR );
      g.fillRect( x, y, width, height );
    }
  }

}
