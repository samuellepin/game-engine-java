package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.CategoryFsm.CATEGORY;
import src.Model.World.Map;
import src.AI.FSM;

public class Rabbit extends Entity
{
  public Rabbit()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Alien - " + super.toString();
  }
}
