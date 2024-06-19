package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

public class Throw implements ActionFsm
{

  private DIRECTION m_dir;
  
  public Throw()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
  }
  
  public Throw( DIRECTION dir )
  {
    m_dir = dir;
  }
  
  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
