package src.View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import src.Game;
import src.Model.Entity;
import src.Model.Model;
import src.Model.World.Map;

public class View
{
  private static final View   INSTANCE = new View();

  private Model               m_model;
  private Viewport            m_leftViewport, m_rightViewport;
  private Background          m_background;
  private ArrayList< Avatar > m_avatars;

  private View()
  {
    m_model = Model.getInstance();

    m_background = new Background( Map.getInstance() );
    m_avatars = new ArrayList<>();

    ArrayList< Entity > entities = m_model.getEntities();
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

    int halfWidth = Game.SCREEN_WIDTH / 2;
    m_leftViewport = new Viewport( Model.getInstance().getPlayer1(), 0, 0, halfWidth, Game.SCREEN_HEIGHT, m_background,
        m_avatars );
    m_rightViewport = new Viewport( Model.getInstance().getPlayer2(), halfWidth, 0, halfWidth, Game.SCREEN_HEIGHT,
        m_background, m_avatars );
  }

  public void updateTrackers()
  {
    m_leftViewport.updateTracker();
    m_rightViewport.updateTracker();
  }

  public void paintInfo( Graphics g )
  {
    g.setColor( Color.white );
    int posX = 5;
    int posY = 20;
    
    Entity player1 = Model.getInstance().getPlayer1();
    
    g.drawString( "Player 1", posX, posY );
    posX += 5;
    posY += 16;
    g.drawString( "x = " + player1.getX(), posX, posY );
    posY += 16;
    g.drawString( "y = " + player1.getY(), posX, posY );
    posY += 16;
    g.drawString( "width = " + player1.getWidth(), posX, posY );
    posY += 16;
    g.drawString( "height = " + player1.getHeight(), posX, posY );
  }

  public void paint( Graphics g )
  {
    int      widthHalf = Game.SCREEN_WIDTH / 2;
    Graphics g1        = g.create( m_leftViewport.getX(), m_leftViewport.getY(), m_leftViewport.getWidth(),
        m_leftViewport.getHeight() );
    m_leftViewport.setColor( Color.red );
    m_leftViewport.paint( g1 );
    g1.dispose();

    Graphics g2 = g.create( m_rightViewport.getX(), m_rightViewport.getY(), m_rightViewport.getWidth(),
        m_rightViewport.getHeight() );
    m_rightViewport.setColor( Color.blue );
    m_rightViewport.paint( g2 );
    g2.dispose();

    this.paintInfo( g );
  }

  public static View getInstance()
  {
    return INSTANCE;
  }
}
