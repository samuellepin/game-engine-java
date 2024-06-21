package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/*L'entité prend l'orientation dir*/
public class Turn implements ActionFsm
{
  private Direction m_dir;
  private long      m_timeToTurn;

  public Turn()
  {
    m_timeToTurn = 0;
    m_dir = new Direction( Direction.DIRECTION.Right );
  }

  public Turn( Direction dir, long time )
  {
    m_dir = dir;
    m_timeToTurn = time;
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doTurn( m_dir.toAngle( entity.getOrientation() ), m_timeToTurn );

  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Turn )
    {
      Turn turn = (Turn)action;
      if( turn.m_dir.equals( m_dir ) ) return true;
    }
    return false;
  }

}
