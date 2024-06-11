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
        (int)m_points[ 0 ].getRX(), 
        (int)m_points[ 1 ].getRX(), 
        (int)m_points[ 2 ].getRX(), 
        (int)m_points[ 3 ].getRX() };
  }

  public int[] getRY()
  {
    return new int[] { 
        (int)m_points[ 0 ].getRY(), 
        (int)m_points[ 1 ].getRY(), 
        (int)m_points[ 2 ].getRY(), 
        (int)m_points[ 3 ].getRY() };
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
