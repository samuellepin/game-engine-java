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
    m_dir = new Direction(Direction.DIRECTION.Forward);
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

}
