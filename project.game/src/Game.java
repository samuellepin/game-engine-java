package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.graphics.GameCanvas;
import src.Model.Model;
import src.View.View;

public class Game
{
  private static Game     INSTANCE;
  public static final int WIDTH  = 720;
  public static final int HEIGHT = 480;

  public static void main( String args[] ) throws Exception
  {
    try
    {
      INSTANCE = new Game();
    }
    catch ( Throwable th )
    {
      th.printStackTrace( System.err );
    }
  }

  private JFrame         m_frame;
  private JLabel         m_text;
  private CanvasListener m_listener;
  private Model          m_model;
  private View           m_view;
  private GameCanvas     m_canvas;
  private long           m_textElapsed;

  public static Game getInstance()
  {
    return INSTANCE;
  }

  public int getWidth()
  {
    return m_canvas.getWidth();
  }

  public int getHeight()
  {
    return m_canvas.getHeight();
  }

  private Game() throws Exception
  {
    m_listener = new CanvasListener( this );
    m_canvas = new GameCanvas( m_listener );
    Dimension d = new Dimension( WIDTH, HEIGHT );
    m_frame = m_canvas.createFrame( d );
    m_text = new JLabel();
    m_text.setText( "Tick: 0ms FPS=0" );
    m_model = new Model( WIDTH, HEIGHT - m_text.getHeight() );
    m_view = new View( m_model, m_canvas );
    m_frame.setTitle( "Blair Witch Project" );
    m_frame.setLayout( new BorderLayout() );
    m_frame.add( m_canvas, BorderLayout.CENTER );
    m_frame.add( m_text, BorderLayout.SOUTH );
    m_frame.setLocationRelativeTo( null );
    m_frame.setVisible( true );
  }

  void tick( long elapsed )
  {
    m_model.tick( elapsed );

    m_textElapsed += elapsed;
    if( m_textElapsed > 1000 )
    {
      m_textElapsed = 0;
      float  period = m_canvas.getTickPeriod();
      int    fps    = m_canvas.getFPS();

      String txt    = "Tick=" + period + "ms";
      while( txt.length() < 15 ) txt += " ";
      txt = txt + fps + " fps   ";
      txt += " pos = (" + Model.getPlayerPos().getX() + ", " + Model.getPlayerPos().getY() + ")";
      m_text.setText( txt );
    }
  }

  void paint( Graphics g )
  {
    m_view.paint( g );
  }
  
  public Model getModel()
  {
    return m_model;
  }

}
