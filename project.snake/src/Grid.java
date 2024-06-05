package src;

public class Grid
{
  private static final int COLS_NUM = 10;
  private static final int ROW_NUM  = 10;
  private Cell[][]         m_map;

  public class Cell
  {
    int    m_x;
    int    m_y;
    Entity m_entity;

    Cell( int x, int y, Entity e )
    {
      m_x = x;
      m_y = y;
      m_entity = e;
    }

    int getX()
    {
      return m_x;
    }

    int getY()
    {
      return m_y;
    }

    private void setEntity( Entity e )
    {
      m_entity = e;
    }

    Entity GetEntity()
    {
      return m_entity;
    }
  }

  Grid()
  {
    m_map = new Cell[ COLS_NUM ][ ROW_NUM ];
    for ( int i = 0; i < COLS_NUM; i++ )
    {
      for ( int j = 0; j < ROW_NUM; j++ )
      {
        m_map[ i ][ j ] = new Cell( j, i, null );
      }
    }
  }

  boolean move( Entity e, Cell c )
  {
    if( c.GetEntity() == null )
    {
      e.setCell( c );
      c.setEntity( e );
      return true;
    }
    return false;
  }

  Cell getCell( int x, int y )
  {
    return m_map[ x ][ y ];
  }
}
