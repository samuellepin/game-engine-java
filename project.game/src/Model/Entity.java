package src.Model;

import src.AI.Automaton;
import src.AI.State;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public abstract class Entity
{
  protected Automaton m_automaton;
  protected State     m_state;
  protected long      m_elapsedTime;
  protected AABB      m_hitbox;
  protected double    m_orientation;
  protected double    m_velocity;
  protected Circle    m_visionField;
  protected boolean   m_isMoving;
  protected boolean   m_hasCollision;

  public Entity( Automaton automaton )
  {
    m_automaton = automaton;
    if( m_automaton != null ) m_state = automaton.getInitialState();
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    m_visionField = new Circle( this.getHitbox().getMin(), Config.VISION_FIELD_RADIUS );
    m_hasCollision = true;
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
  }

  public State getState()
  {
    return m_state;
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

  public void move()
  {
    double d = m_velocity * (double)m_elapsedTime;
    if( d >= 3 * m_velocity )
    {
      d = 3 * m_velocity;
    }
    this.getHitbox().translate( d * Math.cos( m_orientation ), d * Math.sin( m_orientation ) );
    Model m = Model.getInstance();
    if( Map.getInstance().detectCollision( this ) )
    {
      repulse();
    }
    for ( Entity e : Model.getInstance().getEntities() )
    {
      if( e != this && e.hasCollision() 
          && Collision.detect( this.getHitbox(), e.getHitbox() ) )
      {
        e.repulse();
      }
    }
  }

  public void turn( double orientation )
  {
    m_orientation = orientation;
  }

  public void repulse()
  {
    m_orientation += Math.PI;
    move();
  }

  @Override
  public String toString()
  {
    return "(x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight()
        + ")";
  }
}
