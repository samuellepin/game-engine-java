package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

public class Protect implements ActionFsm
{
  
  private DIRECTION m_dir;
  private long m_time;
  
  public Protect()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
    m_time = 100;
  }
  
  public Protect( DIRECTION dir, Integer time )
  {
    m_dir = dir;
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
