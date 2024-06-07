package src;

import src.Grid.Cell;
import src.AI.Automaton;
import src.AI.State;

public abstract class Entity
{
  protected Automaton       m_automaton;
  protected State           m_state;
  protected Grid.Cell       m_cell;
  protected DIRECTION       m_direction;
  private long              m_elapsedTime;
  private static final long ANIMATION_SPEED = 500;

  protected Entity( Automaton automaton, Cell cell )
  {
    m_automaton = automaton;
    m_state = automaton.getInitialState();
    m_cell = cell;
    m_direction = DIRECTION.UP;
    m_elapsedTime = 0;
  }

  public boolean cell( DIRECTION direction, CATEGORY category )
  {
    return false;
  }

  public boolean move()
  {
    return false;
  }

  public void tick( long elapsed )
  {
    m_elapsedTime += elapsed;
    if( m_elapsedTime > ANIMATION_SPEED )
    {
      m_automaton.nextState( this );
      m_elapsedTime = 0;
    }
  }

  public void setCell( Cell c )
  {
    m_cell = c;
  }

  public State getState()
  {
    return m_state;
  }

  public Cell getCell()
  {
    return m_cell;
  }

  public DIRECTION getDirection()
  {
    return m_direction;
  }

  public boolean turn( DIRECTION direction )
  {
    return false;
  }
  
  public void setDirection(DIRECTION direction)
  {
    m_direction = direction;
  }
}
