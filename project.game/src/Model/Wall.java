package src.Model;

import src.AI.FSM;

public class Wall extends Entity
{
  public Wall( FSM automaton )
  {
    super( automaton );
  }

  @Override
  public String toString()
  {
    return "Wall - " + super.toString();
  }
  
}
