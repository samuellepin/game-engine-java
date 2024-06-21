package src.Model;

import java.util.ArrayList;

import src.Config;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Model
{
  private static final Model         INSTANCE = new Model();

  private ArrayList< Entity >        m_entities;            /// < Players, Walls, Items, etc.
  private ArrayList< EntityTracker > m_trackers;
  private boolean                    m_isGameOver;
  private Entity                     m_player1;
  private Entity                     m_player2;
  private Entity                     m_itemToWin;

  public static Model getInstance()
  {
    return INSTANCE;
  }

  private Model()
  {
    m_isGameOver = false;

    m_entities = new ArrayList< Entity >();
    m_trackers = new ArrayList< EntityTracker >();

    for ( Entity e : Map.getInstance().getWalls() )
    {
      m_entities.add( e );
    }
  }

  public void tick( long elapsed )
  {
    for ( Entity e : m_entities )
    {
      e.tick( elapsed );
    }
    if( Collision.detect( m_player1.getHitbox(), m_itemToWin.getHitbox() ) )
    {
      m_isGameOver = true;
    }
  }

  public boolean isGameOver()
  {
    return m_isGameOver;
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public void addEntities( Entity entity )
  {
    m_entities.add( entity );
  }

  public void removeEntities( Entity entity )
  {
    m_entities.remove( entity );
  }

  public Entity getPlayer1()
  {
    return m_player1;
  }

  public void setPlayer1( Entity e )
  {
    m_player1 = e;
  }

  public void setPlayer2( Entity e )
  {
    m_player2 = e;
  }

  public Entity getPlayer2()
  {
    return m_player2;
  }

  public ArrayList< EntityTracker > getTrackers()
  {
    return m_trackers;
  }

  public void addEntity( Entity e )
  {
    m_entities.add( e );
  }

  public void setItemToWin( Entity e )
  {
    m_itemToWin = e;
  }

  public Entity getItemToWin()
  {
    return m_itemToWin;
  }

  public void addEnenmies( Entity entity, int min, int max )
  {
    int num = min + Config.getRandom().nextInt( max - min );
    for ( int i = 0; i < num; i++ )
    {
      Entity e = null;
      try
      {
        e = entity.clone();
      }
      catch ( CloneNotSupportedException except )
      {
        except.printStackTrace();
      }
      e.setId( e.getId() + i + 1 );
      e.setPos( Map.getInstance().getRandomPos() );
      this.addEntity( e );
    }
  }
}
