package src.AI;

import java.util.ArrayList;

import src.Model.Entity;

public class StateFsm
{
  private String                  m_name;
  private ArrayList< TransitionFsm > m_transitions;

  public StateFsm(String name )
  {
    m_name = name;
    m_transitions = new ArrayList< TransitionFsm >();
  }

  public StateFsm( String name, ArrayList< TransitionFsm > trans )
  {
    m_name = name;
    m_transitions = trans;
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
    return this.getName() == state.getName() ;
  }
  
  public boolean evaluate(Entity e) {
    for(TransitionFsm t : m_transitions) {
      if(t.evaluate( e )) {
        t.getAction().execute( e );
        e.setState( t.getDestination() );
        return true;
      }
    }
    return false;
  }
}
