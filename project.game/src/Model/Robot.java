package src.Model;

import java.util.List;

import src.AI.Brain;
import src.AI.Direction;

public class Robot extends Entity
{
  private Spy m_spy;
  
  public Robot()
  {
    super();
  }
  
  public void setSpy( Spy spy)
  {
    m_spy = spy;
  }
  
  @Override
  public void doEgg( Direction dir )
  {
    m_brain.step();
    Brain brobot = m_spy.getBrain();
    Brain bspy = getBrain();
    m_spy.setBrain( bspy );
    setBrain( brobot );
    brobot.setEntity( this );
    bspy.setEntity( m_spy );
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
