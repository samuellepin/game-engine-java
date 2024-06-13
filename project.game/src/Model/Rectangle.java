package src.Model;

public class Rectangle extends Entity
{
  private Vector[] m_points;

  public Rectangle( Vector p1, Vector p2, Vector p3, Vector p4 )
  {
    super( null );
    m_points = new Vector[] { p1, p2, p3, p4 };
  }

  public int[] getRX()
  {
    return new int[] { 
        (int)m_points[ 0 ].getVX(), 
        (int)m_points[ 1 ].getVX(), 
        (int)m_points[ 2 ].getVX(), 
        (int)m_points[ 3 ].getVX() };
  }

  public int[] getRY()
  {
    return new int[] { 
        (int)m_points[ 0 ].getVY(), 
        (int)m_points[ 1 ].getVY(), 
        (int)m_points[ 2 ].getVY(), 
        (int)m_points[ 3 ].getVY() };
  }
  
  public int getNum()
  {
    return m_points.length;
  }
  
  public Vector[] getPoints()
  {
    return m_points;
  }
}
