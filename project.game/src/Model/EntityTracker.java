package src.Model;

import java.util.ArrayList;

import src.Model.Collision.AABB;
import src.Model.World.Map;

/**
 * This class tracks an entity for the view. It shows all the entities in the
 * current rectangular focus.
 */

public class EntityTracker extends AABB
{

  private Entity              m_target;
  private ArrayList< Entity > m_entities;
  private TrackerListener     m_listener;

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

// Le probleme vient d'ici !!!
//  private static final double DEFAULT_WIDTH = Map.getInstance().getWidth(); // / Config.RATIO;
//super( 0, 0, DEFAULT_WIDTH, DEFAULT_WIDTH * ratio_h / ratio_w );
  
  public EntityTracker( Entity e, int screenWidth, int screenHeight )
  {
    super( 0, 0, screenWidth, screenHeight );
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
    double x = m_target.getX() + m_target.getWidth() / 2 - this.getWidth() / 2;
    double y = m_target.getY() + m_target.getHeight() / 2 - this.getHeight() / 2;
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
