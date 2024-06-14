package src.Model;

import src.AI.FSM;

public class Player extends Entity
{

  public Player( FSM automaton )
  {
    super( automaton );
    super.setWidth( 25 );
    super.setHeight( 50 );
    super.setOrientation( 0 );
  }
  
}
