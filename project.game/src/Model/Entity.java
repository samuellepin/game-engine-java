package src.Model;

import java.util.ArrayList;
import java.util.List;

import src.AI.Brain;
import src.AI.CategoryFsm;
import src.AI.CategoryFsm.CATEGORY;
import src.AI.Direction;
import src.AI.FSM;
import src.AI.FsmFactory;
import src.Model.Collision.AABB;
import src.Model.Collision.Arc;
import src.Model.Collision.Collision;
import src.Model.World.Tile;

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
  protected Angle          m_moveDirection;
  protected boolean        m_isProtected;
  protected Angle          m_protectDirection;
  protected Entity         m_objectInHand;
  protected List< Entity > m_inventory;
  protected boolean        m_isResting;
  protected int            m_hp;
  protected int            m_maxHp;
  protected Entity         m_originEntity;

  public boolean isNonOriginForm()
  {
    return this.m_originEntity != null;
  }

  public Entity()
  {
    m_tracker = null;
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    m_orientation = new Angle( 0 );
    m_velocity = 0;
    double radius        = Config.getInstance().getParameters().getVisionFieldRadius();
    Angle  apertureAngle = Config.getInstance().getParameters().getVisionFieldApertureAngle();
    m_visionField = new Arc( this.m_hitbox.getBarycenter(), radius, m_orientation, apertureAngle );
    m_isMoving = false;
    m_hasCollision = true;
    m_id = -1;
    m_cat = new CategoryFsm();
    m_isWaiting = false;
    m_timeToWait = 0;
    m_brain = new Brain( this );
    m_moveDirection = new Angle( 0 );
    m_isProtected = false;
    m_protectDirection = new Angle( 0 );
    m_objectInHand = null;
    m_inventory = new ArrayList<>();
    m_isResting = false;
    m_hp = 0;
    m_maxHp = 0;
    m_originEntity = null;
  }

  public void setOriginEntity( Entity e )
  {
    m_originEntity = e;
  }

  @Override
  public Entity clone() throws CloneNotSupportedException
  {
    Entity e = (Entity)super.clone();
    e.setTracker( null );
    e.m_hitbox = m_hitbox.clone();
    e.m_visionField = m_visionField.clone();
    e.m_visionField.setCenter( e.m_hitbox.getBarycenter() );
    e.m_orientation = m_orientation.clone();
    e.m_protectDirection = m_protectDirection.clone();
    e.m_moveDirection = m_moveDirection.clone();
    return e;
  }

  public void setTracker( EntityTracker tracker )
  {
    if( this.m_originEntity != null )
    {
      this.m_originEntity.setTracker( tracker );
      return;
    }
    m_tracker = tracker;
  }

  public void setHasCollision( boolean hasCollision )
  {
    if( this.m_originEntity != null )
    {
      this.m_originEntity.setHasCollision( hasCollision );
      return;
    }
    m_hasCollision = hasCollision;
  }

  public boolean hasCollision()
  {
    if( this.m_originEntity != null )
    {
      return this.m_originEntity.hasCollision();
    }
    return m_hasCollision;
  }

  public void setIsMoving( boolean isMoving )
  {
    if( this.m_originEntity != null )
    {
      this.m_originEntity.setIsMoving( isMoving );
      return;
    }
    m_isMoving = isMoving;
  }

  public boolean isMoving()
  {
    if( this.m_originEntity != null )
    {
      return this.m_originEntity.isMoving();
    }
    return m_isMoving;
  }

  public void tick( long elapsed )
  {
//    if( this.m_originEntity != null )
//    {
//      this.m_originEntity.tick( elapsed );
//      return;
//    }
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
    if( this.m_originEntity != null )
    {
      this.m_originEntity.doAdd( var, n );
      return;
    }
    m_brain.step();
  }

  public void doWait( long time )
  {
    if( this.m_originEntity != null )
    {
      this.m_originEntity.doWait( time );
      return;
    }
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
    m_moveDirection.setValue( dir.toAngle( m_orientation ) );
    m_timeToWait = 20;
    m_isMoving = true;
  }

  public void doMove( double dir )
  {
    m_moveDirection = new Angle( dir );
    m_timeToWait = 20;
    m_isMoving = true;
  }

  public void doMove( Direction dir, long time )
  {
    m_moveDirection.setValue( dir.toAngle( m_orientation ) );
    m_timeToWait = time;
    m_isMoving = true;
  }

  public void doMove( double dir, long time )
  {
    m_moveDirection = new Angle( dir );
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
      this.getHitbox().translate( d * Math.cos( m_moveDirection.getValue() ),
          d * Math.sin( m_moveDirection.getValue() ) );
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
        e.getHit( damage );
      }
    }
    m_brain.step();
  }

  public void doProtect( double orientation, long time )
  {
    m_isProtected = true;
    m_protectDirection.setValue( orientation );
    m_timeToWait = time;
  }

  public void doProtect( Direction direction, long time )
  {
    m_isProtected = true;
    m_protectDirection.setValue( direction.toAngle( m_orientation ) );
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

  public void getHit( int damage )
  {
    // À implémenter pour chaque entité si nécessaire
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

  public boolean getTrue()
  {
    return true;
  }

  public boolean getMyDir( Direction dir )
  {
    return m_orientation.getValue() == dir.toAngle( Angle.ANGLE_ZERO );
  }

  public boolean getCell( Direction dir, CategoryFsm cat )
  {
    Direction absDir;
    switch ( dir.getDirection() )
    {
    case Forward:
    case Backward:
    case Left:
    case Right:
      absDir = new Direction( Direction.toDirection( dir.toAngle( m_orientation ) ) );
      break;
    default:
      absDir = new Direction( dir.getDirection() );
      break;
    }
    double radius;
    switch ( absDir.getDirection() )
    {
    case North:
    case South:
      radius = Tile.HEIGHT;
      break;
    case NE:
    case NW:
    case SE:
    case SW:
      radius = ( Tile.HEIGHT + Tile.WIDTH ) / 2;
      break;
    default:
      radius = Tile.WIDTH;
      break;
    }
    Vector pos = m_hitbox.getBarycenter();
    Arc    a   = new Arc( pos, radius, new Angle( absDir.toAngle( Angle.ANGLE_ZERO ) ),
        new Angle( Direction.DIRECTION_ANGLE ) );

    for ( Entity e : Model.getInstance().getEntities() )
    {
      if( cat.getType() == e.getType() ) return Collision.detect( e.getHitbox(), a );
    }
    return false;
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
    if( this.m_originEntity != null )
    {
      return this.m_originEntity.getWidth();
    }
    return m_hitbox.getWidth();
  }

  public double getHeight()
  {
    if( this.m_originEntity != null )
    {
      return this.m_originEntity.getHeight();
    }
    return m_hitbox.getHeight();
  }

  public Angle getOrientation()
  {
    if( this.m_originEntity != null )
    {
      return this.m_originEntity.getOrientation();
    }
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
    if( this.m_originEntity != null )
    {
      return this.m_originEntity.getElapsedTime();
    }
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

  public CategoryFsm.CATEGORY getType()
  {
    return m_cat.getType();
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

  public void setFSM( FSM fsm )
  {
    m_brain.setFSM( fsm );
  }

  public void setFSM( String name )
  {
    this.setFSM( FsmFactory.getInstance().getFSM( name ) );
  }

  public void setMaxHP( int maxHP )
  {
    m_hp = maxHP;
    m_maxHp = maxHP;
  }

  public void setCategory( CATEGORY cat )
  {
    m_cat.setType( cat );
  }

  public Vector getBarycenter()
  {
    return m_hitbox.getBarycenter();
  }
}
