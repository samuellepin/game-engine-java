package src.AI.Condition;

import src.AI.CategoryFsm;
import src.AI.Direction;
import src.Model.Entity;

public class Closest implements ConditionFsm
{

  private CategoryFsm  m_cat;
  private Direction m_dir;

  public Closest()
  {
    // TODO define default values
    m_cat = new CategoryFsm( CategoryFsm.CATEGORY.Adversary );
    m_dir = new Direction( Direction.DIRECTION.Forward );
  }

  public Closest( CategoryFsm cat, Direction dir )
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

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Closest )
    {
      Closest closest = (Closest)action;
      if( closest.m_cat.equals( m_cat ) && closest.m_dir.equals( m_dir ) ) return true;
    }
    return false;
  }

}
