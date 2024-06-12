package src.Model.Biome;

import java.util.Random;

import src.Model.Biome.BiomeA;

import java.util.ArrayList;

public class Map
{
  private int                m_width;
  private int                m_height;
  private Grid               m_grid;
  private Random             m_random = new Random();
  private Biome[]            m_biome_tab;
  private ArrayList< Biome > m_biomes;

  public Map( int width, int height )
  {
    m_width = width;
    m_height = height;
    m_grid = new Grid( width, height );
    m_random.setSeed( 123485789 );
    fillMap();
    System.out.println( this.toString() );
  }

  public int getHeight()
  {
    return m_height;
  }

  public int getWidth()
  {
    return m_width;
  }

  public ArrayList< Biome > getBiomes()
  {
    return m_biomes;
  }

  private boolean placeBiome( int x, int y, Biome biome )
  {
    if( x + biome.getWidth() + 2 < m_width && y + biome.getHeight() + 2 < m_height )
    {
      for ( int i = x - 2; i < x + biome.getWidth() + 2; i++ )
      {
        for ( int j = y - 2; j < y + biome.getHeight() + 2; j++ )
        {
          if( m_grid.getCell( i, j ).getBiome() != null )
          {
            return false;
          }
        }
      }
      for ( int i = x; i < x + biome.getWidth(); i++ )
      {
        for ( int j = y; j < y + biome.getHeight(); j++ )
        {
          m_grid.getCell( i, j ).setBiome( biome );
        }
      }
      m_grid.getCell( x + (int)biome.getEntry().getX(), y + (int)biome.getEntry().getY() ).setName( "Entry" );
      return true;
    }
    return false;
  }

  public void fillMap()
  {
    int   biomeX;
    int   biomeY;
    Biome biome;
    int   i = 0;
    while( i < 50 )
    {
      biomeX = m_random.nextInt( m_width - 2 ) + 2;
      biomeY = m_random.nextInt( m_height - 2 ) + 2;
      biome = new BiomeA( biomeX, biomeY );// random entre les différents biomes
      placeBiome( biomeX, biomeY, biome );
      i++ ;
      // if( ! ( placeBiome( biomeX, biomeY, biome ) ) ) break;
      // m_biomes.add( biome );

    }
  }

  @Override
  public String toString()
  {
    StringBuilder strb = new StringBuilder();
    for ( int i = 0; i < m_grid.m_rows_num; i++ )
    {
      for ( int j = 0; j < m_grid.m_cols_num; j++ )
      {
        switch ( m_grid.getCell( j, i ).getBiomeName() )
        {
        case "BiomeA":
          strb.append( "🟦" );
          break;
        case "Entry":
          strb.append( "🟫" );
          break;
        default:
          strb.append( "⬛" );
          break;
        }
      }
      strb.append( "\n" );
    }
    return strb.toString();
  }
}