package src.AI.Condition;

import src.Model.Entity;

public class True implements ConditionFsm
{

  @Override
  public boolean evaluate( Entity entity )
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof True ) return true;
    return false;
  }
}
