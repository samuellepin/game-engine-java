package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

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
