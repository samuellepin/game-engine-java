package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.RandomAccessFile;

import javax.swing.JFrame;

import info3.game.Sound;
import info3.game.graphics.GameCanvas;
import info3.game.sound.RandomFileInputStream;
import src.Model.Model;
import src.Model.World.Map;
import src.View.View;

public class Game
{
  private static Game INSTANCE;
  public static int   SCREEN_WIDTH  = 2 * Config.getInstance().getView().getScreenWidth();
  public static int   SCREEN_HEIGHT = Config.getInstance().getView().getScreenHeight();

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
  private CanvasListener m_listener;
  private Model          m_model;
  private View           m_view;
  private GameCanvas     m_canvas;

  public static Game getInstance()
  {
    return INSTANCE;
  }

  private Game() throws Exception
  {
    Config.getInstance().initialize();

    m_model = Model.getInstance();

    m_view = View.getInstance();

    Map map = Map.getInstance();

    m_listener = new CanvasListener( this );

    m_canvas = new GameCanvas( m_listener );
    m_canvas.setSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );

    m_frame = m_canvas.createFrame( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );
    m_frame.setTitle( Config.getInstance().getView().getTitle() );
    m_frame.setLayout( new FlowLayout() );
    m_frame.add( m_canvas );
    m_frame.setLocationRelativeTo( null );
    m_frame.setVisible( true );
  }

  public void tick( long elapsed )
  {
    m_model.tick( elapsed );
  }

  public void paint( Graphics g )
  {
    m_view.paint( g );
  }

  public void loadMusic( String filename )
  {
    try
    {
      RandomAccessFile      file = new RandomAccessFile( filename, "r" );
      RandomFileInputStream fis  = new RandomFileInputStream( file );
      m_canvas.playMusic( fis, 0, 0.5f );
    }
    catch ( Throwable th )
    {
      th.printStackTrace( System.err );
      System.exit( -1 );
    }
  }
}