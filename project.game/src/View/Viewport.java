package src.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Vector;

public class Viewport
{
  private EntityTracker       m_tracker;
  private Color               m_color;
  private int                 m_x, m_y, m_width, m_height;
  private Background          m_background;
  private ArrayList< Avatar > m_avatars;

  public Viewport( Entity e, int x, int y, int width, int height, Background background, ArrayList< Avatar > avatars )
  {
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;
    m_tracker = new EntityTracker( Model.getInstance(), e );
    m_background = background;
    m_avatars = avatars;
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
    g.setColor( m_color );
    g.fillRect( 0, 0, m_width, m_height );
    
    m_background.paint( g );

    Vector trackerPos = m_tracker.getMin();

    for ( Entity e : m_tracker.getEntities() )
    {
      Avatar avatar = AvatarFactory.make( e );

      if( avatar != null )
      {
        int      x        = (int) ( EntityTracker.RATIO * ( e.getX() - trackerPos.getX() ) );
        int      y        = (int) ( EntityTracker.RATIO * ( e.getY() - trackerPos.getY() ) );
        int      width    = (int) ( EntityTracker.RATIO * e.getWidth() );
        int      height   = (int) ( EntityTracker.RATIO * e.getHeight() );

        Graphics graphics = g.create( x, y, width, height );
        avatar.paint( graphics );
        graphics.dispose();
      }
    }
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
