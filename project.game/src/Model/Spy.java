package src.Model;

import src.AI.Automaton;
import src.Model.World.Map;

public class Spy extends Entity
{
  public Spy( Automaton automaton )
  {
    super( automaton );
    this.setPos( Map.getInstance().getRandomPos() );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 4 );
  }
  
  public Spy( Automaton automaton, int id, double width, double height, double velocity, boolean hasCollision )
  {
    super( automaton, id, width, height, velocity, hasCollision );
    this.setPos( Map.getInstance().getRandomPos() );
  }
  
  @Override
  public String toString()
  {
    return "Spy - " + super.toString();
  }
}
