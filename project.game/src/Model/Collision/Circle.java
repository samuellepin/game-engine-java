package src.Model.Collision;

import src.Model.Vector;

public class Circle implements Hitbox
{
  private Vector m_topLeftCorner;
  private double m_radius;

  public Circle( Vector topLeftCorner, double radius )
  {
    m_topLeftCorner = topLeftCorner;
    m_radius = radius;
  }
  
  public Circle( double x, double y, double radius )
  {
    m_topLeftCorner = new Vector( x, y );
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

  public Vector getCenter()
  {
    return new Vector( m_topLeftCorner.getX() + this.getRadius(), m_topLeftCorner.getY() + this.getRadius() );
  }
  
  public Vector getTopLeftCorner()
  {
    return m_topLeftCorner;
  }
  
  @Override
  public String toString()
  {
    return "(x=" + this.getCenter().getX() + ", y=" + this.getCenter().getY() + ", r=" + this.getRadius() + ")";
  }
}
