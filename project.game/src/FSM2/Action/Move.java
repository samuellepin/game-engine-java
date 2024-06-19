package src.FSM2.Action;

import src.Model.Entity;

public class Move implements Action
{
  @Override
  public void run( Entity entity )
  {
    entity.doMove( 0 );
  }

}
