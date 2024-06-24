package src.AI.Action;

import src.AI.Direction;
import src.Model.Entity;

/*L'entité appelle getHit() sur chaque entité dans son champ de vision, dans la direction dir*/
public class Hit implements ActionFsm
{

  private Direction m_dir;
  private int m_damage;

  public Hit( Direction dir )
  {
    m_dir = dir;
    m_damage = 1;
  }
  
  public Hit(Direction dir , Integer n) {
    m_dir = dir;
    m_damage = n.intValue();
  }
  
  public Hit(Direction dir , int n) {
    m_dir = dir;
    m_damage = n;
  }

  public Hit()
  {
    m_dir = new Direction(Direction.DIRECTION.Forward);
    m_damage = 1;
  }

  @Override
  public void execute( Entity entity )
  {
    entity.doHit( m_dir.toAngle( entity.getOrientation() ), m_damage );
  }

  @Override
  public boolean equals( Object action )
  {
    if( action instanceof Hit )
    {
      Hit hit = (Hit)action;
      if( hit.m_dir.equals( m_dir ) && hit.m_damage == this.m_damage) return true;
    }
    return false;
  }

}
