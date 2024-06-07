package src;

import src.Grid.Cell;
import src.AI.Automaton;
import src.AI.SnakeHeadAutomaton;
import src.AI.AppleAutomaton;
import src.AI.State;

public class SnakeHead extends Entity
{
  private SnakeTail m_snakeTail;
  private int       m_length;

  public SnakeHead( Automaton automaton, Cell cell )
  {
    super( automaton, cell );
    m_length = 1;
  }

  @Override
  public boolean cell( DIRECTION direction, CATEGORY category )
  {
    return Grid.getInstance().checkCell( m_cell, m_direction, category );
  }

  @Override
  public boolean move()
  {
    return Grid.getInstance().move( this );
  }

  public boolean isDead()
  {
    return this.getState() == SnakeHeadAutomaton.STATE_DEAD;
  }

  @Override
  public boolean turn( DIRECTION direction )
  {
    if( direction == DIRECTION.RIGHT )
    {
      switch ( m_direction )
      {
      case RIGHT:
        m_direction = DIRECTION.DOWN;
        break;
      case DOWN:
        m_direction = DIRECTION.LEFT;
        break;
      case LEFT:
        m_direction = DIRECTION.UP;
        break;
      case UP:
        m_direction = DIRECTION.RIGHT;
        break;
      default:
        break;
      }
    }
    else if( direction == DIRECTION.LEFT )
    {
      switch ( m_direction )
      {
      case RIGHT:
        m_direction = DIRECTION.UP;
        break;
      case DOWN:
        m_direction = DIRECTION.RIGHT;
        break;
      case LEFT:
        m_direction = DIRECTION.DOWN;
        break;
      case UP:
        m_direction = DIRECTION.LEFT;
        break;
      default:
        break;
      }
    }
    move();
    return true;
  }

//  public void eat()
//  {
//    if( m_snakeTail == null )
//    {
//      m_snakeTail = new SnakeTail(  )
//    }
//    while( m_snakeTail.getNext() != null )
//    {
//      
//    }
//    m_length++;
//  }
}
