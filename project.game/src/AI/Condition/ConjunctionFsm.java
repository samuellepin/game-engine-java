package src.AI.Condition;

import src.Model.Entity;

public class ConjunctionFsm implements ConditionFsm
{
  ConditionFsm m_c1, m_c2;

  public ConjunctionFsm( ConditionFsm c1, ConditionFsm c2 )
  {
    m_c1 = c1;
    m_c2 = c2;
  }

  public boolean evaluate( Entity e )
  {
    return m_c1.evaluate( e ) && m_c2.evaluate( e );
  }
}
