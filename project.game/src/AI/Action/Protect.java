package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;
/*Ignore les getHit() qu'il reçoit des entités
 * dans la direction dir, pendant t mili-secondes.*/
public class Protect implements ActionFsm
{
  
  private Direction m_dir;
  private long m_time;
  
  public Protect()
  {
    // TODO define default values
    m_dir = new Direction(Direction.DIRECTION.Forward);
    m_time = 100;
  }
  
  public Protect( Direction dir, Integer time )
  {
    m_dir = dir;
    m_time = time.longValue();
  }

  @Override
  public void execute( Entity entity )
  {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Protect )
    {
      Protect protect = (Protect)action;
      if( protect.m_dir.equals( m_dir ) && protect.m_time == m_time ) return true;
    }
    return false;
  }
}
