package src.Model.Entities;

import src.Model.Entity;

public class Spy extends Entity
{
//  private Entity  m_metamorph;
//  private boolean m_updateView;
  
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
