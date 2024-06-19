package src.AI.Condition;

import src.Model.Entity;

public class NegationFsm implements ConditionFsm
{
  private ConditionFsm cond;
  
  public NegationFsm(ConditionFsm c) {
    cond = c;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    return !cond.evaluate( entity );
  }

}
