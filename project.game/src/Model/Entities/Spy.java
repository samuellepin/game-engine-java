package src.Model.Entities;

import java.awt.event.KeyEvent;

import src.Controller;
import src.AI.Direction;
import src.Model.Entity;
import src.Model.Model;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Spy extends Entity
{
  private boolean m_isUsingRobot;
  private Robot m_robot;

  public Spy()
  {
    super();
    m_isUsingRobot = false;
    m_robot = new Robot();
    m_robot.setFSM( "Robot" );
    m_robot.setId( -this.getId() );
    m_robot.setDim( 25, 25 );
    m_robot.setVelocity( 2 * this.m_velocity );
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
      m_robot.setPos( this.getPos().clone() );
      m_robot.translate( this.getWidth()+10, 0 );
      m_robot.setOrientation( this.getOrientation().clone() );
    }
    catch ( CloneNotSupportedException e )
    {
      e.printStackTrace();
    }
    m_brain.step();
  }
  
  @Override
  public void tick( long dt )
  {
    if( m_isUsingRobot )
    {
      m_robot.tick( dt );
      if( Controller.getInstance().isKeyDown( KeyEvent.VK_SPACE ) )
      {
        m_robot.doMove( m_robot.getOrientation().getValue() );
      }
    }
    else
    {
      super.tick( dt ); 
    }
  }
}
