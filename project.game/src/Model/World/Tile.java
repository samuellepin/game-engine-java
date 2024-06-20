package src.Model.World;

import src.Config;

public class Tile
{
  public static final double HEIGHT = Config.getInstance().getWorld().getTile().getWidth();
  public static final double WIDTH  = Config.getInstance().getWorld().getTile().getHeight();

  private TILE_TYPE          m_type;
  private double             m_x, m_y;

  public Tile( TILE_TYPE type, double x, double y )
  {
    m_type = type;
    m_x = x;
    m_y = y;
  }

  public double getX()
  {
    return m_x;
  }

  public double getY()
  {
    return m_y;
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
