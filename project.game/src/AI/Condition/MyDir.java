package src.AI.Condition;

import src.AI.Direction;
import src.Model.Entity;

public class MyDir implements ConditionFsm
{

  private Direction m_dir;

  public MyDir()
  {
    // TODO define default values
    m_dir = new Direction( Direction.DIRECTION.Forward );
  }

  public MyDir( Direction dir )
  {
    m_dir = dir;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    return entity.getMyDir( m_dir );
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof MyDir )
    {
      MyDir mydir = (MyDir)action;
      if( mydir.m_dir.equals( m_dir ) ) return true;
    }
    return false;
  }
}
