package src.Model;

import src.AI.Automaton;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Spy extends Entity
{

  private Circle m_visionField;

  public Spy( Automaton automaton )
  {
    super( automaton );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 6 );
    super.updateHitbox();

    m_visionField = new Circle( this.getPos(), 150.0 );
  }

  public void move()
  {
    double d       = m_velocity * (double)m_elapsedTime;
    Vector prevPos = m_pos;
    m_pos = m_pos.add( d * Math.cos( m_orientation ), d * Math.sin( m_orientation ) );
    super.updateHitbox();
    if( Map.getInstance().detectCollision( this ) ) // || Collision.detect( Model.getPlayer2().getHitbox(), Model.getPlayer().getHitbox() 
    {
      m_pos = prevPos;
      repulse();
    }
  }

  public void turn( double orientation )
  {
    m_orientation = orientation;
  }

  public void repulse()
  {
    m_orientation += Math.PI;
    double d       = 4;
    m_pos = m_pos.add( d * Math.cos( m_orientation ), d * Math.sin( m_orientation ) );
    super.updateHitbox();
  }

  public Circle getVisionField()
  {
    return m_visionField;
  }

}
