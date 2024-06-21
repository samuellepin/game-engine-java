package src.Model;

import src.AI.FSM;

public class Camera extends Entity
{
  private double ratio;
  public Camera( FSM fsm )
  {
    super( null );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
    ratio=0.1;
  }
  
  @Override
  public void doTurn( double orientation )
  {
    m_orientation+=orientation*ratio;
    m_brain.step();
  }
}
