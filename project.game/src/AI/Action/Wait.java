package src.AI.Action;

import src.Model.Entity;

public class Wait implements ActionFsm
{

  private long m_time;

  public Wait()
  {
    // TODO define default values
    m_time = 100;
  }

  public Wait( Integer time )
  {
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
