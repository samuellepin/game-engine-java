package src.Model;

import src.AI.FSM;

public class Spy extends Entity
{
  public Spy( FSM automaton )
  {
    super( automaton );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 4 );
  }
}
