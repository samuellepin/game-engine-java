package src.Model.World;

public class Tile
{
  private TILE_TYPE m_type;
  
  public Tile( TILE_TYPE type )
  {
    m_type = type;
  }
  
  public TILE_TYPE getType()
  {
    return m_type;
  }
  
  public void setType( TILE_TYPE type )
  {
    m_type = type;
  }
}
