package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import info3.game.graphics.GameCanvas;
import src.Model.Model;
import src.View.View;

public class Game
{
  private static Game     INSTANCE;
  public static final int SCREEN_WIDTH = 720;
  public static final int SCREEN_HEIGHT = 480;

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
    m_model = new Model();
    m_view = new View( m_model, null );
    m_listener = new CanvasListener( this );
    m_canvas = new GameCanvas( m_listener );
    m_canvas.setSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );
    m_frame = m_canvas.createFrame( new Dimension( 1280, 720 ) );
    m_text = new JLabel();
    m_text.setText( "Tick: 0ms FPS=0" );
    m_frame.setTitle( "Metal Gear" );
    m_frame.setLayout( new FlowLayout() );
    m_frame.add( m_canvas );
    m_frame.setLocationRelativeTo( null );
    m_frame.setVisible( true );
  }

  void tick( long elapsed )
  {
    m_model.tick( elapsed );
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
