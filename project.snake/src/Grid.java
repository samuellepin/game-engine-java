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
    Cell oldCell = e.getCell();
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

    Cell newCell = this.getCell( x, y );

    if( newCell.getCategory() != CATEGORY.EMPTY ) return false;

    setEntity( null, oldCell );
    e.setCell( newCell );
    setEntity( e, newCell );
    
    if( !(e instanceof SnakeHead) ) return true;
   
    SnakeHead head = (SnakeHead)e;
    SnakeTail tail = head.getTail();
    
    
    return true;
  }
  
  public void moveSnakeTail( SnakeTail tail, Cell newCell )
  {
    if( tail == null ) return;
    
    Cell oldCell = tail.getCell();
    setEntity( null, oldCell );
    tail.setCell( newCell );
    setEntity( tail, newCell );
    
    moveSnakeTail( tail.getNext(), newCell );
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

  public void snakeHeadASCII( StringBuilder strb, int x, int y )
  {
    DIRECTION dir = Grid.getInstance().getCell( x, y ).getEntity().getDirection();
    switch ( dir )
    {
    case UP:
      strb.append( "^" );
      break;
    case DOWN:
      strb.append( "V" );
      break;
    case RIGHT:
      strb.append( ">" );
      break;
    case LEFT:
      strb.append( "<" );
      break;
    }
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
          snakeHeadASCII( strb, x, y );
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

  public Apple getApple()
  {
    for ( int y = 0; y < Grid.ROWS_NUM; y++ )
    {
      for ( int x = 0; x < Grid.COLS_NUM; x++ )
      {
        Entity e = m_map[ y ][ x ].getEntity();
        if( e instanceof Apple )
        {
          return (Apple)e;
        }
      }
    }
    return null;
  }
}
