package src.Model;

import java.util.ArrayList;

import src.Model.World.Map;

public class Model
{
  private int                 m_width;
  private int                 m_height;
  private boolean             m_isGameOver;
  private ArrayList< Entity > m_entities;
  private Light               m_light;

  public static Vector        m_viewport;
  public static Vector        m_playerPos;
  public static Vector        m_offset;

  private Map                 m_map;

  public Model( int width, int height )
  {
    m_map = new Map();
    m_playerPos = new Vector( 0, 0 );
    m_viewport = new Vector( (double)width / 2, (double)height / 2 );
    m_isGameOver = false;
    m_width = width;
    m_height = height;
    m_entities = new ArrayList< Entity >();
    double W = width / 2;
    System.out.println( W );
//    m_entities.add( new Light( 0, 0, 100.0f ) );
    m_light = new Light( 0, 0, 100.0f );

//    m_entities.add( new Rectangle( new Vector( 70 - 30, 0 ), new Vector( 70 - 20, 30 ), new Vector( 44 - 20, 50 ),
//        new Vector( 40 - 20, -5 ) ) );

    m_offset = new Vector( 0, 0 );
    
    m_map.setPos( m_light, 1, 1 );
    
    updateOffset();
  }

  public static Vector getViewport()
  {
    return m_viewport;
  }

  public static Vector getOffset()
  {
    return m_offset;
  }

  public void tick( long elapsed )
  {
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
    m_playerPos = Vector.add( m_playerPos, new Vector( x, y ) );
  }

  public static Vector getPlayerPos()
  {
    return m_playerPos;
  }

  public static void updateOffset()
  {
    m_offset.setPos( m_viewport.getX() - m_playerPos.getX(), m_viewport.getY() - m_playerPos.getY() );
  }
}
