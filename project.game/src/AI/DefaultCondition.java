package src.AI;

import src.Model.Entity;

public class DefaultCondition implements ConditionFsm
{
  @Override
  public boolean evaluate( Entity entity )
  {
    return true;
  }
}
