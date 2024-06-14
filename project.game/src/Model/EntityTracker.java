package src.Model;

import java.util.ArrayList;

import src.Model.Collision.AABB;

/**
 * This class tracks an entity for the view. It shows all the entities in the
 * current rectangular focus.
 */
public class EntityTracker
{
  private static final int    RATIO_MAP = 5;

  private Entity              m_e;
  private Model               m_model;
  private AABB                m_hitbox;
  private Vector              m_pos, m_dim;
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
    
    void moved ()
    {
      setPos();
    }
  }

  public EntityTracker( Entity e, int ratio_w, int ratio_h )
  {
    m_e = e;
    m_model = Model.getInstance();
    double width = m_model.getMap().getWidth() / RATIO_MAP;
    m_dim = new Vector( width, width * ratio_h / ratio_w );
    m_pos = new Vector(0,0);
    setPos();
    m_hitbox = new AABB( m_pos, m_dim );
    m_entities = m_model.getEntities();
    
    m_listener = new TrackerListener();
  }
  
  private void setPos() {
    m_pos.setX( m_e.getX() + m_e.getWidth() / 2 - m_dim.getX() / 2);
    m_pos.setY( m_e.getY() + m_e.getHeight() / 2 - m_dim.getY() / 2);
  }

  public Vector getPos()
  {
    return m_pos;
  }

  public Vector getDim()
  {
    return m_dim;
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public void setAspectRatio( int ratio_w, int ratio_h )
  {
    m_dim.setY( m_dim.getX() * ratio_h / ratio_w );
    setPos();
  }

  public TrackerListener getTracker()
  {
    return m_listener;
  }
  
  public AABB getHitbox() {
    return m_hitbox;
  }
  
  public TrackerListener getListener () {
    return m_listener;
  }
}
