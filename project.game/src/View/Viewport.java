package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Vector;
import src.Model.World.Map;
import src.Model.World.Tile;

public class Viewport
{
  private EntityTracker m_tracker;
  private Color         m_color;
  private int           m_x, m_y, m_width, m_height;

  public Viewport( Entity e, int x, int y, int width, int height, ArrayList< Avatar > avatars )
  {
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;
    m_tracker = new EntityTracker( Model.getInstance(), e );
  }

  public void setTracker( Entity target )
  {
    m_tracker = new EntityTracker( Model.getInstance(), target );
  }

  public int metersToPixels( double d )
  {
    return (int) ( EntityTracker.RATIO * d );
  }

  public void paint( Graphics g )
  {
    g.setColor( Color.darkGray );
    g.fillRect( 0, 0, m_width, m_height );

    Vector trackerPos = m_tracker.getMin();

    for ( int Y = 0; Y < Map.ROWS_NUM; Y++ )
    {
      for ( int X = 0; X < Map.COLS_NUM; X++ )
      {
        int        x          = (int) ( EntityTracker.RATIO * ( X * Tile.WIDTH - trackerPos.getX() ) );
        int        y          = (int) ( EntityTracker.RATIO * ( Y * Tile.HEIGHT - trackerPos.getY() ) );
        int        width      = (int) ( EntityTracker.RATIO * Tile.WIDTH );
        int        height     = (int) ( EntityTracker.RATIO * Tile.HEIGHT );

        TileAvatar tileAvatar = new TileAvatar( Map.getInstance().getTile( X, Y ) );
        tileAvatar.paint( g, x, y, width, height );
      }
    }

    for ( Entity e : m_tracker.getEntities() )
    {
      Avatar avatar = AvatarFactory.make( e );

      if( avatar != null )
      {
        int x      = (int) ( EntityTracker.RATIO * ( e.getX() - trackerPos.getX() ) );
        int y      = (int) ( EntityTracker.RATIO * ( e.getY() - trackerPos.getY() ) );
        int width  = (int) ( EntityTracker.RATIO * e.getWidth() );
        int height = (int) ( EntityTracker.RATIO * e.getHeight() );

        avatar.paint( g, x, y, width, height );
      }
    }

    g.setColor( m_color );
    g.drawRect( 0, 0, m_width-1, m_height-1 );
  }

  public int getX()
  {
    return m_x;
  }

  public int getY()
  {
    return m_y;
  }

  public int getWidth()
  {
    return m_width;
  }

  public int getHeight()
  {
    return m_height;
  }

  public void setColor( Color c )
  {
    m_color = c;
  }

  public void updateTracker()
  {
    m_tracker.centerOnTarget();
  }
}
