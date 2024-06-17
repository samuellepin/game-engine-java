package src.Model;

import java.util.ArrayList;

import src.Game;
import src.Model.Collision.AABB;

/**
 * This class tracks an entity for the view. It shows all the entities in the
 * current rectangular focus.
 */

public class EntityTracker extends AABB
{
  private Entity              m_target;
  private Model               m_model;
  private ArrayList< Entity > m_entities;

  public EntityTracker( Model model, Entity target )
  {
    super( 0, 0, 0, 0 );
    m_model = model;
    m_target = target;
    m_entities = m_model.getEntities();
    this.centerOnTarget();
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public void centerOnTarget()
  {
    double halfWidth  = Game.SCREEN_WIDTH / ( 4 * Config.RATIO ) - m_target.getWidth() / 2;
    double halfHeight = Game.SCREEN_HEIGHT / ( 2 * Config.RATIO ) - m_target.getHeight() / 2;
    super.resize( new Vector( m_target.getX() - halfWidth, m_target.getY() - halfHeight ),
        new Vector( m_target.getX() + halfWidth, m_target.getY() + halfHeight ) );
  }
}
