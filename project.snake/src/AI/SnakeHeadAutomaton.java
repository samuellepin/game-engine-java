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
    addTransition( STATE_IDLE, STATE_IDLE, new Cell( DIRECTION.UP, CATEGORY.EMPTY ), new Move( DIRECTION.UP ) );
    addTransition( STATE_IDLE, STATE_IDLE, new Cell( DIRECTION.DOWN, CATEGORY.EMPTY ), new Move( DIRECTION.DOWN ) );
    addTransition( STATE_IDLE, STATE_IDLE, new Cell( DIRECTION.RIGHT, CATEGORY.EMPTY ), new Move( DIRECTION.RIGHT ) );
    addTransition( STATE_IDLE, STATE_IDLE, new Cell( DIRECTION.LEFT, CATEGORY.EMPTY ), new Move( DIRECTION.LEFT ) );
    addTransition( STATE_IDLE, STATE_DEAD, new DefaultCondition(), new DefaultAction() );
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
