package src.Model.World;

import src.Model.Vector;
import src.Model.Collision.AABB;

public class Tile
{
  private TILE_TYPE m_type;
  private AABB      m_hitbox;

  public Tile( TILE_TYPE type, double x, double y )
  {
    m_type = type;
    m_hitbox = new AABB( new Vector( x, y ), new Vector( x + Map.TILE_WIDTH, y + Map.TILE_HEIGHT ) );
  }

  public TILE_TYPE getType()
  {
    return m_type;
  }

  public void setType( TILE_TYPE type )
  {
    m_type = type;
    if( type == TILE_TYPE.FLOOR )
    {
      m_hitbox = null;
    }
  }

  public AABB getHitbox()
  {
    return m_hitbox;
  }
}
