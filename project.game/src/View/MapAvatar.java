package src.View;

import java.awt.Color;
import java.awt.Graphics;

import src.Model.Vector;
import src.Model.World.Map;
import src.Model.World.TILE_TYPE;

public class MapAvatar implements Avatar
{
  private Map m_map;
  
  public MapAvatar( Map map )
  {
    m_map = map;
  }
  
  public Color getTileColor( TILE_TYPE type )
  {
    switch( type )
    {
    case EMPTY:
      return Color.black;
    case FLOOR:
      return Color.white;
    case WALL:
      return Color.gray;
    }
    return Color.red;
  }

  @Override
  public void paint( Graphics g )
  {
    for( int y = 0; y < Map.ROWS_NUM; y++ )
    {
      for( int x = 0; x < Map.COLS_NUM; x++ )
      {
        g.setColor( getTileColor( m_map.getTile( x, y ).getType() ) );
        Vector pos = new Vector( x*Map.TILE_WIDTH, y * Map.TILE_HEIGHT );
        g.fillRect( (int)pos.getRX(), (int)pos.getRY(), Map.TILE_WIDTH, Map.TILE_HEIGHT );
      }
    }
  }
  
}
