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
  private Model               m_model;
  private ArrayList< Avatar > m_avatars;
  private MapAvatar           m_mapAvatar;
  private PlayerAvatar        m_playerAvatar;
  private PlayerAvatar        m_opponentAvatar;

  public View( Model model, GameCanvas canvas )
  {
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
    
    m_mapAvatar = new MapAvatar( m_model.getMap() );
    m_playerAvatar = new PlayerAvatar( Model.getPlayer() );
    m_opponentAvatar = new PlayerAvatar( Model.getOpponent() );
  }

  public void paint( Graphics g )
  {
    g.setColor( Color.black );
    g.fillRect( 0, 0, 1000, 1000 );
//    g.fillRect( 0, 0, m_canvas.getWidth(), m_canvas.getHeight() );
    
    m_mapAvatar.paint( g );
    
    m_playerAvatar.paint( g );
    
    m_opponentAvatar.paint( g );

    for ( Avatar avatar : m_avatars )
    {
      avatar.paint( g );
    }

  }
}
