package src.Model;

import src.AI.FSM;
import src.AI.State;
import src.Game;

public abstract class Entity
{
  protected FSM m_automaton;
  protected State     m_state;
  protected long      m_elapsedTime;
  protected Vector    m_pos;
  protected double    m_width, m_height;
  protected double    m_orientation;
  protected Vector    m_velocity;

  public Entity( FSM automaton )
  {
    m_automaton = automaton;
    if( m_automaton != null ) m_state = automaton.getInitialState();
    m_elapsedTime = 0;
    m_pos = new Vector( 0, 0 );
  }

  public void tick( long elapsed )
  {
    m_elapsedTime += elapsed;
  }

  public State getState()
  {
    return m_state;
  }

  public Vector getPos()
  {
    return m_pos;
  }

  public double getX()
  {
    return m_pos.getX();
  }

  public double getY()
  {
    return m_pos.getY();
  }

  public double getVX()
  {
    return m_pos.getVX() - m_width / 2;
  }

  public double getVY()
  {
    return m_pos.getVY() - m_height / 2;
  }

  public void setPos( Vector pos )
  {
    m_pos = pos;
  }

  public void setWidth( double width )
  {
    m_width = width;
  }

  public double getWidth()
  {
    return m_width;
  }

  public void setHeight( double height )
  {
    m_height = height;
  }

  public double getHeight()
  {
    return m_height;
  }

  public double getOrientation()
  {
    return m_orientation;
  }

  public void setOrientation( double orientation )
  {
    m_orientation = orientation;
  }

  public void setVelocity( Vector velocity )
  {
    m_velocity = velocity;
  }

  public Vector getVelocity()
  {
    return m_velocity;
  }
}
