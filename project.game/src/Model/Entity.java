package src.Model;

import java.util.ArrayList;
import java.util.List;

import src.AI.Brain;
import src.AI.CategoryFsm;
import src.AI.Direction;
import src.AI.FSM;
import src.Model.Collision.AABB;
import src.Model.Collision.Arc;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;

import java.text.DecimalFormat;

import src.Config;

public abstract class Entity implements Cloneable
{
  protected EntityTracker  m_tracker;
  protected long           m_elapsedTime;
  protected AABB           m_hitbox;
  protected Angle          m_orientation;
  protected double         m_velocity;
  protected Arc            m_visionField;
  protected boolean        m_isMoving;
  protected boolean        m_hasCollision;
  protected int            m_id;
  protected CategoryFsm    m_cat;
  protected boolean        m_isWaiting;
  protected long           m_timeToWait;
  protected Brain          m_brain;
  protected double         m_moveDirection;
  protected boolean        m_isProtected;
  protected double         m_protectDirection;
  protected Entity         m_objectInHand;
  protected List< Entity > m_inventory;
  protected boolean        m_isResting;
  protected int            m_hp;
  protected int            m_maxHp;

  public Entity( FSM fsm, CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    m_orientation = new Angle( 0 );
    m_brain = new Brain( this, fsm );
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    double radius        = Config.getInstance().getParameters().getVisionFieldRadius();
    Angle  apertureAngle = Config.getInstance().getParameters().getVisionFieldApertureAngle();
    m_visionField = new Arc( this.m_hitbox.getBarycenter(), radius, m_orientation, apertureAngle );
    m_hasCollision = true;
    m_cat = new CategoryFsm( type, options );
    m_id = -1;
    m_maxHp = hp;
    m_hp = hp;
  }

  public Entity( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    m_orientation = new Angle( 0 );
    m_brain = new Brain( this, fsm );
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    double radius        = Config.getInstance().getParameters().getVisionFieldRadius();
    Angle  apertureAngle = Config.getInstance().getParameters().getVisionFieldApertureAngle();
    m_visionField = new Arc( this.m_hitbox.getBarycenter(), radius, m_orientation, apertureAngle );
    this.setId( id );
    this.setDim( width, height );
    this.setVelocity( velocity );
    this.setHasCollision( hasCollision );
    m_cat = new CategoryFsm( type, options );

    m_maxHp = hp;
    m_hp = hp;
  }

