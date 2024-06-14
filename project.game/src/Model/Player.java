package src.Model;

import src.AI.Automaton;

public class Player extends Entity
{

  public Player( Automaton automaton )
  {
    super( automaton );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 6 );
    super.updateHitbox();
  }
  
}
