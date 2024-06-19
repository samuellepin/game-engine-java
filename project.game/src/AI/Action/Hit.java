package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

public class Hit implements ActionFsm
{

  private DIRECTION m_dir;
  
  public Hit( DIRECTION dir )
  {
    m_dir = dir;
  }
  
  public Hit()
  {
    //TODO define default values
    m_dir = DIRECTION.Forward;
  }
  
  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
