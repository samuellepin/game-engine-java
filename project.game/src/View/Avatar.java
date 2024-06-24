package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Angle;
import src.Model.Document;
import src.Model.Entity;
import src.Model.Wall;

public abstract class Avatar
{

  static final long          ANIMATION_TIME     = 50;

  protected Entity           m_entity;
  protected AvatarFactory    m_factory;

  private static final Color HITBOX_COLOR       = Color.yellow;
  private static final Color VISION_FIELD_COLOR = new Color( 200, 0, 255, 100 );

  public Avatar( Entity e )
  {
    this.setEntity( e );
    m_factory = AvatarFactory.getInstance();
  }

  public abstract void paint( Graphics g, int x, int y, int width, int height );

  public void paintHitbox( Graphics g, int x, int y, int width, int height )
  {
    if( width < 0 )
    {
      width *= -1;
      x -= width;
    }
    g.setColor( HITBOX_COLOR );
    g.drawRect( x, y, width, height );
  }

  public void paintVisionField( Graphics g, int x, int y, int width, int height, double startAngle, double arcAngle )
  {
    if( m_entity instanceof Wall || m_entity instanceof Document ) return;
    g.setColor( VISION_FIELD_COLOR );
    Angle start = new Angle( startAngle );
    Angle total = new Angle( arcAngle );
    g.fillArc( x, y, width, height, -(int)start.toDegree(), -(int)total.toDegree() );
  }

  public Entity getEntity()
  {
    return m_entity;
  }

  protected void setEntity( Entity e )
  {
    m_entity = e;
  }
  
  public BufferedImage[][] loadAnimation4( BufferedImage[][] img, String src, int num )
  {
    if( img == null )
    {
      img = new BufferedImage[ 4 ][];
      String ext = ".png";
      try
      {
        img[ 0 ] = AvatarFactory.loadSprite( src + "Up" + ext, 1, num );
        img[ 1 ] = AvatarFactory.loadSprite( src + "Right" + ext, 1, num );
        img[ 2 ] = AvatarFactory.loadSprite( src + "Down" + ext, 1, num );
        img[ 3 ] = AvatarFactory.loadSprite( src + "Left" + ext, 1, num );
      }
      catch ( IOException e1 )
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    return img;
  }
}
