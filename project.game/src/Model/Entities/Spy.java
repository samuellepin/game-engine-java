package src.Model.Entities;

import java.awt.event.KeyEvent;
import java.lang.reflect.Method;

import src.Controller;
import src.AI.Direction;
import src.Model.Angle;
import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Collision.Collision;
import src.Model.World.Map;
import src.View.View;

public class Spy extends Entity
{
  private boolean m_isUsingRobot;
  private Robot   m_robot;

  public Spy()
  {
    super();
    m_isUsingRobot = false;
    m_robot = new Robot();
    m_robot.setFSM( "Robot" );
    m_robot.setId( -this.getId() );
    m_robot.setDim( 25, 25 );
    m_robot.setVelocity( 0.15 );
    m_robot.setMaxHP( 100 );
    m_robot.setHasCollision( true );
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
    if( m_isUsingRobot ) return;
    m_isUsingRobot = true;
    Model.getInstance().addEntity( m_robot );
    try
    {
//      m_robot.setMaxHP( 100 );
      m_robot.setPos( this.getPos().clone() );
      m_robot.translate( this.getWidth() + 10, 0 );
      m_robot.setOrientation( this.getOrientation().clone() );
      m_robot.setTracker( super.m_tracker );
      Model.getInstance().getTrackers().get( 0 ).changeTarget( m_robot );
    }
    catch ( CloneNotSupportedException e )
    {
      e.printStackTrace();
    }
    m_brain.step();
  }

  @Override
  public void moveTracker()
  {
    EntityTracker tracker = this.getTracker();
    if( m_isUsingRobot )
    {
      tracker = m_robot.getTracker();
    }
    if( tracker != null )
    {
      tracker.getListener().moved();
    }
  }

  @Override
  public void tick( long dt )
  {
    if( m_isUsingRobot )
    {
      if( m_robot.isDead() )
      {
        m_isUsingRobot = false;
        Model.getInstance().removeEntity( m_robot );
        Model.getInstance().getTrackers().get( 0 ).changeTarget( this );
      }
      else
      {
        m_robot.tick( dt );
      }
    }
    else
    {
      super.tick( dt );
    }
  }

  @Override
  public Angle getOrientation()
  {
    if( m_isUsingRobot )
    {
      return m_robot.getOrientation();
    }
    return super.getOrientation();
  }
}
