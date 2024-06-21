package src.Model;

import src.AI.FSM;

public class Camera extends Entity
{

  public Camera( FSM fsm )
  {
    super( null );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
  }

}
