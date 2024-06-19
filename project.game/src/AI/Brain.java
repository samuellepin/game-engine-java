package src.AI;

import java.util.ArrayList;

import src.Model.Entity;

public class Brain
{
  private Automaton           m_automaton;
  private Entity              m_entity;
  private State               m_state;
  private ArrayList< Action > m_actions;

  public Brain( Entity e, Automaton a )
  {
    m_entity = e;
    m_automaton = a;
    m_state = m_automaton.getInitialState();
    m_actions = new ArrayList< Action >();
  }

  private void step()
  {
    if( m_actions.isEmpty() )
    {
      Transition t = m_automaton.nextState( m_entity );
      m_state = t.getDestination();
      m_actions.add( t.getAction() );
    }
  }
}
