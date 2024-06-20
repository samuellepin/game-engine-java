package src.AI.Condition;

import src.AI.KEY;
import src.Model.Entity;

public class Key implements ConditionFsm
{
  private KEY m_key;

  public Key()
  {
    // TODO define default values
    m_key = KEY.UNDERSCORE;
  }

  public Key( KEY key )
  {
    m_key = key;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    // TODO Auto-generated method stub
    return false;
  }

}
