package src.AI;

public class TransitionFsm
{
  private StateFsm     m_dst;
  private ConditionFsm m_cond;
  private ActionFsm    m_act;

  TransitionFsm(StateFsm dst, ConditionFsm cond, ActionFsm act )
  {
    m_dst = dst;
    m_cond = cond;
    m_act = act;
  }

  public StateFsm getDestination()
  {
    return m_dst;
  }

  public ConditionFsm getCondition()
  {
    return m_cond;
  }

  public ActionFsm getAction()
  {
    return m_act;
  }

  public void setDestination( StateFsm dst )
  {
    m_dst = dst;
  }

  public void setCondition( ConditionFsm cond )
  {
    m_cond = cond;
  }

  public void setAction( ActionFsm act )
  {
    m_act = act;
  }
 
}
