package src.View;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Vector;

public class Viewport extends Container
{
  EntityTracker m_tracker;
  Image         m_background;
  Model         m_model;

  public Viewport( Image background, Entity e, Model model )
  {
    m_tracker = new EntityTracker( e, model );
    m_background = background;
    m_model = model;
    
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

  public void paint( Graphics g )
  {
    ArrayList< Entity > entities = m_tracker.getEntities();

    for ( Entity entity : entities )
    {
      Avatar avatar;
      try
      {
        avatar = AvatarFactory.make( entity );
      }
      catch ( Exception e )
      {
        avatar = null;
      }
      if (avatar != null)
        avatar.paint( g );
    }
  }
}
