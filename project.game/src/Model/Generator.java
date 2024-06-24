package src.Model;

import java.util.List;

import src.AI.CategoryFsm;
import src.AI.FSM;
import src.Model.World.Map;

public class Generator extends Entity
{
  private boolean m_isEnabled;

  public Generator()
  {
    super();
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
