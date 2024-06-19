package src.Model;

import src.AI.Automaton;

public class Spy extends Entity
{
  public Spy( Automaton automaton )
  {
    super( automaton );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 4 );
  }
}
