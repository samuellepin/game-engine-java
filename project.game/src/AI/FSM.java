package src.AI;

import java.util.ArrayList;

import src.Model.Entity;

public class FSM
{
  private ArrayList< Transition > m_transitions;
  private ArrayList< State >      m_states;
  private State                   m_init_state;

  public FSM()
  {
    m_transitions = new ArrayList< Transition >();
    m_states = new ArrayList< State >();
  }

  /*
   * Set the initial state of the FSM, the initial state is added to the list of states.
   * @param init The initial state
   */
  public void setInitState( State init )
  {
    m_init_state = init;
    addState(init);
  }

  /*
   * Get the initial state of the FSM
   * @return The initial state
   */
  public State getInitialState()
  {
    return m_init_state;
  }
  
  /*
   * Add a state to the FSM
   * @param st The state to add
   */
  public void addState( State st) {
    if(!m_states.contains( st )) {
      m_states.add( st ) ;
    }
  }

  /*
   * Add a transition to the FSM
   * @param src The source state of the transition
   * @param dst The destination state of the transition
   * @param cond The condition to evaluate
   * @param act The action to perform
   */
  public void addTransition( State src, State dst, Condition cond, Action act )
  {
    m_transitions.add( new Transition( src, dst, cond, act ) );
  }

  /*
   * Evaluate the next state of the entity
   * @param entity The entity to evaluate
   * @return true if the entity has changed state, false otherwise
   */
  public boolean nextState( Entity entity )
  {
    for ( Transition transition : m_transitions )
    {
      if( transition.getSource().isEqual( entity.getState() ) && transition.getCondition().evaluate( entity ) )
      {
        return transition.getAction().run( entity );
      }
    }
    return false;
  }
}
