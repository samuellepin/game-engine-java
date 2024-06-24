package src.Model;

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
//  public Guard( Automaton automaton )
//  {
//    super( automaton );
//    this.setPos( Map.getInstance().getRandomPos() );
//    super.setVelocity( 0.1 );
//  }

  public Guard( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options, hp );
    this.setPos( Map.getInstance().getRandomPos() );
    m_ownAlarm = new Alarm( this );
  }

  @Override
  public String toString()
  {
    return "Guard - " + super.toString();
  }

  void follow( Entity entity )
  {
    Vector OP = entity.getPos();
    Vector OE = this.getPos();
    Vector EP = Vector.sub( OP, OE );
    super.setOrientation( EP.getAngle() );
    doMove( m_orientation );
  }

  private long countdown;

  void shot( long dt )
  {
    countdown += dt;
    if( countdown > 1000 )
    {
      System.out.println( "Shot!" );
      Model.getInstance().addShot( new Shot( this.getPos(), this.getOrientation() ) );
      countdown = 0;
    }
  }

  @Override
  public void tick( long elapsed )
  {
    super.tick( elapsed );
//    AABB h1 = Model.getInstance().getPlayer1().getHitbox();
    Circle c1 = Model.getInstance().getPlayer1().getVisionField();
    Circle c2 = super.getVisionField();
//    System.out.println( "Tick " + this.toString() );
    if( Collision.detect( c1, c2 ) )
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
      Vector dist=Vector.sub( m_otherAlarm.getOpponentPos(), getPos() );
      m_moveDirection=dist.getAngle();
    }
    else {
      m_moveDirection = dir.toAngle( m_orientation );
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