package src.Model;

public class Grid
{
  public static int m_cols_num;
  public static int m_rows_num;
  private Cell[][]  m_map;

  public class Cell
  {
    private int     m_x;
    private int     m_y;
    private Biome m_biome;

    public Cell( int x, int y, Biome biome)
    {
      m_x = x;
      m_y = y;
      m_biome = biome;
    }

    public int getX()
    {
      return m_x;
    }

    public int getY()
    {
      return m_y;
    }

    public void setBiome(Biome biome)
    {
      m_biome = biome;
    }

    public Biome getBiome()
    {
      return m_biome;
    }

  }

  public Grid( int cols, int rows )
  {
    m_cols_num = cols;
    m_rows_num = rows;
    m_map = new Cell[ m_cols_num ][ m_rows_num ];
    for ( int i = 0; i < m_cols_num; i++ )
    {
      for ( int j = 0; j < m_rows_num; j++ )
      {
        m_map[ i ][ j ] = new Cell( j, i, null );
      }
    }
  }
  public Cell getCell(int x, int y) {
    return m_map[y][x];
  }
}