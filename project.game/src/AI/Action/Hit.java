package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;
/*L'entité appelle getHit() sur chaque entité dans son champ de vision, dans la direction dir*/
public class Hit implements ActionFsm
{

  private Direction m_dir;
  
  public Hit( Direction dir )
  {
    m_dir = dir;
  }
  
  public Hit()
  {
    //TODO define default values
    m_dir = new Direction(Direction.DIRECTION.Forward);
  }
  
  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
