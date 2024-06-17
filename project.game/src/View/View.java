package src.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import src.Model.Entity;
import src.Model.EntityTracker;
import src.Model.Model;
import src.Game;

public class View
{
  private static final View INSTANCE = new View();

  private Model             m_model;
  private Viewport[]        m_viewports;

  private View()
  {
    AvatarFactory.Initialize();

    m_model = Model.getInstance();
    m_viewports = new Viewport[ 2 ];
    int halfWidth = Game.SCREEN_WIDTH / 2;
    m_viewports[ 0 ] = new Viewport( m_model.getPlayer1(), 0, 0, halfWidth, Game.SCREEN_HEIGHT );
    m_viewports[ 1 ] = new Viewport( m_model.getPlayer2(), halfWidth, 0, halfWidth, Game.SCREEN_HEIGHT );
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
    EntityTracker tracker1 = Model.getInstance().getTrackers().get( 0 );
    g.drawString( "x=" + tracker1.getX() + ", y=" + tracker1.getY(), posX, posY );
  }

  public void paint( Graphics g )
  {
    if( Model.getInstance().isGameOver() )
    {
      g.setColor( Color.black );
      g.fillRect( 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT );
      g.setColor( Color.red );
      g.setFont( new Font( null, 0, 0 ) );
      g.setFont( new Font( "Serif", Font.BOLD, 32 ) );
      g.drawString( "Game Over", Game.SCREEN_WIDTH / 2 - 85, Game.SCREEN_HEIGHT / 2 );
      return;
    }

    g.setColor( Color.black );
    g.fillRect( 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT );

    // Corrigé - oublie de détruire graphics et position/dimension pas cohérentes
    for ( int i = 0; i < m_viewports.length; i++ )
    {
      Viewport vp = m_viewports[ i ];
      if( vp != null )
      {
        Graphics graphics = g.create( (int)vp.getX(), (int)vp.getY(), (int)vp.getWidth(), (int)vp.getHeight() );
        vp.paint( graphics );
        graphics.dispose();
      }
    }

    this.paintInfo( g );
  }

  public static View getInstance()
  {
    return INSTANCE;
  }
}
