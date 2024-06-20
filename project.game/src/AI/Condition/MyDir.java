package src.AI.Condition;

import src.AI.Direction;
import src.Model.Entity;

public class MyDir implements ConditionFsm
{

  private Direction m_dir;
  
  public MyDir()
  {
    // TODO define default values
    m_dir = new Direction(Direction.DIRECTION.Forward);
  }
  
  public MyDir( Direction dir )
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
