package src.Model;

public class Map
{
  private int  m_width;
  private int  m_height;
  private Grid m_grid;

  public Map( int width, int height )
  {
    m_width = width;
    m_height = height;
    m_grid = new Grid( width, height );
  }

  public int getHeight()
  {
    return m_height;
  }

  public int getWidth()
  {
    return m_width;
  }

  private boolean placeBiome( Biome biome )
  {
    for ( int i = biome.getX(); i < biome.getWidth(); i++ )
    {
      for ( int j = biome.getY(); j < biome.getHeight(); j++ )
      {
        if( m_grid.getCell( i, j ).getBiome()!=null )
        {
          return false;
        }
      }
    }
    for ( int i = biome.getX(); i < biome.getWidth(); i++ )
    {
      for ( int j = biome.getY(); j < biome.getHeight(); j++ )
      {
        m_grid.getCell( i, j ).setBiome( biome );
      }
    }
    return true;
  }
}