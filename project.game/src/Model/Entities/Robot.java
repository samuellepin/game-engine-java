package src.Model.Entities;

import src.Model.Entity;

public class Robot extends Entity
{
  public Robot()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Robot - " + super.toString();
  }
  
  @Override
  public void tick( long dt )
  {
    
  }
}
