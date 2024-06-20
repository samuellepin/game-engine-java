package src.Model;

import src.AI.Automaton;

public class Wall extends Entity
{
  public Wall( Automaton automaton )
  {
    super( automaton );
  }

  @Override
  public String toString()
  {
    return "Wall - " + super.toString();
  }
  
}
