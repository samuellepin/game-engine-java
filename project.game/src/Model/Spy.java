package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

public class Spy extends Entity
{
  public Spy( FSM automaton, CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options )
  {
    super( automaton, type, options );
    super.setDim( 25, 50 );
    super.setOrientation( 0 );
    super.setVelocity( 4 );
  }
}
