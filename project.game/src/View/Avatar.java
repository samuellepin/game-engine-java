package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;

public abstract class Avatar
{
  protected Entity           m_entity;

  private static final Color HITBOX_COLOR = Color.yellow;

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

  public Entity getEntity()
  {
    return m_entity;
  }

  protected void setEntity( Entity e )
  {
    m_entity = e;
  }
}
