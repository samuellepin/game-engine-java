package src.Model;

import java.util.ArrayList;
import java.util.List;
import src.AI.Direction;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

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

  void follow( Entity entity )
  {
    Vector OP = entity.getBarycenter();
    Vector OE = this.getBarycenter();
    Vector EP = Vector.sub( OP, OE );
    super.getOrientation().setValue( EP.getAngle() );
    doMove( m_orientation.getValue() );
  }

  private long countdown;

  void shot( long dt )
  {
    countdown += dt;
    if( countdown > 1000 )
    {
      System.out.println( "Shot!" );
      Shot shot = new Shot( this.getBarycenter(), this.getOrientation() );
      while( Collision.detect( m_hitbox, shot.getPos() ) )
      {
        shot.move( dt );
      }
      Model.getInstance().addShot( shot );
      countdown = 0;
    }
  }

  @Override
  public void doHit( double orientation, int damage )
  {
    ArrayList< Entity > entities = Model.getInstance().getEntities();
    for ( Entity e : entities )
    {
      Vector  dist         = Vector.sub( e.getPos(), this.getPos() );

      boolean closeEnough  = m_visionField.getRadius() >= dist.getMagnitude();
      boolean correctAngle = orientation - ( Math.PI / 4 ) <= dist.getAngle();
      correctAngle = correctAngle && dist.getAngle() <= orientation + ( Math.PI / 4 );

      if( closeEnough && correctAngle )
      {
        Shot s = new Shot( this.getPos(), new Angle( dist.getAngle(), false ) );
      }
    }
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

      shot( elapsed );
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
  public void doWizz( List< Object > parameters )// déclanché après closest de l'automate
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