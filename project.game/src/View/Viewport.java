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
  private EntityTracker           m_tracker;
  private Color                   m_color;
  private int                     m_x, m_y, m_width, m_height;
  private ArrayList< Avatar >     m_avatars;
  private ArrayList< TileAvatar > m_tileAvatars;

  public Viewport( Entity e, int x, int y, int width, int height, ArrayList< Avatar > avatars,
      ArrayList< TileAvatar > tileAvatars )
  {
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;
    m_tracker = new EntityTracker( Model.getInstance(), e );
    m_avatars = avatars;
    m_tileAvatars = tileAvatars;
  }

  public void paint( Graphics g )
  {
    g.setColor( Color.darkGray );
    g.fillRect( 0, 0, m_width, m_height );

    Vector trackerPos = m_tracker.getMin();

    for ( TileAvatar tileAvatar : m_tileAvatars )
    {
      Tile t = tileAvatar.getTile();
      int x      = (int) ( EntityTracker.RATIO * ( t.getX() - trackerPos.getX() ) );
      int y      = (int) ( EntityTracker.RATIO * ( t.getY() - trackerPos.getY() ) );
      int width  = (int) ( EntityTracker.RATIO * Tile.WIDTH );
      int height = (int) ( EntityTracker.RATIO * Tile.HEIGHT );
      tileAvatar.paint( g, x, y, width, height );
    }

    for ( Avatar avatar : m_avatars )
    {
      Entity e      = avatar.getEntity();
      int    x      = (int) ( EntityTracker.RATIO * ( e.getX() - trackerPos.getX() ) );
      int    y      = (int) ( EntityTracker.RATIO * ( e.getY() - trackerPos.getY() ) );
      int    width  = (int) ( EntityTracker.RATIO * e.getWidth() );
      int    height = (int) ( EntityTracker.RATIO * e.getHeight() );
      avatar.paint( g, x, y, width, height );
    }

    g.setColor( m_color );
    g.drawRect( 0, 0, m_width - 1, m_height - 1 );
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
