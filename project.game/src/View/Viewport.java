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

// Corrigé - Viewport n'est plus un component #pas de bounds
// Suppression de m_img, l'image est déjà chargé par AvatarFactory
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
    if( e != null )
    e.setTracker( m_tracker );
    m_model.getTrackers().add( m_tracker );
  }

  public int metersToPixels( double d )
  {
    return (int) ( d * 1 );
  }
  ///< d * (double)m_width / m_tracker.getWidth()

  private void paintTiles( Graphics g )
  {
    Vector mini = m_tracker.getMin();
    Vector maxi = m_tracker.getMax();
    if( maxi.getX() > Map.COLS_NUM * Tile.WIDTH ) maxi.setX( Map.COLS_NUM * Tile.WIDTH );
    if( maxi.getY() > Map.ROWS_NUM * Tile.HEIGHT ) maxi.setY( Map.ROWS_NUM * Tile.HEIGHT );

    for ( int i = -metersToPixels( ( mini.getX() % Tile.WIDTH ) + Tile.WIDTH ); i < m_width
        - metersToPixels( maxi.getX() % Tile.WIDTH ); i += metersToPixels( Tile.WIDTH ) )
    {
      for ( int j = -metersToPixels( ( mini.getY() % Tile.HEIGHT ) + Tile.HEIGHT ); j < m_height
          - metersToPixels( maxi.getY() % Tile.HEIGHT ); j += metersToPixels( Tile.HEIGHT ) )
      {
        g.drawImage( AvatarFactory.m_floorImg, i, j, metersToPixels( Tile.WIDTH ), metersToPixels( Tile.HEIGHT ),
            null );
      }
    }
  }

  public void paint( Graphics g )
  {    
    this.paintTiles( g );

    for ( Entity e : m_tracker.getEntities() )
    {
      Avatar avatar = AvatarFactory.make( e );

      if( avatar != null )
      {
        int avatar_x      = metersToPixels( e.getX() - m_tracker.getX() );
        int avatar_y      = metersToPixels( e.getY() - m_tracker.getY() );
        int avatar_width  = metersToPixels( e.getWidth() );
        int avatar_height = metersToPixels( e.getHeight() );

        avatar.paint( g, avatar_x, avatar_y, avatar_width, avatar_height );
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
  
}
