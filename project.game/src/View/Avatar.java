package src.View;

import java.awt.Graphics;

import src.Model.Entity;

public abstract class Avatar
{
  protected Entity m_entity;

  public abstract void paint( Graphics g );

  public Entity getEntity()
  {
    return m_entity;
  }

  public int getWidth( Graphics g )
  {
    return (int)g.getClipBounds().getWidth();
  }
  
  public int getHeight( Graphics g )
  {
    return (int)g.getClipBounds().getHeight();
  }
}
