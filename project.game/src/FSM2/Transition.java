package src.FSM2;

import src.FSM2.Action.Action;
import src.FSM2.Condition.Condition;

public class Transition
{
  private State     m_src;
  private State     m_dst;
  private Condition m_cond;
  private Action    m_act;

  Transition( State src, State dst, Condition cond, Action act )
  {
    m_src = src;
    m_dst = dst;
    m_cond = cond;
    m_act = act;
  }

  public State getSource()
  {
    return m_src;
  }

  public State getDestination()
  {
    return m_dst;
  }

  public Condition getCondition()
  {
    return m_cond;
  }

  public Action getAction()
  {
    return m_act;
  }
}
