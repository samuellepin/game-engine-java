package src;

import java.util.Random;

public class Grid
{
  public static final int   COLS_NUM = 10;
  public static final int   ROWS_NUM = 10;
  private Cell[][]          m_map;

  private static final Grid INSTANCE = new Grid();

  public static Grid getInstance()
  {
    return INSTANCE;
  }

  public static CATEGORY EntityToCategory( Entity e )
  {
    if( e instanceof SnakeHead )
    {
      return CATEGORY.SNAKE;
    }
    else if( e instanceof Apple )
    {
      return CATEGORY.APPLE;
    }
    return CATEGORY.EMPTY;
  }

  public class Cell
  {
    int      m_x;
    int      m_y;
    Entity   m_entity;
    CATEGORY m_category;

    public Cell( int x, int y, Entity e )
    {
      m_x = x;
      m_y = y;
      setEntity( e );
    }

    public int getX()
    {
      return m_x;
    }

    public int getY()
    {
      return m_y;
    }

    private void setEntity( Entity e )
    {
      m_entity = e;
      m_category = EntityToCategory( e );
    }

    public Entity getEntity()
    {
      return m_entity;
    }

    public CATEGORY getCategory()
    {
      return m_category;
    }
  }

  private Grid()
  {
    m_map = new Cell[ COLS_NUM ][ ROWS_NUM ];
    for ( int i = 0; i < COLS_NUM; i++ )
    {
      for ( int j = 0; j < ROWS_NUM; j++ )
      {
        m_map[ i ][ j ] = new Cell( j, i, null );
      }
    }
  }

  public void setEntity( Entity e, Cell c )
  {
    m_map[ c.getY() ][ c.getX() ].setEntity( e );
  }

  public boolean move( Entity e )
  {
    int x = e.getCell().getX();
    int y = e.getCell().getY();

    switch ( e.getDirection() )
    {
    case UP:
      y-- ;
      break;
    case DOWN:
      y++ ;
      break;
    case RIGHT:
      x++ ;
      break;
    case LEFT:
      x-- ;
      break;
    default:
      break;
    }

    if( ( x < 0 ) || ( x >= COLS_NUM ) || ( y < 0 ) || ( y >= ROWS_NUM ) )
    {
      return false;
    }
    
    Cell c = this.getCell( x, y );
    
    if( c.getCategory() != CATEGORY.EMPTY ) return false;
    
    setEntity( null, e.getCell() );
    e.setCell( c );
    setEntity( e, c );
    
    return true;
  }

  public Cell getCell( int x, int y )
  {
    return m_map[ y ][ x ];
  }

  public Cell getFreeRandomCell()
  {
    Cell   cell;
    Random rand = new Random();

    /// < Attention au boucle infini - vérifier si toute la map est pleine ou non
    do
    {
      cell = m_map[ rand.nextInt( ROWS_NUM ) ][ rand.nextInt( COLS_NUM ) ];
    }
    while( cell.getEntity() != null );

    return cell;
  }

  public boolean checkCell( Cell cell, DIRECTION direction, CATEGORY category )
  {
    int x = cell.getX();
    int y = cell.getY();

    switch ( direction )
    {
    case UP:
      y-- ;
      break;
    case DOWN:
      y++ ;
      break;
    case RIGHT:
      x++ ;
      break;
    case LEFT:
      x-- ;
      break;
    default:
      break;
    }

    if( ( x < 0 ) || ( x >= COLS_NUM ) || ( y < 0 ) || ( y >= ROWS_NUM ) )
    {
      return false;
    }

    return m_map[ y ][ x ].getCategory() == category;
  }

  @Override
  public String toString()
  {
    StringBuilder strb = new StringBuilder();

    for ( int y = 0; y < Grid.ROWS_NUM; y++ )
    {
      for ( int x = 0; x < Grid.COLS_NUM; x++ )
      {
        switch ( Grid.getInstance().getCell( x, y ).getCategory() )
        {
        case EMPTY:
          strb.append( "_" );
          break;
        case APPLE:
          strb.append( "@" );
          break;
        case SNAKE:
          strb.append( "S" );
          break;
        default:
          strb.append( "?" );
          break;
        }
      }
      strb.append( "\n" );
    }

    return strb.toString();
  }

}
