package src.AI;

import src.CATEGORY;
import src.DIRECTION;
import src.Entity;

public class AppleAutomaton extends Automaton
{
  public static final State           STATE_UNEATEN = new State( 0 );
  public static final State           STATE_EATEN   = new State( 1 );

  private static final AppleAutomaton INSTANCE      = new AppleAutomaton();

  private AppleAutomaton()
  {
    super();
    addTransition( STATE_UNEATEN, STATE_EATEN, new Cell( DIRECTION.HERE, CATEGORY.EMPTY ), new DefaultAction() );
    addTransition( STATE_UNEATEN, STATE_UNEATEN, new Cell( DIRECTION.HERE, CATEGORY.APPLE ), new DefaultAction() );
  }

  public State getInitialState()
  {
    return STATE_UNEATEN;
  }

  public static AppleAutomaton getInstance()
  {
    return INSTANCE;
  }

}
