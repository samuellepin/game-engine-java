package src.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Vector;
import src.Model.World.*;

public class Viewport extends Component
{
  private static final long serialVersionUID = 1L;
  EntityTracker             m_tracker;
  Model                     m_model;
  BufferedImage             m_img;

  public Viewport( Entity e, int x, int y, int width, int height )
  {
    this.setBounds( x, y, width, height );
    m_model = Model.getInstance();
    setTrack( e );

    try
    {
      m_img = AvatarFactory.loadImage( "resources/Tile_Brick.png" );
    }
    catch ( Exception exception )
    {
      throw new RuntimeException( "Can't find the tile image" );
    }
  }

  public void setTrack( Entity e )
  {
    if( m_tracker != null )
    {
      m_model.getTrackers().remove( m_tracker );
    }
    m_tracker = new EntityTracker( e, getWidth(), getHeight() );
    e.setTracker( m_tracker );
    m_model.getTrackers().add( m_tracker );
  }

  public int metersToPixels( double d )
  {

    return (int)(d * this.getWidth() / m_tracker.getWidth());
  }

  private void paintTiles( Graphics g )
  {
    Vector mini = m_tracker.getMin();
    Vector maxi = m_tracker.getMax();
    if( maxi.getX() > Map.COLS_NUM * Tile.WIDTH ) maxi.setX( Map.COLS_NUM * Tile.WIDTH );
    if( maxi.getY() > Map.ROWS_NUM * Tile.HEIGHT ) maxi.setY( Map.ROWS_NUM * Tile.HEIGHT );

    for ( int i = -metersToPixels( ( mini.getX() % Tile.WIDTH ) + Tile.WIDTH ); i < this.getWidth()
        - metersToPixels( maxi.getX() % Tile.WIDTH ); i += metersToPixels( Tile.WIDTH ) )
    {
      for ( int j = -metersToPixels( ( mini.getY() % Tile.HEIGHT ) + Tile.HEIGHT ); j < this.getHeight()
          - metersToPixels( maxi.getY() % Tile.HEIGHT ); j += metersToPixels( Tile.HEIGHT ) )
      {
        g.drawImage( m_img, i, j, metersToPixels( Tile.WIDTH ), metersToPixels( Tile.HEIGHT ), null );
      }
    }
  }

  public void paint( Graphics g )
  {
    paintTiles( g );
    ArrayList< Entity > entities = m_tracker.getEntities();

    for ( Entity e : entities )
    {
      Avatar avatar = AvatarFactory.make( e );

      if( avatar != null )
      {
        int    avatar_x      = metersToPixels(e.getX() - m_tracker.getPos().getX());
        int    avatar_y      = metersToPixels(e.getY() - m_tracker.getPos().getY());
        int    avatar_width  = metersToPixels( e.getWidth() );
        int    avatar_height = metersToPixels( e.getHeight() );

        avatar.paint( g, avatar_x, avatar_y, avatar_width, avatar_height );
      }
    }
    g.setColor( Color.black );
    g.drawRect( 0, 0, this.getWidth() - 1, this.getHeight() - 1 );
  }
}
