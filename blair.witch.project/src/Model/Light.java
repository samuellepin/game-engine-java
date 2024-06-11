package src.Model;

public class Light extends Entity
{
  private double m_radius;
  
  public Light( double x, double y, double r )
  {
    super( null );
    m_radius = r;
    m_pos = new Vector( x, y );
  }
  
  public double getRadius()
  {
    return m_radius;
  }
}
