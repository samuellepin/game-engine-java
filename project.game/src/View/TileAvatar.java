package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.Model.Vector;
import src.Model.World.Map;
import src.Model.World.TILE_TYPE;
import src.Model.World.Tile;

public class TileAvatar extends Avatar
{
  private static BufferedImage m_floorImg;
  private Tile                 m_tile;

  static
  {
    try
    {
      m_floorImg = AvatarFactory.loadImage( "resources/Tile_Brick.png" );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
  }

  public TileAvatar( Tile tile )
  {
    m_tile = tile;
  }

  public Color getColor( TILE_TYPE type )
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

  public BufferedImage getImage( TILE_TYPE type )
  {
    switch ( type )
    {
    case FLOOR:
      return m_floorImg;
    default:
      break;
    }
    return null;
  }

  @Override
  public void paint( Graphics g )
  {
    TILE_TYPE     type  = m_tile.getType();
    Color         color = this.getColor( type );
    BufferedImage img   = this.getImage( type );
    if( img != null )
    {
      g.drawImage( m_floorImg, 0, 0, this.getWidth( g ), this.getHeight( g ), null );
    }
    else
    {
      g.setColor( color );
      g.drawRect( 0, 0, this.getWidth( g ), this.getHeight( g ) );
    }
  }

}
