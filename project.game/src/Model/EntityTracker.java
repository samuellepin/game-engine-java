package src.Model;

import java.util.ArrayList;

/**
 * This class tracks an entity for the view. It shows all the entities in the
 * current rectangular focus.
 */
public class EntityTracker
{
  private static final int RATIO_MAP = 10;

  Entity                   m_e;
  Model                    m_model;
  double                   m_x, m_y, m_width, m_height;
  ArrayList< Entity >      m_entities;

  public EntityTracker( Entity e, Model model, int ratio_w, int ratio_h )
  {
    m_e = e;
    m_model = model;
    m_width = m_model.getMap().getWidth() / RATIO_MAP;
    setAspectRatio( ratio_w, ratio_h );
    m_entities = m_model.getEntities();
  }

  public Vector getPos()
  {
    return new Vector( m_x, m_y );
  }

  public Vector getDim()
  {
    return new Vector( m_width, m_height );
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public void setAspectRatio( int ratio_w, int ratio_h )
  {
    m_height = m_width * ratio_h / ratio_w;
  }
}
