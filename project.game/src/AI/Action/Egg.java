package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;
/*Crée une nouvelle entité de la même classe, dans la direction dir.*/
public class Egg implements ActionFsm
{

  private Direction m_dir;

  public Egg( Direction dir )
  {
    m_dir = dir;
  }

  public Egg()
  {
    // TODO define default values
    m_dir = new Direction(Direction.DIRECTION.BackWard);
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
