package src.AI;

import src.Entity;

public class DefaultCondition implements Condition
{
  @Override
  public boolean evaluate( Entity entity )
  {
    return true;
  }
}
