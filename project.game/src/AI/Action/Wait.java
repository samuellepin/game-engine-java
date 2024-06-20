package src.AI.Action;

import src.Model.Entity;

/*L'entité ne fait rien pendant t milli-secondes*/
public class Wait implements ActionFsm
{

  private long m_time;

  public Wait()
  {
    m_time = 100;
  }

  public Wait( Integer time )
  {
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doWait(m_time);

  }

}
