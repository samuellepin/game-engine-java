package src.Model.Entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import src.Config;
import src.Controller;
import src.Game;
import src.AI.Direction;

import src.Model.Alarm;
import src.Model.Angle;
import src.Model.Entity;
import src.Model.Model;
import src.Model.Shot;
import src.Model.Vector;
import src.Model.Collision.Collision;

public class Guard extends Entity
{
  private Alarm   m_ownAlarm;
  private Alarm   m_otherAlarm;
  private boolean m_isAlarmed;

  public Guard()
  {
    super();
    m_ownAlarm = new Alarm( this );
  }

  @Override
  public String toString()
  {
    return "Guard - " + super.toString();
  }

//  void follow( Entity entity )
//  {
//    Vector OP = entity.getBarycenter();
//    Vector OE = this.getBarycenter();
//    Vector EP = Vector.sub( OP, OE );
//    super.getOrientation().setValue( EP.getAngle() );
//    doMove( m_orientation.getValue() );
//  }

  private long countdown;

  void shot( long dt, Angle orientation )
  {
    countdown += dt;
    if( countdown > 50 )
    {
//      System.out.println( "Shot!" );
      Shot shot = new Shot( this.getBarycenter(), orientation );
      while( Collision.detect( m_hitbox, shot.getPos() ) )
      {
        shot.move( dt );
      }
      Model.getInstance().addShot( shot );
      countdown = 0;
    }
  }
  
  private Vector pointToVector( Point point ) // Point in pixel
  {
    Vector vec = new Vector( 0 , 0 );
    double r = 1/Config.getInstance().getView().getZoom();
    vec.setX( r * (point.getX()-Game.SCREEN_WIDTH/2) + m_tracker.getX() );
    vec.setY( r * point.getY() + m_tracker.getY() );
    return vec;
  }

  @Override
  public void doHit( double orientation, int damage )
  {
    Point pos = Controller.getInstance().getMousePos();
    Vector OC = this.getBarycenter();
    Vector OP = pointToVector( pos );
    Vector CP = Vector.sub( OP, OC );
    double dir = CP.getAngle();
    shot( m_elapsedTime, new Angle( dir ) );
//    ArrayList< Entity > entities = Model.getInstance().getEntities();
//    for ( Entity e : entities )
//    {
//      Vector  dist         = Vector.sub( e.getPos(), this.getPos() );
//
//      boolean closeEnough  = m_visionField.getRadius() >= dist.getMagnitude();
//      boolean correctAngle = orientation - ( Math.PI / 4 ) <= dist.getAngle();
//      correctAngle = correctAngle && dist.getAngle() <= orientation + ( Math.PI / 4 );
//
//      if( closeEnough && correctAngle )
//      {
////        Shot s = new Shot( this.getPos(), new Angle( dist.getAngle(), false ) );
//      }
//    }
    m_brain.step();
  }

  @Override
  public void tick( long elapsed )
  {
    super.tick( elapsed );
//    AABB h1 = Model.getInstance().getPlayer1().getHitbox();
//    System.out.println( "Tick " + this.toString() );
    if( Collision.detect( Model.getInstance().getPlayer1().getHitbox(), this.getVisionField() ) )
    {
//      System.out.println( "Collision : " + c1.toString() + " - " + c2.toString() );
      // follow( Model.getInstance().getPlayer1() );

// SHOT ICI
//      shot( elapsed );
    }
    if( m_ownAlarm.isActive() )// actif si le garde à déclanché son alarme
    {
      if( !m_ownAlarm.timer( elapsed ) )// pour actualiser le timer
      {
        m_isAlarmed = false;// peut-etre faux si les deux alarmes sont actives
      }
    }
  }

  @Override
  public void doMove( Direction dir )
  {
    if( dir.getDirection() == Direction.DIRECTION.Underscore )
    {
      Vector dist = Vector.sub( m_otherAlarm.getOpponentPos(), getPos() );
      m_moveDirection.setValue( dist.getAngle() );
    }
    else
    {
      m_moveDirection.setValue( dir.toAngle( m_orientation ) );
    }
    m_timeToWait = 20;
    m_isMoving = true;
  }

  @Override
  public void doWizz( List< Object > parameters )// déclanché après cell de l'automate
  {
    m_isAlarmed = true;
    m_ownAlarm.alert();
    m_otherAlarm = m_ownAlarm;
    m_brain.step();
  }

  @Override
  public boolean getGot()
  {
    return m_isAlarmed;
  }

  public void setAlarm( Alarm alarm )// déclanché par alarm si un autre garde détecte le joueur
  {
    m_otherAlarm = alarm;
    m_isAlarmed = true;
  }

}