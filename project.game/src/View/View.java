package src.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import src.Game;
import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Model.World.Map;
import src.Model.World.TILE_TYPE;
import src.Model.World.Tile;

public class View
{
  private static final View       INSTANCE = new View();

  private Model                   m_model;
  private Viewport                m_leftViewport, m_rightViewport;
  private ArrayList< Avatar >     m_avatars;
  private ArrayList< TileAvatar > m_tileAvatars;

  private View()
  {
    AvatarFactory.Initialize();

    m_model = Model.getInstance();
    m_avatars = new ArrayList<>();
    m_tileAvatars = new ArrayList<>();

    ArrayList< Entity > entities = m_model.getEntities();
    for ( Entity e : entities )
    {
      m_avatars.add( AvatarFactory.make( e ) );
    }

    Map map = Map.getInstance();
    for ( int Y = 0; Y < Map.ROWS_NUM; Y++ )
    {
      for ( int X = 0; X < Map.COLS_NUM; X++ )
      {
        Tile tile = map.getTile( X, Y );
        if( tile.getType() == TILE_TYPE.FLOOR )
        {
          m_tileAvatars.add( new TileAvatar( tile ) );
        }
      }
    }

    int halfWidth = Game.SCREEN_WIDTH / 2;
    m_leftViewport = new Viewport( Model.getInstance().getPlayer1(), 0, 0, halfWidth, Game.SCREEN_HEIGHT, m_avatars,
        m_tileAvatars );
    m_rightViewport = new Viewport( Model.getInstance().getPlayer2(), halfWidth, 0, halfWidth, Game.SCREEN_HEIGHT,
        m_avatars, m_tileAvatars );
  }

  public void updateTrackers()
  {
    m_leftViewport.updateTracker();
    m_rightViewport.updateTracker();
  }

  public int paintInfoEntity( Graphics g, String title, Entity e, int posX, int posY )
  {
    g.drawString( title, posX, posY );
    posY += 16;
    g.drawString( "x = " + e.getX(), posX, posY );
    posY += 16;
    g.drawString( "y = " + e.getY(), posX, posY );
    posY += 16;
//    g.drawString( "width = " + e.getWidth(), posX, posY );
//    posY += 16;
//    g.drawString( "height = " + e.getHeight(), posX, posY );
//    posY += 16 * 2;

    return posY;
  }

  public void paintInfo( Graphics g )
  {
    g.setColor( Color.white );
    int    posX    = 5;
    int    posY    = 20;

    Entity player1 = Model.getInstance().getPlayer1();
    posY += this.paintInfoEntity( g, "Player 1", player1, posX, posY );
  }

  public void paint( Graphics g )
  {
    if( Model.getInstance().isGameOver() )
    {
      g.setColor( Color.black );
      g.fillRect( 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT );
      g.setColor( Color.red );
      g.setFont( new Font(null, 0, 0) );
      g.setFont(new Font("Serif", Font.BOLD, 32));
      g.drawString( "Game Over", Game.SCREEN_WIDTH/2 - 85, Game.SCREEN_HEIGHT/2 );
      return;
    }
    
    Graphics g1 = g.create( m_leftViewport.getX(), m_leftViewport.getY(), m_leftViewport.getWidth(),
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
