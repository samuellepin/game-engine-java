package src.Model.Entities;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.Entity;
import src.Model.World.Map;

///< Documentation succinte de LUSTRE
public class Document extends Entity
{
  
  public Document()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Doc - " + super.toString();
  }
}
