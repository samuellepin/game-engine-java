package src.Model;

import src.AI.FSM;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;

public class Guard extends Spy
{
  public Guard( FSM automaton )
  {
    super( automaton );
    super.setVelocity( 0.1 );
  }

  void follow( Entity entity )
  {
    Vector OP = entity.getPos();
    Vector OE = this.getPos();
    Vector EP = Vector.sub( OP, OE );
    super.setOrientation( EP.getAngle() );
    doMove( m_orientation );
  }

  @Override
  public void tick( long elapsed )
  {
    m_elapsedTime = elapsed;
    Circle c1 = Model.getInstance().getPlayer1().getVisionField();
    Circle c2 = super.getVisionField();
    if( Collision.detect( c1, c2 ) )
    {
      follow( Model.getInstance().getPlayer1() );
    }
  }
}