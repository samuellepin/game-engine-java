package src.Model;

import java.util.ArrayList;

import src.AI.CategoryFsm;
import src.Model.Collision.Collision;
import src.Model.World.Map;

public class Model
{
  private static final Model         INSTANCE = new Model();

  private Map                        m_map;                 /// < Background with no collisions : Floor
  private ArrayList< Entity >        m_entities;            /// < Players, Walls, Items, etc.
  private ArrayList< EntityTracker > m_trackers;
  private boolean                    m_isGameOver;
  private Entity                     m_player1;
  private Entity                     m_player2;
  private Entity                     m_document;

  public static Model getInstance()
  {
    return INSTANCE;
  }

  private Model()
  {
    m_isGameOver = false;

    m_entities = new ArrayList< Entity >();
    m_trackers = new ArrayList< EntityTracker >();

    m_map = Map.getInstance();
    for ( Entity e : Map.getInstance().getWalls() )
    {
      m_entities.add( e );
    }

    Document doc = new Document( null, CategoryFsm.CATEGORY.Void, new ArrayList< CategoryFsm.CATEGORY >() );
    doc.setPos( m_map.getPos( 3, 3 ) );
    m_entities.add( doc );
    m_document = doc;

    Spy spy = new Spy( null, CategoryFsm.CATEGORY.Void, new ArrayList< CategoryFsm.CATEGORY >() );
    spy.setPos( m_map.getPos( 5, 5 ) );
    this.setPlayer1( spy );
    m_entities.add( spy );

    Guard guard = new Guard( null, CategoryFsm.CATEGORY.Void, new ArrayList< CategoryFsm.CATEGORY >() );
    guard.setPos( m_map.getPos( 6, 6 ) );
    this.setPlayer2( guard );
    m_entities.add( guard );
  }

  public void tick( long elapsed )
  {
    for ( Entity e : m_entities )
    {
      e.tick( elapsed );
    }
    if( Collision.detect( m_player1.getHitbox(), m_document.getHitbox() ) )
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
}
