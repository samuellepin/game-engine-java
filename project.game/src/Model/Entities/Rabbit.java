package src.Model.Entities;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.CategoryFsm.CATEGORY;
import src.Model.Entity;
import src.Model.World.Map;
import src.AI.FSM;

public class Rabbit extends Animal
{
  public Rabbit()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Rabbit - " + super.toString();
  }
}
