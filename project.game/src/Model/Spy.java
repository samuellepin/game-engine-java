package src.Model;

import src.AI.Automaton;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.Model.World.Map;

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
