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
  public static final double  RATIO = 1; // 1m -> 2px

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
    double halfWidth  = Game.SCREEN_WIDTH / ( 4 * RATIO );
    double halfHeight = Game.SCREEN_HEIGHT / ( 2 * RATIO );
    super.resize( new Vector( m_target.getX() - halfWidth, m_target.getY() - halfHeight ),
        new Vector( m_target.getX() + halfWidth, m_target.getY() + halfHeight ) );
  }
}
