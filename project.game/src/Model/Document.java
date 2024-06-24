package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.World.Map;

///< Documentation succinte de LUSTRE
public class Document extends Entity
{

  public Document( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision,
      CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options, int hp )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options, hp );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Doc - " + super.toString();
  }
}
