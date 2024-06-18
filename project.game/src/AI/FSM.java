package src.AI;

import java.util.ArrayList;
import java.util.Iterator;

import src.AI.Action.ActionFsm;
import src.AI.Condition.ConditionFsm;
import src.Model.Entity;

public class FSM
{
  private ArrayList< StateFsm > m_states;
  private StateFsm              m_init_state;
  private String                m_name;

  public FSM( String name )
  {
    m_states = new ArrayList< StateFsm >();
    m_name = name;
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
    Iterator iter = m_states.iterator();
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
   * Add a transition to the FSM
   * 
   * @param src The source state of the transition
   * 
   * @param dst The destination state of the transition
   * 
   * @param cond The condition to evaluate
   * 
   * @param act The action to perform
   */
  public void addTransition( StateFsm src, StateFsm dst, ConditionFsm cond, ActionFsm act )
  {
    src.addTransition( new TransitionFsm( dst, cond, act ) );
  }


  /*
   * Evaluate the next state of the entity
   * 
   * @param entity The entity to evaluate
   * 
   * @return true if the entity has changed state, false otherwise
   */
  public boolean nextState( Entity entity )
  {
    /*
     * for ( Transition transition : m_transitions ) { if(
     * transition.getSource().isEqual( entity.getState() ) &&
     * transition.getCondition().evaluate( entity ) ) { return
     * transition.getAction().run( entity ); } }
     */
    System.out.println( "TO DO nexte State" );
    for ( StateFsm state : m_states )
    {
      if( state.isEqual( entity.getState() ) )
      {
        
        return true;
      }
    }
    return false;
  }
}
