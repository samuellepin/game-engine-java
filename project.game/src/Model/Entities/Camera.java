package src.Model.Entities;

import src.Model.Alarm;
import src.Model.Entity;

// security camera for MG
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
