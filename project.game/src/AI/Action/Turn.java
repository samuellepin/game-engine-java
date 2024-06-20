package src.AI.Action;

import src.AI.DIRECTION;
import src.Model.Entity;

/*L'entité prend l'orientation dir*/
public class Turn implements ActionFsm
{
  private DIRECTION m_dir;

  public Turn()
  {
    // TODO define default values
    m_dir = DIRECTION.Right;
  }

  public Turn( DIRECTION dir )
  {
    m_dir = dir;
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

}
