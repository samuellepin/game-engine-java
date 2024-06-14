package src.Model;

import java.util.ArrayList;

import src.Game;
import src.Model.World.Map;
import src.Model.World.Tile;
import src.Model.World.TILE_TYPE;

public class Model
{
  private boolean                   m_isGameOver;
  private ArrayList< EntityTracker > m_trackers;
  private ArrayList< Entity >       m_entities;

  public static Vector              m_viewport;
  public static Vector              m_viewPos;
  
  public static Player              m_player;
  public static Player              m_opponent;

  private Map                       m_map;

  public static Vector              m_screenCenter;

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
    m_trackers = new ArrayList< EntityTracker >();

    m_player = new Player( null );
    m_player.setPos( m_map.getPos( 5, 5 ) );
    m_opponent = new Opponent( null );
    m_opponent.setPos( m_map.getPos( 7, 7 ) );

    m_entities.add( m_player );
    m_entities.add( m_opponent );
    addWalls();

    updateViewPos();
  }
  
  private void addWalls ()
  {
    for (int y = 0; y < Map.ROWS_NUM; y++) {
      for (int x = 0; x < Map.COLS_NUM; x ++) {
        Tile t = m_map.getTile( x, y );
        if (t.getType() == TILE_TYPE.WALL)
          m_entities.add( new Wall(null, t) );
      }
    }
  }

  public Vector getViewPos()
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

  public void translateViewport( double x, double y )
  {
    m_player.setPos( Vector.add( m_player.getPos(), new Vector( x, y ) ) );
  }

  public Vector getPlayerPos()
  {
    return m_player.getPos();
  }

  public void updateViewPos()
  {
    m_viewPos.setPos( m_screenCenter.getX() - m_player.getX(), m_screenCenter.getY() - m_player.getY() );
  }

  public Player getPlayer()
  {
    return m_player;
  }

  public Player getOpponent()
  {
    return m_opponent;
  }
  
  public ArrayList<EntityTracker> getTrackers() {
    return m_trackers;
  }
}
