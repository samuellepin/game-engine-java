package src;

import src.Grid.Cell;
import src.AI.Automaton;
import src.AI.SnakeHeadAutomaton;
import src.AI.AppleAutomaton;
import src.AI.State;

public class SnakeHead extends Entity
{
  public SnakeHead( Automaton automaton, Cell cell )
  {
    super( automaton, cell );
  }

  @Override
  public boolean cell( DIRECTION direction, CATEGORY category )
  {
    return Grid.getInstance().checkCell( m_cell, direction, category );
  }

  @Override
  public boolean move( DIRECTION direction )
  {
    m_direction = direction;
    return Grid.getInstance().move( this );
  }

  public boolean isDead()
  {
    return this.getState() == SnakeHeadAutomaton.STATE_DEAD;
  }
}
