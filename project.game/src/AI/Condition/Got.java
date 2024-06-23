package src.AI.Condition;

import src.AI.CategoryFsm;
import src.Model.Entity;

public class Got implements ConditionFsm
{

  private CategoryFsm m_cat;
  private int         m_floorVal;

  public Got()
  {
    // TODO define default values
    m_cat = new CategoryFsm( CategoryFsm.CATEGORY.PlayerA );
    m_floorVal = 0;
  }

  public Got( CategoryFsm cat )
  {
    m_cat = cat;
    // TODO define default values
    m_floorVal = 0;
  }

  public Got( CategoryFsm cat, Integer floorVal )
  {
    m_cat = cat;
    m_floorVal = floorVal;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    return entity.got();
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
