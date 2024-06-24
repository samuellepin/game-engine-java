package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

public class Camera extends Entity
{
  private double ratio;
  
  public Camera( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options, hp );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
    ratio=0.1;
  }
  
  @Override
  public void doTurn( double orientation )
  {
    m_orientation+=(orientation-m_orientation)*ratio;
    m_brain.step();
  }
}
