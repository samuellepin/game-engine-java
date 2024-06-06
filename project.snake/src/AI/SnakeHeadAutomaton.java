package src.AI;

import src.CATEGORY;
import src.DIRECTION;
import src.Entity;

public class SnakeHeadAutomaton extends Automaton
{
  public static final State STATE_IDLE = new State( 0 );
  public static final State STATE_DEAD = new State( 1 );
  
  private static final SnakeHeadAutomaton INSTANCE = new SnakeHeadAutomaton();
  
  private SnakeHeadAutomaton()
  {
    super();
    addTransition( STATE_IDLE, STATE_IDLE, new DefaultCondition(), new Turn( DIRECTION.RIGHT ) );
  }
  
  public State getInitialState()
  {
    return STATE_IDLE;
  }
  
  public static SnakeHeadAutomaton getInstance()
  {
    return INSTANCE;
  }
  
}
