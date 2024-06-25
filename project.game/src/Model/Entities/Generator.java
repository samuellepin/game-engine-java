package src.Model.Entities;

import src.Model.Entity;

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
