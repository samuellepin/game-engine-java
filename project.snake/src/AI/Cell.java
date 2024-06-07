package src.AI;

import src.CATEGORY;
import src.DIRECTION;
import src.Entity;

public class Cell implements Condition
{
  private DIRECTION m_direction;
  private CATEGORY  m_category;

  public Cell( DIRECTION direction, CATEGORY category )
  {
    m_direction = direction;
    m_category = category;
  }

  @Override
  public boolean evaluate( Entity entity )
  {
    return entity.cell( m_direction, m_category );
  }
}