  @Override
  public Entity clone() throws CloneNotSupportedException
  {
    Entity e = (Entity)super.clone();
    e.setTracker( null );
    e.m_hitbox = m_hitbox.clone();
    e.m_visionField = m_visionField.clone();
    e.m_visionField.setCenter( e.m_hitbox.getBarycenter() );
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
    tickMove( elapsed );
    tickWait( elapsed );
    tickRest( elapsed );
    tickProtect( elapsed );
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

  public void doAdd( CategoryFsm var, int n )
  {
    m_brain.step();
  }

  public void doWait( long time )
  {
    m_isWaiting = true;
    m_timeToWait = time;
  }

  /* Called every tick, wait if the entity is supposed to wait */
  private void tickWait( long elapsed )
  {
    if( m_isWaiting )
    {
      m_timeToWait -= elapsed;
    }
    if( m_timeToWait <= 0 )
    {
      m_isWaiting = false;
      m_brain.step();
    }
  }

  public void doMove( Direction dir )
  {
    m_moveDirection = dir.toAngle( m_orientation );
    m_timeToWait = 20;
    m_isMoving = true;
  }

  public void doMove( double dir )
  {
    m_moveDirection = dir;
    m_timeToWait = 20;
    m_isMoving = true;
  }

  public void doMove( Direction dir, long time )
  {
    m_moveDirection = dir.toAngle( m_orientation );
    m_timeToWait = time;
    m_isMoving = true;
  }

  public void doMove( double dir, long time )
  {
    m_moveDirection = dir;
    m_timeToWait = time;
    m_isMoving = true;
  }

  /* Called every tick, moves the entity if the entity is supposed to move */
  private void tickMove( long elapsed )
  {
    if( m_isMoving )
    {
      double dt = elapsed;
      m_timeToWait -= elapsed;

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

      if( m_timeToWait <= 0 )
      {
        m_isMoving = false;
        m_brain.step();
      }
    }
  }

  public void doTurn( double orientation )
  {
    m_orientation.setValue( orientation );
    m_brain.step();
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
    ArrayList< Entity > entities = Model.getInstance().getEntities();
    for ( Entity e : entities )
    {
      Vector  dist         = Vector.sub( e.getPos(), this.getPos() );

      boolean closeEnough  = m_visionField.getRadius() >= dist.getMagnitude();
      boolean correctAngle = orientation - ( Math.PI / 4 ) <= dist.getAngle();
      correctAngle = correctAngle && dist.getAngle() <= orientation + ( Math.PI / 4 );

      if( closeEnough && correctAngle )
      {
        e.getHit();
      }
    }
    m_brain.step();
  }

  public void doProtect( double orientation, long time )
  {
    m_isProtected = true;
    m_protectDirection = orientation;
    m_timeToWait = time;
  }

  public void doProtect( Direction direction, long time )
  {
    m_isProtected = true;
    m_protectDirection = direction.toAngle( m_orientation );
    m_timeToWait = time;
  }

  private void tickProtect( long elapsed )
  {
    if( m_isProtected )
    {
      m_timeToWait -= elapsed;
      if( m_timeToWait <= 0 )
      {
        m_isProtected = false;
        m_brain.step();
      }
    }
  }

  public void getHit()
  {
    // À implémenter pour chaque entité
  }

  public void doPick( double orientation )
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
        if( m_objectInHand != null )
        {
          m_inventory.add( m_objectInHand );
        }
        entities.remove( e );
        m_objectInHand = e;
      }
    }
    m_brain.step();
  }

  public void doThrow( double orientation )
  {
    Entity e     = m_objectInHand;
    Model  model = Model.getInstance();
    m_objectInHand = null;
    model.addEntities( e );
    m_brain.step();
  }

  public void doStore()
  {
    Entity e = m_objectInHand;
    m_objectInHand = null;
    m_inventory.add( e );
    m_brain.step();

  }

  public void doGet()
  {
    Entity e = m_inventory.remove( 0 );
    doStore();
    m_objectInHand = e;
    m_brain.step();
  }

  public void doRest( long time, int pow )
  {
    m_isResting = true;
    m_timeToWait = time;
    addPow( pow );
  }

  private void tickRest( long elapsed )
  {
    if( m_isResting )
    {
      m_timeToWait -= elapsed;
      if( m_timeToWait <= 0 )
      {
        m_isResting = false;
        m_brain.step();
      }
    }
  }

  /* Ne fait rien par défaut, à override si l'entité en a besoin */
  public void addPow( int pow )
  {

  }

  public void doExplode()
  {
    Model model = Model.getInstance();
    model.removeEntities( this );
    ArrayList< EntityTracker > trackers = model.getTrackers();
    for ( EntityTracker tracker : trackers )
    {
      tracker.getListener().left( this );
    }
    m_brain.step();
  }

  public void doEgg( Direction dir )
  {
//    Model model = Model.getInstance();
    m_brain.step();
  }

  /* to override */
  public void doPop( List< Object > parameters )
  {
    m_brain.step();
  }

  /* to override */
  public void doWizz( List< Object > parameters )
  {
    m_brain.step();
  }

  // Spécifique à notre physique
  public void repulse()
  {
    m_orientation.add( Math.PI );
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

  public Angle getOrientation()
  {
    return m_orientation;
  }

  public void setOrientation( Angle orientation )
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

  public Arc getVisionField()
  {
    return m_visionField;
  }

  public long getElapsedTime()
  {
    return m_elapsedTime;
  }

  public void turn( double orientation )
  {
    m_orientation.setValue( orientation );
  }

  @Override
  public String toString()
  {
    DecimalFormat df = new DecimalFormat( "#.0" );
    return "[" + this.getId() + "]" + "(x=" + df.format( getX() ) + ", y=" + df.format( getY() ) + ", w="
        + df.format( getWidth() ) + ", h=" + df.format( getHeight() ) + ")";
  }

  public int getId()
  {
    return m_id;
  }

  public void setId( int id )
  {
    m_id = id;
  }

  public void subHP( int damage )
  {
    m_hp -= damage;
  }

  public boolean isDead()
  {
    return m_hp == 0;
  }

  public int getHP()
  {
    return m_hp;
  }

  public int getMaxHP()
  {
    return m_maxHp;
  }

  public FSM getFSM()
  {
    return m_brain.getFSM();
  }
}
