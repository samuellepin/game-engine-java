package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

public class Camera extends Entity
{
  private Alarm               m_ownAlarm;
  private static final double RATIO = 0.1;

  public Camera()
  {
    super();
    super.setDim( 25, 25 );
    super.setHasCollision( false );
    m_ownAlarm = new Alarm( this );
  }

  @Override
  public void doTurn( double orientation )
  {
    m_orientation.add( ( orientation - m_orientation.getValue() ) * RATIO );
    m_brain.step();
  }
}
