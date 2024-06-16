package src.Model.World;

public class Tile
{
  private TILE_TYPE m_type;
  public static final double   HEIGHT = 100;
  public static final double   WIDTH  = 100;

  public Tile( TILE_TYPE type, double x, double y )
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
