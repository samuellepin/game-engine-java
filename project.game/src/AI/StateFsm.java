package src.AI;

import java.util.ArrayList;

public class StateFsm
{
  private int                     m_id;
  private String                  m_name;
  private ArrayList< TransitionFsm > m_transitions;

  public StateFsm( int id, String name )
  {
    m_id = id;
    m_name = name;
    m_transitions = new ArrayList< TransitionFsm >();
  }

  public StateFsm( int id, String name, ArrayList< TransitionFsm > trans )
  {
    m_id = id;
    m_name = name;
    m_transitions = trans;
  }

  public void addTransition( TransitionFsm trans )
  {
    m_transitions.add( trans );
  }

  public void setID( int id )
  {
    m_id = id;
  }

  public int getID()
  {
    return m_id;
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
    return ( this.getID() == state.getID() ) && ( this.getName() == state.getName() );
  }
}
