package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Guard extends Entity
{
  public Guard()
  {
    super();
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
    super.getOrientation().setValue( EP.getAngle() );
    doMove( m_orientation.getValue() );
  }

  private long countdown;

  void shot( long dt )
  {
    countdown += dt;
    if( countdown > 1000 )
    {
      System.out.println("Shot!");
      Model.getInstance().addShot( new Shot( this.getPos(), this.getOrientation() ) );
      countdown = 0;
    }
  }

  @Override
  public void tick( long elapsed )
  {
    super.tick( elapsed );
//    AABB h1 = Model.getInstance().getPlayer1().getHitbox();
//    System.out.println( "Tick " + this.toString() );
    if( Collision.detect( Model.getInstance().getPlayer1().getHitbox(),
        this.getVisionField() ) )
    {
//      System.out.println( "Collision : " + c1.toString() + " - " + c2.toString() );
      follow( Model.getInstance().getPlayer1() );
      shot( elapsed );
    }
  }
}