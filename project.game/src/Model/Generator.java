package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.World.Map;

public class Generator extends Entity
{
  private boolean m_isEnabled;
  
  public Generator( FSM fsm, int id, double width, double height, double velocity, boolean hasCollision, CategoryFsm.CATEGORY type, List< CategoryFsm.CATEGORY > options )
  {
    super( fsm, id, width, height, velocity, hasCollision, type, options );
    this.setPos( Map.getInstance().getRandomPos() );
    m_isEnabled = false;
  }

  @Override
  public String toString()
  {
    return "Generator - " + super.toString();
  }
  
  public void enable()
  {
    m_isEnabled = true;
  }
  
  public boolean isEnabled()
  {
    return m_isEnabled;
  }
}
