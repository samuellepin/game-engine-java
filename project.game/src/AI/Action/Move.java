package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

/*Se déplace pendant t milli-secondes, dans la direction dir avec la vélocite actuelle de l'entité*/
public class Move implements ActionFsm
{

  private DIRECTION m_dir;
  private long      m_time;

  public Move()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
    m_time = 100;
  }

  public Move( DIRECTION dir, Integer time )
  {
    m_dir = dir;
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
