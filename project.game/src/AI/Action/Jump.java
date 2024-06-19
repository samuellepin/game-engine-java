package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

/* Se dipisce de dist metres dans 1a direction dir,
 * en ignorant les obstacles. Si l'entité aterrit sur un obstacle,
 * le saut s'arrete avant cet obstacle.*/
public class Jump implements ActionFsm
{

  private DIRECTION m_dir;
  private double    m_dist;

  public Jump()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
    m_dist = 0;
  }

  public Jump( DIRECTION dir, Integer dist )
  {
    m_dir = dir;
    m_dist = dist.doubleValue();
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
