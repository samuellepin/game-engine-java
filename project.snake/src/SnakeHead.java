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
    return Grid.getInstance().checkCell( m_cell, getCardinal( direction ), category );
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
    m_direction = getCardinal( direction );
    move();
    return true;
  }

  private DIRECTION getCardinal( DIRECTION direction )
  {
    DIRECTION cardinal = m_direction;
    if( direction == DIRECTION.RIGHT )
    {
      switch ( m_direction )
      {
      case RIGHT:
        cardinal = DIRECTION.DOWN;
        break;
      case DOWN:
        cardinal = DIRECTION.LEFT;
        break;
      case LEFT:
        cardinal = DIRECTION.UP;
        break;
      case UP:
        cardinal = DIRECTION.RIGHT;
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
        cardinal = DIRECTION.UP;
        break;
      case DOWN:
        cardinal = DIRECTION.RIGHT;
        break;
      case LEFT:
        cardinal = DIRECTION.DOWN;
        break;
      case UP:
        cardinal = DIRECTION.LEFT;
        break;
      default:
        break;
      }
    }
    return cardinal;
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
