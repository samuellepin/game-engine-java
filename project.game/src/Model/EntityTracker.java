package src.Model;

import java.util.ArrayList;

/**
 * This class tracks an entity for the view. It shows all the entities in the
 * current rectangular focus.
 */
public class EntityTracker
{
  Entity m_e;
  Model  m_model;
  double m_x, m_y, m_width, m_height;

  public EntityTracker( Entity e, Model model )
  {
    m_e = e;
    m_model = model;
  }

  public Vector getPos()
  {
    throw new RuntimeException( "NYI" );
  }

  public Vector getDim()
  {
    throw new RuntimeException( "NYI" );
  }

  public void setTrack( Entity e )
  {
    throw new RuntimeException( "NYI" );
  }

  public ArrayList< Entity > getEntities()
  {
    throw new RuntimeException( "NYI" );
  }

  public void setAspectRatio( int width, int height )
  {
    throw new RuntimeException( "NYI" );
  }
}
