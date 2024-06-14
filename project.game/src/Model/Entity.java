package src.Model;

import src.AI.Automaton;
import src.AI.State;
import src.Model.Collision.AABB;
import src.Model.World.Map;

public abstract class Entity
{
  protected Automaton     m_automaton;
  protected State         m_state;
  protected long          m_elapsedTime;
  protected Vector        m_pos;
  protected Vector        m_dim;
  protected double        m_orientation;
  protected double        m_velocity;
  protected AABB          m_hitbox;
  protected Model         m_model;

  public Entity( Automaton automaton )
  {
    m_automaton = automaton;
    if( m_automaton != null ) m_state = automaton.getInitialState();
    m_elapsedTime = 0;
    m_pos = new Vector( 0, 0 );
    m_dim = new Vector( 0, 0 );
    m_hitbox = new AABB( null, null );
    updateHitbox();
  }

  public void updateHitbox()
  {
    Vector pos = m_pos;
    pos = pos.sub( this.getWidth() / 2, this.getHeight() / 2 );
    m_hitbox.resize( pos, pos.add( this.getWidth(), this.getHeight() ) );
  }

  public void tick( long elapsed )
  {
    m_elapsedTime = elapsed;
  }

  public void doWait()
  {
    // TODO
    throw new RuntimeException( "NYI" );
  }

  public void doMove( double orientation )
  {
    double d       = m_velocity * (double)m_elapsedTime;
    Vector prevPos = m_pos;
    m_pos = m_pos.add( d * Math.cos( orientation ), d * Math.sin( orientation ) );
    updateHitbox();
    if( Map.getInstance().detectCollision( this ) )
    {
      m_pos = prevPos;
    }
    
    for (EntityTracker tracker : m_model.m_trackers) {
      
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

  public State getState()
  {
    return m_state;
  }

  public Vector getPos()
  {
    return m_pos;
  }

  public double getX()
  {
    return m_pos.getX();
  }

  public double getY()
  {
    return m_pos.getY();
  }

  public double getVX()
  {
    return m_pos.getVX() - this.getWidth() / 2;
  }

  public double getVY()
  {
    return m_pos.getVY() - this.getHeight() / 2;
  }

  public void setPos( Vector pos )
  {
    m_pos = pos;
    this.updateHitbox();
  }

  public void setWidth( double width )
  {
    m_dim.setX( width );
  }

  public double getWidth()
  {
    return m_dim.getX();
  }

  public void setHeight( double height )
  {
    m_dim.setY( height );
  }

  public double getHeight()
  {
    return m_dim.getY();
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

  public Vector getDim()
  {
    return m_dim;
  }

  public void setDim( double width, double height )
  {
    m_dim.setPos( width, height );
  }

  public AABB getHitbox()
  {
    return m_hitbox;
  }
}
