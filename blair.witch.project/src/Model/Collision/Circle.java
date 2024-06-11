package src.Model.Collision;

import src.Model.Vector;

public class Circle implements Hitbox
{
  private Vector m_center;
  private double m_radius;

  public Circle( Vector center, double radius )
  {
    m_center = center;
    m_radius = radius;
  }

  public void setRadius( double radius )
  {
    m_radius = radius;
  }

  public double getRadius()
  {
    return m_radius;
  }

  public void setCenter( Vector center )
  {
    m_center = center;
  }

  public Vector getCenter()
  {
    return m_center;
  }
}
