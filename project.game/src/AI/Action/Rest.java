package src.AI.Action;

import src.Model.Entity;

/*Ne fait rien pendant t mili-secondes,
 *et appelle la méthode addPow(pow)*/
public class Rest implements ActionFsm
{

  private long m_time;
  private int  m_pow;

  public Rest()
  {
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
    entity.doRest( m_time, m_pow );
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Rest )
    {
      Rest rest = (Rest)action;
      if( rest.m_time == m_time && rest.m_pow == m_pow ) return true;
    }
    return false;
  }

}
