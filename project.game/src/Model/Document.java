package src.Model;

import src.AI.Automaton;
import src.Model.World.Map;

///< Documentation succinte de LUSTRE
public class Document extends Entity
{
  public Document( Automaton automaton )
  {
    super( null );
    this.setPos( Map.getInstance().getRandomPos() );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
  }
  
  public Document( Automaton automaton, int id, double width, double height, double velocity, boolean hasCollision )
  {
    super( automaton, id, width, height, velocity, hasCollision );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Doc - " + super.toString();
  }
}
