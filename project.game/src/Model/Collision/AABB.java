package src.Model.Collision;

import src.Model.Vector;

public class AABB implements Hitbox
{
  private Vector m_min;
  private Vector m_max;

  public AABB( Vector pmin, Vector pmax )
  {
    m_min = pmin;
    m_max = pmax;
  }

  public AABB( double minX, double minY, double maxX, double maxY )
  {
    m_min = new Vector( minX, minY );
    m_max = new Vector( maxX, maxY );
  }

  public Vector getMin()
  {
    return m_min;
  }

  public Vector getMax()
  {
    return m_max;
  }

  public void resize( Vector pmin, Vector pmax )
  {
    m_min = pmin;
    m_max = pmax;
  }
  
  public double getWidth()
  {
    return m_max.getX() - m_min.getX();
  }
  
  public double getHeight()
  {
    return m_max.getY() - m_min.getY();
  }
  
  private static double min( double a, double b )
  {
    return a < b ? a : b;
  }
  
  private static double max( double a, double b )
  {
    return a > b ? a : b;
  }
  
  public static AABB merge( AABB hitbox1, AABB hitbox2 )
  {
    double minX = AABB.min( hitbox1.getMin().getX(), hitbox2.getMin().getX() );
    double minY = AABB.min( hitbox1.getMin().getY(), hitbox2.getMin().getY() );
    double maxX = AABB.max( hitbox1.getMin().getX(), hitbox2.getMin().getX() );
    double maxY = AABB.max( hitbox1.getMin().getY(), hitbox2.getMin().getY() );
    
    return new AABB( minX, minY, maxX, maxY );
  }
}
