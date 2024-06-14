package src.Model.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import src.Model.Archive;
import src.Model.Config;
import src.Model.Entity;
import src.Model.Vector;
import src.Model.Collision.Collision;

public class Map
{
  private Tile[][]           m_tiles;
  private Random             m_rand;
  private Biome[]            m_biome_tab;                     // pour le random entre les différents biomes
  private ArrayList< Biome > m_biomes;                        // pour afficher les biomes pas forcément utile
  public static final int    TILE_WIDTH           = 100;
  public static final int    TILE_HEIGHT          = 100;
  public static final int    COLS_NUM             = 51;
  public static final int    ROWS_NUM             = 51;
  public int                 m_spaceBetweenBiomes = 3;

  private static Map         INSTANCE             = new Map();

  public static Map getInstance()
  {
    return INSTANCE;
  }

  /// < Backtracker algorithm
  private Map()
  {
    m_rand = new Random();
    m_rand.setSeed( Config.SEED );
    m_tiles = new Tile[ ROWS_NUM ][ COLS_NUM ];
    for ( int i = 0; i < ROWS_NUM; i++ )
    {
      for ( int j = 0; j < COLS_NUM; j++ )
      {
        if( ( i % 2 != 0 ) && ( j % 2 != 0 ) )
        {
          m_tiles[ i ][ j ] = new Tile( TILE_TYPE.EMPTY, (double) ( j * Map.TILE_WIDTH ),
              (double) ( i * Map.TILE_HEIGHT ) );
        }
        else if( i == 0 || j == 0 || i == ROWS_NUM - 1 || j == COLS_NUM - 1 )
        {
          m_tiles[ i ][ j ] = new Tile( TILE_TYPE.BORDER, (double) ( j * Map.TILE_WIDTH ),
              (double) ( i * Map.TILE_HEIGHT ) );
        }
        else
        {
          m_tiles[ i ][ j ] = new Tile( TILE_TYPE.WALL, (double) ( j * Map.TILE_WIDTH ),
              (double) ( i * Map.TILE_HEIGHT ) );
        }
      }
    }
    fillMap();

    System.out.println( this.toString() );

    createPath( 1, 1 );

    System.out.println( this.toString() );

    // createRoom( ( new Random() ).nextInt() % 60 + 40 );

    // System.out.println( this.toString() );
  }

  public Tile getTile( int x, int y )
  {
    assert y < ROWS_NUM && x < COLS_NUM;
    return m_tiles[ y ][ x ];
  }

  public ArrayList< Biome > getBiomes()
  {
    return m_biomes;
  }

  public void createPath( int x, int y )
  {
    Tile                tile       = getTile( x, y );
    ArrayList< Vector > directions = new ArrayList<>();
    directions.add( new Vector( 0, -2 ) );
    directions.add( new Vector( -2, 0 ) );
    directions.add( new Vector( 2, 0 ) );
    directions.add( new Vector( 0, 2 ) );
    Collections.shuffle( directions ,m_rand);

    for ( int i = 0; i < 4; i++ )
    {
      int dx = x + (int)directions.get( i ).getX();
      int dy = y + (int)directions.get( i ).getY();

      if( isValidTile( dx, dy ) && this.getTile( dx, dy ).getType() == TILE_TYPE.EMPTY )
      {
        tile = this.getTile( dx, dy );
        if( tile.getType() == TILE_TYPE.EMPTY )
        {
          tile.setType( TILE_TYPE.FLOOR );

          int ddx = x + (int)directions.get( i ).getX() / 2;
          int ddy = y + (int)directions.get( i ).getY() / 2;

          tile = this.getTile( ddx, ddy );
          tile.setType( TILE_TYPE.FLOOR );

          createPath( dx, dy );
        }
      }
    }
  }

  public boolean isValidTile( int x, int y )
  {
    return x > 0 && y > 0 && x < COLS_NUM - 1 && y < ROWS_NUM - 1;
  }

