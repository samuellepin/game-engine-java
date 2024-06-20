package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/*L'entité prend l'orientation dir*/
public class Turn implements ActionFsm
{
  private Direction m_dir;

  public Turn()
  {
    m_dir = new Direction( Direction.DIRECTION.Right );
  }

  public Turn( Direction dir )
  {
    m_dir = dir;
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doTurn( m_dir.toAngle( entity.getOrientation() ) );

  }

}
