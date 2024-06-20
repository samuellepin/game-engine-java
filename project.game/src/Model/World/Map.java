package src.Model.World;

import java.util.ArrayList;
import java.util.Collections;

import src.Config;
import src.AI.FsmFactory;
import src.Model.Vector;
import src.Model.Wall;

public class Map
{
  private ArrayList< Position > m_tilesAlreadyUsed;

  private Tile[][]              m_tiles;
  private Biome[]               m_biome_tab;                                                                 // pour le
                                                                                                             // random
                                                                                                             // entre
                                                                                                             // les
                                                                                                             // différents
                                                                                                             // biomes
  private ArrayList< Biome >    m_biomes;                                                                    // pour
                                                                                                             // afficher
                                                                                                             // les
                                                                                                             // biomes
                                                                                                             // pas
                                                                                                             // forcément
                                                                                                             // utile
  public static final int       COLS_NUM             = Config.getInstance().getWorld().getColsNum();
  public static final int       ROWS_NUM             = Config.getInstance().getWorld().getRowsNum();
  public int                    m_spaceBetweenBiomes = Config.getInstance().getWorld().getBiome().getSpace();

  private static Map            INSTANCE             = new Map();
  private ArrayList< Wall >     m_walls;
//  private ArrayList< AABB > m_hitbox;

  public static Map getInstance()
  {
    return INSTANCE;
  }

