package src.Model;

import src.AI.Automaton;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Guard extends Spy
{
//  public Guard( Automaton automaton )
//  {
//    super( automaton );
//    this.setPos( Map.getInstance().getRandomPos() );
//    super.setVelocity( 0.1 );
//  }

  public Guard( Automaton automaton, int id, double width, double height, double velocity, boolean hasCollision )
  {
    super( automaton, id, width, height, velocity, hasCollision );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Guard - " + super.toString();
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
    super.tick( elapsed );
    AABB h1 = Model.getInstance().getPlayer1().getHitbox();
//    Circle c1 = Model.getInstance().getPlayer1().getVisionField();
    Circle c2 = super.getVisionField();
    System.out.println( "Tick " + this.toString() );
    if( Collision.detect( h1, c2 ) )
    {
      System.out.println( "Collision : " + h1.toString() + " - " + c2.toString() );
      follow( Model.getInstance().getPlayer1() );
    }
  }
}