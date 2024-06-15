package src.View;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.Vector;

public class Viewport extends Component
{
  private static final long serialVersionUID = 1L;
  EntityTracker m_tracker;
  Model         m_model;

  public Viewport( Entity e, int x, int y, int width, int height )
  {
    this.setBounds( x, y, width, height );
    m_tracker = new EntityTracker( e, getWidth(), getHeight() );
    m_model = Model.getInstance();
    m_model.getTrackers().add( m_tracker );
  }

  public void setTrack( Entity e )
  {
    m_tracker = new EntityTracker( e, getWidth(), getHeight() );
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

    for ( Entity e : entities )
    {
      Avatar avatar = AvatarFactory.make( e );

      if( avatar != null )
      {
        Vector pos           = worldPosToViewportPos( new Vector( e.getX(), e.getY() ) );
        int    avatar_x      = (int)pos.getX();
        int    avatar_y      = (int)pos.getY();
        int    avatar_width  = metersToPixels( e.getWidth() );
        int    avatar_height = metersToPixels( e.getHeight() );

        avatar.paint( g.create( avatar_x, avatar_y, avatar_width, avatar_height ) );
      }
    }
  }
}
