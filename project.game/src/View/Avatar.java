package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Config;
import src.Model.Document;
import src.Model.Entity;
import src.Model.Wall;

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

  public void paintVisionField( Graphics g, int x, int y, int width, int height )
  {
    if( m_entity instanceof Wall || m_entity instanceof Document ) return;
    g.setColor( VISION_FIELD_COLOR );
    g.fillOval( x, y, width, height );
  }

  public Entity getEntity()
  {
    return m_entity;
  }

  protected void setEntity( Entity e )
  {
    m_entity = e;
  }
  
  static final long ANIMATION_TIME = 50; // marche plus à cause de factory
  
  protected void updateAnimation( int img_num )
  {
  }
  
}
