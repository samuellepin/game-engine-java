package src;

import src.Grid.Cell;
import src.AI.Automaton;

public class SnakeTail extends Entity
{
  private SnakeTail m_next;

  public SnakeTail( Automaton automaton, Cell cell )
  {
    super( automaton, cell );
    m_next = null;
  }

  public SnakeTail getNext()
  {
    return m_next;
  }
}
