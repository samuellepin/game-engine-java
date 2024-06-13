package src.View;

import java.awt.Component;
import java.awt.Graphics;

import src.Model.Entity;

public abstract class Avatar extends Component
{
  protected Entity m_entity;

  public abstract void paint( Graphics g );

  public Entity getEntity()
  {
    return m_entity;
  }
}
