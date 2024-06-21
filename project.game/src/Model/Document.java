package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

///< Documentation succinte de LUSTRE
public class Document extends Entity
{
  public Document( FSM automaton, CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options )
  {
    super( null, type, options );
    super.setDim( 25, 25 );
    super.setHasCollision( false );
  }
}
