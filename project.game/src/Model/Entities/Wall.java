package src.Model.Entities;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.Entity;

public class Wall extends Entity
{
  public Wall()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    return "Wall - " + super.toString();
  }
  
}
