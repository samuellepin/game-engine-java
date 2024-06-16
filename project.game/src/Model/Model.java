package src.Model;

import java.util.ArrayList;

import src.Model.World.Map;
import src.View.View;

public class Model
{
  private static final Model  INSTANCE = new Model();

  private Map                 m_map;                 /// < Background with no collisions : Floor
  private ArrayList< Entity > m_entities;            /// < Players, Walls, Items, etc.
  private boolean             m_isGameOver;
  private Entity              m_player1;
  private Entity              m_player2;

  public static Model getInstance()
  {
    return INSTANCE;
  }

  private Model()
  {
    m_isGameOver = false;

    m_entities = new ArrayList< Entity >();

    m_map = Map.getInstance();
    for ( Entity e : Map.getInstance().getWalls() )
    {
      m_entities.add( e );
    }

    Spy spy = new Spy( null );
    spy.setPos( m_map.getPos( 5, 5 ) );
    this.setPlayer1( spy );
    m_entities.add( spy );

//    this.setPlayer2( spy );

    Guard guard = new Guard( null );
    guard.setPos( m_map.getPos( 7, 7 ) );
    this.setPlayer2( guard );
    m_entities.add( guard );

  }

  public void tick( long elapsed )
  {
    for ( Entity e : m_entities )
    {
      e.tick( elapsed );
    }
    View.getInstance().updateTrackers();
  }

  public boolean isGameOver()
  {
    return m_isGameOver;
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
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
}
