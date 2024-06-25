package src.Model.Entities;

import java.util.List;

import src.AI.Brain;
import src.AI.Direction;
import src.Model.Entity;
import src.Model.Model;
import src.Model.EntityTracker;

public class Robot extends Entity
{
  private Spy m_spy;

  public Robot()
  {
    super();
  }

  public void setSpy( Spy spy )
  {
    m_spy = spy;
  }

  @Override
  public void doEgg( Direction dir )
  {
    m_brain.step();
    Brain brobot = m_spy.getBrain();
    Brain bspy   = getBrain();
    m_spy.setBrain( bspy );
    setBrain( brobot );
    brobot.setEntity( this );
    bspy.setEntity( m_spy );
    for ( EntityTracker tracker : Model.getInstance().getTrackers() )
    {
      if( tracker.getTarget().equals( this ) )
      {
        tracker.setTarget( m_spy );
        tracker.centerOnTarget();
        m_spy.setTracker( tracker );
      }
    }
  }

  @Override
  public void doWizz( List< Object > parameters )
  {

  }

  @Override
  public String toString()
  {
    return "Robot - " + super.toString();
  }
}
