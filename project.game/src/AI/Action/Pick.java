package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

/*Si un Pickable se situe dans son champ de vision, dans la direction dir,
 *il prend cet objet en main.
 *L'objet qu'il tenait précédemment est transfere dans Son inventaire*/
public class Pick implements ActionFsm
{

  private DIRECTION m_dir;

  public Pick()
  {
    // TODO define default values
    m_dir = DIRECTION.Forward;
  }

  public Pick( DIRECTION dir )
  {
    m_dir = dir;
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
