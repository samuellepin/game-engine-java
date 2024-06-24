package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.Model.World.Map;
import src.AI.FSM;

public class Spy extends Entity
{
  private Entity  m_metamorph;
  private boolean m_updateView;
  
  public Spy()
  {
    super();
  }

  @Override
  public String toString()
  {
    return "Spy - " + super.toString();
  }
  
  @Override
  public void getHit(int damage) {
    this.subHP( damage );
  }
}
