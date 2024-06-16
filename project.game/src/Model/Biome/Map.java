package src.Model.Biome;

import java.util.Random;
import src.Model.World.Tile;
import src.Model.World.TILE_TYPE;
import java.util.ArrayList;

public class Map
{
  private Tile[][]           m_tiles;
  private Random             m_random = new Random();
  private ArrayList< Biome > m_biomes;
  public static final int    COLS_NUM = 15;
  public static final int    ROWS_NUM = 15;

  public Map()
  {
    m_tiles = new Tile[ ROWS_NUM ][ COLS_NUM ];
    for ( int i = 0; i < ROWS_NUM; i++ )
    {
      for ( int j = 0; j < COLS_NUM; j++ )
      {
        m_tiles[ i ][ j ] = new Tile( TILE_TYPE.EMPTY, j, i );
      }
    }
    m_random.setSeed( 123485789 );
    fillMap();
    System.out.println( this.toString() );
  }

  public int getHeight()
  {
    return ROWS_NUM;
  }

  public int getWidth()
  {
    return COLS_NUM;
  }

  public ArrayList< Biome > getBiomes()
  {
    return m_biomes;
  }

  private boolean placeBiome( int x, int y, Biome biome )
  {
    if( x + biome.getWidth() < COLS_NUM && y + biome.getHeight() < ROWS_NUM )
    {
      for ( int i = x - 1; i < x + biome.getWidth() + 1; i++ )
      {
        for ( int j = y - 1; j < y + biome.getHeight() + 1; j++ )
        {
          if( m_tiles[ j ][ i ].getType() != TILE_TYPE.EMPTY )
          {
            return false;
          }
        }
      }
      for ( int i = x; i < x + biome.getWidth(); i++ )
      {
        for ( int j = y; j < y + biome.getHeight(); j++ )
        {
          m_tiles[ j ][ i ].setType( TILE_TYPE.WALL );
        }
      }
      int entryX = x + (int)biome.getEntry().getX();
      int entryY = y + (int)biome.getEntry().getY();
      while( entryY == 1 && entryX == 1 || entryY == ROWS_NUM - 2 && entryX == COLS_NUM - 2
          || entryX == 1 && entryY == ROWS_NUM - 2 || entryX == COLS_NUM - 2 && entryY == 1 )
      {
        biome.entryNext();
        entryX = x + (int)biome.getEntry().getX();
        entryY = y + (int)biome.getEntry().getY();
      }
      m_tiles[ entryY ][ entryX ].setType( TILE_TYPE.EMPTY );
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
    while( i < 5000 )
    {
      biomeX = m_random.nextInt( COLS_NUM - 2 ) + 1;// random entre 1 et COLS_NUM-1
      biomeY = m_random.nextInt( ROWS_NUM - 2 ) + 1;// random entre 1 et ROWS_NUM-1
      if( biomeX % 2 == 0 )
      {
        biomeX++ ;
      }
      if( biomeY % 2 == 0 )
      {
        biomeY++ ;
      }
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
    for ( int i = 0; i < ROWS_NUM; i++ )
    {
      for ( int j = 0; j < COLS_NUM; j++ )
      {
        switch ( m_tiles[ i ][ j ].getType() )
        {
        case WALL:
          strb.append( "🟦" );
          break;
        case EMPTY:
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