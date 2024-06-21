package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

public class Wall extends Entity
{
  public Wall( FSM fsm, CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options )
  {
    super( fsm, type, options );
  }

  @Override
  public String toString()
  {
    return "Wall - " + super.toString();
  }
  
}
