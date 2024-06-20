package src.Model;

import java.util.ArrayList;

import src.Model.Collision.AABB;

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

    void entered( Entity e )
    {
      m_entities.add( e );
    }

    void left( Entity e )
    {
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

  private void centerOnTarget()
  {
    if( m_target == null )
    {
      return;
    }
    double x = m_target.getX() + m_target.getWidth() / 2 - m_ratioWidth / ( 2 * Config.RATIO );
    double y = m_target.getY() + m_target.getHeight() / 2 - m_ratioHeight / ( 2 * Config.RATIO );
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
}