  @Override
  public String toString()
  {
    StringBuilder strb = new StringBuilder();
    for ( int i = 0; i < ROWS_NUM; i++ )
    {
      for ( int j = 0; j < COLS_NUM; j++ )
      {
        switch ( this.getTile( j, i ).getType() )
        {
        case EMPTY:
          strb.append( "🟦" );
          break;
        case WALL:
          strb.append( "⬛" );
          break;
        case FLOOR:
          strb.append( "⬜" );
          break;
        case BORDER:
          strb.append( "🟫" );
          break;
        case BIOME:
          strb.append( "🟩" );
          break;
        default:
          strb.append( "🟧" );
          break;
        }
      }
      strb.append( "\n" );
    }
    return strb.toString();
  }

  public void createRoom( int num )
  {
    for ( int i = 0; i < num; i++ )
    {
      int width  = m_rand.nextInt() % 2 + 1;
      int height = m_rand.nextInt() % 2 + 1;
      int startI = m_rand.nextInt() % ( COLS_NUM - 2 ) + 1;
      int startY = m_rand.nextInt() % ( ROWS_NUM - 2 ) + 1;
      for ( int j = -1; j < width; j++ )
      {
        for ( int z = -1; z < height; z++ )
        {
          int newI = startI + j;
          int newY = startY + z;
          if( this.isValidTile( newI, newY ) )
          {
            this.getTile( newI, newY ).setType( TILE_TYPE.FLOOR );
          }
        }
      }
    }
  }

  public Vector getPos( int x, int y )
  {
    return new Vector( (double) ( x * Map.TILE_WIDTH ) + (double)Map.TILE_WIDTH / 2,
        (double) ( y * Map.TILE_HEIGHT ) + (double)Map.TILE_HEIGHT / 2 );
  }

  public boolean detectCollision( Entity entity )
  {
    for ( int i = 0; i < ROWS_NUM; i++ )
    {
      for ( int j = 0; j < COLS_NUM; j++ )
      {
        if( Collision.detect( entity.getHitbox(), m_tiles[ i ][ j ].getHitbox() ) )
        {
          return true;
        }
      }
    }
    return false;
  }

  private boolean placeBiome( int x, int y, Biome biome )
  {
    if( x + biome.getWidth() + m_spaceBetweenBiomes <= COLS_NUM
        && y + biome.getHeight() + m_spaceBetweenBiomes <= ROWS_NUM )
    {
      for ( int i = x - m_spaceBetweenBiomes; i < x + biome.getWidth() + m_spaceBetweenBiomes; i++ )
      {
        for ( int j = y - m_spaceBetweenBiomes; j < y + biome.getHeight() + m_spaceBetweenBiomes; j++ )
        {
          if( m_tiles[ j ][ i ].getType() == TILE_TYPE.BIOME )
          {
            return false;
          }
        }
      }
      for ( int i = x; i < x + biome.getWidth(); i++ )
      {
        for ( int j = y; j < y + biome.getHeight(); j++ )
        {
          m_tiles[ j ][ i ].setType( TILE_TYPE.BIOME );
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
    while( i < 500 )
    {
      biomeX = m_rand.nextInt( COLS_NUM - 2 - m_spaceBetweenBiomes ) + m_spaceBetweenBiomes;// random entre
                                                                                            // spaceBetweenBiomes et
                                                                                            // COLS_NUM-2
      biomeY = m_rand.nextInt( ROWS_NUM - 2 - m_spaceBetweenBiomes ) + m_spaceBetweenBiomes;// random entre
                                                                                            // spaceBetweenBiomes et
                                                                                            // ROWS_NUM-2
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

  public double getWidth()
  {
    return (double) ( Map.COLS_NUM * Map.TILE_WIDTH );
  }

  public double getHeight()
  {
    return (double) ( Map.ROWS_NUM * Map.TILE_HEIGHT );
  }

}
