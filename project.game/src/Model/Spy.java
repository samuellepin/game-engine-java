package src.Model;

import src.Model.World.Map;
import src.AI.FSM;

public class Spy extends Entity
{
  public Spy( FSM fsm )
  {
    super( fsm );
    this.setPos( Map.getInstance().getRandomPos() );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 4 );
  }
  
  public Spy( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision )
  {
    super( fsm, id, width, height, velocity, hasCollision );
    this.setPos( Map.getInstance().getRandomPos() );
  }
  
  @Override
  public String toString()
  {
    return "Spy - " + super.toString();
  }
}
