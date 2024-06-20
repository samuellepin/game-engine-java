package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/*Se déplace pendant t milli-secondes, dans la direction dir avec la vélocite actuelle de l'entité*/
public class Move implements ActionFsm
{

  private Direction m_dir;
  private long      m_time;

  public Move()
  {
    m_dir = new Direction(Direction.DIRECTION.Forward);
    m_time = 100;
  }

  public Move( Direction dir, Integer time )
  {
    m_dir = dir;
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doMove( m_dir, m_time );
  }

}
