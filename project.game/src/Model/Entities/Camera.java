package src.Model.Entities;

import src.Model.Alarm;
import src.Model.Entity;

// security camera for MG
public class Camera extends Entity
{
  private Alarm               m_ownAlarm;

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
    m_orientation.add( m_elapsedTime * m_velocity );
    m_brain.step();
  }
}
