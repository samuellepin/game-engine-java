package src.AI;

import src.Model.Entity;

public class DefaultAction implements Action
{
  @Override
  public boolean run( Entity entity )
  {
    return true;
  }
}
