package src.Model;

import src.AI.Automaton;
import src.AI.State;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;

public abstract class Entity
{
  protected Automaton m_automaton;
  protected State     m_state;
  protected long      m_elapsedTime;
  protected AABB      m_hitbox;
  protected double    m_orientation;
  protected double    m_velocity;
  protected Circle    m_visionField;
  

  public Entity( Automaton automaton )
  {
    m_automaton = automaton;
    if( m_automaton != null ) m_state = automaton.getInitialState();
    m_elapsedTime = 0;
    m_hitbox = new AABB( 0, 0, 0, 0 );
    m_visionField = new Circle( this.getHitbox().getMin(), Config.VISION_FIELD_RADIUS );
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

  public void turn( double theta )
  {

  }

  public void move()
  {

  }

  @Override
  public String toString()
  {
    return "(x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight()
        + ")";
  }
}
