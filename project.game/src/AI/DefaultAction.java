package src.AI;

import src.Model.Entity;

public class DefaultAction implements ActionFsm
{
  @Override
  public boolean run( Entity entity )
  {
    return true;
  }
}
