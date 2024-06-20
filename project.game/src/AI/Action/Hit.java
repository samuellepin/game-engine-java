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
    m_dir = new Direction(Direction.DIRECTION.Forward);
  }
  
  @Override
  public void execute( Entity entity )
  {
    entity.doHit( m_dir.toAngle( entity.getOrientation() ) );
  }

}
