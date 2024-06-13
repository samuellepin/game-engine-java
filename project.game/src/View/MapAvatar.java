package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.Model.Vector;
import src.Model.World.Map;
import src.Model.World.TILE_TYPE;

public class MapAvatar implements Avatar
{
  private Map           m_map;
  private BufferedImage m_floorImg;

  public MapAvatar( Map map )
  {
    m_map = map;
    try
    {
      m_floorImg = AvatarFactory.loadImage( "resources/Tile_Brick.png" );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
  }

  public Color getTileColor( TILE_TYPE type )
  {
    switch ( type )
    {
    case EMPTY:
      return Color.blue;
    case FLOOR:
      return Color.white;
    case WALL:
      return Color.black;
    }
    return Color.red;
  }
  
  public BufferedImage getTileImage( TILE_TYPE type )
  {
    switch( type )
    {
    case FLOOR:
      return m_floorImg;
    }
    return null;
  }

  @Override
  public void paint( Graphics g )
  {
    BufferedImage img = null;
    for ( int y = 0; y < Map.ROWS_NUM; y++ )
    {
      for ( int x = 0; x < Map.COLS_NUM; x++ )
      {
        TILE_TYPE type = m_map.getTile( x, y ).getType();
        g.setColor( getTileColor( type ) );
        img = getTileImage( type );
        Vector pos = new Vector( x * Map.TILE_WIDTH, y * Map.TILE_HEIGHT );
        if( img != null )
        {
          g.drawImage( m_floorImg, (int)pos.getVX(), (int)pos.getVY(), Map.TILE_WIDTH, Map.TILE_HEIGHT, null );
        }
        else
        {
          g.fillRect( (int)pos.getVX(), (int)pos.getVY(), Map.TILE_WIDTH, Map.TILE_HEIGHT );
        }
        AABBAvatar.paint( g, m_map.getTile( x, y ).getHitbox() );
      }
    }
  }

}
