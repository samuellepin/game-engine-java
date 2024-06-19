package src.AI.Action;

import src.Model.Entity;

public class Rest implements ActionFsm
{

  private long m_time;
  private int  m_pow;

  public Rest()
  {
    // TODO define default values
    m_time = 100;
    m_pow = 5;
  }

  public Rest( Integer time, Integer pow )
  {
    m_time = time.longValue();
    m_pow = pow.intValue();
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
