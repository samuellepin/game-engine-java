package src.AI;

import java.util.ArrayList;

import src.Entity;

public class Automaton
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

  public boolean next( Entity entity )
  {
    for ( Transition transition : m_transitions )
    {
      if( transition.getSource().isEqual( entity.getState() ) && transition.getCondition().evaluate( entity ) )
      {
        transition.getAction().run( entity );
        return true;
      }
    }
    return false;
  }
}
