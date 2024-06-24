package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.CategoryFsm.CATEGORY;
import src.Model.World.Map;
import src.AI.FSM;

public class Rabbit extends Entity
{

  public Rabbit( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options, hp );
    this.setPos( Map.getInstance().getRandomPos() );
  }
  
  public Rabbit( FSM fsm )
  {
    super( fsm, -1, 25, 25, 3, true, CATEGORY.Moveable, null, 20 );
  }

  @Override
  public String toString()
  {
    return "Alien - " + super.toString();
  }
}
