package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;

public class Wall extends Entity
{
  public Wall( FSM automaton, CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options )
  {
    super( automaton, type, options );
  }

}
