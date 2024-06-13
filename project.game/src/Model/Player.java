package src.Model;

import src.AI.Automaton;

public class Player extends Entity
{

  public Player( Automaton automaton )
  {
    super( automaton );
    super.setWidth( 25 );
    super.setHeight( 50 );
    super.setOrientation( 0 );
  }
  
}
