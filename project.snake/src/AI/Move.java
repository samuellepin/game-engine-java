package src.AI;

import src.DIRECTION;
import src.Entity;

public class Move implements Action
{
  public Move()
  {
  }

  @Override
  public boolean run( Entity entity )
  {
    return entity.move();
  }

}
