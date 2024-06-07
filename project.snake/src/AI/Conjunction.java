package src.AI;

import src.Entity;

public class Conjunction implements Condition
{
  Condition m_c1, m_c2;

  Conjunction( Condition c1, Condition c2 )
  {
    m_c1 = c1;
    m_c2 = c2;
  }

  public boolean evaluate( Entity e )
  {
    return m_c1.evaluate( e ) && m_c2.evaluate( e );
  }
}
