package src.Model;

import java.util.ArrayList;
import src.AI.Brain;
import src.AI.Direction;
import src.AI.FSM;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;

public abstract class Entity
{
  protected Brain         m_brain;
  protected EntityTracker m_tracker;
  protected long          m_elapsedTime;
  protected AABB          m_hitbox;
  protected double        m_orientation;
  protected double        m_velocity;
  protected Circle        m_visionField;
  protected boolean       m_isMoving;
  protected double        m_moveDirection;
  protected long          m_timeToMove;
  protected boolean       m_hasCollision;
  protected Entity              m_objectInHand;
  protected ArrayList< Entity > m_inventory;

  public Entity( FSM automaton )
  {
    m_brain = new Brain( this, automaton );
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    m_visionField = new Circle( this.getHitbox().getMin(), Config.VISION_FIELD_RADIUS );
    m_hasCollision = true;
  }

  public void setTracker( EntityTracker tracker )
  {
    m_tracker = tracker;
  }

  public void setHasCollision( boolean hasCollision )
  {
    m_hasCollision = hasCollision;
  }

  public boolean hasCollision()
  {
    return m_hasCollision;
  }

  public void setIsMoving( boolean isMoving )
  {
    m_isMoving = isMoving;
  }

  public boolean isMoving()
  {
    return m_isMoving;
  }

  public void tick( long elapsed )
  {
    m_elapsedTime = elapsed;
    tickMove(elapsed);
    callListener();
  }

  private void callListener()
  {
    for ( EntityTracker tracker : Model.getInstance().getTrackers() )
    {
      if( !tracker.getEntities().contains( this ) && Collision.detect( m_hitbox, tracker ) )
      {
        tracker.getListener().entered( this );
      }
      else if( tracker.getEntities().contains( this ) && !Collision.detect( m_hitbox, tracker ) )
      {
        tracker.getListener().left( this );
      }
    }
  }

  public void doAdd( String var, int n )
  {

  }

  public void doWait()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doMove( Direction dir )
  {
    m_moveDirection = dir.toAngle( m_orientation );
    m_timeToMove = 20;
    m_isMoving = true;
  }
  
  public void doMove( double dir )
  {
    m_moveDirection = dir;
    m_timeToMove = 20;
    m_isMoving = true;
  }
  
  public void doMove( Direction dir, long time )
  {
    m_moveDirection = dir.toAngle( m_orientation );
    m_timeToMove = time;
    m_isMoving = true;
  }
  
  public void doMove( double dir, long time )
  {
    m_moveDirection = dir;
    m_timeToMove = time;
    m_isMoving = true;
  }

  /* Called every tick, moves the entity if the entity is supposed to move */
  private void tickMove( long elapsed )
  {
    if( m_isMoving )
    {
      double dt = elapsed / 1000;
      if( m_timeToMove < elapsed )
      {
        dt = m_timeToMove / 1000;
      }
      m_timeToMove -= elapsed;

      double d     = m_velocity * dt;

      double prevX = this.getHitbox().getX();
      double prevY = this.getHitbox().getY();
      this.getHitbox().translate( d * Math.cos( m_moveDirection ), d * Math.sin( m_moveDirection ) );
      for ( Entity e : Model.getInstance().getEntities() )
      {
        if( e != this && e.hasCollision() && Collision.detect( this.getHitbox(), e.getHitbox() ) )
        {
          this.setPos( prevX, prevY );
//        e.repulse();
        }
      }

      if( m_tracker != null )
      {
        m_tracker.getListener().moved();
      }

      callListener();
      
      if (m_timeToMove <= 0) {
        m_isMoving = false;
        m_brain.step();
      }
    }
  }

  public void doTurn( double orientation )
  {
    m_orientation = orientation;
  }

  public void doJump( double orientation, double dist )
  {
    double prevX = this.getHitbox().getX();
    double prevY = this.getHitbox().getY();
    this.getHitbox().translate( dist * Math.cos( orientation ), dist * Math.sin( orientation ) );
    for ( Entity e : Model.getInstance().getEntities() )
    {
      if( e != this && e.hasCollision() && Collision.detect( this.getHitbox(), e.getHitbox() ) )
      {
        this.setPos( prevX, prevY );
//      e.repulse();
      }
    }

    if( m_tracker != null )
    {
      m_tracker.getListener().moved();
    }

    callListener();

    m_brain.step();
  }

  public void doHit( double orientation )
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doProtect( double orientation )
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doPick( double orientation )
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doThrow( double orientation )
  {
    Entity e     = m_objectInHand;
    Model  model = Model.getInstance();
    m_objectInHand = null;
    model.addEntities( e );
  }

  public void doStore()
  {
    Entity e = m_objectInHand;
    m_objectInHand = null;
    m_inventory.add( e );

  }

  public void doGet()
  {
    Entity e = m_inventory.remove( 0 );
    doStore();
    m_objectInHand = e;
  }

  public void doPower()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doExplode()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doEgg()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  // Spécifique à notre physique
  public void repulse()
  {
    m_orientation += Math.PI;
//    doMove( m_orientation );
  }

  public Vector getPos()
  {
    return m_hitbox.getMin();
  }

  public double getX()
  {
    return m_hitbox.getX();
  }

  public double getY()
  {
    return m_hitbox.getY();
  }

  public void setPos( Vector pos )
  {
    m_hitbox.setPos( pos );
  }

  public void setPos( double x, double y )
  {
    m_hitbox.setPos( x, y );
  }

  public double getWidth()
  {
    return m_hitbox.getWidth();
  }

  public double getHeight()
  {
    return m_hitbox.getHeight();
  }

  public double getOrientation()
  {
    return m_orientation;
  }

  public void setOrientation( double orientation )
  {
    m_orientation = orientation;
  }

  public void setVelocity( double velocity )
  {
    m_velocity = velocity;
  }

  public double getVelocity()
  {
    return m_velocity;
  }

  public void setDim( double width, double height )
  {
    m_hitbox.setDim( width, height );
  }

  public AABB getHitbox()
  {
    return m_hitbox;
  }

  public Circle getVisionField()
  {
    return m_visionField;
  }

  public long getElapsedTime()
  {
    return m_elapsedTime;
  }

  public void turn( double orientation )
  {
    m_orientation = orientation;
  }

  @Override
  public String toString()
  {
    return "(x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight()
        + ")";
  }
}
