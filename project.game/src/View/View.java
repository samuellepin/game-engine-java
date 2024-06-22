package src.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;

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
    AvatarFactory.getInstance();

    m_model = Model.getInstance();
    m_viewports = new Viewport[ 2 ];
    int halfWidth = Game.SCREEN_WIDTH / 2;
    m_viewports[ 0 ] = new Viewport( m_model.getPlayer1(), 0, 0, halfWidth, Game.SCREEN_HEIGHT );
    m_viewports[ 1 ] = new Viewport( m_model.getPlayer2(), halfWidth, 0, halfWidth, Game.SCREEN_HEIGHT );
  }

  public int paintInfoEntity( Graphics g, String title, Entity e, int posX, int posY )
  {
    DecimalFormat df = new DecimalFormat( "#.0" );
    g.drawString( title, posX, posY );
    posY += 16;
    g.drawString( "x = " + df.format( e.getX() ), posX, posY );
    posY += 16;
    g.drawString( "y = " + df.format( e.getY() ), posX, posY );
    posY += 16;

    return posY;
  }

  public void paintInfo( Graphics g )
  {
    g.setColor( Color.white );
    int    posX    = 5;
    int    posY    = 20;

    Entity player1 = Model.getInstance().getPlayer1();
    posY += this.paintInfoEntity( g, "Player 1", player1, posX, posY );
    Entity player2 = Model.getInstance().getPlayer2();
    posY += this.paintInfoEntity( g, "Player 2", player2, posX, posY );
  }

  public void paintGameOver( Graphics g )
  {
    Font font = new Font( "Serif", Font.BOLD, 48 );
    g.setFont( font );
    FontMetrics fm     = g.getFontMetrics();
    String      text   = "GAME OVER";
    int         width  = fm.stringWidth( text );
    int         height = fm.getAscent() + fm.getDescent() + fm.getLeading();
    int         posX   = ( Game.SCREEN_WIDTH - width ) / 2;
    int         posY   = ( Game.SCREEN_HEIGHT - height ) / 2;

    g.setColor( Color.black );
    g.fillRect( 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT );
    g.setColor( Color.red );
    g.drawString( text, posX, posY + height - fm.getDescent() );
    int offset = 10;
    g.drawRect( posX-offset, posY, width+2*offset, height );
  }

  public void paint( Graphics g )
  {
    if( Model.getInstance().isGameOver() )
    {
      this.paintGameOver( g );
      return;
    }

    g.setColor( Color.black );
    g.fillRect( 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT );

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
