package src.Model.Entities;

import src.AI.Brain;
import src.Model.Collision.AABB;
import src.AI.CategoryFsm;
import src.AI.Direction;
import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Collision.Collision;

import java.util.ArrayList;
import java.util.Iterator;

public class Spy extends Entity
{
  private boolean m_robotMade;
  private Robot   m_robot;

  private boolean m_isInBox;
  private Box     m_box;

  private Entity  m_itemSelected;

  public Spy()
  {
    super();
    m_robot = new Robot();
    m_robot.setFSM( "Robot" );
    m_robot.setId( -this.getId() );
    m_robot.setDim( 25, 25 );
    m_robot.setVelocity( 0.15 );
    m_robot.setMaxHP( 100 );
    m_robot.setHasCollision( true );

    m_isInBox = false;

    m_itemSelected = null;
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
        double              max           = Math.max( m_hitbox.getHeight(), m_hitbox.getWidth() );
        hb.translate( max * Math.cos( exploreDir.toAngle( m_orientation ) ),
            max * Math.sin( exploreDir.toAngle( m_orientation ) ) );
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
      for ( EntityTracker tracker : Model.getInstance().getTrackers() )
      {
        if( tracker.getTarget().equals( this ) )
        {
          tracker.changeTarget( m_robot );
          m_robot.setTracker( tracker );
        }
      }
    }
    catch ( CloneNotSupportedException e )
    {
      throw new RuntimeException( "Strange clone fail in robot.clone()" );
    }
  }

  @Override
  public void moveTracker()
  {
    EntityTracker tracker = this.getTracker();
    if( m_isInBox )
    {
      tracker = m_box.getTracker();
    }
    if( tracker != null )
    {
      tracker.getListener().moved();
    }
  }

  @Override
  public void tick( long dt )
  {
    if( m_box != null && m_box.isExplosed() )
    {
      m_itemSelected = null;
      this.setPos( m_box.getX() + m_box.getWidth()+5, m_box.getY() );
      m_isInBox = false;
      super.setVisible( true );
      Model.getInstance().getTrackers().get( 0 ).changeTarget( this );
    }
    if( m_isInBox )
    {
      m_box.tick( dt );
    }
    else
    {
      super.tick( dt );
    }
  }

  @Override
  public boolean getCell( Direction dir, CategoryFsm cat )
  {
    for ( Entity e : Model.getInstance().getEntities() )
    {
      if( this != e && Collision.detect( e.getHitbox(), this.getHitbox() ) )
      {
        if( e.getCategoryType() == cat.getType() )
        {
          m_itemSelected = e;
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void doPick( double orientation )
  {
    if( m_itemSelected instanceof Box )
    {
      m_box = (Box)m_itemSelected;
      super.setVisible( false );
      m_box.setTracker( super.m_tracker );
      Model.getInstance().getTrackers().get( 0 ).changeTarget( m_box );
      m_box.setEmpty( false );
      m_brain.step();
    }
  }
}
