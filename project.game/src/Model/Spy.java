package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.Model.World.Map;
import src.AI.FSM;

public class Spy extends Entity
{

  public Spy( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Spy - " + super.toString();
  }
}
