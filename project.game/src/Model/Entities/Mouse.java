package src.Model.Entities;

import src.Model.Entity;

public class Mouse extends Animal
{
  public Mouse()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Mouse - " + super.toString();
  }
}
