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
  }

  public void paint( Graphics g )
  {
    g.setColor( Color.black );
    g.fillRect( 0, 0, m_canvas.getWidth(), m_canvas.getHeight() );
    
    double size = 1000;
    Vector origin = new Vector( 0, 0 );
    Vector right = new Vector( size, 0 );
    Vector left = new Vector( -size, 0 );
    Vector up = new Vector( 0, -size );
    Vector down = new Vector( 0, size );
    g.setColor( Color.white );
    g.drawLine( (int)origin.getRX(), (int)origin.getRY(), (int)right.getRX(), (int)right.getRY() );
    g.drawLine( (int)origin.getRX(), (int)origin.getRY(), (int)left.getRX(), (int)left.getRY() );
    g.drawLine( (int)origin.getRX(), (int)origin.getRY(), (int)up.getRX(), (int)up.getRY() );
    g.drawLine( (int)origin.getRX(), (int)origin.getRY(), (int)down.getRX(), (int)down.getRY() );
    
    m_lightAvatar.paint( g );

    for ( Avatar avatar : m_avatars )
    {
      avatar.paint( g );
    }

  }
}
