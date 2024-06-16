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
    super.setVelocity( 2 );
    m_visionField = new Circle( this.getPos(), 150.0 );
  }

  public void move()
  {
    double d = m_velocity * (double)m_elapsedTime;
    if( d >= 5 )
    {
      d = 5;
    }
    super.getHitbox().translate( d * Math.cos( m_orientation ), d * Math.sin( m_orientation ) );
    Model m = Model.getInstance();
    if( Map.getInstance().detectCollision( this )
        || Collision.detect( m.getPlayer1().getHitbox(), m.getPlayer2().getHitbox() ) )
    {
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
    move();
  }

  public Circle getVisionField()
  {
    return m_visionField;
  }

}
