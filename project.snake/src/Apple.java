package src;

import src.Grid.Cell;
import src.AI.AppleAutomaton;
import src.AI.Automaton;

public class Apple extends Entity
{
  protected Apple( Automaton automaton, Cell cell )
  {
    super( automaton, cell );
  }

  boolean isEaten()
  {
    return this.getState() == AppleAutomaton.STATE_EATEN;
  }
}
