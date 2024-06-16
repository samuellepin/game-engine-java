package src.View;
import java.awt.Graphics;

import src.Model.World.Map;

public class Background extends Avatar
{
  private Map            m_map;
  private TileAvatar[][] m_tiles;

  public Background( Map map )
  {
    m_map = map;
    m_tiles = new TileAvatar[ Map.ROWS_NUM ][ Map.COLS_NUM ];
    for ( int y = 0; y < Map.ROWS_NUM; y++ )
    {
      for ( int x = 0; x < Map.COLS_NUM; x++ )
      {
        m_tiles[ y ][ x ] = new TileAvatar( m_map.getTile( x, y ) );
      }
    }
  }

  @Override
  public void paint( Graphics g )
  {
//    for ( int y = 0; y < Map.ROWS_NUM; y++ )
//    {
//      for ( int x = 0; x < Map.COLS_NUM; x++ )
//      {
//        m_tiles[ y ][ x ].paint( g );
//      }
//    }
  }
}
