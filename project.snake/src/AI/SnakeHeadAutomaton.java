package src.AI;

import src.CATEGORY;
import src.DIRECTION;
import src.Entity;

public class SnakeHeadAutomaton extends Automaton
{
  public SnakeHeadAutomaton()
  {
    super();

    State idleState = new State( 0 );
    State deadState = new State( 1 );

    addTransition( idleState, idleState, new Cell( DIRECTION.UP, CATEGORY.EMPTY ), new Move( DIRECTION.UP ) );
    addTransition( idleState, idleState, new Cell( DIRECTION.DOWN, CATEGORY.EMPTY ), new Move( DIRECTION.DOWN ) );
    addTransition( idleState, idleState, new Cell( DIRECTION.RIGHT, CATEGORY.EMPTY ), new Move( DIRECTION.RIGHT ) );
    addTransition( idleState, idleState, new Cell( DIRECTION.LEFT, CATEGORY.EMPTY ), new Move( DIRECTION.LEFT ) );
    addTransition( idleState, deadState, new DefaultCondition(), new DefaultAction() );
  }
}
