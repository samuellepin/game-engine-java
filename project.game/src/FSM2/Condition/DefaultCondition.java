package src.FSM2.Condition;

import src.Model.Entity;

public class DefaultCondition implements Condition
{
  @Override
  public boolean evaluate( Entity entity )
  {
    return true;
  }
}
