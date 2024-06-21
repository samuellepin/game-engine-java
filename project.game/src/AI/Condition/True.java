package src.AI.Condition;

import src.Model.Entity;

public class True implements ConditionFsm
{

  @Override
  public boolean evaluate( Entity entity )
  {
    return true;
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof True ) return true;
    return false;
  }
}
