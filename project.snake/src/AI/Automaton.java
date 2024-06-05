package src.AI;

import java.util.ArrayList;

import src.Entity;

public abstract class Automaton
{
  private ArrayList< Transition > m_transitions;

  public Automaton()
  {
    m_transitions = new ArrayList< Transition >();
  }
  
  protected void addTransition( State src, State dst, Condition cond, Action act )
  {
    m_transitions.add( new Transition( src, dst, cond, act ) );
  }

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
  
  public abstract State getInitialState();
}
