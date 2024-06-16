package src.Model;

import src.AI.Automaton;
import src.AI.State;
import src.Model.Collision.AABB;
import src.Model.Collision.Hitbox;
import src.Game;

public abstract class Entity
{
  protected Automaton m_automaton;
  protected State     m_state;
  protected long      m_elapsedTime;
  protected Vector    m_pos;
  protected Vector    m_dim;
  protected double    m_orientation;
  protected double    m_velocity;
  protected AABB      m_hitbox;

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
    pos = pos.sub( this.getWidth()/2, this.getHeight()/2 );
    m_hitbox.resize( pos, pos.add( this.getWidth(), this.getHeight() ) );
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

  public void setPos( Vector pos )
  {
    m_pos = pos;
    this.updateHitbox();
  }
  
  public void setPos( double x, double y )
  {
    m_pos.setX( x );
    m_pos.setY( y );
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
  
  public void turn( double theta )
  {
    
  }
  
  public void move()
  {
    
  }
}
