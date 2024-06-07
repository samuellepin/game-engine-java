package src.AI;

import src.CATEGORY;
import src.DIRECTION;
import src.Entity;
import src.Game;
import src.Grid;

public class SnakeTailAutomaton extends Automaton
{
  public static final State               STATE_IDLE = new State( 0 );
  public static final State               STATE_DEAD = new State( 1 );

  private static final SnakeTailAutomaton INSTANCE   = new SnakeTailAutomaton();

  private SnakeTailAutomaton()
  {
    super();
  }

  public State getInitialState()
  {
    return STATE_IDLE;
  }

  public static SnakeTailAutomaton getInstance()
  {
    return INSTANCE;
  }
}
