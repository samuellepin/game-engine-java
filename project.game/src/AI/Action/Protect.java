package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;
/*Ignore les getHit() qu'il reçoit des entités
 * dans la direction dir, pendant t mili-secondes.*/
public class Protect implements ActionFsm
{
  
  private Direction m_dir;
  private long m_time;
  
  public Protect()
  {
    m_dir = new Direction(Direction.DIRECTION.Forward);
    m_time = 100;
  }
  
  public Protect( Direction dir, Integer time )
  {
    m_dir = dir;
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doProtect( m_dir, m_time );
  }

}
