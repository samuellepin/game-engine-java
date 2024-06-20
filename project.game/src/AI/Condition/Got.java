package src.AI.Condition;

import src.AI.CATEGORY;
import src.Model.Entity;

public class Got implements ConditionFsm
{

  private CATEGORY m_cat;
  private int      m_floorVal;

  public Got()
  {
    // TODO define default values
    m_cat = CATEGORY.PlayerA;
    m_floorVal = 0;
  }

  public Got( CATEGORY cat )
  {
    m_cat = cat;
    // TODO define default values
    m_floorVal = 0;
  }

  public Got( CATEGORY cat, Integer floorVal )
  {
    m_cat = cat;
    m_floorVal = floorVal;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    // TODO Auto-generated method stub
    return false;
  }

}