  /// < Backtracker algorithm
  private Map()
  {
    m_tilesAlreadyUsed = new ArrayList<>();
    m_tiles = new Tile[ ROWS_NUM ][ COLS_NUM ];
    for ( int i = 0; i < ROWS_NUM; i++ )
    {
      for ( int j = 0; j < COLS_NUM; j++ )
      {
        if( ( i % 2 != 0 ) && ( j % 2 != 0 ) )
        {
          m_tiles[ i ][ j ] = new Tile( TILE_TYPE.EMPTY, (double) ( j * Tile.WIDTH ), (double) ( i * Tile.HEIGHT ) );
        }
        else if( i == 0 || j == 0 || i == ROWS_NUM - 1 || j == COLS_NUM - 1 )
        {
          m_tiles[ i ][ j ] = new Tile( TILE_TYPE.WALL, (double) ( j * Tile.WIDTH ), (double) ( i * Tile.HEIGHT ) );
        }
        else
        {
          m_tiles[ i ][ j ] = new Tile( TILE_TYPE.WALL, (double) ( j * Tile.WIDTH ), (double) ( i * Tile.HEIGHT ) );
        }
      }
    }
    fillMap();

    createPath( 1, 1 );

    System.out.println( this.toString() );

    // createRoom( ( new Random() ).nextInt() % 60 + 40 );

    // System.out.println( this.toString() );

    m_walls = new ArrayList<>();

    if( !Config.getInstance().getParameters().isWallsEnabled() ) return;

    for ( int y = 0; y < ROWS_NUM; y++ )
    {
      for ( int x = 0; x < COLS_NUM; x++ )
      {
        if( this.getTile( x, y ).getType() == TILE_TYPE.WALL )
        {
          Wall wall = new Wall( FsmFactory.getInstance().getFSM( "Wall" ) );
          wall.setPos( x * Tile.WIDTH, y * Tile.HEIGHT );
          wall.setDim( Tile.WIDTH, Tile.HEIGHT );
          m_walls.add( wall );
        }
      }
    }

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
    Collections.shuffle( directions, Config.getRandom() );

    for ( int i = 0; i < 4; i++ )
    {
      int dx = x + (int)directions.get( i ).getX();
      int dy = y + (int)directions.get( i ).getY();

      if( isValidTile( dx, dy ) && this.getTile( dx, dy ).getType() == TILE_TYPE.EMPTY )
      {
        tile = this.getTile( dx, dy );
        tile.setType( TILE_TYPE.FLOOR );

        int ddx = x + (int)directions.get( i ).getX() / 2;
        int ddy = y + (int)directions.get( i ).getY() / 2;

        tile = this.getTile( ddx, ddy );
        tile.setType( TILE_TYPE.FLOOR );

        createPath( dx, dy );
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
      int width  = Config.getRandom().nextInt() % 2 + 1;
      int height = Config.getRandom().nextInt() % 2 + 1;
      int startI = Config.getRandom().nextInt() % ( COLS_NUM - 2 ) + 1;
      int startY = Config.getRandom().nextInt() % ( ROWS_NUM - 2 ) + 1;
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
    return new Vector( (double) ( x * Tile.WIDTH + 5 ), (double) ( y * Tile.HEIGHT + 5 ) );
  }

  class Position
  {
    public int x, y;

    public boolean equals( Position pos )
    {
      return x == pos.x && y == pos.y;
    }

    public Position( int x, int y )
    {
      this.x = x;
      this.y = y;
    }
  }

  public Vector getRandomPos()
  {
    int x = Config.getRandom().nextInt( Map.COLS_NUM );
    int y = Config.getRandom().nextInt( Map.ROWS_NUM );
    if( this.getTile( x, y ).getType() != TILE_TYPE.FLOOR ) return getRandomPos();
    Position p = new Position( x, y );
    for ( Position pos : m_tilesAlreadyUsed )
    {
      if( pos.equals( p ) )
      {
        return getRandomPos();
      }
    }
    m_tilesAlreadyUsed.add( p );
    return this.getPos( x, y );
  }

// Inutile - les murs sont dans la liste des entités
//  public boolean detectCollision( Entity entity )
//  {
//    for ( Wall wall : m_walls )
//    {
//      if( Collision.detect( entity.getHitbox(), wall.getHitbox() ) )
//      {
//        return true;
//      }
//    }
//    return false;
//  }

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
      biomeX = Config.getRandom().nextInt( COLS_NUM - 2 - m_spaceBetweenBiomes ) + m_spaceBetweenBiomes;// random entre
      // spaceBetweenBiomes et
      // COLS_NUM-2
      biomeY = Config.getRandom().nextInt( ROWS_NUM - 2 - m_spaceBetweenBiomes ) + m_spaceBetweenBiomes;// random entre
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
    return (double) ( Map.COLS_NUM * Tile.WIDTH );
  }

  public double getHeight()
  {
    return (double) ( Map.ROWS_NUM * Tile.HEIGHT );
  }

  public ArrayList< Wall > getWalls()
  {
    return m_walls;
  }

//  public int getOptimalDirection( int posX, int posY )
//  {
//    int bestDir = 0;
//    int bestDirCnt = 0;
//    int c = 0;
//    
//    ///< DROITE
//    for( c = 0; c < Map.ROWS_NUM - posX; c++ )
//    {
//      if( this.getTile( posX + c, posY ).getType() != TILE_TYPE.WALL ) break;
//    }
//    if( c > bestDirCnt )
//    {
//      bestDirCnt = c;
//      bestDir = 0;
//    }
//    
//    ///< GAUCHE
//    for( c = 0; c < Map.ROWS_NUM - posX; c++ )
//    {
//      if( this.getTile( posX + c, posY ).getType() != TILE_TYPE.WALL ) break;
//    }
//    if( c > bestDirCnt )
//    {
//      bestDirCnt = c;
//      bestDir = 0;
//    }
//    
//    for( c = 0; c < posX-1; c++ )
//    {
//      if( this.getTile( c, posY ).getType() != TILE_TYPE.WALL ) break;
//    }
//    if( c > bestDirCnt )
//    {
//      bestDirCnt = c;
//      bestDir = 0;
//    }
//    
//    for( c = 0; c < Map.ROWS_NUM - posX; c++ )
//    {
//      if( this.getTile( posX + c, posY ).getType() != TILE_TYPE.WALL ) break;
//    }
//    if( c > bestDirCnt )
//    {
//      bestDirCnt = c;
//      bestDir = 0;
//    }
//    
//    return 0;
//  }
//
//  public ArrayList< Wall > getMergeWalls()
//  {
//    for ( int y = 0; y < Map.ROWS_NUM; y++ )
//    {
//      for ( int x = 0; x < Map.ROWS_NUM; x++ )
//      {
//
//      }
//    }
//  }

}
