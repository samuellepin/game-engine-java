package src.AI.Condition;

import src.AI.CATEGORY;
import src.AI.DIRECTION;
import src.Model.Entity;

public class Closest implements ConditionFsm
{

  private CATEGORY  m_cat;
  private DIRECTION m_dir;

  public Closest()
  {
    // TODO define default values
    m_cat = CATEGORY.Adversary;
    m_dir = DIRECTION.Forward;
  }

  public Closest( CATEGORY cat, DIRECTION dir )
  {
    m_cat = cat;
    m_dir = dir;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    // TODO Auto-generated method stub
    return false;
  }

}
