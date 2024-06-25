package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/**
 * Crée une nouvelle entité de la même classe, dans la direction dir.
 * 
 * @field m_dir
 */
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
    m_dir = new Direction(Direction.DIRECTION.Backward);
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doEgg( m_dir );
  }
  
  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Egg )
    {
      Egg egg = (Egg)action;
      if( egg.m_dir.equals( m_dir ) ) return true;
    }
    return false;
  }

}
