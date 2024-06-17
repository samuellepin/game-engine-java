package src.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JFrame;
import info3.game.graphics.GameCanvas;
import src.Model.Model;
import src.Model.Vector;
import src.Model.Collision.AABB;
import src.Model.Collision.Circle;
import src.Model.Collision.Collision;
import src.View.View;

public class CollisionTest
{
  private static CollisionTest INSTANCE;
  public static final int      SCREEN_WIDTH  = 600;
  public static final int      SCREEN_HEIGHT = 600;

  public static void main( String args[] ) throws Exception
  {
    try
    {
      INSTANCE = new CollisionTest();
    }
    catch ( Throwable th )
    {
      th.printStackTrace( System.err );
    }
  }

  private JFrame         m_frame;
  private CanvasListener m_listener;
  private GameCanvas     m_canvas;
  private AABB           m_aabb;
  private Circle         m_circle;
  private boolean        m_collision;

  public static CollisionTest getInstance()
  {
    return INSTANCE;
  }

  private CollisionTest() throws Exception
  {
    m_aabb = new AABB( 100, 100, 170, 140 );
    m_circle = new Circle( 50, 50, 35 );

    m_listener = new CanvasListener( this );

    m_canvas = new GameCanvas( m_listener );
    m_canvas.setSize( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );

    m_frame = m_canvas.createFrame( new Dimension( SCREEN_WIDTH, SCREEN_HEIGHT ) );
    m_frame.setTitle( "Collision Test" );
    m_frame.setLayout( new FlowLayout() );
    m_frame.add( m_canvas );
    m_frame.setLocationRelativeTo( null );
    m_frame.setVisible( true );
  }

  void tick( long elapsed )
  {
    m_collision = Collision.detect( m_aabb, m_circle );
  }

  void paint( Graphics g )
  {
    g.setColor( Color.DARK_GRAY );
    g.fillRect( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );

    Vector pos = m_circle.getTopLeftCorner();
    int    dim = (int) ( 2 * m_circle.getRadius() );
    g.setColor( Color.red );
    g.fillOval( (int)pos.getX(), (int)pos.getY(), dim, dim );
    
    g.setColor( Color.yellow );
    g.fillRect( (int)m_aabb.getX(), (int)m_aabb.getY(), (int)m_aabb.getWidth(), (int)m_aabb.getHeight() );
  
    if( m_collision )
    {
      g.setColor( Color.red );
      g.drawString( "Collision detected!", 20, 20 );
    }
    else
    {
      g.setColor( Color.white );
      g.drawString( "No collision detected", 20, 20 );
    }
  }
  
  void translateCircle( double x, double y )
  {
    m_circle.getTopLeftCorner().translate( x, y );
  }
  
  void translateAABB( double x, double y )
  {
    m_aabb.getMin().translate( x, y );
  }
}