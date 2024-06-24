package src.Model.Entities;

import java.util.List;

import src.AI.CategoryFsm.CATEGORY;
import src.Model.Entity;
import src.AI.FSM;

public class Robot extends Entity
{
  public Robot()
  {
    super();
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
