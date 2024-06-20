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

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Got )
    {
      Got got = (Got)action;
      if( got.m_cat.equals( m_cat ) && got.m_floorVal == m_floorVal ) return true;
    }
    return false;
  }

}
