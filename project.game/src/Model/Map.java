package src.Model;

import java.util.Random;

public class Map
{
  private int     m_width;
  private int     m_height;
  private Grid    m_grid;
  private Random  m_random = new Random();
  private Biome[] m_biome_tab;

  public Map( int width, int height )
  {
    m_width = width;
    m_height = height;
    m_grid = new Grid( width, height );
    m_random.setSeed( 123456789 );
  }

  public int getHeight()
  {
    return m_height;
  }

  public int getWidth()
  {
    return m_width;
  }

  private boolean placeBiome( int x, int y, Biome biome )
  {
    for ( int i = x; i < biome.getWidth(); i++ )
    {
      for ( int j = y; j < biome.getHeight(); j++ )
      {
        if( m_grid.getCell( i, j ).getBiome() != null )
        {
          return false;
        }
      }
    }
    for ( int i = x; i < biome.getWidth(); i++ )
    {
      for ( int j = y; j < biome.getHeight(); j++ )
      {
        m_grid.getCell( i, j ).setBiome( biome );
      }
    }
    return true;
  }

  public void fillMap()
  {
    int   biomeX;
    int   biomeY;
    Biome biome;
    while( true )
    {
      biomeX = m_random.nextInt( m_width );
      biomeY = m_random.nextInt( m_height );
      biome = new BiomeA( biomeX, biomeY );
      if( ! ( placeBiome( biomeX, biomeY, biome ) ) ) break;
      //ajouter le biome à la liste
    }
  }
}