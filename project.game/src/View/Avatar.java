package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Config;
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
    if( !Config.getInstance().getView().shouldPaintHitbox() )
    {
      return;
    }
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
    if( !Config.getInstance().getView().shouldPaintVisionField() )
    {
      return;
    }
    Config cfg = Config.getInstance();
    double zoom = cfg.getView().getZoom();
    int r = (int) ( cfg.getParameters().getVisionFieldRadius() * zoom  );
    g.setColor( VISION_FIELD_COLOR );
    x += m_entity.getWidth() * zoom / 2;
    y += m_entity.getHeight() * zoom / 2;
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
  
  static final long ANIMATION_TIME = 50; // marche plus à cause de factory
  
  protected void updateAnimation( int img_num )
  {
  }
  
}
