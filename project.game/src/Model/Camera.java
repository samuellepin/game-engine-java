package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
// security camera for MG
public class Camera extends Entity
{
  private static final double RATIO = 0.1;

  @Override
  public void doTurn( double orientation )
  {
    m_orientation.add( ( orientation - m_orientation.getValue() ) * RATIO );
    m_brain.step();
  }
}
