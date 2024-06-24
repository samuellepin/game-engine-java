package src.Model;

import java.util.ArrayList;

import src.Model.Collision.Circle;
import src.Model.Collision.Collision;

public class Alarm
{
  private Vector  m_pos;
  private Circle  m_alarmArea;
  private double  m_radius;
  private boolean m_isActive;
  private double  m_timer;

  public Alarm( Entity entity )
  {
    m_radius = 500;
    m_pos = entity.getPos();
    m_alarmArea = new Circle( m_pos, m_radius );
    m_isActive = false;
  }

  public void alert()
  {
    ArrayList< Entity > entities = Model.getInstance().getEntities();
    m_isActive = true;
    m_timer = 10000;
    for ( Entity e : entities )
    {
      if(true)
      if( e instanceof Guard )
      {
        if( Collision.detect( m_alarmArea, e.getHitbox() ) )
        {
          System.out.println(e.getX()+" "+e.getY());
          ( (Guard)e ).setAlarm( this );
        }
      }
    }
  }

  public boolean timer( long elapsed )
  {
    m_timer -= elapsed;
    if( m_timer <= 0 )
    {
      m_isActive = false;
      return false;
    }
    return true;
  }

  public boolean isActive()
  {
    return m_isActive;
  }
}
