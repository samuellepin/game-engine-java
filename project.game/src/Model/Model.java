package src.Model;

import java.util.ArrayList;

import src.Game;
import src.Model.World.Map;

public class Model
{
  private boolean             m_isGameOver;
  private ArrayList< Entity > m_entities;
  private Light               m_light;

  public static Vector        m_viewport;
  public static Vector        m_viewPos;

  public static Player        m_player;
  public static Opponent      m_opponent;

  private Map                 m_map;

  public static Vector        m_screenCenter;

  private static final Model  INSTANCE = new Model();

  public static Model getInstance()
  {
    return INSTANCE;
  }

  private Model()
  {
    m_isGameOver = false;
    m_screenCenter = new Vector( Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 );
    m_viewPos = new Vector( 0, 0 );

    m_map = Map.getInstance();
    m_entities = new ArrayList< Entity >();

    m_player = new Player( null );
    m_player.setPos( m_map.getPos( 5, 5 ) );
    m_opponent = new Opponent( null );
    m_opponent.setPos( m_map.getPos( 7, 7 ) );
    
    updateViewPos();
  }

  public static Vector getViewPos()
  {
    return m_viewPos;
  }

  public void tick( long elapsed )
  {
    m_player.tick( elapsed );
    m_opponent.tick( elapsed );
  }

  public boolean isGameOver()
  {
    return m_isGameOver;
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public Map getMap()
  {
    return m_map;
  }

  public static void translateViewport( double x, double y )
  {
    m_player.setPos( Vector.add( m_player.getPos(), new Vector( x, y ) ) );
  }

  public static Vector getPlayerPos()
  {
    return m_player.getPos();
  }

  public static void updateViewPos()
  {
    m_viewPos.setPos( m_screenCenter.getX() - m_player.getX(), m_screenCenter.getY() - m_player.getY() );
  }

  public static Player getPlayer()
  {
    return m_player;
  }

  public static Player getOpponent()
  {
    return m_opponent;
  }
}
