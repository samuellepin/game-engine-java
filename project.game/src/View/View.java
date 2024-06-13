package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import info3.game.graphics.GameCanvas;
import src.Game;
import src.Model.Entity;
import src.Model.Model;
import src.Model.Vector;

public class View
{
  private GameCanvas          m_canvas;
  private Model               m_model;
  private ArrayList< Avatar > m_avatars;
  private LightAvatar         m_lightAvatar;
  private MapAvatar           m_mapAvatar;
  private PlayerAvatar        m_playerAvatar;

  public View( Model model, GameCanvas canvas )
  {
    m_canvas = canvas;
    m_model = model;
    m_avatars = new ArrayList<>();

    ArrayList< Entity > entities = model.getEntities();
    for ( Entity e : entities )
    {
      try
      {
        m_avatars.add( AvatarFactory.make( e ) );
      }
      catch ( Exception e1 )
      {
        e1.printStackTrace();
      }
    }
    
    m_lightAvatar = new LightAvatar( m_model.getLight(), entities );
    m_mapAvatar = new MapAvatar( m_model.getMap() );
    m_playerAvatar = new PlayerAvatar( Model.getPlayer() );
  }

  public void paint( Graphics g )
  {
    g.setColor( Color.black );
    g.fillRect( 0, 0, 1000, 1000 );
//    g.fillRect( 0, 0, m_canvas.getWidth(), m_canvas.getHeight() );
    
    m_mapAvatar.paint( g );
    
    m_playerAvatar.paint( g );

    for ( Avatar avatar : m_avatars )
    {
      avatar.paint( g );
    }

  }
}
