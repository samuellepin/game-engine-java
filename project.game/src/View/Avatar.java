package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
    g.setColor( HITBOX_COLOR );
    g.drawRect( x, y, width, height );
  }

  public void paintVisionField( Graphics g, int x, int y )
  {
    int r = (int)Config.VISION_FIELD_RADIUS;
    g.setColor( VISION_FIELD_COLOR );
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
}
