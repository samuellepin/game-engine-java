package src.AI;

import java.util.ArrayList;
import java.util.Iterator;

import src.Model.Entity;

public class FSM implements Cloneable
{
  private ArrayList< StateFsm > m_states;
  private StateFsm              m_init_state;
  private String                m_name;

  public FSM( String name )
  {
    m_states = new ArrayList< StateFsm >();
    m_name = name;
  }

  public FSM( String name, ArrayList< StateFsm > st )
  {
    m_states = st;
    m_name = name;
  }

  public FSM( String name, ArrayList< StateFsm > st, StateFsm init )
  {
    m_states = st;
    m_name = name;
    m_init_state = init;
  }

  public FSM clone() throws CloneNotSupportedException
  {
    FSM cloned = (FSM)super.clone();
    cloned.m_init_state = m_init_state.clone();
    cloned.m_name = m_name;
    cloned.m_states = (ArrayList< StateFsm >)m_states.clone();
    return cloned;
  }

  /*
   * Set the initial state of the FSM, the initial state is added to the list of
   * states.
   * 
   * @param init The initial state
   */
  public void setInitState( StateFsm state )
  {
    m_init_state = state;
    addState( state );
  }

  /*
   * Get the initial state of the FSM
   * 
   * @return The initial state
   */
  public StateFsm getInitialState()
  {
    return m_init_state;
  }

  /*
   * Add a state to the FSM
   * 
   * @param st The state to add
   */
  public void addState( StateFsm st )
  {
    Iterator< StateFsm > iter = m_states.iterator();
    while( iter.hasNext() )
    {
      StateFsm s = (StateFsm)iter.next();
      if( !s.getName().equals( st ) )
      {
        return;
      }
    }
    m_states.add( st );

  }

  /*
   * Evaluate the next state of the entity
   * 
   * @param entity The entity to evaluate
   * 
   * @return true if the entity has changed state, false otherwise
   */
  public TransitionFsm nextState( Entity e, StateFsm currentState )
  {
    for ( StateFsm state : m_states )
    {
      if( state.isEqual( currentState ) )
      {
        return state.evaluate( e );
      }
    }
    return null;
  }

  public String getName()
  {
    return m_name;
  }
}
