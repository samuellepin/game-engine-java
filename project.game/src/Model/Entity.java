package src.Model;

import src.AI.Automaton;
import src.AI.State;
import src.Game;

public abstract class Entity
{
  protected Automaton m_automaton;
  protected State     m_state;
  protected long      m_elapsedTime;
  protected Vector    m_pos;

  public Entity( Automaton automaton )
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
  
  public void setPos( Vector pos )
  {
    m_pos = pos;
  }
}
