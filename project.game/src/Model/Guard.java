package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Guard extends Entity
{
//  public Guard( Automaton automaton )
//  {
//    super( automaton );
//    this.setPos( Map.getInstance().getRandomPos() );
//    super.setVelocity( 0.1 );
//  }

  public Guard( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision , CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options)
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Guard - " + super.toString();
  }

//  void follow( Entity entity )
//  {
//    Vector OP = entity.getPos();
//    Vector OE = this.getPos();
//    Vector EP = Vector.sub( OP, OE );
//    super.setOrientation( EP.getAngle() );
//    doMove( m_orientation );
//  }
//
//  @Override
//  public void tick( long elapsed )
//  {
//    super.tick( elapsed );
//    Circle c1 = Model.getInstance().getPlayer1().getVisionField();
//    Circle c2 = super.getVisionField();
//  }
}