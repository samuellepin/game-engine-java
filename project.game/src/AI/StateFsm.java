package src.AI;

import java.util.ArrayList;

import src.AI.Action.ActionFsm;
import src.Model.Entity;

public class StateFsm implements Cloneable
{
  private String                     m_name;
  private ArrayList< TransitionFsm > m_transitions;

  public StateFsm( String name )
  {
    m_name = name;
    m_transitions = new ArrayList< TransitionFsm >();
  }

  public StateFsm( String name, ArrayList< TransitionFsm > trans )
  {
    m_name = name;
    m_transitions = trans;
  }

  public StateFsm clone() throws CloneNotSupportedException
  {
    StateFsm cloned = (StateFsm)super.clone();
    cloned.m_name = m_name;
    cloned.m_transitions=(ArrayList< TransitionFsm >)m_transitions.clone();
    return cloned;
  }
  
  public void addTransition( TransitionFsm trans )
  {
    m_transitions.add( trans );
  }

  public void setName( String name )
  {
    m_name = name;
  }

  public String getName()
  {
    return m_name;
  }

  public boolean isEqual( StateFsm state )
  {
    return this.getName() == state.getName();
  }

  public TransitionFsm evaluate( Entity e )
  {
    for ( TransitionFsm t : m_transitions )
    {
      if( t.evaluate( e ) )
      {
        return t;
      }
    }
    return null;
  }
}
