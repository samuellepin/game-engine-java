package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

public class Egg implements ActionFsm
{

  private DIRECTION m_dir;

  public Egg( DIRECTION dir )
  {
    m_dir = dir;
  }

  public Egg()
  {
    // TODO define default values
    m_dir = DIRECTION.BackWard;
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
