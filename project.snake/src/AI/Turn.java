package src.AI;

import src.DIRECTION;
import src.Entity;

public class Turn implements Action
{
  private DIRECTION m_direction;

  public Turn( DIRECTION direction )
  {
    m_direction = direction;
  }

  @Override
  public boolean run( Entity entity )
  {
    return entity.turn( m_direction );
  }

}