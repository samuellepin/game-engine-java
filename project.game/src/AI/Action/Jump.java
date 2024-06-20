package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/* Se dipisce de dist metres dans 1a direction dir,
 * en ignorant les obstacles. Si l'entité aterrit sur un obstacle,
 * le saut s'arrete avant cet obstacle.*/
public class Jump implements ActionFsm
{

  private Direction m_dir;
  private double    m_dist;

  public Jump()
  {
    m_dir = new Direction(Direction.DIRECTION.Forward);
    m_dist = 1;
  }

  public Jump( Direction dir, Integer dist )
  {
    m_dir = dir;
    m_dist = dist.doubleValue();
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doJump( m_dir.toAngle( entity.getOrientation() ), m_dist );

  }
  
  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Jump )
    {
      Jump jump = (Jump)action;
      if( jump.m_dir.equals( m_dir ) && jump.m_dist == m_dist ) return true;
    }
    return false;
  }

}
