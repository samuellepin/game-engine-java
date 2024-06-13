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
  public static Player        m_opponent;

  private Map                 m_map;

  public static Vector        m_screenCenter;

  public Model()
  {
    m_map = Map.getInstance();
    m_screenCenter = new Vector( Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2 );
    m_isGameOver = false;
    m_entities = new ArrayList< Entity >();
    double W = Game.SCREEN_WIDTH / 2;
    System.out.println( W );
//    m_entities.add( new Light( 0, 0, 100.0f ) );
    m_light = new Light( 0, 0, 100.0f );

//    m_entities.add( new Rectangle( new Vector( 70 - 30, 0 ), new Vector( 70 - 20, 30 ), new Vector( 44 - 20, 50 ),
//        new Vector( 40 - 20, -5 ) ) );

    m_viewPos = new Vector( 0, 0 );

    m_player = new Player( null );
    m_player.setPos( m_map.getPos( 5, 5 ) );
    m_opponent = new Player( null );

    updateViewPos();
  }

  public static Vector getViewPos()
  {
    return m_viewPos;
  }

  public void tick( long elapsed )
  {
    m_player.tick( elapsed );
  }

  public boolean isGameOver()
  {
    return m_isGameOver;
  }

  public ArrayList< Entity > getEntities()
  {
    return m_entities;
  }

  public Light getLight()
  {
    return m_light;
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
}
