package src.Model;

import src.AI.FSM;

///< Documentation succinte de LUSTRE
public class Document extends Entity
{
  public Document( FSM automaton )
  {
    super( null );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
  }
}
