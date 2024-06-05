package src.AI;

import java.util.ArrayList;

import src.Entity;

public class Automaton
{
  private ArrayList< Transition > m_transitions;

  public Automaton( ArrayList< Transition > transitions )
  {
    m_transitions = transitions;
  }

  public boolean Next( Entity entity )
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
