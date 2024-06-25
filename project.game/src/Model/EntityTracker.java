package src.Model;

import java.util.ArrayList;

import src.Model.Collision.AABB;
import src.Config;
import src.Game;

/**
 * This class tracks an entity for the view. It shows all the entities in the
 * current rectangular focus.
 */

public class EntityTracker extends AABB
{

  private Entity              m_target;
  private ArrayList< Entity > m_entities;
  private TrackerListener     m_listener;
  double                      m_ratioWidth, m_ratioHeight;

  public class TrackerListener
  {
    @Override
    public String toString()
    {
      return "Entity tracker of " + m_target.toString();
    }

    void entered( Entity e )
    {
//      System.out.println( this.toString() + " - " + e.toString() + " entered" );
      m_entities.add( e );
    }

    void left( Entity e )
    {
//      System.out.println( this.toString() + " - " + e.toString() + " left" );
      m_entities.remove( e );
    }

    void moved()
    {
      centerOnTarget();
    }
  }

  public EntityTracker( Entity e, int ratioWidth, int ratioHeight )
  {
    super( 0, 0, ratioWidth, ratioHeight );
    m_ratioWidth = ratioWidth;
    m_ratioHeight = ratioHeight;
    m_target = e;
    centerOnTarget();
    m_entities = new ArrayList< Entity >();
    m_listener = new TrackerListener();
  }
  
  public void resize()
  {
    super.setDim( Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT );
  }

  public void centerOnTarget()
  {
    if( m_target == null )
    {
      return;
    }
    double zoom = Config.getInstance().getView().getZoom();
    double x    = m_target.getX() + m_target.getWidth() / 2 - m_ratioWidth / ( 2 * zoom );
    double y    = m_target.getY() + m_target.getHeight() / 2 - m_ratioHeight / ( 2 * zoom );
    this.setPos( x, y );
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public TrackerListener getListener()
  {
    return m_listener;
  }
  
  public Entity getTarget()
  {
    return m_target;
  }
  
  public void setTarget( Entity target )
  {
    m_target = target;
  }
}
