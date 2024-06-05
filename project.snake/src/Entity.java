package src;

import src.Grid.Cell;
import src.AI.Automaton;
import src.AI.State;

public abstract class Entity
{
  protected Automaton m_automaton;
  protected State     m_state;
  protected Grid.Cell m_cell;
  protected DIRECTION m_direction;

  protected Entity( Automaton automaton, Cell cell )
  {
    m_automaton = automaton;
    m_state = automaton.getInitialState();
    m_cell = cell;
    m_direction = DIRECTION.UP;
  }
  
  public boolean cell( DIRECTION direction, CATEGORY category )
  {
    return false;
  }

  public boolean move( DIRECTION direction )
  {
    return false;
  }

  public void tick( int elapsed )
  {
    m_automaton.nextState( this );
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
}
