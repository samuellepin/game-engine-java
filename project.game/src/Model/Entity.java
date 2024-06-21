package src.Model;

import src.AI.FSM;
import src.AI.StateFsm;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

import java.text.DecimalFormat;

import src.Config;

public abstract class Entity implements Cloneable
{
  protected FSM           m_fsm;
  protected StateFsm      m_state;
  protected EntityTracker m_tracker;
  protected long          m_elapsedTime;
  protected AABB          m_hitbox;
  protected double        m_orientation;
  protected double        m_velocity;
  protected Circle        m_visionField;
  protected boolean       m_isMoving;
  protected boolean       m_hasCollision;
  protected int           m_id;
  

  public Entity( FSM fsm )
  {
    m_fsm = fsm;
    if( m_fsm != null ) m_state = m_fsm.getInitialState();
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    m_visionField = new Circle( this.getHitbox().getMin(),
        Config.getInstance().getParameters().getVisionFieldRadius() );
    m_hasCollision = true;
    m_id = -1;
  }

  public Entity( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision )
  {
    m_fsm = fsm;
    if( m_fsm != null ) m_state = fsm.getInitialState();
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    m_visionField = new Circle( this.getHitbox().getMin(),
        Config.getInstance().getParameters().getVisionFieldRadius() );

    this.setId( id );
    this.setDim( width, height );
    this.setVelocity( velocity );
    this.setHasCollision( hasCollision );
  }

  @Override
  public Entity clone() throws CloneNotSupportedException
  {
    Entity e = (Entity)super.clone();
    e.setTracker( null );
    e.m_hitbox = m_hitbox.clone();
    e.m_visionField = m_visionField.clone();
    e.m_visionField.setTopLeftCorner( e.m_hitbox.getPos() );
    return e;
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
    callListener();
  }

  public void doWait()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doMove( double orientation )
  {
    double d = m_velocity * (double)m_elapsedTime;
    if( d >= 3 * m_velocity || d <= 0 )
    {
      d = 3 * m_velocity;
    }
    double prevX = this.getHitbox().getX();
    double prevY = this.getHitbox().getY();
    double dx = d * Math.cos( m_orientation );
    double dy = d * Math.sin( m_orientation );
    this.getHitbox().translate( dx, dy );
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

  public void doTurn( double orientation )
  {
    m_orientation = orientation;
  }

  public void doJump( double orientation )
  {
    // TODO
    throw new RuntimeException( "NYI" );
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
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doStore()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doGet()
  {
    // TODO
    throw new RuntimeException( "NYI" );
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
    doMove( m_orientation );
  }

  public StateFsm getState()
  {
    return m_state;
  }
  
  public void setState(StateFsm s) {
    m_state = s;
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
    DecimalFormat df = new DecimalFormat( "#.0" );
    return  "[" + this.getId() + "]" + "(x=" + df.format( getX() ) + ", y=" + df.format( getY() ) + ", w=" + df.format( getWidth() )
        + ", h=" + df.format( getHeight() ) + ")";
  }

  public int getId()
  {
    return m_id;
  }

  public void setId( int id )
  {
    m_id = id;
  }
}
