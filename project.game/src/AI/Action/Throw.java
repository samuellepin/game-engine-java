package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/*Lache l'objet qu'il a en main, dans la direction dir.*/
public class Throw implements ActionFsm
{

  private Direction m_dir;

  public Throw()
  {
    m_dir = new Direction( Direction.DIRECTION.Forward );
  }

  public Throw( Direction dir )
  {
    m_dir = dir;
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doThrow( m_dir.toAngle( entity.getOrientation() ) );

  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Throw )
    {
      Throw th = (Throw)action;
      if( th.m_dir.equals( m_dir ) ) return true;
    }
    return false;
  }

}
