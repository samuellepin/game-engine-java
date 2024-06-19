package src.AI.Condition;

import src.AI.DIRECTION;
import src.Model.Entity;

public class MyDir implements ConditionFsm
{

  private DIRECTION m_dir;
  
  public MyDir()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
  }
  
  public MyDir( DIRECTION dir )
  {
    m_dir = dir;
  }
  
  @Override
  public boolean evaluate( Entity entity )
  {
    // TODO Auto-generated method stub
    return false;
  }

}
