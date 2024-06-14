package src.Model.World;

import src.Model.Entity;
import src.Model.Vector;
import src.Model.Collision.AABB;

public class Tile extends Entity
{
  private TILE_TYPE m_type;

  public Tile( TILE_TYPE type, double x, double y )
  {
    super( null );
    m_type = type;
    m_hitbox = new AABB( new Vector( x, y ), new Vector( x + Map.TILE_WIDTH, y + Map.TILE_HEIGHT ) );
    super.setPos( x, y );
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
}
