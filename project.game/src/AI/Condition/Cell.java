package src.AI.Condition;

import src.AI.CATEGORY;
import src.AI.Direction;
import src.Model.Entity;

public class Cell implements ConditionFsm
{

  private Direction m_dir;
  private CATEGORY  m_cat;

  public Cell()
  {
    // TODO define default values
    m_dir = new Direction( Direction.DIRECTION.Forward );
    m_cat = CATEGORY.Adversary;
  }

  public Cell( Direction dir, CATEGORY cat )
  {
    m_dir = dir;
    m_cat = cat;
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
    if( action instanceof Cell )
    {
      Cell cell = (Cell)action;
      if( cell.m_cat.equals( m_cat ) && cell.m_dir.equals( m_dir ) ) return true;
    }
    return false;
  }

}