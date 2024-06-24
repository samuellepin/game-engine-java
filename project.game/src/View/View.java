package src.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

import info3.game.graphics.GameCanvas;
import src.Model.Entity;
import src.Model.Model;
import src.Config;
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

  public void paintFPS( Graphics g )
  {
    Game game = Game.getInstance();
    if( game == null ) return;
    
    GameCanvas canvas = game.getCanvas();
    if( canvas == null ) return;

    int  FPS  = canvas.getFPS();

    Font font = new Font( "Arial", Font.BOLD, 16 );
    g.setFont( font );
    FontMetrics fm     = g.getFontMetrics();
    String      text   = FPS + " FPS";
    int         width  = fm.stringWidth( text );
    int         height = fm.getAscent() + fm.getDescent() + fm.getLeading();
    int         posX   = Game.SCREEN_WIDTH - width - 5;
    int         posY   = height;

    g.setColor( Color.red );
    g.drawString( text, posX, posY );
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
    
    this.paintFPS( g );
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
    g.drawRect( posX - offset, posY, width + 2 * offset, height );
  }
  
  public void updateScreenDimension()
  {
    Game game = Game.getInstance();
    if( game == null ) return;
    
    GameCanvas canvas = game.getCanvas();
    if( canvas == null ) return;

    Game.SCREEN_WIDTH = canvas.getWidth();
    Game.SCREEN_HEIGHT = canvas.getHeight();

    this.m_viewports[ 0 ].update( 0, 0, Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT );
    this.m_viewports[ 0 ].getTracker().resize();
    this.m_viewports[ 1 ].update( Game.SCREEN_WIDTH/2, 0, Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT );
    this.m_viewports[ 1 ].getTracker().resize();
  }

  public void paint( Graphics g )
  {
//    this.updateScreenDimension();
    
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
        Graphics2D graphics = (Graphics2D)g.create( (int)vp.getX(), (int)vp.getY(), (int)vp.getWidth(),
            (int)vp.getHeight() );

        vp.paint( graphics );

        if( i == 1 && Config.getInstance().getView().isReducedVisionFieldEnabled() )
        {
          int              radius = (int)Model.getInstance().getPlayer2().getVisionField().getRadius();
          int              x      = (int)vp.getWidth() / 2;
          int              y      = (int)vp.getHeight() / 2;
          Ellipse2D.Double circle = new Ellipse2D.Double( x - radius, y - radius, radius * 2, radius * 2 );
          graphics.setClip( circle );
          vp.paint( graphics );
          graphics.setClip( null );
          Area outside = new Area( new Rectangle( 0, 0, (int)vp.getWidth(), (int)vp.getHeight() ) );
          outside.subtract( new Area( circle ) );
          graphics.setClip( outside );
          graphics.setColor( Color.BLACK );
          graphics.fillRect( 0, 0, (int)vp.getWidth(), (int)vp.getHeight() );
        }

        graphics.dispose();

      }
    }

//    this.paintInfo( g );
  }

  public static View getInstance()
  {
    return INSTANCE;
  }
}
