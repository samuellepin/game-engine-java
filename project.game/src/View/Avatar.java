package src.View;

import java.awt.Component;
import java.awt.Graphics;

import src.Model.Entity;

public abstract class Avatar extends Component
{
  protected Entity m_entity;

  public abstract void paint( Graphics g, int x, int y, int width, int height );

  public Entity getEntity()
  {
    return m_entity;
  }
}
