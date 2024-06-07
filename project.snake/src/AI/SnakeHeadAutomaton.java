package src.AI;

import src.CATEGORY;
import src.DIRECTION;
import src.Entity;
import src.Game;
import src.Grid;

public class SnakeHeadAutomaton extends Automaton
{
  public static final State               STATE_IDLE  = new State( 0 );
  public static final State               STATE_DEAD  = new State( 1 );
 

  private static final SnakeHeadAutomaton INSTANCE    = new SnakeHeadAutomaton();

  private SnakeHeadAutomaton()
  {
    super();
    /// < The direction is useless here - we use the direction of the snake
    addTransition( STATE_IDLE, STATE_IDLE,
        new Conjunction( new Cell( DIRECTION.HERE, CATEGORY.EMPTY ), new Conjunction(
            new Cell( DIRECTION.LEFT, CATEGORY.EMPTY ), new Cell( DIRECTION.RIGHT, CATEGORY.EMPTY ) ) ),
        Turn.RandomTurn(true,true,true) );
    addTransition( STATE_IDLE, STATE_IDLE,
        new Conjunction( new Cell( DIRECTION.HERE, CATEGORY.EMPTY ), new Cell( DIRECTION.RIGHT, CATEGORY.EMPTY ) ),
        Turn.RandomTurn(false,true,true) );
    addTransition( STATE_IDLE, STATE_IDLE,
        new Conjunction( new Cell( DIRECTION.HERE, CATEGORY.EMPTY ), new Cell( DIRECTION.LEFT, CATEGORY.EMPTY ) ),
        Turn.RandomTurn(true,true,false) );
    addTransition( STATE_IDLE, STATE_IDLE,
        new Conjunction( new Cell( DIRECTION.LEFT, CATEGORY.EMPTY ), new Cell( DIRECTION.RIGHT, CATEGORY.EMPTY ) ),
        Turn.RandomTurn(true,false,true) );
    addTransition( STATE_IDLE, STATE_IDLE, new Cell( DIRECTION.LEFT, CATEGORY.EMPTY ), new Turn( DIRECTION.LEFT ) );
    addTransition( STATE_IDLE, STATE_IDLE, new Cell( DIRECTION.RIGHT, CATEGORY.EMPTY ), new Turn( DIRECTION.RIGHT ) );
    addTransition( STATE_IDLE, STATE_IDLE, new DefaultCondition(), new Turn( DIRECTION.HERE ) );
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
