package src.Model;
import src.AI.FSM;
import src.Model.World.Map;

///< Documentation succinte de LUSTRE
public class Document extends Entity
{
  public Document( FSM fsm )
  {
    super( fsm );
    this.setPos( Map.getInstance().getRandomPos() );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
  }
  
  public Document( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision )
  {
    super( fsm, id, width, height, velocity, hasCollision );
    this.setPos( Map.getInstance().getRandomPos() );
  }

  @Override
  public String toString()
  {
    return "Doc - " + super.toString();
  }
}
