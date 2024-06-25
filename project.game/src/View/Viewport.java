package src.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import src.Config;
import src.Controller;
import src.Game;
import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Shot;
import src.Model.Vector;
import src.Model.Collision.AABB;
import src.Model.Collision.Arc;
import src.Model.World.*;

public class Viewport
{
  private EntityTracker m_tracker;
  private Model         m_model;
  private int           m_x, m_y, m_width, m_height;

  public Viewport( Entity e, int x, int y, int width, int height )
  {
    m_model = Model.getInstance();
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;
    setTrack( e );
  }

  public void setTrack( Entity e )
  {
    if( m_tracker != null )
    {
      m_model.getTrackers().remove( m_tracker );
    }
    m_tracker = new EntityTracker( e, m_width, m_height ); // on donne des pixels par des ratios ici...
    if( e != null ) e.setTracker( m_tracker );
    m_model.getTrackers().add( m_tracker );
  }

  public int metersToPixels( double d )
  {
    return (int) ( d * Config.getInstance().getView().getZoom() );
  }
  /// < d * (double)m_width / m_tracker.getWidth()

  private void paintTiles( Graphics g )
  {
    BufferedImage sprite = AvatarFactory.getInstance().getFloorSprite();
    Vector        mini   = m_tracker.getMin();
    Vector        maxi   = m_tracker.getMax();
    if( maxi.getX() > Map.COLS_NUM * Tile.WIDTH ) maxi.setX( Map.COLS_NUM * Tile.WIDTH );
    if( maxi.getY() > Map.ROWS_NUM * Tile.HEIGHT ) maxi.setY( Map.ROWS_NUM * Tile.HEIGHT );

    for ( int i = -metersToPixels( ( mini.getX() % Tile.WIDTH ) + Tile.WIDTH ); i < m_width
        - metersToPixels( ( maxi.getX() % Tile.WIDTH ) - Tile.WIDTH ); i += metersToPixels( Tile.WIDTH ) )
    {
      for ( int j = -metersToPixels( ( mini.getY() % Tile.HEIGHT ) + Tile.HEIGHT ); j < m_height
          - metersToPixels( ( maxi.getY() % Tile.HEIGHT ) - Tile.WIDTH ); j += metersToPixels( Tile.HEIGHT ) )
      {
        g.drawImage( sprite, i, j, metersToPixels( Tile.WIDTH ), metersToPixels( Tile.HEIGHT ), null );
      }
    }
  }

  public void paintShots( Graphics g )
  {
    int x, y;
    int r = 2;
    for ( Shot shot : Model.getInstance().getShots() )
    {
      x = metersToPixels( shot.getX() - m_tracker.getX() );
      y = metersToPixels( shot.getY() - m_tracker.getY() );
      g.setColor( Color.yellow );
      g.fillOval( x - r, y - r, 2 * r, 2 * r );
    }
  }

  public void paintHP( Graphics g )
  {
    Entity e = m_tracker.getTarget();
    g.setColor( Color.white );
    int    width   = 200;
    int    height  = 20;
    int    padding = 15;
    int    x       = padding;
    int    y       = Game.SCREEN_HEIGHT - padding - height;
    
    double rate    = 0;
    
    if( e != null )
    rate = (double)e.getHP() / (double)e.getMaxHP();

    if( rate < 0.25 )
    {
      g.setColor( Color.red );
    }
    else if( rate < 0.50 )
    {
      g.setColor( Color.orange );
    }
    else
    {
      g.setColor( Color.green );
    }

    g.fillRect( x, y, (int) ( (double)width * rate ), height );

    padding = 3;
    g.setColor( Color.white );
    g.drawRect( x - padding, y - padding, width + 2 * padding - 1, height + 2 * padding - 1 );

    if( e != null )
    {
      g.drawString( e.getHP() + "/" + e.getMaxHP(), x + 5, y + 15 );
    }
    
  }
  
  public void paintMouseInfo( Graphics g )
  {
    Controller ctr = Controller.getInstance();
    Point pt = ctr.getMousePos();
    g.setColor( Color.white );
    g.drawString( "Pos = (" + pt.getX() + ", " + pt.getY() + ")", 5, 55 );
  }

  public void paintInventory( Graphics g )
  {
    Entity e       = m_tracker.getTarget();
    if( e == null ) return;
    int    itemNo  = e.getInventory().size();
    int    itemMax = Model.getInstance().getKeyItems().size();

    Font   font    = new Font( "Arial", Font.BOLD, 12 );
    g.setFont( font );
    FontMetrics fm     = g.getFontMetrics();
    String      text   = "No. Key Items : " + itemNo + "/" + itemMax;
    int         width  = fm.stringWidth( text );
    int         height = fm.getAscent() + fm.getDescent() + fm.getLeading();
    int         posX   = Game.SCREEN_WIDTH/2 - width - 5;
    int         posY   = Game.SCREEN_HEIGHT - height;

    g.setColor( Color.white );
    g.drawString( text, posX, posY );
  }

  public void paint( Graphics g )
  {
    this.paintTiles( g );

    for ( Entity e : Model.getInstance().getEntities() ) /// < m_tracker.getEntities()
    {
      if( !e.isVisible() ) continue;
      Avatar avatar = null;
      try
      {
        avatar = AvatarFactory.getInstance().make( e );
      }
      catch ( Exception e1 )
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      if( avatar != null )
      {
        int x, y, width, height;

        if( Config.getInstance().getView().shouldPaintVisionField() )
        {
          Arc vision = e.getVisionField();
          x = metersToPixels( vision.getTopLeftCorner().getX() - m_tracker.getX() );
          y = metersToPixels( vision.getTopLeftCorner().getY() - m_tracker.getY() );
          width = metersToPixels( vision.getWidth() );
          height = metersToPixels( vision.getHeight() );
          avatar.paintVisionField( g, x, y, width, height, vision.getStartAngle(), vision.getArcAngle() );
        }

        x = metersToPixels( e.getX() - m_tracker.getX() );
        y = metersToPixels( e.getY() - m_tracker.getY() );
        width = metersToPixels( e.getWidth() );
        height = metersToPixels( e.getHeight() );
        avatar.paint( g, x, y, width, height );

        if( Config.getInstance().getView().shouldPaintHitbox() )
        {
          AABB hitbox = e.getHitbox();
          x = metersToPixels( hitbox.getX() - m_tracker.getX() );
          y = metersToPixels( hitbox.getY() - m_tracker.getY() );
          width = metersToPixels( hitbox.getWidth() );
          height = metersToPixels( hitbox.getHeight() );
          avatar.paintHitbox( g, x, y, width, height );
        }
      }
    }

    this.paintShots( g );

    this.paintHP( g );
    this.paintInventory( g );
//    this.paintMouseInfo( g );

    // FRAME
    g.setColor( Color.black );
    g.drawRect( 0, 0, this.getWidth() - 1, this.getHeight() - 1 );
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

  public void update( int x, int y, int width, int height )
  {
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;
  }

  public EntityTracker getTracker()
  {
    return m_tracker;
  }
}
