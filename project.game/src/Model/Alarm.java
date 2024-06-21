package src.Model;

import src.Model.Collision.Circle;

public class Alarm
{
  private Vector  m_pos;
  private Circle  m_zoneAlarm;
  private double  m_radius;
  private boolean m_isActive;

  public Alarm(Entity entity)
  {
    m_radius = 200;
    m_pos=entity.getPos();
    m_zoneAlarm = new Circle( m_pos, m_radius );
  }

  public void alarmePos( Vector pos )
  {
    m_pos = pos;
  }

  public void alerte()
  {
    
  }
}
