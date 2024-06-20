package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/*Si un Pickable se situe dans son champ de vision, dans la direction dir,
 *il prend cet objet en main.
 *L'objet qu'il tenait précédemment est transfere dans Son inventaire*/
public class Pick implements ActionFsm
{

  private Direction m_dir;

  public Pick()
  {
    m_dir = new Direction(Direction.DIRECTION.Forward);
  }

  public Pick( Direction dir )
  {
    m_dir = dir;
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doPick( m_dir.toAngle( entity.getOrientation() ) );
  }

}
