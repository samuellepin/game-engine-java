package src.Model;

import java.util.ArrayList;

import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.Entities.Guard;

public class Alarm
{
  private Vector  m_pos;
  private Circle  m_alarmArea;
  private double  m_radius;
  private boolean m_isActive;
  private double  m_timer;
  private Vector  m_opponentPos;

  public Alarm( Entity entity )
  {
    m_radius = 200;
    m_pos = entity.getPos();
    m_alarmArea = new Circle( m_pos, m_radius );
    m_isActive = false;
  }

  public Vector getOpponentPos()
  {
    return m_opponentPos;
  }

  public void alert()
  {
    ArrayList< Entity > entities = Model.getInstance().getEntities();
    m_isActive = true;
    m_timer = 1000;
    Entity player1 = Model.getInstance().getPlayer1();
    Entity player2 = Model.getInstance().getPlayer2();
    if( Collision.detect( player1.getHitbox(), m_alarmArea ) )
    {
      m_opponentPos = player1.getPos();
    }
    else if( Collision.detect( player2.getHitbox(), m_alarmArea ) )
    {
      m_opponentPos = player2.getPos();
    }
    for ( Entity e : entities )
    {
      if( true ) if( e instanceof Guard )
      {
        if( Collision.detect( e.getHitbox(), m_alarmArea ) )
        {
          System.out.println( e.getX() + " " + e.getY() );
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
