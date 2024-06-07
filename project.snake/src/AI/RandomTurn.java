package src.AI;

import java.util.Random;

import src.DIRECTION;
import src.Entity;

public class RandomTurn implements Action
{
  private DIRECTION   m_direction;
  private DIRECTION[] m_direction_tab = { DIRECTION.LEFT, DIRECTION.RIGHT, DIRECTION.HERE };

  public RandomTurn( boolean left, boolean front, boolean right )
  {
    if( left && front && right )
    {
      m_direction = m_direction_tab[ new Random().nextInt( 3 ) ];
    }
    else if( right && front )
    {
      m_direction = m_direction_tab[ new Random().nextInt( 2 ) + 1 ];
    }
    else if( left && front )
    {
      m_direction = m_direction_tab[ new Random().nextInt( 2 ) * 2 ];
    }
    else if( right && left )
    {
      m_direction = m_direction_tab[ new Random().nextInt( 2 ) ];
    }
  }

  @Override
  public boolean run( Entity entity )
  {
    return entity.turn( m_direction );
  }

}
