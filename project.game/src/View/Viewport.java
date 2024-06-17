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
import src.Model.World.Map;
import info3.game.graphics.GameCanvas;

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
    Vector trackerDims = m_tracker.getDim();

    double d_px        = d * this.getWidth() / trackerDims.getX();

    return (int)d_px;
  }

  /* Converts the world position pos to a pixel coordinate on the viewport */
  public Vector worldPosToViewportPos( Vector pos )
  {
    Vector cameraPos   = new Vector( pos.getX() - m_tracker.getPos().getX(), pos.getY() - m_tracker.getPos().getY() );
    Vector viewportPos = new Vector( metersToPixels( cameraPos.getX() ), metersToPixels( cameraPos.getY() ) );
    return new Vector( (int)viewportPos.getX(), (int)viewportPos.getY() );
  }

  private void paintTiles( Graphics g )
  {
    Vector mini = m_tracker.getPos();
    Vector maxi = m_tracker.getPos().add( m_tracker.getDim() );
    if( maxi.getX() > Map.COLS_NUM * Map.TILE_WIDTH ) maxi.setX( Map.COLS_NUM * Map.TILE_WIDTH );
    if( maxi.getY() > Map.ROWS_NUM * Map.TILE_HEIGHT ) maxi.setY( Map.ROWS_NUM * Map.TILE_HEIGHT );
    
    for ( int i = -metersToPixels( mini.getX() % Map.TILE_WIDTH ); i < this.getWidth()
        - metersToPixels( maxi.getX() % 100 ); i += metersToPixels( Map.TILE_WIDTH ) )
    {
      for ( int j = -metersToPixels( mini.getY() % Map.TILE_HEIGHT ); j < this.getHeight()
          - metersToPixels( maxi.getY() % 100 ); j += metersToPixels( Map.TILE_HEIGHT ) )
      {
        g.drawImage( m_img, i, j, metersToPixels( Map.TILE_WIDTH ), metersToPixels( Map.TILE_HEIGHT ), null );
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
        Vector pos           = worldPosToViewportPos( new Vector( e.getX(), e.getY() ) );
        int    avatar_x      = (int)pos.getX();
        int    avatar_y      = (int)pos.getY();
        int    avatar_width  = metersToPixels( e.getWidth() ) + 1;
        int    avatar_height = metersToPixels( e.getHeight() ) + 1;

        avatar.paint( g, avatar_x, avatar_y, avatar_width, avatar_height );
      }
    }
    g.setColor( Color.black );
    g.drawRect( 0, 0, this.getWidth(), this.getHeight() );
  }
}
