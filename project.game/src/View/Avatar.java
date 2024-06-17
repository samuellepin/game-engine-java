package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Config;
import src.Model.Entity;

public abstract class Avatar
{
  protected Entity           m_entity;

  private static final Color HITBOX_COLOR       = Color.yellow;
  private static final Color VISION_FIELD_COLOR = new Color( 200, 0, 255, 100 );

  public Avatar( Entity e )
  {
    this.setEntity( e );
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

  public void paintVisionField( Graphics g, int x, int y )
  {
    int r = (int) ( Config.VISION_FIELD_RADIUS * Config.RATIO );
    g.setColor( VISION_FIELD_COLOR );
    x += m_entity.getWidth() * Config.RATIO  / 2;
    y += m_entity.getHeight() * Config.RATIO / 2;
    g.fillOval( x - r, y - r, 2 * r, 2 * r );
  }

  public Entity getEntity()
  {
    return m_entity;
  }

  protected void setEntity( Entity e )
  {
    m_entity = e;
  }
  
  static final long ANIMATION_TIME = 50;
  
  protected void updateAnimation( int img_num )
  {
  }
  
}
