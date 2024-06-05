package src.AI;

import src.DIRECTION;
import src.Entity;

public class Move implements Action
{
  private DIRECTION m_direction;

  public Move( DIRECTION direction )
  {
    m_direction = direction;
  }

  @Override
  public boolean run( Entity entity )
  {
    return entity.move( m_direction );
  }

}
