package src.Model;

import java.util.List;

import src.AI.CategoryFsm.CATEGORY;
import src.AI.FSM;

public class Robot extends Entity
{

  public Robot( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision, CATEGORY type,
      List< CATEGORY > options )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options );
  }
  
  @Override
  public void doWizz( List< Object > parameters )
  {
    
  }

  @Override
  public String toString()
  {
    return "Robot - " + super.toString();
  }
}
