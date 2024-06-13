package src.Model;

import src.AI.Automaton;
import src.Model.World.Map;

public class Player extends Entity
{

  public Player( Automaton automaton )
  {
    super( automaton );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 6 );
    super.updateHitbox();
  }
  
  public void move()
  {
    double d = m_velocity * (double)m_elapsedTime;
    Vector prevPos = m_pos;
    m_pos = m_pos.add( d * Math.cos( m_orientation ), d * Math.sin( m_orientation ) );
    super.updateHitbox();
    if( Map.getInstance().detectCollision( this ) )
    {
      m_pos = prevPos;
    }
  }
  
  public void turn(double orientation)
  {
    m_orientation = orientation;
  }
  
}
