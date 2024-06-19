package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

public class Pick implements ActionFsm
{

  private DIRECTION m_dir;

  public Pick()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
  }

  public Pick( DIRECTION dir )
  {
    m_dir = dir;
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
