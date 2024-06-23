package src.Model;

public class Shot
{
  private static final double LIMIT = 500;
  
  private Vector m_pos;
  private double m_orientation;
  private double m_velocity;
  private boolean m_hasTouched;
  private double m_distance;
  
  public Shot( Vector pos, double orientation, double velocity )
  {
    m_pos = pos;
    m_orientation = orientation;
    m_velocity = velocity;
  }
  
  public update()
  {
    if( this.hasTouched() ) return;
  }
  
  public boolean hasTouched()
  {
    return m_hasTouched;
  }
}
