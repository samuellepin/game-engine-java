package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Model.Entity;
import src.Model.World.TILE_TYPE;
import src.Model.World.Tile;

public class TileAvatar extends Avatar
{
  private Tile m_tile;
  
  public TileAvatar( Tile t )
  {
    super( null );
    m_tile = t;
  }
  
  public Tile getTile()
  {
    return m_tile;
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
      return AvatarFactory.m_floorImg;
    default:
      break;
    }
    return null;
  }

  @Override
  public void paint( Graphics g, int x, int y, int width, int height )
  {
    TILE_TYPE     type  = m_tile.getType();
    Color         color = this.getColor( type );
    BufferedImage img   = this.getImage( type );
    
    if( img != null )
    {
      g.drawImage( img, x, y, width, height, null );
    }
    else
    {
      g.setColor( color );
      g.drawRect( x, y, width, height );
    }
  }

}
