package src.FSM2.Condition;

import src.Model.Entity;

public class Negation implements Condition
{
  private Condition m_cond;
  
  public Negation( Condition cond )
  {
    m_cond = cond;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    return !m_cond.evaluate( entity );
  }

}
