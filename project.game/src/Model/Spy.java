package src.Model;

import java.util.ArrayList;
import java.util.Iterator;

import src.AI.Brain;
import src.AI.Direction;
import src.Model.Collision.AABB;
import src.Model.Collision.Collision;

public class Spy extends Entity
{
  boolean m_robotMade;
  Robot   m_robot;

  public Spy()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Spy - " + super.toString();
  }

  @Override
  public void getHit( int damage )
  {
    this.subHP( damage );
  }

  @Override
  public void doEgg( Direction dir )
  {
    try
    {
      if( !m_robotMade )
      {
        m_robot = (Robot)Model.getInstance().getRobotReference().clone();
        m_robot.setSpy( this );
        ArrayList< Entity > modelEntities = Model.getInstance().getEntities();
        AABB                hb            = m_robot.getHitbox();
        Direction           exploreDir    = new Direction( dir.getDirection() );
        Iterator< Entity >  iter          = modelEntities.iterator();
        hb.translate( 2 * Math.cos( exploreDir.toAngle( m_orientation ) ),
            2 * Math.sin( exploreDir.toAngle( m_orientation ) ) );
        while( iter.hasNext() )
        {
          Entity e = iter.next();
          if( Collision.detect( hb, e.getHitbox() ) )
          {
            exploreDir.changeDirection();
            hb.translate( 2 * Math.cos( exploreDir.toAngle( m_orientation ) ),
                2 * Math.sin( exploreDir.toAngle( m_orientation ) ) );
            if( exploreDir.equals( dir ) )
            {
              m_brain.step();
              return;
            }
            iter = modelEntities.iterator();
          }
        }
        Model.getInstance().addQueue( m_robot );
        m_robotMade = true;
      }
      Brain brobot = m_robot.getBrain();
      Brain bspy   = getBrain();
      m_brain.step();
      m_robot.setBrain( bspy );
      setBrain( brobot );
      brobot.setEntity( this );
      bspy.setEntity( m_robot );
    }
    catch ( CloneNotSupportedException e )
    {
      throw new RuntimeException( "Strange clone fail in robot.clone()" );
    }
  }
}
